package tcom.group.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcom.group.shopping.model.Product;
import tcom.group.shopping.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.insert(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);    }


    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}

