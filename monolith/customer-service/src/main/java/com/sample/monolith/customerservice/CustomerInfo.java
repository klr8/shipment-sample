package com.sample.monolith.customerservice;

import java.util.Objects;

public class CustomerInfo {

    private final String customerId;
    private final String name;
    private final String address;
    private final int grade;

    public CustomerInfo(String customerId, String name, String address, int grade) {
        this.customerId = Objects.requireNonNull(customerId);
        this.name = Objects.requireNonNull(name);
        this.address = Objects.requireNonNull(address);
        this.grade = grade;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getGrade() {
        return grade;
    }

    public String toString() {
        return name;
    }
}