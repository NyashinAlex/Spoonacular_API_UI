package models.computeGlycemicLoad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ingredient {
    private int id;
    private String original;
    private double glycemicIndex;
    private double glycemicLoad;

}
