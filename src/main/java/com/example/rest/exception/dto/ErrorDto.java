package com.example.rest.exception.dto;

public class ErrorDto {

    private String errorType;
    private String[] messages;

    public ErrorDto(String errorType, String[] messages) {
        this.errorType = errorType;
        this.messages = messages;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String[] getMessages() {
        return messages;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }
}
