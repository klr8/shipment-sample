package com.sample.monolith.customerservice;

import java.util.Optional;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomerService {

    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public Optional<CustomerInfo> findCustomer(String customerId) {
        logger.info("Looking up customer {}", customerId);

        if (!"foo".equals(customerId)) {
            return Optional.empty();
        }
        
        return Optional.of(new CustomerInfo(
            "foo",
            "Paulus De Boskabouter",
            "Bosstraat 12, 4001 Meerdaalwoud",
            17));
    }
}