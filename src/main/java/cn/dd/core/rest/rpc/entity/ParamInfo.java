package cn.dd.core.rest.rpc.entity;


import cn.dd.core.rest.rpc.annotation.RequestSource;

/**
 * Created by czdujb on 2015/10/28.
 */
public class ParamInfo {

    //实际request中传入的值的名称
    private String value;

    //参数类型，暂时不使用
    private String type;

    //是否必要参数，默认为false，暂时不使用
    private boolean required;

    //request参数存放位置，header还是body 默认为body
    private RequestSource source;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public RequestSource getSource() {
        return source;
    }

    public void setSource(RequestSource source) {
        this.source = source;
    }
}
