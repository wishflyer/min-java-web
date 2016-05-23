package cn.dd.core.rest.result;

import cn.dd.core.utils.JsonUtils;

import java.io.IOException;

/**
 * Description: 实现REST请求的时候的返回对象，该对象将被序列化成json字符串。
 */
public class JsonRESTResult extends ObjectRESTResult {

    public JsonRESTResult() {
    }

    public JsonRESTResult(int returnCode, Object returnObject) {
        this.setStatusCode(returnCode);
        this.setReturnObj(returnObject);
    }

    @Override
    public String convertObjToHttpResponse() {

        ResultWrapper wrapper = new ResultWrapper();
        wrapper.setReturnObj(getReturnObj());
        wrapper.setStatusCode(getStatusCode());

        String json = "{}";
        try {
            json = JsonUtils.toJson(wrapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
