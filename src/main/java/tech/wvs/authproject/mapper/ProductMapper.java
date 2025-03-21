package tech.wvs.authproject.mapper;


import tech.wvs.authproject.controller.dto.ProductRequest;
import tech.wvs.authproject.controller.dto.ProductResponse;
import tech.wvs.authproject.entity.Product;

public class ProductMapper {
    public static Product toEntity(ProductRequest request) {
        Product prod = new Product();
        prod.setName(request.name());
        prod.setPrice(request.price());
        return prod;
    }

    public static ProductResponse toResponse(Product entity) {
        return new ProductResponse(
                entity.getId(),
                entity.getName(),
                entity.getPrice());
    }
}
