package models.ComputeGlycemicLoad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComputeGlycemicLoadResponse {

    private String status;
    private double totalGlycemicLoad;
    private ArrayList<Ingredient> ingredients;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalGlycemicLoad() {
        return totalGlycemicLoad;
    }

    public void setTotalGlycemicLoad(double totalGlycemicLoad) {
        this.totalGlycemicLoad = totalGlycemicLoad;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}