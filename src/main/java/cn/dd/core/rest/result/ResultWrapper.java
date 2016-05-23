package cn.dd.core.rest.result;

import java.io.Serializable;

public class ResultWrapper implements Serializable {
    private int statusCode;

    private Object returnObj;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getReturnObj() {
        return returnObj;
    }

    public void setReturnObj(Object returnObj) {
        this.returnObj = returnObj;
    }
}
