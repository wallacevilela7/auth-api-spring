package tech.wvs.authproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.wvs.authproject.controller.dto.ProductRequest;
import tech.wvs.authproject.controller.dto.ProductResponse;
import tech.wvs.authproject.mapper.ProductMapper;
import tech.wvs.authproject.service.ProductService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService productService) {
        this.service = productService;
    }

    //Salvar
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ProductRequest request) {
        var product = service.create(request);

        return ResponseEntity.created(URI.create("/api/products/" + product.getId())).build();
    }


    //Buscar todos
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        var items = service.findAll();

        return !items.isEmpty() ?
                ResponseEntity.ok(items.stream().map(item -> ProductMapper.toResponse(item)).toList()) :
                ResponseEntity.noContent().build();
    }

    //Busca por id
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id){
        var entity = service.findById(id);

        return entity.isPresent() ?
                ResponseEntity.ok(ProductMapper.toResponse(entity.get())) :
                ResponseEntity.notFound().build();
    }

    //Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id,
                                                  @RequestBody ProductRequest request) {
        var entity = service.update(id, request);

        return entity.isPresent() ?
                ResponseEntity.ok(ProductMapper.toResponse(entity.get())) :
                ResponseEntity.notFound().build();
    }

    //Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        var deleted = service.delete(id);

        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
