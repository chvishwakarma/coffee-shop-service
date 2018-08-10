package com.coffeeshop.domain.product;

import com.coffeeshop.domain.user.staff.StaffRegisterRequestDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Chandan Vishwakarma
 */
@Component
public class ProductRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> paramClass) {
        return ProductRequestDTO.class.equals(paramClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name",
                "Required[name]",
                "name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"description",
                "Required[description]",
                "description is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"price",
                "Required[price]",
                "price is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"totalQuantity",
                "Required[totalQuantity]",
                "totalQuantity is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"availableQuantity",
                "Required[availableQuantity]",
                "availableQuantity is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"status",
                "Required[status]",
                "status is required");
    }
}
