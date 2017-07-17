package com.example.cxf.springboot.ws.impl;

import com.example.cxf.springboot.ws.OLSWebservice;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Sunny on 7/17/2017.
 */
@Service
@WebService(endpointInterface = "com.example.cxf.springboot.ws.OLSWebservice")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
@Path("/")
@Api("/")
public class OLSWebserviceImpl implements OLSWebservice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @GET
    @Path("/hello")
    public String sayHello() throws Exception {
        return "Hello World!";
    }

}
