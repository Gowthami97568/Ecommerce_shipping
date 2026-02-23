// Modified by Gowthami
package com.gowthami.ecommerce.dto;

/*
  Response for GET /api/v1/warehouse/nearest.
  Returns the closest active warehouse to the seller's location,
  along with the straight-line distance calculated via Haversine formula.
*/
public class NearestWarehouseResponse {

    private Long warehouseId;
    private String warehouseCode;
    private String warehouseName;
    private String city;
    private LocationDto warehouseLocation;
    private double distanceFromSellerKm;

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocationDto getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(LocationDto warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    public double getDistanceFromSellerKm() {
        return distanceFromSellerKm;
    }

    public void setDistanceFromSellerKm(double distanceFromSellerKm) {
        this.distanceFromSellerKm = distanceFromSellerKm;
    }

    // Geographic coordinates of the warehouse
    public static class LocationDto {
        private double lat;
        private double lng;

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }
}
