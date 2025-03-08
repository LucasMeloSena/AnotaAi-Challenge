package br.dev.lucasena.anota_ai_challenge.domain.useCases.category;

import br.dev.lucasena.anota_ai_challenge.domain.dtos.CategoryDTO;
import br.dev.lucasena.anota_ai_challenge.domain.exceptions.category.CategoryNotFoundException;
import br.dev.lucasena.anota_ai_challenge.domain.models.Category;
import br.dev.lucasena.anota_ai_challenge.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCategoryUseCase {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category execute(String id, CategoryDTO data) {
        Category category = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

        if (data.title() != null && !data.title().isEmpty()) {
            category.setTitle(data.title());
        }
        if (data.description() != null && !data.description().isEmpty()) {
            category.setDescription(data.description());
        }

        this.categoryRepository.save(category);
        return category;
    }
}
