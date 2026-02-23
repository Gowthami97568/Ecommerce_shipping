// Modified by Gowthami
package com.gowthami.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
  Represents a marketplace warehouse / fulfilment centre.
  Sellers drop products here; the marketplace then ships to the customer.
  Only active warehouses are considered when finding the nearest drop-off point.
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique identifier e.g. "BLR_Warehouse"
    @Column(name = "warehouse_code", unique = true, nullable = false, length = 50)
    private String warehouseCode;

    @Column(name = "warehouse_name", nullable = false, length = 100)
    private String warehouseName;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "capacity_tons")
    private Double capacityTons;

    @Column(name = "contact_number", length = 15)
    private String contactNumber;

    // Inactive warehouses are excluded from nearest-warehouse lookups
    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}
