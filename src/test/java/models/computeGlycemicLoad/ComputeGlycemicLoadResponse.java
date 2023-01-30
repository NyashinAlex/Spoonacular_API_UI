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

}