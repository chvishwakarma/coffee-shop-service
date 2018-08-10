package com.coffeeshop.constant;

/**
 * Constant values for request mapping of controller classes
 *
 * @author chandan vishwakarma
 * @see com.coffeeshop.controller
 */
public class Route {


    /* STAFF ENDPOINTS*/
    public static final String STAFF = "/api/staff";
    public static final String STAFF_REGISTER =  RouteEntity.REGISTER;
    public static final String STAFF_ME =  RouteEntity.ME;


    /* PRODUCT ENDPOINTS*/
    public static final String PRODUCT = "/api/product";
    public static final String PRODUCT_CREATE =  RouteEntity.CREATE;
    public static final String PRODUCT_LIST =  RouteEntity.LIST;
    public static final String PRODUCT_BY_ID =  RouteEntity.PRODUCT_GET_BY_ID;

    /* ORDER ENDPOINS */
    public static final String ORDER = "/api/order";
    public static final String ORDER_CREATE =  RouteEntity.CREATE;
    public static final String ORDER_LIST =  RouteEntity.LIST;
    public static final String ORDER_BY_ID =  RouteEntity.ORDER_GET_BY_ID;
}
