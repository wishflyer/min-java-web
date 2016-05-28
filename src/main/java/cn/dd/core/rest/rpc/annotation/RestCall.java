package cn.dd.core.rest.rpc.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by czdujb on 2015/10/28.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RestCall {
    String URL();
    HttpMethod RequestType() default HttpMethod.POST;
    //是否使用nignx服务器
    boolean UseAPIServer() default true;
    //是否使用url传参数,这个参数只有在GET的时候才效
    boolean UseURLTransferParam() default false;

}
