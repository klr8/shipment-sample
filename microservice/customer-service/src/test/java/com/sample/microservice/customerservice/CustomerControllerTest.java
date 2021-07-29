package com.sample.microservice.customerservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

    @Autowired
    private CustomerService customerService;

    @Test
    public void testShip() {
        CustomerInfo customerInfo = customerService.findCustomer("foo").get();

        CustomerController.CustomerInfoDto resp =
            restTemplate.getForObject("http://localhost:" + port + "/customer/{customerId}", CustomerController.CustomerInfoDto.class, "foo");
        
        assertEquals(resp.customerId, customerInfo.getCustomerId());
        assertEquals(resp.name, customerInfo.getName());
        assertEquals(resp.address, customerInfo.getAddress());
        assertEquals(resp.grade, customerInfo.getGrade());
    }
}