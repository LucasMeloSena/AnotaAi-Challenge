package br.dev.lucasena.anota_ai_challenge.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Catalog {
    private String ownerId;
    private List<Product> catalog;

    public Catalog(String ownerId, List<Product> products) {
        this.ownerId = ownerId;
        this.catalog = products;
    }
}
