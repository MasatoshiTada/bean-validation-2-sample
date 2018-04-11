package com.example.rest.dto;

import javax.validation.constraints.NotBlank;

public class HelloDto {

    @NotBlank(message = "{hello.notblank}")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
