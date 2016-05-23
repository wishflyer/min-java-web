package cn.dd.core.rest.result;

/**
 * Created by czdujb on 2015/10/28.
 */
public interface RESTStatusCode {

    // HTTP相关状态码
    int OK = 200;
    int BAD_REQUEST = 400;
    int NOT_FOUND = 404;
    int ERROR = 500;

    // 业务相关状态码
    int SUCCESS = 800;
    int FAIL = 900;

    int NULL_SUCCESS = 801;
    int NULL_FAIL = 901;

}
