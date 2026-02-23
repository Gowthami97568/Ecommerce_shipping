// Modified by Gowthami
package com.gowthami.ecommerce.dto;

import com.gowthami.ecommerce.enums.DeliverySpeed;
import jakarta.validation.constraints.NotNull;

/*
  Request body for POST /api/v1/shipping-charge/calculate.
  Contains everything needed to auto-resolve the nearest warehouse
  and compute the full shipping cost.
*/
public class CalculateShippingRequest {

    @NotNull(message = "sellerId is required")
    private Long sellerId;

    @NotNull(message = "productId is required")
    private Long productId;

    @NotNull(message = "customerId is required")
    private Long customerId;

    @NotNull(message = "deliverySpeed is required (STANDARD or EXPRESS)")
    private DeliverySpeed deliverySpeed;

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public DeliverySpeed getDeliverySpeed() {
        return deliverySpeed;
    }

    public void setDeliverySpeed(DeliverySpeed deliverySpeed) {
        this.deliverySpeed = deliverySpeed;
    }
}
