package com.example.cxf.springboot.ws;

import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Created by Sunny on 7/17/2017.
 */
@WebService
public interface OLSWebservice {

    @WebMethod
    @WebResult
    String sayHello()throws Exception;

}
