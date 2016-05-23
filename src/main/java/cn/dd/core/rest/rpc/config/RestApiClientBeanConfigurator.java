package cn.dd.core.rest.rpc.config;

import cn.dd.core.rest.rpc.scanner.RestApiClientBeanScanner;
import cn.dd.core.spring.AbstractBeanDefinitionConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;


public class RestApiClientBeanConfigurator extends AbstractBeanDefinitionConfigurator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected ClassPathBeanDefinitionScanner getScanner(BeanDefinitionRegistry registry) {
        RestApiClientBeanScanner scanner = new RestApiClientBeanScanner(registry);
        return scanner;
    }


    @Override
    protected void doProcessPropertyPlaceHolders(PropertyValues values) {}

}
