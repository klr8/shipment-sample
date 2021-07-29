package com.sample.monolith.shipment;

import java.util.Objects;

import com.sample.monolith.warehouse.PackageInfo;
import com.sample.monolith.customerservice.CustomerInfo;

public class Shipment {

    private final String trackingNumber;
    private final ShipmentType shipmentType;
    private final PackageInfo packageInfo;
    private final CustomerInfo customerInfo;

    public Shipment(String trackingNumber, ShipmentType shipmentType, PackageInfo packageInfo, CustomerInfo customerInfo) {
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

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public String toString() {
        return trackingNumber;
    }
}