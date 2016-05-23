package cn.dd.core.rest.rpc.builder;


import cn.dd.core.config.CoreConfig;
import cn.dd.core.rest.result.JsonRESTResult;
import cn.dd.core.rest.result.RESTStatusCode;
import cn.dd.core.rest.rpc.annotation.HttpMethod;
import cn.dd.core.rest.rpc.annotation.Param;
import cn.dd.core.rest.rpc.annotation.RequestSource;
import cn.dd.core.rest.rpc.client.RestGetClient;
import cn.dd.core.rest.rpc.client.RestPostClient;
import cn.dd.core.rest.rpc.entity.ParamInfo;
import cn.dd.core.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by czdujb on 2015/10/28.
 */
@Component("RequestExecutor")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RequestExecutor {

    private ArrayList<ParamInfo> paramInfoList = new ArrayList<ParamInfo>();

    private ThreadLocal<Map<String, String>> headerValues = new ThreadLocal<Map<String, String>>();
    private ThreadLocal<Map<String, String>> bodyValues = new ThreadLocal<Map<String, String>>();

    //目标方法
    private Method targetMethod;

    //保存RPC类型，是post还是get
    private HttpMethod httpMethod;

    //返回类型
    private Class<?> returnType;

    //要调用的URL
    private String requestUrl;

    @Autowired
    private CoreConfig coreConfig;


    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    //执行操作
    public Object execute(Object[] args) throws IOException {

        String response = null;
        //获取参数
        getParam(args);
        //取得参数
        Map<String, String> header = headerValues.get();
        Map<String, String> body = bodyValues.get();

        //根据调用类型（get/post)进行调用
        if (httpMethod.equals(HttpMethod.GET)) {
            RestGetClient client = new RestGetClient();
            response = client.callRestRPC(coreConfig.getAPIServer() + requestUrl, header);
        } else if (httpMethod.equals(HttpMethod.POST)) {
            RestPostClient client = new RestPostClient();
            response = client.callRestRPC(coreConfig.getAPIServer() + requestUrl, header, body);
        }

        headerValues.set(null);
        bodyValues.set(null);

        System.out.println(">>response:"+response);

        return processResponse(response);

    }

    //包装返回结果
    private Object processResponse(String responseString) throws IOException {
        /*System.out.println("returnType:"+returnType);
        String objString = JsonUtils.toJson(responseString);
        System.out.println("objString:"+objString);*/
        //return JsonUtils.fromJson(objString, returnType);
        return responseString;
    }

/*
    //包装返回结果
    private Object processResponse(String responseString) throws IOException {

        try{
            JsonRESTResult result = JsonUtils.fromJson(responseString, JsonRESTResult.class);
            if(result.getStatusCode() == RESTStatusCode.SUCCESS) {
                if(returnType.isPrimitive()) {
                    return result.getReturnObj();
                }else if(returnType == String.class) {
                    return result.getReturnObj().toString();
                }else{
                    String objString = JsonUtils.toJson(result.getReturnObj());
                    return JsonUtils.fromJson(objString, returnType);
                }
            }else if(result.getStatusCode() == RESTStatusCode.NULL_SUCCESS) {
                return null;
            }
        }catch (JsonParseException e){
            return responseString;
        }

        return null;
    }*/

    //获取参数,更具source，放入headerValues或者是bodyValues
    public void getParam(Object[] args) throws IOException {
        Map<String, String> header = new HashMap<String, String>();
        Map<String, String> body = new HashMap<String, String>();
        for (int index = 0; index < args.length; index++) {
            ParamInfo paramInfo = paramInfoList.get(index);
            String requestValue = serializeRequestValue(args[index], paramInfo);
            if(paramInfo.getSource() == RequestSource.Header) {
                header.put(paramInfo.getValue(), requestValue);
            }else{
                body.put(paramInfo.getValue(), requestValue);
            }
        }
        headerValues.set(header);
        bodyValues.set(body);
    }


    //分析要发送的request的参数情况,保存相应信息
    public void analyzeParams(Method targetMethod) {
        this.targetMethod = targetMethod;
        //获取方法参数注解
        Annotation[][] annotations = targetMethod.getParameterAnnotations();
        for (Annotation[] annotation : annotations) {
            for (Annotation paramAnnotation : annotation) {
                paramInfoList.add(parseParamInfo(paramAnnotation));
            }
        }
    }

    public ParamInfo parseParamInfo(Annotation paramAnnotation) {
        ParamInfo paramInfo = new ParamInfo();
        Param param = (Param) paramAnnotation;
        paramInfo.setSource(param.source());
        paramInfo.setRequired(param.required());
        paramInfo.setType(param.type());
        paramInfo.setValue(param.value());
        return paramInfo;
    }


    private String serializeRequestValue(Object arg, ParamInfo pi) throws IOException {
        String requestValue;

        if (arg == null) return "";

        Class paramClazz = arg.getClass();
        if (paramClazz.isPrimitive()) {
            requestValue = arg.toString();
        } else if (arg instanceof String) {
            requestValue = arg.toString();
        } else {
            requestValue = JsonUtils.toJson(arg);
        }
        return requestValue;
    }

}