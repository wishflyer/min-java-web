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



    /**
     * 获取微信服务器IP地址
     * https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN
     */
    @RestCall(URL="https://api.weixin.qq.com/cgi-bin/getcallbackip",RequestType= HttpMethod.GET,UseAPIServer = false,UseURLTransferParam=true)
    public String getRealIP(@Param(value = "access_token",source = RequestSource.Header) String access_token);

}
