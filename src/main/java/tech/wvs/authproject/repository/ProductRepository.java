package tech.wvs.authproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.wvs.authproject.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
