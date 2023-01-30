package models.products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Nutrient {

    private String name;
    private double amount;
    private String unit;
    private double percentOfDailyNeeds;

}
