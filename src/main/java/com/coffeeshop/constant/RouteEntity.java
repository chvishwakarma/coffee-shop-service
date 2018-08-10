package com.coffeeshop.constant;

/**
 * Constant values for entities used for Routes or request mapping URLs
 *
 * @author chandan vishwakarma
 * @see Route
 */
public class RouteEntity {

    public static final String SEPARATOR = "/";
    public static final String PRODUCT = "product";
    public static final String ORDER = "order";
    public static final String CUSTOMER = "customer";
    public static final String REGISTER = "/";
    public static final String ME = "/me";
    public static final String CREATE = "/";
    public static final String LIST = "/";
    public static final String BY_ID = "{id}";

    public static final String PRODUCT_CREATE = PRODUCT+SEPARATOR+CREATE;
    public static final String PRODUCT_LIST = PRODUCT+SEPARATOR+LIST;
    public static final String PRODUCT_GET_BY_ID = SEPARATOR+BY_ID;

    public static final String ORDER_CREATE = ORDER+SEPARATOR+CREATE;
    public static final String ORDER_LIST = ORDER+SEPARATOR+LIST;
    public static final String ORDER_GET_BY_ID = SEPARATOR+BY_ID;

    public static final String CUSTOMER_LIST = CUSTOMER+SEPARATOR+LIST;
    public static final String CUSTOMER_GET_BY_ID = CUSTOMER+SEPARATOR+BY_ID;
}
