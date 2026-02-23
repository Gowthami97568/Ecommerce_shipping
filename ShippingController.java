// Modified by Gowthami
package com.gowthami.ecommerce.controller;

import com.gowthami.ecommerce.dto.CalculateShippingRequest;
import com.gowthami.ecommerce.dto.CalculateShippingResponse;
import com.gowthami.ecommerce.dto.ShippingChargeResponse;
import com.gowthami.ecommerce.enums.DeliverySpeed;
import com.gowthami.ecommerce.exception.InvalidInputException;
import com.gowthami.ecommerce.service.ShippingCalculatorService;
import com.gowthami.ecommerce.service.ShippingChargeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for shipping charge operations.
 * Exposes two endpoints:
 * 1. GET - Calculate shipping charge from a known warehouse to a customer.
 * 2. POST - Full end-to-end calculation (warehouse auto-resolved from seller).
 */
@RestController
@RequestMapping("/api/v1/shipping-charge")
public class ShippingController {

    private final ShippingChargeService totalShippingCostService;
    private final ShippingCalculatorService shippingCalculatorService;

    @Autowired
    public ShippingController(ShippingChargeService totalShippingCostService,
            ShippingCalculatorService shippingCalculatorService) {
        this.totalShippingCostService = totalShippingCostService;
        this.shippingCalculatorService = shippingCalculatorService;
    }

    /**
     * Get the shipping charge from a specific warehouse to a customer.
     *
     * @param warehouseId   ID of the source warehouse
     * @param customerId    ID of the destination customer
     * @param productId     ID of the product (needed for weight-based pricing)
     * @param deliverySpeed STANDARD or EXPRESS (case-insensitive)
     * @return shipping charge with full breakdown
     *
     *         Example: GET
     *         /api/v1/shipping-charge?warehouseId=1&customerId=1&productId=1&deliverySpeed=STANDARD
     */
    @GetMapping
    public ResponseEntity<ShippingChargeResponse> getShippingCharge(
            @RequestParam Long warehouseId,
            @RequestParam Long customerId,
            @RequestParam Long productId,
            @RequestParam String deliverySpeed) {

        DeliverySpeed speed = parseDeliverySpeed(deliverySpeed);
        ShippingChargeResponse response = totalShippingCostService.computeShippingCostCharge(
                warehouseId, customerId, productId, speed);
        return ResponseEntity.ok(response);
    }

    /**
     * Calculate the complete shipping charge for a seller-to-customer shipment.
     *
     * Automatically finds the nearest warehouse to the seller, then calculates
     * the charge from that warehouse to the customer.
     *
     * @param request contains sellerId, productId, customerId, deliverySpeed
     * @return combined response with nearest warehouse and shipping charge details
     *
     *         Example: POST /api/v1/shipping-charge/calculate
     *         Body: { "sellerId": 1, "productId": 1, "customerId": 1,
     *         "deliverySpeed": "EXPRESS" }
     */
    @PostMapping("/calculate")
    public ResponseEntity<CalculateShippingResponse> computeShippingCostCharge(
            @Valid @RequestBody CalculateShippingRequest request) {

        CalculateShippingResponse response = shippingCalculatorService.calculate(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Parse the deliverySpeed string from query param into the enum value.
     * Accepts case-insensitive input (e.g., "standard", "STANDARD", "Standard").
     */
    private DeliverySpeed parseDeliverySpeed(String deliverySpeed) {
        try {
            return DeliverySpeed.valueOf(deliverySpeed.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(
                    "Invalid deliverySpeed: '" + deliverySpeed + "'. Accepted values: STANDARD, EXPRESS");
        }
    }
}
