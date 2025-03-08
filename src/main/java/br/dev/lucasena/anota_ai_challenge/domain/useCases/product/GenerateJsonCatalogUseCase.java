package br.dev.lucasena.anota_ai_challenge.domain.useCases.product;

import br.dev.lucasena.anota_ai_challenge.domain.models.Catalog;
import br.dev.lucasena.anota_ai_challenge.domain.models.Product;
import br.dev.lucasena.anota_ai_challenge.repositories.ProductRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class GenerateJsonCatalogUseCase {
    @Value("${aws.bucket-name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;
    @Autowired
    private ProductRepository productRepository;

    public void execute(String ownerId) {
        List<Product> products = this.productRepository.findByOwnerId(ownerId);
        Catalog catalog = new Catalog(ownerId, products);
        String jsonCatalog = new Gson().toJson(catalog);

        byte[] contentAsBytes = jsonCatalog.getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(contentAsBytes);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(contentAsBytes.length);
        metadata.setContentType("application/json");

        String fileName = String.format("owner-%s-catalog", ownerId);
        s3Client.putObject(bucketName, fileName, inputStream, metadata);
    }
}
