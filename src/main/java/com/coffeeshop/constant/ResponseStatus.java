package com.coffeeshop.constant;

/**
 * Server status message constant
 * @author Chandan Vishwakarma
 * @see com.coffeeshop.authentication
 * @see com.coffeeshop.controller
 */
public enum ResponseStatus {

    SUCCESS("ok"),
    FAIL("fail"),
    API_ERROR("server fault");

    private String status;

    ResponseStatus(String value) {

        this.status = value;
    }

    public String getValue() { return status; }

    public static ResponseStatus parse(String statusText) {

        ResponseStatus status = null; // Default
        for (ResponseStatus item : ResponseStatus.values()) {
            if (item.getValue().equals(statusText)) {
                status = item;
                break;
            }
        }
        return status;

    }
}
