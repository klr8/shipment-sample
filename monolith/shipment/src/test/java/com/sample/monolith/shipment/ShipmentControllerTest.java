package com.sample.monolith.shipment;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sample.monolith.warehouse.WarehouseService;
import com.sample.monolith.warehouse.PackageInfo;
import com.sample.monolith.customerservice.CustomerService;
import com.sample.monolith.customerservice.CustomerInfo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ShipmentControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private WarehouseService warehouseService;
    
    @Test
    public void testShip() {
        CustomerInfo customerInfo = customerService.findCustomer("foo").get();
        PackageInfo packageInfo = warehouseService.findPackage("bar").get();

        ShipmentController.ShipmentRequestDto req = new ShipmentController.ShipmentRequestDto();
        req.customerId = "foo";
        req.packageId = "bar";
        ShipmentController.ShipmentTrackerDto resp =
            restTemplate.postForObject("http://localhost:" + port + "/ship", req, ShipmentController.ShipmentTrackerDto.class);
        
        assertNotNull(resp.trackingNumber);
        assertEquals(resp.packageDescription, packageInfo.getDescription());
        assertEquals(resp.customerName, customerInfo.getName());
    }
}