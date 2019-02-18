package com.bj58.scf.bean;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhangyining on 18/12/19 019.
 */
@Data
public class ScfFactoryBean<T> implements FactoryBean {

    /**
     * 属性名称需要与ScfBeanDefinitionParser中放入builder中的一致
     * tcp访问url
     */
    private String url;

    /**
     * 接口
     */
    private Class<T> interfaceClass;

    private MapperInvocationHandler handler = new MapperInvocationHandler();

    /**
     * 属性建议通过set方法注入，构造方法注入容易出错
     */
    public ScfFactoryBean(){

    }

    @Override
    @SuppressWarnings("unchecked")
    public T getObject() throws Exception {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{interfaceClass}, handler);
    }

    @Override
    public Class<T> getObjectType() {
        return interfaceClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    private static class MapperInvocationHandler implements InvocationHandler{

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(method.getName());
            return null;
        }
    }
}
