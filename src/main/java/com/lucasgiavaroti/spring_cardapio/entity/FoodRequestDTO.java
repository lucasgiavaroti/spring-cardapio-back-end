package com.lucasgiavaroti.spring_cardapio.entity;

import lombok.Getter;

public record FoodRequestDTO(String title, String image, Double price) {
}
