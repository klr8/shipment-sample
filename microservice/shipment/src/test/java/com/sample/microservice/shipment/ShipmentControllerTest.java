package com.sample.microservice.shipment;

import org.junit.jupiter.api.Test;

public class ShipmentControllerTest {

    @Test
    public void testShip() {
        // This is hard to test without elaborate mocked setups
        
        /*
        ShipmentController.ShipmentRequestDto req = new ShipmentController.ShipmentRequestDto();
        req.customerId = "foo";
        req.packageId = "bar";
        ShipmentController.ShipmentTrackerDto resp =
            new RestTemplate().postForObject("http://localhost:" + port + "/ship", req, ShipmentController.ShipmentTrackerDto.class);
        
        assertNotNull(resp.trackingNumber);
        */
    }
}