package com.coffeeshop.domain;


import com.coffeeshop.constant.ResponseStatus;
import com.coffeeshop.service.common.MessageService;

/**
 * Common HTTP response sending Domain
 * @author Chandan Vishwakarma
 * @see com.coffeeshop.authentication
 * @see com.coffeeshop.controller
 */

public class Response {

    private ResponseStatus status;
    private String message;
    private Object data;

    MessageService messageService;

    public Response(MessageService messageService) {
        this.status= ResponseStatus.SUCCESS;
        this.messageService = messageService;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public Response setStatus(ResponseStatus status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = this.messageService.getMessage(message);
        return this;
    }

    public Object getData() {
        return data;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public Response setData(Object data,Boolean isLocalizedMessage) {
        this.data = data;
        if(isLocalizedMessage){
            this.data = this.messageService.getMessage(String.valueOf(data));
        }
        return this;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
