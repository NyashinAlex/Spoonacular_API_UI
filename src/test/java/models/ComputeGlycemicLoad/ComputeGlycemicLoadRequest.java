package models.ComputeGlycemicLoad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

//@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComputeGlycemicLoadRequest {

    private ArrayList<String> ingredients;

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
}
