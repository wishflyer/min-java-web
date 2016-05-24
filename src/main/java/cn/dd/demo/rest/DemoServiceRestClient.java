package cn.dd.demo.rest;

import cn.dd.core.myBatis.base.PageParam;
import cn.dd.core.rest.result.JsonRESTResult;
import cn.dd.core.rest.result.RESTStatusCode;
import cn.dd.core.utils.UUIDUtils;
import cn.dd.demo.myBatis.dao.TestDao;
import cn.dd.demo.myBatis.entity.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wishflyer on 2016/5/18.
 */
@RestController
public class DemoServiceRestClient {

    @Resource
    private TestDao testDao;


    @RequestMapping("/helloMyBatis")
    public void helloMyBatis(){

        System.out.println("-----start-----");
        Test test;
        //System.out.println("getById...");
        //Test test = testDao.getById("1");
        //System.out.println(test);

        System.out.println("insert...");
        test = new Test();
        String uuid = UUIDUtils.getUUID();
        test.setId(uuid);
        test.setMsg("insert-"+uuid);
        System.out.println(testDao.insert(test));


        System.out.println("insertBatch...");
        List<Test> testList = new ArrayList<Test>();
        test = new Test();
        uuid = UUIDUtils.getUUID();
        test.setId(uuid);
        test.setMsg("insertBatch-"+uuid);
        testList.add(test);
        test = new Test();
        uuid = UUIDUtils.getUUID();
        test.setId(uuid);
        test.setMsg("insertBatch-"+uuid);
        testList.add(test);
        System.out.println(testDao.insertBatch(testList));

        System.out.print("deleteBatch...");
        String[] ids = new String[]{"aab5a979def8441586e4333be84d3d58","e150e2be16f24d39a96f7ddf406e037f","3243"};
        System.out.println(testDao.deleteBatch(ids));

        System.out.println("getByPage...");
        PageParam pageParam = new PageParam(2,5);
        Map map = pageParam.getPageParamMap();
        List<Test> testList1= testDao.getByPage(map);
        System.out.println(testList1);

        System.out.println("------end------");

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