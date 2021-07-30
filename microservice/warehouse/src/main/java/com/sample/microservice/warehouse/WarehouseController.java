package com.sample.microservice.warehouse;

import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = Objects.requireNonNull(warehouseService);
    }

    @GetMapping("/package/{packageId}")
    public PackageInfoDto findPackage(@PathVariable String packageId) {
        PackageInfo packageInfo = warehouseService.findPackage(packageId).get();
        PackageInfoDto dto = new PackageInfoDto();
        dto.packageId = packageInfo.getPackageId();
        dto.description = packageInfo.getDescription();
        dto.inWarehouse = packageInfo.isInWarehouse();
        return dto;
    }

    public static class PackageInfoDto {

        public String packageId;
        public String description;
        public boolean inWarehouse;
    }
}