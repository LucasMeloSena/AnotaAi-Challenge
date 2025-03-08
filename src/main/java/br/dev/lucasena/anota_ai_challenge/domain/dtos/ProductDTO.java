package br.dev.lucasena.anota_ai_challenge.domain.dtos;

import br.dev.lucasena.anota_ai_challenge.domain.models.Category;

public record ProductDTO(String title, String description, String ownerId, Integer price, String categoryId) {
}
