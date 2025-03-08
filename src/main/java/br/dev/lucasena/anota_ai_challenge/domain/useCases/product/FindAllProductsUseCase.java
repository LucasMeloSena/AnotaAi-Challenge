package br.dev.lucasena.anota_ai_challenge.domain.useCases.product;

import br.dev.lucasena.anota_ai_challenge.domain.models.Product;
import br.dev.lucasena.anota_ai_challenge.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllProductsUseCase {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> execute() {
        return this.productRepository.findAll();
    }
}
