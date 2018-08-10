package com.coffeeshop.domain.order;

import com.coffeeshop.domain.product.ProductRequestDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Chandan Vishwakarma
 */
@Component
public class OrderRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> paramClass) {
        return OrderRequestDTO.class.equals(paramClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"customer",
                "Required[customer]",
                "customer is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"items",
                "Required[items]",
                "items is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"paymentMode",
                "Required[paymentMode]",
                "paymentMode is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"paymentStatus",
                "Required[paymentStatus]",
                "paymentStatus is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"discountAmount",
                "Required[discountAmount]",
                "discountAmount is required");
    }
}
