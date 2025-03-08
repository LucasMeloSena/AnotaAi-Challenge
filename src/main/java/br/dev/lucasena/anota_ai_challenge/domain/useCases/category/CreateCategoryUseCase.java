package br.dev.lucasena.anota_ai_challenge.domain.useCases.category;

import br.dev.lucasena.anota_ai_challenge.domain.dtos.CategoryDTO;
import br.dev.lucasena.anota_ai_challenge.domain.models.Category;
import br.dev.lucasena.anota_ai_challenge.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCategoryUseCase {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category execute(CategoryDTO categoryDto) {
        Category category = new Category(categoryDto);
        categoryRepository.save(category);
        return category;
    }
}
