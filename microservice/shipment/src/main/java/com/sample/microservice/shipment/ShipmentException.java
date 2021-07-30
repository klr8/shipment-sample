package com.sample.microservice.shipment;

class ShipmentException extends RuntimeException {

    public ShipmentException(String msg) {
        super(msg);
    }
}