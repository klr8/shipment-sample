package com.sample.monolith.shipment;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.sample.monolith.warehouse.WarehouseService;
import com.sample.monolith.warehouse.PackageInfo;
import com.sample.monolith.customerservice.CustomerService;
import com.sample.monolith.customerservice.CustomerInfo;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ShipmentService {

    private final Logger logger = LoggerFactory.getLogger(ShipmentService.class);

    private final WarehouseService warehouseService;
    private final CustomerService customerService;

    public ShipmentService(WarehouseService warehouseService, CustomerService customerService) {
        this.warehouseService = Objects.requireNonNull(warehouseService);
        this.customerService = Objects.requireNonNull(customerService);
    }
    
    public Shipment shipPackage(String customerId, String packageId) throws ShipmentException {
        Optional<PackageInfo> packageInfo = warehouseService.findPackage(packageId);

        if (!packageInfo.isPresent()) {
            throw new ShipmentException("Cannot find package " + packageId);
        }

        if (!packageInfo.get().isInWarehouse()) {
            throw new ShipmentException("Package " + packageId + " has not yet arrived at the warehouse");
        }

        Optional<CustomerInfo> customerInfo = customerService.findCustomer(customerId);

        if (!customerInfo.isPresent()) {
            throw new ShipmentException("Cannot find customer " + customerId);
        }

        ShipmentType shipmentType = ShipmentType.fromCustomerGrade(customerInfo.get().getGrade());

        String trackingNumber = UUID.randomUUID().toString();

        logger.info("Shipping {} to {} with {} shipping", packageInfo.get().getDescription(), customerInfo.get().getName(), shipmentType.name());

        return new Shipment(trackingNumber, shipmentType, packageInfo.get(), customerInfo.get());
    }
}