package com.sample.monolith.customerservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CustomerServiceTest {

    private CustomerService customerService = new CustomerService();

    @Test
    public void testFindCustomer() {
        assertTrue(customerService.findCustomer("foo").isPresent());
    }

    @Test
    public void testFindCustomer_notFound() {
        assertFalse(customerService.findCustomer("bla").isPresent());
    }
}