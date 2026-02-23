
import com.gowthami.ecommerce.dto.NearestWarehouseResponse;
import com.gowthami.ecommerce.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for warehouse-related operations.
 * Exposes the endpoint that helps sellers identify their nearest drop-off
 * warehouse.
 */
@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    /**
     * Get the nearest active warehouse for a given seller and product.
     *
     * The seller uses this information to know where to physically drop off
     * their product after a customer places an order.
     *
     * @param sellerId  the ID of the seller
     * @param productId the ID of the product being shipped
     * @return the nearest warehouse with location and distance details
     *
     *         Example: GET /api/v1/warehouse/nearest?sellerId=1&productId=1
     */
    @GetMapping("/nearest")
    public ResponseEntity<NearestWarehouseResponse> getNearestWarehouse(
            @RequestParam Long sellerId,
            @RequestParam Long productId) {

        NearestWarehouseResponse response = warehouseService.findNearestWarehouse(sellerId, productId);
        return ResponseEntity.ok(response);
    }
}
