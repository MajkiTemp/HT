package tcom.group.shopping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tcom.group.shopping.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}

