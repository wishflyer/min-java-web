package cn.dd.demo.myBatis.service.impl;

import cn.dd.demo.myBatis.dao.Test2Dao;
import cn.dd.demo.myBatis.service.Test2Service;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Test2ServiceImpl implements Test2Service {
    @Resource
	private Test2Dao test2Dao;

}
