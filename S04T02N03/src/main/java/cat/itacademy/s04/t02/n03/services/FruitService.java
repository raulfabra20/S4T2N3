package cat.itacademy.s04.t02.n03.services;

import cat.itacademy.s04.t02.n03.model.Fruit;
import cat.itacademy.s04.t02.n03.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FruitService {

    @Autowired
    private FruitRepository fruitRepository;

    public List<Fruit> getAll(){
        return fruitRepository.findAll();
    }

    public Optional<Fruit> getOne(int id) {
        return fruitRepository.findById(id);
    }

    public Fruit update(Fruit fruit) {
        return fruitRepository.save(fruit);
    }

    public Fruit add(Fruit fruit) {
        return fruitRepository.save(fruit);
    }

    public void delete(int id) {
        fruitRepository.deleteById(id);
    }

    public Fruit getUpdatedFruit(Optional<Fruit> opFruit, Fruit newFruit) throws NoSuchElementException {
        if(opFruit.isPresent()) {
            Fruit updatedFruit = opFruit.get();
            updatedFruit.setName(newFruit.getName());
            updatedFruit.setQuantityKilos(newFruit.getQuantityKilos());
            return updatedFruit;
        } else {
            throw new NoSuchElementException("Fruit not found");
        }
    }
}
