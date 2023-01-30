package models.products;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class FoodIngredientsMapResponse {

    private String original;
    private String originalName;
    private String ingredientImage;
    private ArrayList<String> meta;
    private ArrayList<Product> products;

}