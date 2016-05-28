package cn.dd.core.weixin.rest.dao.impl;

import cn.dd.core.myBatis.base.BaseMybatisDao;
import cn.dd.core.weixin.rest.entity.WeixinAccessToken;
import cn.dd.core.weixin.rest.dao.WeixinAccessTokenDao;
import org.springframework.stereotype.Repository;


@Repository("weixinAccessTokenDao")
public class WeixinAccessTokenDaoImpl extends BaseMybatisDao<WeixinAccessToken,String> implements WeixinAccessTokenDao{
	
	@Override
	public String getMybatisMapperNamesapce() {
		return "cn.dd.core.weixin.rest.dao.WeixinAccessToken";
	}

}
