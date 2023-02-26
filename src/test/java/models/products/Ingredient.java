package models.products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ingredient {

    private String name;
    private String safety_level;
    private String description;

}