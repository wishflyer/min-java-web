package cn.dd.core.weixin.rest.rest;

import cn.dd.core.utils.JsonUtils;
import cn.dd.core.weixin.config.WinXinConfig;
import cn.dd.core.weixin.rest.dao.WeixinAccessTokenDao;
import cn.dd.core.weixin.rest.entity.WeixinAccessToken;
import cn.dd.core.weixin.rpc.WinxinRPCClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by wishflyer on 2016/5/18.
 */
@RestController
public class WinxinRestClient {

    @Resource
    private WinxinRPCClient winxinRPCClient;

    @Resource
    private WeixinAccessTokenDao weixinAccessTokenDao;

    /**
     * 刷新access_token并写入数据库保存
     */
    @RequestMapping("/winxin/getAccessToken")
    public void refreshAccessToken(){
        System.out.println("-----start-----");
        String msg = winxinRPCClient.getAccessToken("client_credential",WinXinConfig.AppID,WinXinConfig.AppSecret);
        Map<String,Object> paramMap;
        try {
            paramMap = JsonUtils.parseMap(msg);
            //不包含errcode字段，否则表示错误
            if(!paramMap.containsKey("errcode")){
                WeixinAccessToken weixinAccessToken = new WeixinAccessToken();
                weixinAccessToken.setId("weixinAccessToken");
                weixinAccessToken.setAccessToken((String)paramMap.get("access_token"));
                weixinAccessToken.setExpiresIn((int)paramMap.get("expires_in"));
                weixinAccessToken.setUpdateDate(new Date());
                weixinAccessTokenDao.update(weixinAccessToken);
            }else{
                //TODO 出错处理
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("------end------");
    }



}