package tech.wvs.authproject.service;

import org.springframework.stereotype.Service;
import tech.wvs.authproject.controller.dto.ProductRequest;
import tech.wvs.authproject.entity.Product;
import tech.wvs.authproject.mapper.ProductMapper;
import tech.wvs.authproject.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(ProductRequest request) {
        return repository.save(ProductMapper.toEntity(request));
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Product> update(Long id, ProductRequest request) {
        var entityOpt = repository.findById(id);

        if(entityOpt.isPresent()) {

            var entity = entityOpt.get();

            updateFields(request, entity);
            repository.save(entity);

            return Optional.of(entity);
        }

        return Optional.empty();
    }

    private static void updateFields(ProductRequest request, Product entity) {
        if(request.name() != null && !request.name().isEmpty()) {
            entity.setName(request.name());
        }

        if(request.price() != null) {
            entity.setPrice(request.price());
        }
    }

    public boolean delete(Long id) {
        var exists = repository.existsById(id);

        if(exists) {
            repository.deleteById(id);
        }

        return exists;
    }
}
