package com.demo.config;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * Created by kingwu on 14/12/2016.
 */
public class SpittrWebAppInitializer {//extends AbstractAnnotationConfigDispatcherServletInitializer {

    

    /*
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setLoadOnStartup(1);
        registration.setMultipartConfig(new MultipartConfigElement("/tmp/spittr/uploads"));
    }


    @Override
    protected Filter[] getServletFilters() {
        return null;
    }
*/

}
