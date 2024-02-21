package com.example.demo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Meals")
public class Meal {
	@Id
	public String id;
	public String name;
	public List<String> ingredients;
	public String recipe;
	
	
	public Meal(String name, List<String> ingredients, String recipe) {
		super();
		this.name = name;
		this.ingredients = ingredients;
		this.recipe = recipe;
	}
	
	public Meal() {
		// TODO Auto-generated constructor stub
	}
	
	public List<String> getIngredients() {
		return ingredients;
	}
	
	public String getName() {
		return name;
	}
	
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Meal [id=" + id + ", name=" + name + ", ingredients=" + ingredients + "]";
	}
	
	
}

