package helpers;

import java.util.Random;

public class RandomData {

    public String randomCuisine() {
        Random rand = new Random();
        int min = 0;
        int max = 26;

        String[] cuisine = {"African", "American", "British", "Cajun", "Caribbean",
                "Chinese", "Eastern European", "European", "French", "German", "Greek",
                "Indian", "Irish", "Italian", "Japanese", "Jewish", "Korean", "Latin American",
                "Mediterranean", "Mexican", "Middle Eastern", "Nordic", "Southern", "Spanish",
                "Thai", "Vietnamese"};

        return cuisine[rand.nextInt((max - min) + 1) + min];
    }

    public String randomDiet() {
        Random rand = new Random();
        int min = 0;
        int max = 11;

        String[] diet = {"Gluten Free", "Ketogenic", "Vegetarian", "Lacto-Vegetarian", "Ovo-Vegetarian",
                "Vegan", "Pescetarian", "Paleo", "Primal", "Low FODMAP", "Whole30"};

        return diet[rand.nextInt((max - min) + 1) + min];
    }
}