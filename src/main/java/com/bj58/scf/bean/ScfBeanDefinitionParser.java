package com.bj58.scf.bean;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author zhangyining on 19/2/18 018.
 */
public class ScfBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        //此处返回的是自定义的FactoryBean，具体参见ScfFactoryBean
        return ScfFactoryBean.class;
    }

    /**
     * 重写父类空方法。主要工作是从element中获取自定义元素值，检查并设置到builder中
     */
    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {

        //get attribute
        String url = element.getAttribute("url");
        String iface = element.getAttribute("interface");
        String implClass = element.getAttribute("implClass");
        String id = element.getAttribute("id");

        //check attribute，id can not be duplicate
        if(StringUtils.hasText(id) && parserContext.getRegistry().containsBeanDefinition(id)) {
            throw new IllegalArgumentException(" interface has exist can't init another one id is " + id);
        } else {
            if(StringUtils.hasText(url)) {
                //set builder property
                builder.addPropertyValue("url", url);
            }

            Class<?> clazz;
            if(StringUtils.hasText(iface)) {
                try {
                    clazz = Class.forName(iface);
                    builder.addPropertyValue("interfaceClass", clazz);
                } catch (ClassNotFoundException e) {
                    throw new IllegalArgumentException("interface not found " + iface);
                }
            }

            if(StringUtils.hasText(implClass)) {
                try {
                    clazz = Class.forName(implClass);
                    builder.addPropertyValue("implClass", clazz);
                } catch (ClassNotFoundException var9) {
                    throw new IllegalArgumentException("implClass not found " + implClass);
                }
            }
        }
    }
}
