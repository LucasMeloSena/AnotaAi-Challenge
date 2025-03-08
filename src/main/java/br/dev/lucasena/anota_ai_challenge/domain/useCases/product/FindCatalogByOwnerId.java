package br.dev.lucasena.anota_ai_challenge.domain.useCases.product;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FindCatalogByOwnerId {
    @Value("${aws.bucket-name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public String execute(String ownerId) {
        String fileName = String.format("owner-%s-catalog", ownerId);
        return this.s3Client.getUrl(this.bucketName, fileName).toString();
    }
}
