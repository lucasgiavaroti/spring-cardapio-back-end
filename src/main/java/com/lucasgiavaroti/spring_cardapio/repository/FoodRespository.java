package com.lucasgiavaroti.spring_cardapio.repository;

import com.lucasgiavaroti.spring_cardapio.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRespository extends JpaRepository <Food, Long> {
}
