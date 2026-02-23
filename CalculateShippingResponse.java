// Modified by Gowthami
package com.gowthami.ecommerce.dto;

/*
  Response for POST /api/v1/shipping-charge/calculate.
  Combines the nearest warehouse result and the full shipping charge
  into a single response so the caller gets everything in one call.
*/
public class CalculateShippingResponse {

    private double totalShippingCost;
    private NearestWarehouseResponse nearestWarehouse;
    private ShippingChargeResponse shippingDetails;

    public double getShippingCharge() {
        return totalShippingCost;
    }

    public void setShippingCharge(double totalShippingCost) {
        this.totalShippingCost = totalShippingCost;
    }

    public NearestWarehouseResponse getNearestWarehouse() {
        return nearestWarehouse;
    }

    public void setNearestWarehouse(NearestWarehouseResponse nearestWarehouse) {
        this.nearestWarehouse = nearestWarehouse;
    }

    public ShippingChargeResponse getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(ShippingChargeResponse shippingDetails) {
        this.shippingDetails = shippingDetails;
    }
}
