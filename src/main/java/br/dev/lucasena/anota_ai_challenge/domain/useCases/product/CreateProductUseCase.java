package br.dev.lucasena.anota_ai_challenge.domain.useCases.product;

import br.dev.lucasena.anota_ai_challenge.domain.dtos.AwsMessageDTO;
import br.dev.lucasena.anota_ai_challenge.domain.dtos.ProductDTO;
import br.dev.lucasena.anota_ai_challenge.domain.exceptions.category.CategoryNotFoundException;
import br.dev.lucasena.anota_ai_challenge.domain.models.Category;
import br.dev.lucasena.anota_ai_challenge.domain.models.Product;
import br.dev.lucasena.anota_ai_challenge.domain.useCases.aws.AwsMessagingUseCase;
import br.dev.lucasena.anota_ai_challenge.domain.useCases.category.FindCategoryById;
import br.dev.lucasena.anota_ai_challenge.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProductUseCase {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FindCategoryById findCategoryById;
    @Autowired
    private AwsMessagingUseCase snsUseCase;

    public Product execute(ProductDTO productDto) {
        Category category = this.findCategoryById.execute(productDto.categoryId()).orElseThrow(CategoryNotFoundException::new);
        Product product = new Product(productDto);
        product.setCategory(category);

        this.productRepository.save(product);
        this.snsUseCase.publish(new AwsMessageDTO(product.toString()));

        return product;
    }
}
