package br.dev.lucasena.anota_ai_challenge.domain.useCases.category;

import br.dev.lucasena.anota_ai_challenge.domain.models.Category;
import br.dev.lucasena.anota_ai_challenge.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllCategoriesUseCase {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> execute() {
        return this.categoryRepository.findAll();
    }
}
