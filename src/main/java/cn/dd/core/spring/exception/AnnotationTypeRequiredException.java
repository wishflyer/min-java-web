package cn.dd.core.spring.exception;

/**
 * Created by czdujb on 2015/10/29.
 */
public class AnnotationTypeRequiredException extends Exception {
    private Class exceptionClazz;

    public AnnotationTypeRequiredException(Class clazz) {
        this.exceptionClazz = clazz;
    }

    public Class getExceptionClazz() {
        return exceptionClazz;
    }

    public String getMessage() {
        return String.format("Class: %s is not an Annotation type", exceptionClazz.getName());
    }
}
