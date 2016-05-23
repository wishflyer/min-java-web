package cn.dd.core.rest.rpc.builder;

import net.sf.cglib.proxy.Enhancer;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by czdujb on 2015/10/28.
 */
public class RestCallFactory<T> implements FactoryBean<T> {

    @Autowired
    public RestProxy proxy;

    //RestClient
    private Class<T> client;

    public void setClient(Class<T> client) {
        this.client = client;
    }

    @Override
    public T getObject() throws Exception {
        //对所有的方法调用进行代理，这里其实就是用来动态调用RPC的
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(client);
        enhancer.setCallback(proxy);
        return (T)enhancer.create();
    }

    @Override
    public Class<?> getObjectType() {
        return client;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
