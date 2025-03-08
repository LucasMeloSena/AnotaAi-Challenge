package br.dev.lucasena.anota_ai_challenge.domain.useCases.category;

import br.dev.lucasena.anota_ai_challenge.domain.exceptions.category.CategoryNotFoundException;
import br.dev.lucasena.anota_ai_challenge.domain.models.Category;
import br.dev.lucasena.anota_ai_challenge.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCategoryUseCase {
    @Autowired
    private CategoryRepository categoryRepository;

    public void execute(String id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        this.categoryRepository.delete(category);
    }
}
