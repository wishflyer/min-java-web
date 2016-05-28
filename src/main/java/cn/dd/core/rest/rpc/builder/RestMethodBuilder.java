package cn.dd.core.rest.rpc.builder;


import cn.dd.core.rest.rpc.annotation.HttpMethod;
import cn.dd.core.rest.rpc.annotation.RestCall;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


import java.lang.reflect.Method;

/**
 * Created by czdujb on 2015/10/28.
 */
public class RestMethodBuilder<T> implements FactoryBean<T>, ApplicationContextAware {

    private Method targetMethod;
    private HttpMethod requestType;
    private ApplicationContext context;
    private Class<?> returnType;
    //private Class<?> restResponseType;
    private String requestUrl;
    private boolean useAPIServer;
    private boolean useURLTransferParam;

    private Class<?> ExecutorClass = RequestExecutor.class;


    public void setTargetMethod(Method targetMethod) {
        this.targetMethod = targetMethod;
        RestCall annotate = targetMethod.getAnnotation(RestCall.class);
        requestType = annotate.RequestType();
        returnType = targetMethod.getReturnType();
        //restResponseType = annotate.ResponseType();
        requestUrl = annotate.URL();
        useAPIServer = annotate.UseAPIServer();
        useURLTransferParam = annotate.UseURLTransferParam();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public T getObject() throws Exception {
        RequestExecutor requestExecutor = (RequestExecutor) context.getBean(ExecutorClass.getSimpleName());
        requestExecutor.analyzeParams(targetMethod);
        requestExecutor.setReturnType(returnType);
        requestExecutor.setRequestUrl(requestUrl);
        requestExecutor.setHttpMethod(requestType);
        requestExecutor.setUseAPIServer(useAPIServer);
        requestExecutor.setUseURLTransferParam(useURLTransferParam);
        return (T) requestExecutor;
    }

    @Override
    public Class<?> getObjectType() {
        return ExecutorClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
