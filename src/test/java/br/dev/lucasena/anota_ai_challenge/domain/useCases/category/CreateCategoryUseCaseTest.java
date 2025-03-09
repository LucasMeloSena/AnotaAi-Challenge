package br.dev.lucasena.anota_ai_challenge.domain.useCases.category;

import br.dev.lucasena.anota_ai_challenge.domain.dtos.CategoryDTO;
import br.dev.lucasena.anota_ai_challenge.domain.models.Category;
import br.dev.lucasena.anota_ai_challenge.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateCategoryUseCaseTest {
    @Mock
    private CategoryRepository categoryRepository;

    @Autowired
    @InjectMocks
    private CreateCategoryUseCase createCategoryUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should create category successfully")
    public void createCategoryTest() {
        CategoryDTO categoryDTO = new CategoryDTO("title", "description", "1234");
        Category expectedResult = new Category(categoryDTO);

        when(categoryRepository.save(any(Category.class))).thenReturn(expectedResult);
        createCategoryUseCase.execute(categoryDTO);

        verify(categoryRepository, times(1)).save(any(Category.class));
    }
}
