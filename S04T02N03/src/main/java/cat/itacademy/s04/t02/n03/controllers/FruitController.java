package cat.itacademy.s04.t02.n03.controllers;

import cat.itacademy.s04.t02.n03.model.Fruit;
import cat.itacademy.s04.t02.n03.services.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fruit")
public class FruitController {

    @Autowired
    private FruitService fruitService;


    @PostMapping("/add")
    public ResponseEntity<?> addFruit(@RequestBody Fruit fruit){
        try{
            Fruit newFruit = fruitService.add(fruit);
            return new ResponseEntity<>(newFruit, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFruit (@RequestBody Fruit newFruit, @PathVariable (value = "id")int id ){
        Optional<Fruit> fruitDetails = fruitService.getOne(id);

        if(fruitDetails.isPresent()){
            Fruit updatedFruit = fruitDetails.get();
            updatedFruit.setName(newFruit.getName());
            updatedFruit.setQuantityKilos(newFruit.getQuantityKilos());
            return new ResponseEntity<>(fruitService.update(updatedFruit), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFruit(@PathVariable (value="id")int id){
        try{
            fruitService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Fruit> getOneFruit(@PathVariable(value = "id") int id){
        Optional<Fruit> oFruit = fruitService.getOne(id);

        if(oFruit.isPresent()){
            return new ResponseEntity<>(oFruit.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Fruit>> getAllFruits(){
        try{
            List<Fruit> listFruits = fruitService.getAll();
            return new ResponseEntity<>(listFruits, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

