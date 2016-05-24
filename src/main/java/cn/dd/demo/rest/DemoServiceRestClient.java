package cn.dd.demo.rest;

import cn.dd.core.rest.result.JsonRESTResult;
import cn.dd.core.rest.result.RESTStatusCode;
import cn.dd.demo.myBatis.dao.TestDao;
import cn.dd.demo.myBatis.entity.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wishflyer on 2016/5/18.
 */
@RestController
public class DemoServiceRestClient {

    @Resource
    private TestDao testDao;


    @RequestMapping("/helloMyBatis")
    public void helloMyBatis(){
        System.out.println("helloMyBatis...");
        Test test = testDao.getById("1");
        System.out.println(test.getMsg());
    }

    @RequestMapping("/helloRest")
    public String helloRest(){
        System.out.println("helloRest...");
        return "testAAAABBBBB";
    }



    @RequestMapping("/jsonRest")
    public JsonRESTResult jsonRest(){
        System.out.println("helloRest...");

        JsonRESTResult restResult = new JsonRESTResult();
        restResult.setStatusCode(RESTStatusCode.SUCCESS);

        DemoEntity entity = new DemoEntity();
        entity.setNumber(8888);
        entity.setCode(10);
        entity.setStatus("OK");

        restResult.setReturnObj(entity);
        restResult.setMsg("this is a test");

        return restResult;
    }

    @RequestMapping("/jsonStringRest")
    public String jsonStringRest(){
        System.out.println("helloJsonString...");

        JsonRESTResult restResult = new JsonRESTResult();
        restResult.setStatusCode(800);

        DemoEntity entity = new DemoEntity();
        entity.setNumber(8888);
        entity.setCode(10);
        entity.setStatus("OK");

        restResult.setReturnObj(entity);
        restResult.setMsg("this is a test");

        return restResult.toHttpResponse();
    }


}