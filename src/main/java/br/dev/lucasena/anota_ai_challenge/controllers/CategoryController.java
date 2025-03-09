package br.dev.lucasena.anota_ai_challenge.controllers;

import br.dev.lucasena.anota_ai_challenge.domain.dtos.CategoryDTO;
import br.dev.lucasena.anota_ai_challenge.domain.models.Category;
import br.dev.lucasena.anota_ai_challenge.domain.useCases.category.CreateCategoryUseCase;
import br.dev.lucasena.anota_ai_challenge.domain.useCases.category.DeleteCategoryUseCase;
import br.dev.lucasena.anota_ai_challenge.domain.useCases.category.FindAllCategoriesUseCase;
import br.dev.lucasena.anota_ai_challenge.domain.useCases.category.UpdateCategoryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@Tag(name = "Categorias", description = "Controller para interação com categorias")
public class CategoryController {
    @Autowired
    private CreateCategoryUseCase createCategoryUseCase;
    @Autowired
    private FindAllCategoriesUseCase findAllCategoriesUseCase;
    @Autowired
    private UpdateCategoryUseCase updateCategoryUseCase;
    @Autowired
    private DeleteCategoryUseCase deleteCategoryUseCase;

    @GetMapping
    @Operation(summary = "Lista todos as categorias")
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = this.findAllCategoriesUseCase.execute();
        return ResponseEntity.ok().body(categories);
    }

    @PostMapping
    @Operation(summary = "Cria uma categoria")
    public ResponseEntity<Category> create(@RequestBody CategoryDTO payload) {
        Category category = this.createCategoryUseCase.execute(payload);
        return ResponseEntity.ok().body(category);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma categoria")
    public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody CategoryDTO payload) {
        Category category = this.updateCategoryUseCase.execute(id, payload);
        return ResponseEntity.ok().body(category);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui uma categoria")
    public ResponseEntity<Category> delete(@PathVariable("id") String id) {
        this.deleteCategoryUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
