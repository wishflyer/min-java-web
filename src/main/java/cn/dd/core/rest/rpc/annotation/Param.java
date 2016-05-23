package cn.dd.core.rest.rpc.annotation;

import java.lang.annotation.*;

/**
 * Created by czdujb on 2015/10/28.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Param {

    //实际request中传入的值的名称
    String value();

    //参数类型，暂时不使用
    String type() default "";

    //是否必要参数，默认为false
    boolean required() default false;

    //request参数存放位置，header还是body 默认为body
    RequestSource source() default RequestSource.Body;



}
