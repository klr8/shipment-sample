package com.sample.microservice.warehouse;

import java.util.Optional;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class WarehouseService {

    private final Logger logger = LoggerFactory.getLogger(WarehouseService.class);

    public Optional<PackageInfo> findPackage(String packageId) {
        logger.info("Looking up package {}", packageId);

        if (!"bar".equals(packageId)) {
            return Optional.empty();
        }

        return Optional.of(new PackageInfo("bar", "Muts", true));
    }
}