package com.coffeeshop.service.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Provides service to read message properties from multiple i18 files
 * @author Chandan
 */
@Service
public class MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    MessageSource messageSource;

    /**
     * Get message from key without any string interpolation
     * @param key
     * @return String
     */
    public String getMessage(String key){
        return messageSource.getMessage(key, null, Locale.getDefault());
    }

    /**
     * Get message from properties file with given key and interpolation
     * of strings with parameters passed as fields
     * @param key
     * @param fields
     * @return String
     */
    public String getMessage(String key, Object... fields){
        return messageSource.getMessage(key, fields, Locale.getDefault());
    }
}
