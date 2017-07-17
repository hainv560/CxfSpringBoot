package com.example.cxf.springboot.config;

import com.example.cxf.springboot.ws.OLSWebservice;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

;

/**
 * Created by Sunny on 7/17/2017.
 */
@Configuration
public class CxfConfiguration {

    @Autowired
    private OLSWebservice olsWebservice;

    @Autowired
    private Bus bus;


    @Bean
    public ServletRegistrationBean cxfServletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new CXFServlet(), "/api/*");
        servletRegistrationBean.setLoadOnStartup(1);
        // Add custom Title to CXF´s ServiceList
        Map<String, String> initParameters = servletRegistrationBean.getInitParameters();
        initParameters.put("service-list-title", "List of OLS Services");


        return servletRegistrationBean;
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, olsWebservice);

        //Hide SOAP WS from Ws list
        /*Map<String,Object> properties = new HashMap<>();
        properties.put("org.apache.cxf.endpoint.private",new Boolean(true));

        endpoint.setProperties(properties);*/
        endpoint.setAddress("/soap");
        endpoint.publish();
        return endpoint;
    }

    @Bean
    public Server rsServer() {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();

        //Hide Restful WS from Ws list
        /*Map<String,Object> properties = new HashMap<>();
        properties.put("org.apache.cxf.endpoint.private",new Boolean(true));

        endpoint.setProperties(properties);*/
        endpoint.setBus(bus);
        endpoint.setAddress("/rest");
        endpoint.setServiceBean(olsWebservice);
        endpoint.setFeatures(Collections.singletonList(swagger2Feature()));
        endpoint.setProvider(jsonProvider(new ObjectMapper()));
        return endpoint.create();
    }

    @Bean
    public JacksonJsonProvider jsonProvider(ObjectMapper objectMapper) {
        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        provider.setMapper(objectMapper);
        return provider;
    }

    @Bean("swagger2Feature")
    public Feature swagger2Feature() {
        Swagger2Feature result = new Swagger2Feature();
        result.setTitle("RESTFul Webservice");
        result.setDescription("OLS RESTFul Webservice");
        result.setBasePath("/swagger");
        result.setVersion("v1");
        result.setSchemes(new String[] { "http", "https" });
        result.setPrettyPrint(true);
        return result;
    }
}
