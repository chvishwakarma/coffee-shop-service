package com.coffeeshop.domain.user.staff;

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
public class StaffRegisterRequestValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!*%]).{6,20})";

    @Override
    public boolean supports(Class<?> paramClass) {
        return StaffRegisterRequestDTO.class.equals(paramClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        StaffRegisterRequestDTO client = (StaffRegisterRequestDTO) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","Required[name]","name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"contactNumber","Required[contactNumber]","mobile is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"gender","Required[gender]","gender is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"birthDate","Required[birthdate]","birth is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","Required[email]","email is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","Required[password]","password is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"confirmPassword","Required[confirmPassword]","confirmPassword is required");

        //if form has no blank or whitespace
        if(!errors.hasErrors()){
            if((!client.getConfirmPassword().equals(client.getPassword()))){
                errors.rejectValue("confirmPassword","Invalid [confirmPassword]","Confirm password not matched");
            }

            // email validation in spring
            if (!(client.getEmail() != null && client.getEmail().isEmpty())) {
                pattern = Pattern.compile(EMAIL_PATTERN);
                matcher = pattern.matcher(client.getEmail());
                if (!matcher.matches()) {
                    errors.rejectValue("email", "Required [email]","Enter a valid email address");
                }
            }
            // password validation in spring
            if(client.getPassword()!=null && !client.getPassword().isEmpty()){
                pattern = Pattern.compile(PASSWORD_PATTERN);
                matcher = pattern.matcher(client.getPassword());
                if (!matcher.matches()) {
                    errors.rejectValue("password", "Required [password]","Password must be alphanumeric with special character using Uppercase and Lowercase");
                }
            }
        }
    }
}
