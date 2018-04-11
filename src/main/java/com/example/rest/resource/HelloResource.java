package com.example.rest.resource;

import com.example.rest.dto.HelloDto;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Path("/hello")
public class HelloResource {

    @GET
    public Response get() {
        return Response.ok("HELLO").build();
    }

    @POST
    public Response post(@Valid HelloDto helloDto) throws Exception {

        Method method = getClass().getMethod("post", HelloDto.class);
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            System.out.println(parameter.getName());
        }

        return Response.ok(helloDto).build();
    }
}
