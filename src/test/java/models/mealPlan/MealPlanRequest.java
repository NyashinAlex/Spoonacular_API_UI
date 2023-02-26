package models.mealPlan;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealPlanRequest {

    private int date;
    private int slot;
    private int position;
    private String type;
    private Value value;

}
