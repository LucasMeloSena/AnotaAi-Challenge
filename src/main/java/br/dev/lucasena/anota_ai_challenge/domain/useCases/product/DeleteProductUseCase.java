package br.dev.lucasena.anota_ai_challenge.domain.useCases.product;

import br.dev.lucasena.anota_ai_challenge.domain.dtos.AwsMessageDTO;
import br.dev.lucasena.anota_ai_challenge.domain.exceptions.category.ProductNotFoundException;
import br.dev.lucasena.anota_ai_challenge.domain.models.Product;
import br.dev.lucasena.anota_ai_challenge.domain.useCases.aws.AwsMessagingUseCase;
import br.dev.lucasena.anota_ai_challenge.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductUseCase {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AwsMessagingUseCase snsUseCase;

    public void execute(String id) {
        Product product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.snsUseCase.publish(new AwsMessageDTO(product.toString()));
        this.productRepository.delete(product);
    }
}
