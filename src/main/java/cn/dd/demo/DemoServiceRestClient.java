package cn.dd.demo;

import cn.dd.core.rest.result.JsonRESTResult;
import cn.dd.core.rest.result.RESTStatusCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wishflyer on 2016/5/18.
 */
@RestController
public class DemoServiceRestClient {



    @RequestMapping("/helloRest")
    public String helloRest(){
        System.out.println("helloRest...");
        return "test";
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