package models.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductsResponse {

    private int id;
    private String title;
    private double price;
    private int likes;
    private ArrayList<String> badges;
    private ArrayList<Object> importantBadges;
    private Nutrition nutrition;
    private Servings servings;
    private double spoonacularScore;
    private ArrayList<String> breadcrumbs;
    private String aisle;
    private String description;
    private String image;
    private String imageType;
    private ArrayList<String> images;
    private String generatedText;
    private String upc;
    private String brand;
    private ArrayList<Ingredient> ingredients;
    private int ingredientCount;
    private String ingredientList;

}
