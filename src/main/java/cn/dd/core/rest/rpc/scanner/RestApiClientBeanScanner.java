package cn.dd.core.rest.rpc.scanner;

import cn.dd.core.rest.rpc.annotation.RestClient;
import cn.dd.core.rest.rpc.builder.RestCallFactory;
import cn.dd.core.rest.rpc.builder.RestMethodBuilder;
import cn.dd.core.spring.AbstractBeanDefinitonScanner;
import cn.dd.core.utils.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by czdujb on 2015/10/28.
 */
public class RestApiClientBeanScanner extends AbstractBeanDefinitonScanner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public RestApiClientBeanScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     * 用于处理获得了BeanDefinition集合之后进行适当修改
     */
    protected void processBeanDefinition(Set<BeanDefinitionHolder> definitionHolders) {
        for (BeanDefinitionHolder holder : definitionHolders) {
            GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();

            logger.debug(String.format(
                    "Creating RESTAPIClientBean configuration bean for base '%s' with name '%s',",
                    definition.getBeanClassName(), holder.getBeanName()));

            //配置调用的Proxy Class
            //获得使用@RPCClient注解的类
            String requestClassName = definition.getBeanClassName();
            //设置该类的Factory的调用类
            definition.getPropertyValues().add("client", definition.getBeanClassName());
            //设置该类为RPCClassFactory
            definition.setBeanClass(RestCallFactory.class);
            //配置实际执行的bean，一个method一个bean.
            processRestCallRequest(requestClassName);
        }
    }

    /*
     * 提供用于设置匹配查询条件的注解，返回一个注解类型
     */
    protected List<Class> doRegisterAnnotationList() {
        ArrayList<Class> list = new ArrayList<Class>();
        list.add(RestClient.class);
        return list;
    }

    private void processRestCallRequest(String requestClassName) {
        Class mapperClazz = null;
        try {
            //通过类名获取类
            mapperClazz = Class.forName(requestClassName);
        } catch (ClassNotFoundException e) {
            logger.debug("get definition from {} failed, check the annotations", requestClassName);

        }
        //对每个方法生成为一个实际的对象
        Method[] methods = mapperClazz.getMethods();
        for (Method method : methods) {
            logger.debug("Creating request_bak client api caller: {}", method.getName());
            String fullName = MethodUtils.getFullMethodName(method);
            GenericBeanDefinition definition = new GenericBeanDefinition();
            //设置bean的名称，方便以后直接通过容器拿出来用
            definition.setBeanClassName(fullName);
            //由于是一个方法，这里给它设置一个具体类
            definition.setBeanClass(RestMethodBuilder.class);
            //设置一个目标方法
            definition.getPropertyValues().add("targetMethod", method);
            //单例模式
            definition.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
            //注册bean
            getRegistry().registerBeanDefinition(fullName, definition);
        }
    }
}
