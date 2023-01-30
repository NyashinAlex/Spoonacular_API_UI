package models.computeGlycemicLoad;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ComputeGlycemicLoadResponse {

    public String status;
    public double totalGlycemicLoad;
    public ArrayList<Ingredient> ingredients;

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