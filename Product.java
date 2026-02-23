// Modified by Gowthami
package com.gowthami.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
  Represents a product listed by a seller on the marketplace.
  Weight and dimensions are used directly in the shipping charge formula.
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false, length = 150)
    private String productName;

    @Column(name = "description", length = 500)
    private String description;

    // e.g. "Instant Food", "Staples", "Beverages"
    @Column(name = "category", length = 100)
    private String category;

    @Column(name = "selling_price_rs", nullable = false, precision = 10, scale = 2)
    private BigDecimal sellingPriceRs;

    // Weight in kg — used directly in shipping cost calculation
    @Column(name = "weight_kg", nullable = false)
    private Double weightKg;

    @Column(name = "dim_length_cm", nullable = false)
    private Double dimLengthCm;

    @Column(name = "dim_width_cm", nullable = false)
    private Double dimWidthCm;

    @Column(name = "dim_height_cm", nullable = false)
    private Double dimHeightCm;

    @Builder.Default
    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity = 0;

    @Column(name = "brand", length = 100)
    private String brand;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    // Many products can belong to one seller
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Volume in cubic cm — useful for future volumetric pricing
    public double getVolumetricCm3() {
        return dimLengthCm * dimWidthCm * dimHeightCm;
    }
}
