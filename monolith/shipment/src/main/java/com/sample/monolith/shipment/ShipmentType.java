package com.sample.monolith.shipment;

public enum ShipmentType {

    STANDARD,
    PRIORITY;

    public static ShipmentType fromCustomerGrade(int grade) {
        if (grade >= 10) {
            return PRIORITY;
        }
        return STANDARD;
    }
}