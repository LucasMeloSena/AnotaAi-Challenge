package br.dev.lucasena.anota_ai_challenge.domain.models;

import br.dev.lucasena.anota_ai_challenge.domain.dtos.ProductDTO;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("product")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;
    private Integer price;
    private Category category;

    public Product(ProductDTO productDto) {
        this.title = productDto.title();
        this.description = productDto.description();
        this.ownerId = productDto.ownerId();
        this.price = productDto.price();
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
