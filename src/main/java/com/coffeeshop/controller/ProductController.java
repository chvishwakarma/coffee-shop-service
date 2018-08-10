package com.coffeeshop.controller;

import com.coffeeshop.constant.APIVersion;
import com.coffeeshop.constant.ResponseStatus;
import com.coffeeshop.constant.Route;
import com.coffeeshop.domain.Response;
import com.coffeeshop.domain.product.*;
import com.coffeeshop.exception.ProductNotFoundException;
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
@RequestMapping(value = Route.PRODUCT)
@Api(tags="Product", description = "Endpoint for Product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    MessageService messageService;

    @Autowired
    ProductRequestValidator productRequestValidator;

    @Autowired
    ProductService productService;

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
    public ResponseEntity createProduct(@RequestBody ProductRequestDTO productRequestDTO,
                                        BindingResult errors)
            throws NoSuchMethodException,MethodArgumentNotValidException {

        Response response = new Response(messageService);
        productRequestValidator.validate(productRequestDTO,errors);

        if(errors.hasErrors()){
            LOGGER.debug("Errors in creating product form {}",productRequestDTO);
            throw new MethodArgumentNotValidException(
                    new MethodParameter(
                            this.getClass()
                                    .getDeclaredMethod(
                                            "createProduct",
                                            ProductRequestDTO.class,
                                            BindingResult.class),
                            0),errors);
        }

        Product product = productService.create(productRequestDTO);
        if (product != null){
            response
                    .setStatus(com.coffeeshop.constant.ResponseStatus.SUCCESS)
                    .setMessage("product.create.success")
                    .setData(ProductAssembler.fromProduct(product));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response
                    .setStatus(com.coffeeshop.constant.ResponseStatus.FAIL)
                    .setMessage("product.create.fail");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = Route.PRODUCT_BY_ID,
            headers = APIVersion.V1,
            method = RequestMethod.PUT)
    @ApiOperation(
            value = "UPDATE PRODUCT",
            notes = "Returns updated product",
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server Error")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity updateProduct(
            @PathVariable("id") Long id,
            @RequestBody ProductRequestDTO productRequestDTO,
            BindingResult errors
    ) throws ProductNotFoundException,NoSuchMethodException,MethodArgumentNotValidException{

        Response response = new Response(messageService);
        Product product = productService.findByID(id);

        if (null == product){
            throw new ProductNotFoundException("product..not.found.exception");
        }

        productRequestValidator.validate(productRequestDTO,errors);

        if(errors.hasErrors()){
            LOGGER.debug("Errors in updating product form {}",productRequestDTO);
            throw new MethodArgumentNotValidException(
                    new MethodParameter(
                            this.getClass()
                                    .getDeclaredMethod(
                                            "updateProduct",
                                            ProductRequestDTO.class,
                                            BindingResult.class),
                            0),errors);
        }

        Product updatedProduct = productService.update(productRequestDTO,id);
        if (updatedProduct != null){
            response
                    .setStatus(com.coffeeshop.constant.ResponseStatus.SUCCESS)
                    .setMessage("product.update.success")
                    .setData(ProductAssembler.fromProduct(updatedProduct));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response
                    .setStatus(com.coffeeshop.constant.ResponseStatus.FAIL)
                    .setMessage("product.update.fail");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = Route.PRODUCT_LIST,
            headers = APIVersion.V1,
            method = RequestMethod.GET)

    @ApiOperation(
            value = "GET ACTIVE PRODUCTS",
            notes = "Returns active products from system",
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server Error")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity getActiveProducts(){

        Response response = new Response(messageService);
        List<ProductWebDTO> webDTOList = new ArrayList<>();
        List<Product> allActiveProduct = productService.findAllActive();
        if (allActiveProduct != null && allActiveProduct.size()>0){
            response
                    .setStatus(com.coffeeshop.constant.ResponseStatus.SUCCESS)
                    .setMessage("product.list.success")
                    .setData(ProductAssembler.fromProductList(allActiveProduct));
        }else{
            response
                    .setStatus(ResponseStatus.FAIL)
                    .setMessage("product.list.fail")
                    .setData(webDTOList);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = Route.PRODUCT_BY_ID,
            headers = APIVersion.V1,
            method = RequestMethod.GET)
    @ApiOperation(
            value = "GET PRODUCT BY ID",
            notes = "Return product by ID",
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server Error")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity getProductByID(@PathVariable("id") Long id) throws ProductNotFoundException{

        Response response = new Response(messageService);
        Product product = productService.findByID(id);
        if (product != null){
            response
                    .setStatus(com.coffeeshop.constant.ResponseStatus.SUCCESS)
                    .setMessage("product.found.success")
                    .setData(ProductAssembler.fromProduct(product));
        }else{
            throw new ProductNotFoundException("product.not.found.exception");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
