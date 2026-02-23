// Modified by Gowthami
package com.gowthami.ecommerce.dto;

import com.gowthami.ecommerce.enums.DeliverySpeed;
import com.gowthami.ecommerce.enums.TransportMode;

/*
  Response for GET /api/v1/shipping-charge.
  Returns the total shipping charge and a cost breakdown so the caller
  can see exactly how the final price was calculated.
*/
public class ShippingChargeResponse {

    private double totalShippingCost;
    private double distanceKm;
    private TransportMode transportMode;
    private DeliverySpeed deliverySpeed;
    private ChargeBreakdown breakdown;

    public double getShippingCharge() {
        return totalShippingCost;
    }

    public void setShippingCharge(double totalShippingCost) {
        this.totalShippingCost = totalShippingCost;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public TransportMode getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(TransportMode transportMode) {
        this.transportMode = transportMode;
    }

    public DeliverySpeed getDeliverySpeed() {
        return deliverySpeed;
    }

    public void setDeliverySpeed(DeliverySpeed deliverySpeed) {
        this.deliverySpeed = deliverySpeed;
    }

    public ChargeBreakdown getBreakdown() {
        return breakdown;
    }

    public void setBreakdown(ChargeBreakdown breakdown) {
        this.breakdown = breakdown;
    }

    /*
     * Line-item breakdown of the shipping charge:
     * standardCourierChargeRs → fixed Rs 10 handling fee
     * expressSurchargeRs → Rs 1.2/kg, only for EXPRESS (0 for STANDARD)
     * distanceBasedChargeRs → distance × weight × transport rate
     * totalChargeRs → sum of all three components
     */
    public static class ChargeBreakdown {
        private double standardCourierChargeRs;
        private double expressSurchargeRs;
        private double distanceBasedChargeRs;
        private double totalChargeRs;

        public double getStandardCourierChargeRs() {
            return standardCourierChargeRs;
        }

        public void setStandardCourierChargeRs(double standardCourierChargeRs) {
            this.standardCourierChargeRs = standardCourierChargeRs;
        }

        public double getExpressSurchargeRs() {
            return expressSurchargeRs;
        }

        public void setExpressSurchargeRs(double expressSurchargeRs) {
            this.expressSurchargeRs = expressSurchargeRs;
        }

        public double getDistanceBasedChargeRs() {
            return distanceBasedChargeRs;
        }

        public void setDistanceBasedChargeRs(double distanceBasedChargeRs) {
            this.distanceBasedChargeRs = distanceBasedChargeRs;
        }

        public double getTotalChargeRs() {
            return totalChargeRs;
        }

        public void setTotalChargeRs(double totalChargeRs) {
            this.totalChargeRs = totalChargeRs;
        }
    }
}
