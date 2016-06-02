package cn.dd.core.rest.base;

import cn.dd.core.utils.JsonUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created by wishflyer on 2016/6/2.
 */
public class BaseRestMethod {
    
    public Map<String, Object> parseJSON(String jsonStr){
        Map<String, Object> paramMap = null;
        try {
            paramMap = JsonUtils.parseMap(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paramMap;
    }

}
