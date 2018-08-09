package com.coffeeshop.controller;

import com.coffeeshop.constant.APIVersion;
import com.coffeeshop.domain.user.LoginDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;

/**
 * Dummy implementation of login API
 * to create a swagger document
 * if you use swagger test ""try out" it should excute correct API
 * @author Chandan Vishwakarma
 */
@RestController
@RequestMapping(value = "/api")
@Api(tags="Authentication", description = "Authentication Module Related Operations")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @RequestMapping(value = "/login",method = RequestMethod.POST, headers = APIVersion.V1)
    @ApiOperation(
            value = "User Authentication URL.",
            notes = "Authenticate user based on social auth tokens",
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server Error")
    })
    public ResponseEntity authenticateUser(@RequestBody LoginDTO loginDTO){
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(new Date());
        return new ResponseEntity<>("The time on the server is: "
                + formattedDate, HttpStatus.OK);
    }
}
