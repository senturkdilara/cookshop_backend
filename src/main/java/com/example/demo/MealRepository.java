package com.example.demo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MealRepository extends MongoRepository<Meal, String>{

	List<Meal> getMealsByIngredients(List<String> ingredients);

}


