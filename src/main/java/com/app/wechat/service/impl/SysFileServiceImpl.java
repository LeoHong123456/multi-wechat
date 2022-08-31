package com.app.wechat.service.impl;

import com.app.wechat.domain.bo.ReceiveBo;
import com.app.wechat.domain.entity.SysFile;
import com.app.wechat.mapper.SysFileMapper;
import com.app.wechat.service.ISysFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
