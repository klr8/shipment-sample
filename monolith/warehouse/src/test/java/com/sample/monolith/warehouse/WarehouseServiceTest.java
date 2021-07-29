package com.sample.monolith.warehouse;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class WarehouseServiceTest {

    private WarehouseService warehouseService = new WarehouseService();

    @Test
    public void testFindPackage() {
        assertTrue(warehouseService.findPackage("bar").isPresent());
    }

    @Test
    public void testFindPackage_notFound() {
        assertFalse(warehouseService.findPackage("bla").isPresent());
    }
}