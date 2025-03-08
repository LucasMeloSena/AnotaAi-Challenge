package br.dev.lucasena.anota_ai_challenge.repositories;

import br.dev.lucasena.anota_ai_challenge.domain.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
