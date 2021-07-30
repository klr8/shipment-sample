package com.sample.microservice.shipment;

import java.util.Objects;

public class Shipment {

    private final String trackingNumber;
    private final ShipmentType shipmentType;
    private final PackageInfoDto packageInfo;
    private final CustomerInfoDto customerInfo;

    public Shipment(String trackingNumber, ShipmentType shipmentType, PackageInfoDto packageInfo, CustomerInfoDto customerInfo) {
        this.trackingNumber = Objects.requireNonNull(trackingNumber);
        this.shipmentType = Objects.requireNonNull(shipmentType);
        this.packageInfo = Objects.requireNonNull(packageInfo);
        this.customerInfo = Objects.requireNonNull(customerInfo);
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public ShipmentType getShipmentType() {
        return shipmentType;
    }

    public PackageInfoDto getPackageInfo() {
        return packageInfo;
    }

    public CustomerInfoDto getCustomerInfo() {
        return customerInfo;
    }

    public String toString() {
        return trackingNumber;
    }
}