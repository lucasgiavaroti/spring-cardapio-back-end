package com.lucasgiavaroti.spring_cardapio.controller;

import com.lucasgiavaroti.spring_cardapio.entity.Food;
import com.lucasgiavaroti.spring_cardapio.entity.FoodRequestDTO;
import com.lucasgiavaroti.spring_cardapio.entity.FoodResponseDTO;
import com.lucasgiavaroti.spring_cardapio.repository.FoodRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodRespository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data) {
        Food foodData = new Food(data);
        repository.save(foodData);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FoodResponseDTO> getAll(){
         List<FoodResponseDTO> foodList = repository.findAll().stream().map(FoodResponseDTO::new).collect(Collectors.toList());
        return foodList;
    }
}
