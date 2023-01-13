package models.ComputeGlycemicLoad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ingredient {
    private int id;
    private String original;
    private double glycemicIndex;
    private double glycemicLoad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public double getGlycemicIndex() {
        return glycemicIndex;
    }

    public void setGlycemicIndex(double glycemicIndex) {
        this.glycemicIndex = glycemicIndex;
    }

    public double getGlycemicLoad() {
        return glycemicLoad;
    }

    public void setGlycemicLoad(double glycemicLoad) {
        this.glycemicLoad = glycemicLoad;
    }
}
