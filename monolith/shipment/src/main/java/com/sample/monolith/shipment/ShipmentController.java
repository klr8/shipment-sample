package com.sample.monolith.shipment;

import java.util.Objects;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = Objects.requireNonNull(shipmentService);
    }

    @PostMapping("/ship")
    public ShipmentTrackerDto shipPackage(@RequestBody ShipmentRequestDto req) {
        Shipment shipment = shipmentService.shipPackage(req.customerId, req.packageId);
        ShipmentTrackerDto tracker = new ShipmentTrackerDto();
        tracker.trackingNumber = shipment.getTrackingNumber();
        tracker.packageDescription = shipment.getPackageInfo().getDescription();
        tracker.customerName = shipment.getCustomerInfo().getName();
        return tracker;
    }

    public static class ShipmentRequestDto {

        public String packageId;
        public String customerId;
    }

    public static class ShipmentTrackerDto {

        public String trackingNumber;
        public String packageDescription;
        public String customerName;
    }
}