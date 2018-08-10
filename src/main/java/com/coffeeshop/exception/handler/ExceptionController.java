package com.coffeeshop.exception.handler;

import com.coffeeshop.domain.Response;
import com.coffeeshop.exception.ProductNotFoundException;
import com.coffeeshop.exception.StaffNotFoundException;
import com.coffeeshop.service.common.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Locale;

/**
 * @author Chandan Vishwakarma
 */
@ControllerAdvice
@Controller
public class ExceptionController implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    private MessageSource messageSource;

    @Autowired
    private MessageService messageService;

    @Autowired
    public ExceptionController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity processValidationError(MethodArgumentNotValidException ex) {
        Response response  = new Response(messageService);
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        response.setStatus(com.coffeeshop.constant.ResponseStatus.FAIL)
                .setMessage("field.validation.error")
                .setData(processFieldErrors(fieldErrors),false);
        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StaffNotFoundException.class)
    public ResponseEntity staffNotFoundException(StaffNotFoundException ex) {
        Response response  = new Response(messageService);
        response.setStatus(com.coffeeshop.constant.ResponseStatus.FAIL)
                .setMessage(ex.getMessage())
                .setData(ex.getMessage(),true);
        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity productNotFoundException(ProductNotFoundException ex) {
        Response response  = new Response(messageService);
        response.setStatus(com.coffeeshop.constant.ResponseStatus.FAIL)
                .setMessage(ex.getMessage())
                .setData(ex.getMessage(),true);
        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorDTO dto = new ValidationErrorDTO();

        for (FieldError fieldError: fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }

        return dto;
    }

    private String resolveLocalizedErrorMessage(FieldError fieldError) {

        Locale currentLocale =  LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
        return localizedErrorMessage;

    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
