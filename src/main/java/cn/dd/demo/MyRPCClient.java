package cn.dd.demo;

import cn.dd.core.rest.result.JsonRESTResult;
import cn.dd.core.rest.rpc.annotation.Param;
import cn.dd.core.rest.rpc.annotation.RestCall;
import cn.dd.core.rest.rpc.annotation.RestClient;

/**
 * Created by wishflyer on 2016/5/23.
 */
@RestClient
public interface MyRPCClient {

    @RestCall(URL="/jsonRest")
    public String  callPRC(@Param(value="param") String param);


    @RestCall(URL="/jsonStringRest")
    public String callPRC2(@Param(value="param") String param);


    @RestCall(URL="/helloRest")
    public String callPRC3(@Param(value="param") String param);

}
