package cn.dd.core.spring;

import cn.dd.core.spring.exception.AnnotationTypeRequiredException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.List;
import java.util.Set;

/**
 * Created by chenlong on 14-6-8.
 */
public abstract class AbstractBeanDefinitonScanner extends ClassPathBeanDefinitionScanner {

    public AbstractBeanDefinitonScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     * 复写的方法，用于注册要生成动态类的注解
     */
    @Override
    protected void registerDefaultFilters() {
        List<Class> annotationList = doRegisterAnnotationList();
        for (Class clazz : annotationList) {
            if (clazz.isAnnotation()) {
                addIncludeFilter(new AnnotationTypeFilter(clazz));
            } else {
                //记录日志，跳过该异常
                AnnotationTypeRequiredException ex = new AnnotationTypeRequiredException(clazz);
                logger.error(ex.getMessage());
                logger.info(ex);
            }
        }
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        this.processBeanDefinition(beanDefinitions);
        return beanDefinitions;
    }

    /**
     * 供继承类进行重载的方法，用于处理获得了BeanDefinition集合之后进行适当修改
     */
    protected abstract void processBeanDefinition(Set<BeanDefinitionHolder> definitionHolders);

    /**
     * 继承类必须重载，提供用于设置匹配查询条件的注解，返回一个注解类型
     */
    protected abstract List<Class> doRegisterAnnotationList();


    /**
     * 按照目前的规则，默认认为合法的都自动扫描类都应该是接口，能够被独立创建
     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return (beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent());
    }

    @Override
    protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) throws IllegalStateException {
        if (super.checkCandidate(beanName, beanDefinition)) {
            return true;
        } else {
            logger.warn("Skipping MapperFactoryBean with name '" + beanName
                    + "' and '" + beanDefinition.getBeanClassName() + "' mapperInterface"
                    + ". Bean already defined with the same name!");
            return false;
        }
    }
}
