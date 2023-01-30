package models.products;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Nutrition {

    private ArrayList<Nutrient> nutrients;
    private CaloricBreakdown caloricBreakdown;
    private double calories;
    private String fat;
    private String protein;
    private String carbs;

}