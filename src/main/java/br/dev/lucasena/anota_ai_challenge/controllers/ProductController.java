package br.dev.lucasena.anota_ai_challenge.controllers;

import br.dev.lucasena.anota_ai_challenge.domain.dtos.CategoryDTO;
import br.dev.lucasena.anota_ai_challenge.domain.dtos.ProductDTO;
import br.dev.lucasena.anota_ai_challenge.domain.models.Category;
import br.dev.lucasena.anota_ai_challenge.domain.models.Product;
import br.dev.lucasena.anota_ai_challenge.domain.useCases.category.CreateCategoryUseCase;
import br.dev.lucasena.anota_ai_challenge.domain.useCases.category.DeleteCategoryUseCase;
import br.dev.lucasena.anota_ai_challenge.domain.useCases.category.FindAllCategoriesUseCase;
import br.dev.lucasena.anota_ai_challenge.domain.useCases.category.UpdateCategoryUseCase;
import br.dev.lucasena.anota_ai_challenge.domain.useCases.product.*;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private CreateProductUseCase createProductUseCase;
    @Autowired
    private FindAllProductsUseCase findAllProductsUseCase;
    @Autowired
    private UpdateProductUseCase updateProductUseCase;
    @Autowired
    private DeleteProductUseCase deleteProductUseCase;
    @Autowired
    private FindCatalogByOwnerId findCatalogByOwnerId;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = this.findAllProductsUseCase.execute();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/catalog/{ownerId}")
    public ResponseEntity<String> findOwnerCatalog(@PathVariable("ownerId") String ownerId) {
        String catalogUrl = this.findCatalogByOwnerId.execute(ownerId);
        return ResponseEntity.ok().body(catalogUrl);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO payload) {
        Product product = this.createProductUseCase.execute(payload);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDTO payload) {
        Product product = this.updateProductUseCase.execute(id, payload);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable ("id") String id) {
        this.deleteProductUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
