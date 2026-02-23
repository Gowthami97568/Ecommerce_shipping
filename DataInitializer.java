// Modified by Gowthami
package com.gowthami.ecommerce.config;

import com.gowthami.ecommerce.entity.Customer;
import com.gowthami.ecommerce.entity.Product;
import com.gowthami.ecommerce.entity.Seller;
import com.gowthami.ecommerce.entity.Warehouse;
import com.gowthami.ecommerce.repository.CustomerRepository;
import com.gowthami.ecommerce.repository.ProductRepository;
import com.gowthami.ecommerce.repository.SellerRepository;
import com.gowthami.ecommerce.repository.WarehouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Seeds the database with initial sample data from the problem statement on
 * application startup.
 * This runs only once — if records already exist, seeding is skipped.
 *
 * Seeded data:
 * - 2 Customers (Cust-123 Shree Kirana Store, Cust-124 Andheri Mini Mart)
 * - 3 Sellers (Nestle, Rice Seller, Sugar Seller) with their products
 * - 2 Warehouses (BLR_Warehouse in Bangalore, MUMB_Warehouse in Mumbai)
 */
@Component
public class DataInitializer implements CommandLineRunner {

        private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

        private final CustomerRepository customerRepository;
        private final SellerRepository sellerRepository;
        private final ProductRepository productRepository;
        private final WarehouseRepository warehouseRepository;

        @Autowired
        public DataInitializer(CustomerRepository customerRepository,
                        SellerRepository sellerRepository,
                        ProductRepository productRepository,
                        WarehouseRepository warehouseRepository) {
                this.customerRepository = customerRepository;
                this.sellerRepository = sellerRepository;
                this.productRepository = productRepository;
                this.warehouseRepository = warehouseRepository;
        }

        @Override
        public void run(String... args) {
                log.info("Checking if initial data seeding is required...");
                seedWarehouses();
                seedSellersAndProducts();
                seedCustomers();
                log.info("Data initialization complete.");
        }

        // -------------------------------------------------------
        // Warehouses
        // -------------------------------------------------------

        private void seedWarehouses() {
                if (warehouseRepository.count() > 0) {
                        log.info("Warehouses already exist — skipping warehouse seed.");
                        return;
                }

                log.info("Seeding warehouses...");

                Warehouse blr = new Warehouse();
                blr.setWarehouseCode("BLR_Warehouse");
                blr.setWarehouseName("Bangalore Fulfilment Centre");
                blr.setCity("Bangalore");
                blr.setAddress("Electronics City Phase 1, Bangalore, Karnataka");
                blr.setLatitude(12.99999);
                blr.setLongitude(37.923273);
                blr.setCapacityTons(500.0);
                blr.setContactNumber("080-12345678");
                blr.setIsActive(true);

                Warehouse mumb = new Warehouse();
                mumb.setWarehouseCode("MUMB_Warehouse");
                mumb.setWarehouseName("Mumbai Fulfilment Centre");
                mumb.setCity("Mumbai");
                mumb.setAddress("Bhiwandi Logistics Park, Thane, Maharashtra");
                mumb.setLatitude(11.99999);
                mumb.setLongitude(27.923273);
                mumb.setCapacityTons(750.0);
                mumb.setContactNumber("022-98765432");
                mumb.setIsActive(true);

                warehouseRepository.save(blr);
                warehouseRepository.save(mumb);
                log.info("Seeded 2 warehouses: BLR_Warehouse, MUMB_Warehouse");
        }

        // -------------------------------------------------------
        // Sellers and their Products
        // -------------------------------------------------------

        private void seedSellersAndProducts() {
                if (sellerRepository.count() > 0) {
                        log.info("Sellers already exist — skipping seller/product seed.");
                        return;
                }

                log.info("Seeding sellers and products...");

                // --- Nestle Seller ---
                Seller nestle = new Seller();
                nestle.setSellerName("Nestle Seller");
                nestle.setContactEmail("sales@nestle-seller.in");
                nestle.setPhoneNumber("9887654321");
                nestle.setGstNumber("GST001NESTLE");
                nestle.setAddress("Kondhwa Industrial Area, Pune");
                nestle.setCity("Pune");
                nestle.setState("Maharashtra");
                nestle.setLatitude(18.5204);
                nestle.setLongitude(73.8567);
                nestle.setIsActive(true);
                nestle = sellerRepository.save(nestle);

                Product maggi = new Product();
                maggi.setProductName("Maggie 500g Packet");
                maggi.setDescription("Nestle Maggie 2-Minute Noodles, 500g pack. Best seller in kirana stores.");
                maggi.setCategory("Instant Food");
                maggi.setBrand("Nestle");
                maggi.setSellingPriceRs(new BigDecimal("10.00"));
                maggi.setWeightKg(0.5);
                maggi.setDimLengthCm(10.0);
                maggi.setDimWidthCm(10.0);
                maggi.setDimHeightCm(10.0);
                maggi.setStockQuantity(5000);
                maggi.setIsActive(true);
                maggi.setSeller(nestle);
                productRepository.save(maggi);

                // --- Rice Seller ---
                Seller riceSeller = new Seller();
                riceSeller.setSellerName("Rice Seller");
                riceSeller.setContactEmail("orders@ricebag.in");
                riceSeller.setPhoneNumber("9756341290");
                riceSeller.setGstNumber("GST002RICE");
                riceSeller.setAddress("Nalgonda Road, Hyderabad");
                riceSeller.setCity("Hyderabad");
                riceSeller.setState("Telangana");
                riceSeller.setLatitude(17.3850);
                riceSeller.setLongitude(78.4867);
                riceSeller.setIsActive(true);
                riceSeller = sellerRepository.save(riceSeller);

                Product rice = new Product();
                rice.setProductName("Rice Bag 10kg");
                rice.setDescription("Premium Sona Masuri rice, 10kg bag. Ideal for bulk grocery stocking.");
                rice.setCategory("Staples");
                rice.setBrand("Aachi");
                rice.setSellingPriceRs(new BigDecimal("500.00"));
                rice.setWeightKg(10.0);
                rice.setDimLengthCm(1000.0);
                rice.setDimWidthCm(800.0);
                rice.setDimHeightCm(500.0);
                rice.setStockQuantity(2000);
                rice.setIsActive(true);
                rice.setSeller(riceSeller);
                productRepository.save(rice);

                // --- Sugar Seller ---
                Seller sugarSeller = new Seller();
                sugarSeller.setSellerName("Sugar Seller");
                sugarSeller.setContactEmail("supply@sugarmill.in");
                sugarSeller.setPhoneNumber("9812345670");
                sugarSeller.setGstNumber("GST003SUGAR");
                sugarSeller.setAddress("Belgaum Sugar Factory Road, Belgaum");
                sugarSeller.setCity("Belgaum");
                sugarSeller.setState("Karnataka");
                sugarSeller.setLatitude(15.8497);
                sugarSeller.setLongitude(74.4977);
                sugarSeller.setIsActive(true);
                sugarSeller = sellerRepository.save(sugarSeller);

                Product sugar = new Product();
                sugar.setProductName("Sugar Bag 25kg");
                sugar.setDescription("Refined white sugar, 25kg HDPE bag. Suitable for retail and commercial use.");
                sugar.setCategory("Staples");
                sugar.setBrand("Renuka Sugars");
                sugar.setSellingPriceRs(new BigDecimal("700.00"));
                sugar.setWeightKg(25.0);
                sugar.setDimLengthCm(1000.0);
                sugar.setDimWidthCm(900.0);
                sugar.setDimHeightCm(600.0);
                sugar.setStockQuantity(1500);
                sugar.setIsActive(true);
                sugar.setSeller(sugarSeller);
                productRepository.save(sugar);

                log.info("Seeded 3 sellers and 3 products.");
        }

        // -------------------------------------------------------
        // Customers (Kirana Stores)
        // -------------------------------------------------------

        private void seedCustomers() {
                if (customerRepository.count() > 0) {
                        log.info("Customers already exist — skipping customer seed.");
                        return;
                }

                log.info("Seeding customers...");

                Customer shree = new Customer();
                shree.setCustomerCode("Cust-123");
                shree.setStoreName("Shree Kirana Store");
                shree.setOwnerName("Ramesh Gupta");
                shree.setPhoneNumber("9847000001");
                shree.setEmail("shree.kirana@gmail.com");
                shree.setAddress("MG Road, Block 5, Patna");
                shree.setCity("Patna");
                shree.setState("Bihar");
                shree.setLatitude(11.232);
                shree.setLongitude(23.445495);
                shree.setIsActive(true);

                Customer andheri = new Customer();
                andheri.setCustomerCode("Cust-124");
                andheri.setStoreName("Andheri Mini Mart");
                andheri.setOwnerName("Suresh Nair");
                andheri.setPhoneNumber("9101000002");
                andheri.setEmail("andheri.mart@gmail.com");
                andheri.setAddress("Andheri West, Shop No. 12, Mumbai");
                andheri.setCity("Mumbai");
                andheri.setState("Maharashtra");
                andheri.setLatitude(17.232);
                andheri.setLongitude(33.445495);
                andheri.setIsActive(true);

                customerRepository.save(shree);
                customerRepository.save(andheri);
                log.info("Seeded 2 customers: Cust-123 (Shree Kirana Store), Cust-124 (Andheri Mini Mart)");
        }
}
