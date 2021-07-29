package com.sample.microservice.shipment;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ShipmentService {

    private final Logger logger = LoggerFactory.getLogger(ShipmentService.class);

    private final RestTemplate restTemplate;

    public ShipmentService(RestTemplate restTemplate) {
        this.restTemplate = Objects.requireNonNull(restTemplate);
    }

    public Shipment shipPackage(String customerId, String packageId) throws ShipmentException {
        Optional<PackageInfoDto> packageInfo = findPackage(packageId);

        if (!packageInfo.isPresent()) {
            throw new ShipmentException("Cannot find package " + packageId);
        }

        if (!packageInfo.get().inWarehouse) {
            throw new ShipmentException("Package " + packageId + " has not yet arrived at the warehouse");
        }

        Optional<CustomerInfoDto> customerInfo = findCustomer(customerId);

        if (!customerInfo.isPresent()) {
            throw new ShipmentException("Cannot find customer " + customerId);
        }

        ShipmentType shipmentType = ShipmentType.fromCustomerGrade(customerInfo.get().grade);

        String trackingNumber = UUID.randomUUID().toString();

        logger.info("Shipping {} to {} with {} shipping", packageInfo.get().description, customerInfo.get().name, shipmentType.name());

        return new Shipment(trackingNumber, shipmentType, packageInfo.get(), customerInfo.get());
    }

    private Optional<PackageInfoDto> findPackage(String packageId) {
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8081/package/{packageId}", PackageInfoDto.class, packageId));
    }

    private Optional<CustomerInfoDto> findCustomer(String customerId) {
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8082/customer/{customerId}", CustomerInfoDto.class, customerId));
    }
}