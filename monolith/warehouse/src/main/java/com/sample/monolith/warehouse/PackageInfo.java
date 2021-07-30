package com.sample.monolith.warehouse;

import java.util.Objects;

public class PackageInfo {

    private final String packageId;
    private final String description;
    private final boolean inWarehouse;

    public PackageInfo(String packageId, String description, boolean inWarehouse) {
        this.packageId = Objects.requireNonNull(packageId);
        this.description = Objects.requireNonNull(description);
        this.inWarehouse = inWarehouse;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getDescription() {
        return description;
    }

    public boolean isInWarehouse() {
        return inWarehouse;
    }

    public String toString() {
        return packageId;
    }
}