package cn.dd.demo.myBatis.service.impl;

import cn.dd.demo.myBatis.dao.TestDao;
import cn.dd.demo.myBatis.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class TestServiceImpl implements TestService {
    @Resource
	private TestDao testDao;

}
