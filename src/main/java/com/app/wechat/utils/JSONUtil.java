package com.app.wechat.utils;
import com.alibaba.fastjson.*;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@Slf4j
public class JSONUtil {

    private static final SerializeConfig config = null;
    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };

//    static {
//        config = new SerializeConfig();
//        config.put(Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
//        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
//    }

    public static <T> T json2Bean(String json, Class<T> t) {
        if (StringUtils.isBlank(json)) {
            log.error("解析的json为空");
            return null;
        }
        return JSON.parseObject(json, t);
    }

    public static <T> String bean2Json(T t) {
        if (t == null) {
            return null;
        }
        return JSON.toJSONString(t);
    }

    /**
     * 过滤null值
     *
     * @param json
     * @return
     */
    public static Map<String, Object> json2Map(String json) {
        return JSONObject.parseObject(json, new TypeReference<Map<String, Object>>() {
        });
    }

    public static String map2Json(Map<String, Object> map) {
        return JSON.toJSONString(map, features);
    }


    /**
     * 根据指定key 排序
     *
     * @param jsonArr
     * @param sortKey
     * @param is_desc
     * @return
     */
    public static JSONArray jsonArraySort(JSONArray jsonArr, String sortKey, boolean is_desc) {
        JSONArray sortedJsonArray = new JSONArray();
        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        for (int i = 0; i < jsonArr.size(); i++) {
            jsonValues.add(jsonArr.getJSONObject(i));
        }
        Collections.sort(jsonValues, new Comparator<JSONObject>() {
            private final String KEY_NAME = sortKey;

            @Override
            public int compare(JSONObject a, JSONObject b) {
                String valA = new String();
                String valB = new String();
                try {
                    valA = a.getString(KEY_NAME);
                    valB = b.getString(KEY_NAME);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (is_desc) {
                    return -valA.compareTo(valB);
                } else {
                    return -valB.compareTo(valA);
                }
            }
        });
        for (int i = 0; i < jsonArr.size(); i++) {
            sortedJsonArray.add(jsonValues.get(i));
        }
        return sortedJsonArray;
    }
}
