package com.helier.orders.orders_api.initializers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import com.helier.orders.orders_api.model.Product;
import com.helier.orders.orders_api.repository.ProductRepository;

@Profile("dev")
@Component
public class DataInitializer implements CommandLineRunner {
    private final ProductRepository productRepository;

    public DataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            if (productRepository.count() == 0) {
                Product product = new Product();
                product.setName("product1");
                product.setDescription("Product 1");
                product.setPrice(10.0);
                product.setStock(100);
    
                Product product2 = new Product();
                product2.setName("product2");
                product2.setDescription("Product 2");
                product2.setPrice(20.0);
                product2.setStock(200);
    
                productRepository.save(product);
                productRepository.save(product2);
                System.out.println("Database initialized with default PRODUCTS.");
            } else {
                System.out.println("Database already contains PRODUCTS.");
            }
        } catch (DataAccessException e) {
            System.err.println("No se pudo conectar a MongoDB. Se omite la inicialización.");
        } catch (Exception e) {
            System.err.println("Error inesperado en DataInitializer:");
            e.printStackTrace();
        }

    }
}
