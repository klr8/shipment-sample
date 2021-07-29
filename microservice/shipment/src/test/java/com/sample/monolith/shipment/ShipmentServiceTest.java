package com.sample.microservice.shipment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.web.client.RestTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
public class ShipmentServiceTest {

    @Test
    public void testShipPackage(@Mock RestTemplate restTemplate) {
        CustomerInfoDto customerInfo = new CustomerInfoDto();
        customerInfo.grade = 12;
        PackageInfoDto packageInfo = new PackageInfoDto();
        packageInfo.inWarehouse = true;

        ShipmentService shipmentService = new ShipmentService(restTemplate);
        when(restTemplate.getForObject("http://localhost:8082/customer/{customerId}", CustomerInfoDto.class, "foo")).thenReturn(customerInfo);
        when(restTemplate.getForObject("http://localhost:8081/package/{packageId}", PackageInfoDto.class, "bar")).thenReturn(packageInfo);

        Shipment shipment = shipmentService.shipPackage("foo", "bar");
        assertNotNull(shipment.getTrackingNumber());
        assertEquals(shipment.getShipmentType(), ShipmentType.PRIORITY);
    }

    @Test
    public void testShipPackage_customerNotFound(@Mock RestTemplate restTemplate) {
        PackageInfoDto packageInfo = new PackageInfoDto();
        packageInfo.inWarehouse = true;

        ShipmentService shipmentService = new ShipmentService(restTemplate);
        when(restTemplate.getForObject("http://localhost:8081/package/{packageId}", PackageInfoDto.class, "bar")).thenReturn(packageInfo);
        when(restTemplate.getForObject("http://localhost:8082/customer/{customerId}", CustomerInfoDto.class, "bla")).thenReturn(null);

        assertThrows(ShipmentException.class, () -> {
            shipmentService.shipPackage("bla", "bar");
        });
    }

    @Test
    public void testShipPackage_packageNotFound(@Mock RestTemplate restTemplate) {
        ShipmentService shipmentService = new ShipmentService(restTemplate);
        when(restTemplate.getForObject("http://localhost:8081/package/{packageId}", PackageInfoDto.class, "bla")).thenReturn(null);

        assertThrows(ShipmentException.class, () -> {
            shipmentService.shipPackage("foo", "bla");
        });
    }
}