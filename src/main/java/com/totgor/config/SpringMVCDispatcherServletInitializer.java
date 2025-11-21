package com.totgor.config;

import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

public class SpringMVCDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?> @Nullable [] getRootConfigClasses() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getRootConfigClasses'");
         System.out.println(" === getRootConfigClasses() returning null === ");
        return null;
    }

    @Override
    protected Class<?> @Nullable [] getServletConfigClasses() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getServletConfigClasses'");
        System.out.println(" === DispatcherServlet configured with SpringConfig  === ");
        return new Class[] {SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getServletMappings'");
        System.out.println(" === Servlet mapped to '/' === ");
        return new String[] {"/"};
    }

     @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println(" === Starting Spring MVC Application === ");
        super.onStartup(servletContext);
        System.out.println(" === Spring MVC Application started successfully === ");
    }
}
