package com.coffeeshop.domain.user.customer;

/**
 * @author Chandan Vishwakarma
 */
public class CustomerAssember {

    public static Customer fromRequestDTO(CustomerRequestDTO customerRequestDTO){
        final Customer customer = new Customer();
        if(null != customerRequestDTO.getId()){
            customer.setId(customerRequestDTO.getId());
        }
        customer.setName(customerRequestDTO.getName());
        customer.setMobile(customerRequestDTO.getMobile());
        customer.setLocation(customerRequestDTO.getLocation());
        return customer;
    }

    public static CustomerWebDTO fromCustomer(Customer customer){
        final CustomerWebDTO customerWebDTO = new CustomerWebDTO();
        customerWebDTO.setId(customer.getId());
        customerWebDTO.setLocation(customer.getLocation());
        customerWebDTO.setName(customer.getName());
        customerWebDTO.setMobile(customer.getMobile());
        return customerWebDTO;
    }
}
