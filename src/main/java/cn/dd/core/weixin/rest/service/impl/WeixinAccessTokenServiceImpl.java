package cn.dd.core.weixin.rest.service.impl;

import cn.dd.core.utils.JsonUtils;
import cn.dd.core.weixin.config.WinXinConfig;
import cn.dd.core.weixin.rest.dao.WeixinAccessTokenDao;
import cn.dd.core.weixin.rest.entity.WeixinAccessToken;
import cn.dd.core.weixin.rest.service.WeixinAccessTokenService;
import javax.annotation.Resource;

import cn.dd.core.weixin.rpc.WinxinRPCClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WeixinAccessTokenServiceImpl implements WeixinAccessTokenService {

    private Logger logger = LoggerFactory.getLogger(WeixinAccessTokenServiceImpl.class);

    @Resource
	private WeixinAccessTokenDao weixinAccessTokenDao;
    @Resource
    private WinxinRPCClient winxinRPCClient;

    private final String MAIN_KEY = "weixinAccessToken";

    /**
     * 刷新access_token并写入数据库保存
     */
    @Override
    public void refreshAccessToken(){
        System.out.println("-----start refresh access_token-----");
        String msg = winxinRPCClient.getAccessToken("client_credential", WinXinConfig.AppID,WinXinConfig.AppSecret);
        Map<String,Object> paramMap;
        try {
            paramMap = JsonUtils.parseMap(msg);
            //不包含errcode字段，否则表示错误
            if(!paramMap.containsKey("errcode")){
                WeixinAccessToken weixinAccessToken = new WeixinAccessToken();
                weixinAccessToken.setId(MAIN_KEY);
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

    @Override
    public String getAccessToken() {
        logger.info("[My Service]:getAccessToken....");
        WeixinAccessToken weixinAccessToken = weixinAccessTokenDao.getById(MAIN_KEY);
        return weixinAccessToken.getAccessToken();
    }

    @Override
    public ArrayList<String> getRealIP() {
        String accessToken = getAccessToken();
        logger.info("[My Service]:getRealIP....");
        String msg = winxinRPCClient.getRealIP(accessToken);
        Map<String,Object> paramMap;
        ArrayList<String> ipList = null;
        try {
            paramMap = JsonUtils.parseMap(msg);
            //不包含errcode字段，否则表示错误
            if(!paramMap.containsKey("errcode")){
                ipList = (ArrayList<String>)paramMap.get("ip_list");
            }else{
                //TODO 出错处理
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ipList;
    }


}
