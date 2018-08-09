package com.coffeeshop.exception.handler;

public class ExceptionJSONInfo {
    private String error;
    private String message;

    public ExceptionJSONInfo(String error, String message){
        this.error=error;
        this.message=message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
