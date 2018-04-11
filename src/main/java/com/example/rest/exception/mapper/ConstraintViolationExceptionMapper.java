package com.example.rest.exception.mapper;

import com.example.rest.exception.dto.ErrorDto;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        String[] messages = e.getConstraintViolations()
                .stream()
                .map(violation -> violation.getMessage())
                .toArray(String[]::new);
        ErrorDto errorDto = new ErrorDto("検証エラー", messages);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorDto).build();
    }
}
