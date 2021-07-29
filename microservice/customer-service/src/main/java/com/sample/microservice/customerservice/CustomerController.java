package com.sample.microservice.customerservice;

import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = Objects.requireNonNull(customerService);
    }

    @GetMapping("/customer/{customerId}")
    public CustomerInfoDto findCustomer(@PathVariable String customerId) {
        CustomerInfo customerInfo = customerService.findCustomer(customerId).get();
        CustomerInfoDto dto = new CustomerInfoDto();
        dto.customerId = customerInfo.getCustomerId();
        dto.name = customerInfo.getName();
        dto.address = customerInfo.getAddress();
        dto.grade = customerInfo.getGrade();
        return dto;
    }

    public static class CustomerInfoDto {

        public String customerId;
        public String name;
        public String address;
        public int grade;
    }
}