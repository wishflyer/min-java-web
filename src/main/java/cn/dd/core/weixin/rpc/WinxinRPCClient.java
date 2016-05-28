package cn.dd.core.weixin.rpc;

import cn.dd.core.rest.rpc.annotation.*;

/**
 * Created by wishflyer on 2016/5/23.
 */
@RestClient
public interface WinxinRPCClient {

    @RestCall(URL="https://api.weixin.qq.com/cgi-bin/token",RequestType= HttpMethod.GET,UseAPIServer = false,UseURLTransferParam=true)
    public String  getAccessToken(@Param(value = "grant_type",source = RequestSource.Header) String grant_type
                                  ,@Param(value = "appid",source = RequestSource.Header) String appid
                                  ,@Param(value = "secret",source = RequestSource.Header) String secret);

}
