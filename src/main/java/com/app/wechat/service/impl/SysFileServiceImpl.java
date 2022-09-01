package com.app.wechat.service.impl;

import com.app.wechat.domain.bo.ReceiveBo;
import com.app.wechat.domain.dto.QueryFileListDto;
import com.app.wechat.domain.entity.SysFile;
import com.app.wechat.domain.vo.QueryFileListVo;
import com.app.wechat.mapper.SysFileMapper;
import com.app.wechat.service.ISysFileService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-08-31
 */
@Service
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements ISysFileService {

    @Override
    public void saveFile(ReceiveBo receiveBo) {
        SysFile file = new SysFile();
        BeanUtils.copyProperties(receiveBo, file);
        file.setCreateTime(LocalDateTime.now());
        file.setUpdateTime(LocalDateTime.now());
        saveOrUpdate(file);
    }

    @Override
    public void saveBatchFile(List<ReceiveBo> receives) {
        List<SysFile> collect = receives.stream().map(item -> {
            SysFile file = new SysFile();
            BeanUtils.copyProperties(item, file);
            file.setCreateTime(LocalDateTime.now());
            file.setUpdateTime(LocalDateTime.now());
            return file;
        }).collect(Collectors.toList());
        saveOrUpdateBatch(collect);
    }

    @Override
    public List<QueryFileListVo> queryFileList(QueryFileListDto queryFileListDto) throws Exception {
        QueryWrapper<SysFile> wrapper = new QueryWrapper<SysFile>();
        if(StringUtils.isNotBlank(queryFileListDto.getFileType())){
            wrapper.eq("file_type", queryFileListDto.getFileType());
        }
        if(StringUtils.isNotBlank(queryFileListDto.getFileName())){
            wrapper.like("file_name", queryFileListDto.getFileName());
        }
        if(StringUtils.isNotBlank(queryFileListDto.getFileOnline())){
            wrapper.eq("file_online", queryFileListDto.getFileOnline());
        }
        if(StringUtils.isNotBlank(queryFileListDto.getVersion())){
            wrapper.like("version", queryFileListDto.getVersion());
        }
        if(StringUtils.isNotBlank(queryFileListDto.getStartTime()) && StringUtils.isNotBlank(queryFileListDto.getEndTime())){
            wrapper.ge("create_time", queryFileListDto.getStartTime());
            wrapper.le("create_time", queryFileListDto.getEndTime());
        }
        List<SysFile> list = list(wrapper);

        return list.stream().map(item -> {
            QueryFileListVo vo = new QueryFileListVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).collect(Collectors.toList());
    }
}
