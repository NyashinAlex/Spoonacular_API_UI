package models.products;

public class Ingredient {

    private String name;
    private String safety_level;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSafety_level() {
        return safety_level;
    }

    public void setSafety_level(String safety_level) {
        this.safety_level = safety_level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
