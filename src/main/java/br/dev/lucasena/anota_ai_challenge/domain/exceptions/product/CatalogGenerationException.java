package br.dev.lucasena.anota_ai_challenge.domain.exceptions.product;

public class CatalogGenerationException extends RuntimeException {
    public CatalogGenerationException(String message) {
        super(message);
    }
}
