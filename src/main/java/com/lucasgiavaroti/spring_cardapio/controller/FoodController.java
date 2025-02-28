package com.lucasgiavaroti.spring_cardapio.controller;

import com.lucasgiavaroti.spring_cardapio.entity.Food;
import com.lucasgiavaroti.spring_cardapio.entity.FoodRequestDTO;
import com.lucasgiavaroti.spring_cardapio.entity.FoodResponseDTO;
import com.lucasgiavaroti.spring_cardapio.repository.FoodRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/food")
public class FoodController {

    @Autowired
    private FoodRespository repository;
    @Autowired
    private FoodRespository foodRespository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FoodResponseDTO> getAll(){
         List<FoodResponseDTO> foodList = repository.findAll().stream().map(FoodResponseDTO::new).collect(Collectors.toList());
        return foodList;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public String saveFood(@RequestBody FoodRequestDTO data) {

        Food foodData = new Food(data);

        repository.save(foodData);
        return "Pedido criado com sucesso!";
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public String deleteFood(@PathVariable long id) {
        repository.deleteById(id);
        return "Pedido excluido com sucesso!";
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFood(@PathVariable long id, @RequestBody FoodRequestDTO data) {

        //Verificar se o pedido com ID passado existe
        Optional<Food> optionalFood = repository.findById(id);

         if(optionalFood.isPresent()){
             //Traz os dados e atualiza eles
             Food foodDataUpdate = optionalFood.get();
             foodDataUpdate.setTitle(data.title());
             foodDataUpdate.setPrice(data.price());
             foodDataUpdate.setImage(data.image());
             repository.save(foodDataUpdate);
             return ResponseEntity.ok("Pedido atualizado com sucesso!");
         }else {
             return ResponseEntity.status(404).body("Pedido não encontrado com o ID: " + id);
         }
    }

}
