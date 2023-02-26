package models.products;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class FoodIngredientsMapRequest {

    private ArrayList<String> ingredients;

}
