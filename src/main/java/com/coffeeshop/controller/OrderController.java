package com.coffeeshop.controller;

import com.coffeeshop.constant.APIVersion;
import com.coffeeshop.constant.ResponseStatus;
import com.coffeeshop.constant.Route;
import com.coffeeshop.domain.Response;
import com.coffeeshop.domain.order.*;
import com.coffeeshop.domain.product.*;
import com.coffeeshop.exception.OrderNotFoundException;
import com.coffeeshop.exception.ProductNotFoundException;
import com.coffeeshop.service.OrderService;
import com.coffeeshop.service.ProductService;
import com.coffeeshop.service.common.MessageService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = Route.ORDER)
@Api(tags="Order", description = "Endpoint for Orders")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    MessageService messageService;

    @Autowired
    OrderRequestValidator orderRequestValidator;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = Route.ORDER_CREATE,
            headers = APIVersion.V1,
            method = RequestMethod.POST)

    @ApiOperation(
            value = "Create An Order",
            notes = "Returns new order created in system",
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server Error")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity createOrder(@RequestBody OrderRequestDTO orderRequestDTO,
                                        BindingResult errors)
            throws NoSuchMethodException,MethodArgumentNotValidException {

        Response response = new Response(messageService);
        orderRequestValidator.validate(orderRequestDTO,errors);

        if(errors.hasErrors()){
            LOGGER.debug("Errors in creating product form {}",orderRequestDTO);
            throw new MethodArgumentNotValidException(
                    new MethodParameter(
                            this.getClass()
                                    .getDeclaredMethod(
                                            "createOrder",
                                            ProductRequestDTO.class,
                                            BindingResult.class),
                            0),errors);
        }

        Order order = orderService.create(orderRequestDTO);
        if (order != null){
            response
                    .setStatus(ResponseStatus.SUCCESS)
                    .setMessage("order.create.success")
                    .setData(OrderAssembler.fromOrder(order));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response
                    .setStatus(ResponseStatus.FAIL)
                    .setMessage("order.create.fail");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }


    @RequestMapping(value = Route.ORDER_LIST,
            headers = APIVersion.V1,
            method = RequestMethod.GET)

    @ApiOperation(
            value = "GET ALL ORDERS",
            notes = "Returns all orders from system",
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server Error")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity getAllOrders(){

        Response response = new Response(messageService);
        List<OrderWebDTO> webDTOList = new ArrayList<>();
        List<Order> allOrders = orderService.getAll();
        if (allOrders != null && allOrders.size()>0){
            response
                    .setStatus(ResponseStatus.SUCCESS)
                    .setMessage("order.list.success")
                    .setData(OrderAssembler.fromOrderList(allOrders));
        }else{
            response
                    .setStatus(ResponseStatus.FAIL)
                    .setMessage("order.list.fail")
                    .setData(webDTOList);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = Route.ORDER_BY_ID,
            headers = APIVersion.V1,
            method = RequestMethod.GET)
    @ApiOperation(
            value = "GET Order BY ID",
            notes = "Return order by ID",
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server Error")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity getOrderByID(@PathVariable("id") Long id) throws OrderNotFoundException{

        Response response = new Response(messageService);
        Order order = orderService.getByID(id);
        if (order != null){
            response
                    .setStatus(ResponseStatus.SUCCESS)
                    .setMessage("order.found.success")
                    .setData(OrderAssembler.fromOrder(order));
        }else{
            throw new OrderNotFoundException("order.not.found.exception");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
