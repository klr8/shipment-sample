package com.sample.monolith.shipment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShipmentServiceTest {

    @Autowired
    private ShipmentService shipmentService;

    @Test
    public void testShipPackage() {
        Shipment shipment = shipmentService.shipPackage("foo", "bar");
        assertNotNull(shipment.getTrackingNumber());
        assertEquals(shipment.getShipmentType(), ShipmentType.PRIORITY);
    }

    @Test
    public void testShipPackage_customerNotFound() {
        assertThrows(ShipmentException.class, () -> {
            shipmentService.shipPackage("bla", "bar");
        });
    }

    @Test
    public void testShipPackage_packageNotFound() {
        assertThrows(ShipmentException.class, () -> {
            shipmentService.shipPackage("foo", "bla");
        });
    }
}