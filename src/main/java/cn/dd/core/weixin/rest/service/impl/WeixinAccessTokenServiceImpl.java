package cn.dd.core.weixin.rest.service.impl;

import cn.dd.core.weixin.rest.dao.WeixinAccessTokenDao;
import cn.dd.core.weixin.rest.service.WeixinAccessTokenService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WeixinAccessTokenServiceImpl implements WeixinAccessTokenService {
    @Resource
	private WeixinAccessTokenDao weixinAccessTokenDao;

}
