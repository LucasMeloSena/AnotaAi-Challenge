package br.dev.lucasena.anota_ai_challenge.controllers;

import br.dev.lucasena.anota_ai_challenge.domain.dtos.ProductDTO;
import br.dev.lucasena.anota_ai_challenge.domain.models.Product;
import br.dev.lucasena.anota_ai_challenge.domain.useCases.product.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@Tag(name = "Produtos", description = "Controller para interação com produtos")
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
    @Operation(summary = "Lista todos os produtos")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = this.findAllProductsUseCase.execute();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/catalog/{ownerId}")
    @Operation(summary = "Retorna o json de um catálogo de produtos dado um owner")
    public ResponseEntity<String> findOwnerCatalog(@PathVariable("ownerId") String ownerId) {
        String catalogUrl = this.findCatalogByOwnerId.execute(ownerId);
        return ResponseEntity.ok().body(catalogUrl);
    }

    @PostMapping
    @Operation(summary = "Cria um produto")
    public ResponseEntity<Product> create(@RequestBody ProductDTO payload) {
        Product product = this.createProductUseCase.execute(payload);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um produto")
    public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDTO payload) {
        Product product = this.updateProductUseCase.execute(id, payload);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um produto")
    public ResponseEntity<Product> delete(@PathVariable ("id") String id) {
        this.deleteProductUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
