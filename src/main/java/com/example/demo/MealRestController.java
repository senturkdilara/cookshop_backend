package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

@RestController
public class MealRestController {
	@Autowired private MealRepository mealRepository;
	@Autowired private userRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(MealRestController.class);
	
	@PostConstruct
	public void init () {
		if(userRepository.count()==0) {
			logger.info("Database is empty, initializing...");
			
			userMaster user1 = new userMaster("dilara","123");
			userMaster user2 = new userMaster("suat","123");
			userMaster user3 = new userMaster("aylin","1234");
			
			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);
			
			logger.info("Users saved");
		}
		if(mealRepository.count()==0) {
			logger.info("Database is empty, initializing...");
			List<String> ingredients1 = new ArrayList<>();
			ingredients1.add("egg");
			ingredients1.add("salt");
			ingredients1.add("butter");
			String recipe1 = "First, beat the eggs.Next, heat the pan.Then, pour in the egg mixture."
					+ "Use a spatula to work around the edges of the omelette, dragging the cooked egg into the center of the pan."
					+ "Fold to close and cook until the eggs are just set.";
			Meal meal1 = new Meal("Omelette", ingredients1, recipe1);
			
			List<String> ingredients2 = new ArrayList<>();
			ingredients2.add("flour");
			ingredients2.add("egg");
			ingredients2.add("butter");
			ingredients2.add("sugar");
			ingredients2.add("milk");
			ingredients2.add("vanilla");
			ingredients2.add("baking powder");

			
			String recipe2 = "Preheat the oven to 350 degrees F (175 degrees C). Grease and flour a 9-inch square cake pan."
					+ "Sugar and butter together in a mixing bowl. Add eggs, one at a time, beating briefly after each addition. Mix in vanilla."
					+ "Combine flour and baking powder in a separate bowl. Add to the wet ingredients and mix well. Add milk and stir until smooth."
					+ "Pour batter into the prepared cake pan.Bake in the preheated oven until the top springs back when lightly touched, 30 to 40 minutes."
					+ "Remove from the oven and cool completely.Enjoy.";
			
			Meal meal2 = new Meal("Cake", ingredients2, recipe2);
			
			List<String> ingredients3 = new ArrayList<>();
			ingredients3.add("rice");
			ingredients3.add("onion");
			ingredients3.add("parmesan");
			ingredients3.add("salt");
			ingredients3.add("white wine");
			ingredients3.add("broth");
			ingredients3.add("butter");
			
			String recipe3 = "Heat the stock.In a heavy pot or dutch oven over medium-low heat,add butter and oil,"
					+ "and sautee onions with salt, stirring with a spatula until softened (do not brown the onions).Add garlic"
					+ "and stir for 30 seconds.Add rice, increase to medium heat, and stir for about 3 minutes until the rice is"
					+ "toasted and starting to look translucent.Stir in the white wine and continue stirring for 2 minutes or until "
					+ "mostly evaporated.Add stock. Continue this process until the rice reaches your desired doneness. This takes "
					+ "20-25 minutes.Add the remaining butter and parmesan cheese.";
			
			Meal meal3 = new Meal("Risotto", ingredients3, recipe3);
			
			List<String> ingredients4 = new ArrayList<>();
			ingredients4.add("pasta");
			ingredients4.add("tomato");
			ingredients4.add("cheese");
			ingredients4.add("arugula");
			ingredients4.add("chickpeas");
			ingredients4.add("cucumber");
			ingredients4.add("parsley");
			ingredients4.add("olive oil");
			ingredients4.add("salt");
			
			String recipe4 = "Cook your pasta in a large pot of salted boiling water."
					+ "Drain it, toss it with some olive oil, and set it aside to cool.Chop your"
					+ "cherry tomatoes and cucumbers, mince the parsley.Whisk together the dressing "
					+ "ingredients.And toss everything together in a big bowl! Season to taste and dig in.";

			Meal meal4 = new Meal("Pasta Salad", ingredients4, recipe4);
			
			
			List<String> ingredients5 = new ArrayList<>();
			ingredients5.add("chicken breasts");
			ingredients5.add("flour");
			ingredients5.add("garlic");
			ingredients5.add("salt");
			ingredients5.add("butter");
			ingredients5.add("white wine");
			ingredients5.add("chicken stock");
			ingredients5.add("heavy cream");
			ingredients5.add("black pepper");

			String recipe5 = "Slice each chicken breast in half horizontally. In a shallow bowl, combine flour, pepper, and 1/2 teaspoon salt."
					+ "Toss chicken in flour mixture until well coated.In a large skillet over medium-high heat, heat butter until it melts. Shake off "
					+ "excess flour from chicken, then arrange half of chicken in pan in an even layer. Fry chicken, turning halfway through, until lightly"
					+ "golden brown and cooked through, 2 to 3 minutes per side. Transfer to a plate. Repeat with remaining chicken.Reduce heat to medium."
					+ "Cook garlic, stirring often, until softened and golden brown, about 2 minutes. Deglaze pan with wine, scraping up any stuck-on bits."
					+ "Pour in stock and cream; season with remaining 1 teaspoon salt. Simmer, stirring occasionally, until sauce is thickened, 8 to 10 minutes."
					+ "Stir in Parmesan and nestle chicken back into sauce. Simmer until chicken is heated through, 2 to 3 minutes.";

			Meal meal5 = new Meal("Creamy Garlic Chicken", ingredients5, recipe5);
			
			mealRepository.save(meal1);
			mealRepository.save(meal2);
			mealRepository.save(meal3);
			mealRepository.save(meal4);
			mealRepository.save(meal5);

			logger.info("Meals saved");
		}
	}
	
	@GetMapping("/getMeals")
	public List<Meal> meals(){
		return mealRepository.findAll();
	}
	
	@GetMapping("/mealsByIngredients")
	public List<Meal> getMealsByIngredients(@RequestParam("ingredients") List<String> ingredients) {
	    // Find meals that contain all the specified ingredients
	    return mealRepository.findAll()
	            .stream()
	            .filter(meal -> meal.getIngredients().containsAll(ingredients))
	            .collect(Collectors.toList());
	}
	
	@PostMapping("/signup")
	 public ResponseEntity<String> signUpUser(@RequestBody userMaster usernow) {
        // Check if the user already exists
        List<userMaster> existingUsers = userRepository.findAll()
        		.stream()
        		.filter(userMaster -> userMaster.getName().contains(usernow.getName()))
        		.collect(Collectors.toList());
        if (existingUsers.size() != 0) {
            // User already exists, return an error response
            return new ResponseEntity<>("User with this username already exists", HttpStatus.BAD_REQUEST);
        } else {
            // Set other user details as needed
            userRepository.save(usernow);
            return new ResponseEntity<>("User signed up successfully", HttpStatus.CREATED);
        }
    }
	@PostMapping("/signin")
	 public ResponseEntity<String> signInUser(@RequestBody userMaster usernow) {
       List<userMaster> existingUsers = userRepository.findAll()
       		.stream()
       		.filter(userMaster -> userMaster.getName().contains(usernow.getName()))
       		.collect(Collectors.toList());
       
       List<userMaster> passwordList = userRepository.findAll()
          		.stream()
          		.filter(userMaster -> userMaster.GetPassword().contains(usernow.GetPassword()))
          		.collect(Collectors.toList());
       
       if (existingUsers.size() != 0 && passwordList.size() != 0 ) {
           // User already exists, return an error response
    	   if(usernow.GetPassword().equals(passwordList.get(0).password))
    	   {	
    		 return new ResponseEntity<>("Succsesfull Login", HttpStatus.CREATED);
    	   }
    	   else
    	   {
    		   return new ResponseEntity<>("Incorrect password", HttpStatus.BAD_REQUEST);
    	   }
           
       } else {
    	   if(passwordList.size() != 0) {
    		   return new ResponseEntity<>("Username doesn't exist", HttpStatus.BAD_REQUEST);
    	   }
    	   else {
    		   return new ResponseEntity<>("Incorrect password", HttpStatus.BAD_REQUEST);
    	   }
           
       }
   }
}
	