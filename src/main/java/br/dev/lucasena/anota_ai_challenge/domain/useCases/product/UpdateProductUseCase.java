package br.dev.lucasena.anota_ai_challenge.domain.useCases.product;

import br.dev.lucasena.anota_ai_challenge.domain.dtos.AwsMessageDTO;
import br.dev.lucasena.anota_ai_challenge.domain.dtos.ProductDTO;
import br.dev.lucasena.anota_ai_challenge.domain.exceptions.category.ProductNotFoundException;
import br.dev.lucasena.anota_ai_challenge.domain.models.Product;
import br.dev.lucasena.anota_ai_challenge.domain.useCases.aws.AwsMessagingUseCase;
import br.dev.lucasena.anota_ai_challenge.domain.useCases.category.FindCategoryById;
import br.dev.lucasena.anota_ai_challenge.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductUseCase {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FindCategoryById findCategoryById;
    @Autowired
    private AwsMessagingUseCase snsUseCase;

    public Product execute(String id, ProductDTO data) {
        Product product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        if (data.categoryId() != null) {
            this.findCategoryById.execute(data.categoryId()).ifPresent(product::setCategory);
        }

        if (product.getTitle() != null && !product.getTitle().isEmpty()) {
            product.setTitle(data.title());
        }
        if (!(product.getPrice() == null)) {
            product.setPrice(data.price());
        }
        if (product.getDescription() != null && !product.getDescription().isEmpty()) {
            product.setDescription(data.description());
        }

        this.productRepository.save(product);
        this.snsUseCase.publish(new AwsMessageDTO(product.toString()));

        return product;
    }

}
