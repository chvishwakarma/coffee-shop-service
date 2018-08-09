package com.coffeeshop.constant;

/**
 * Constant values for the REST controllers API VERSIONS custom header request mapping.
 * Custom HTTP header `COFFEESHOP-API-VERSION` approach is used to create versioning of
 * REST APIs.
 *
 * <p>Visit this link for more information on REST API versioning
 * <a href="https://restfulapi.net/versioning/">
 * REST API VERSIONING Reference/a>.
 *
 * @author Chandan
 * @see com.coffeeshop.controller
 * @see com.coffeeshop.config
 **/
public class APIVersion {

    /**
     * Custom HTTP header key for API versioning {@value}
     */
    public final static String CUSTOM_HEADER_KEY = "COFFEESHOP-API-VERSION";

    /**
     * HTTP header key value separator {@value}
     */
    private final static String HEADER_SEPARATOR = "=";

    /**
     * Custom HTTP header `COFFEESHOP-API-VERSION` with empty value {@value}
     */
    private final static String HEADER = CUSTOM_HEADER_KEY + HEADER_SEPARATOR;

    /**
     * REST API version {@value}
     */
    private final static String VERSION_1 = "1";

    /**
     * Custom HTTP header `COFFEESHOP-API-VERSION` with value {@value}
     */
    public static final String V1 = HEADER + VERSION_1;
}
