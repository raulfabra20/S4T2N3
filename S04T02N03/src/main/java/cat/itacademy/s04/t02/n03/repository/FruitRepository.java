package cat.itacademy.s04.t02.n03.repository;

import cat.itacademy.s04.t02.n03.model.Fruit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitRepository extends MongoRepository<Fruit, Integer> {
}
