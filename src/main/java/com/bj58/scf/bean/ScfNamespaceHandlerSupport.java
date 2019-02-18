package com.bj58.scf.bean;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author zhangyining on 19/2/18 018.
 */
public class ScfNamespaceHandlerSupport extends NamespaceHandlerSupport {
    @Override
    public void init() {
        //解析并注册beanDefinition，service是xsd中的名字
        this.registerBeanDefinitionParser("service", new ScfBeanDefinitionParser());
//        this.registerBeanDefinitionParser("client", new ScfClientBeanDefinitionParser());
    }
}
