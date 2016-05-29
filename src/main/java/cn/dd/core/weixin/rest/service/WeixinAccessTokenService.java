package cn.dd.core.weixin.rest.service;

import cn.dd.core.weixin.rest.entity.WeixinAccessToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


public interface WeixinAccessTokenService {

    /**
     * 刷新access_token并写入数据库保存
     */
    public void refreshAccessToken();

    /**
     * 获取微信access_token
     * @return 获取微信access_token
     */
    public String getAccessToken();

    /**
     * 获取微信服务器IP地址
     * @return 微信服务器IP地址列表
     */
    public ArrayList<String> getRealIP();

}
