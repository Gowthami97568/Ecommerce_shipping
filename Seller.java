// Modified by Gowthami
package com.gowthami.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
  Represents a product seller on the marketplace.
  Sellers are located across India. When an order is placed, the seller drops
  the product at the nearest warehouse to their location.
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seller_name", nullable = false, length = 100)
    private String sellerName;

    @Column(name = "contact_email", length = 100)
    private String contactEmail;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "gst_number", unique = true, length = 20)
    private String gstNumber;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "state", length = 100)
    private String state;

    // Seller's physical location — used to compute nearest warehouse
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
