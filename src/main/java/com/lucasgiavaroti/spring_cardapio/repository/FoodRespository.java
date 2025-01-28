package com.lucasgiavaroti.spring_cardapio.repository;

import com.lucasgiavaroti.spring_cardapio.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRespository extends JpaRepository <Food, Long> {
    List<Food> id(Long id);
}
