package cn.dd.core.rest.rpc.builder;

import cn.dd.core.utils.MethodUtils;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by czdujb on 2015/10/28.
 */
@Component
public class RestProxy implements MethodInterceptor,ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        //RPC调用
        String beanName = MethodUtils.getFullMethodName(method);
        RequestExecutor requestExecutor = (RequestExecutor)applicationContext.getBean(beanName);
        return requestExecutor.execute(args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
