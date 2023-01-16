package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Feature;
import models.ConnectUser.ConnectUserRequest;
import models.ConnectUser.ConnectUserResponse;
import models.MealPlan.Ingredient;
import models.MealPlan.MealPlanRequest;
import models.MealPlan.MealPlanResponse;
import models.MealPlan.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static specs.MealPlanSpec.mealPlanRequestSpec;
import static specs.MealPlanSpec.mealPlanResponseSpec;

public class MealPlanTests {

    Faker faker = new Faker();

    ConnectUserRequest connectUserRequest = new ConnectUserRequest();
    ConnectUserResponse connectUserResponse = new ConnectUserResponse();
    MealPlanRequest mealPlanRequest = new MealPlanRequest();
    Ingredient ingredient = new Ingredient();
    Value value = new Value();
    MealPlanResponse mealPlanResponse = new MealPlanResponse();

    String xApiKey;

    @BeforeEach
    void getApiKey() {
        xApiKey = System.getProperty("x-api-key", "ab4a8b4cc3bf48c6ad8aedf6e8350394");
    }


    @Test
    @Feature("Планирование питания")
    @DisplayName("Удаление существующего плана питания")
    void errorCheckWrongRecipe() {

        step("Создание нового пользователя", () -> {
            connectUserRequest.setUsername(faker.name().username());
            connectUserRequest.setFirstName(faker.name().firstName());
            connectUserRequest.setLastName(faker.name().lastName());
            connectUserRequest.setEmail(faker.internet().emailAddress());

            connectUserResponse = given()
                    .spec(mealPlanRequestSpec)
                    .body(connectUserRequest)
                    .header("x-api-key", xApiKey)
                    .post("/users/connect")
                    .then()
                    .spec(mealPlanResponseSpec)
                    .extract().as(ConnectUserResponse.class);
        });

        step("Создание нового плана питания для тестового пользователя", () -> {
            mealPlanRequest.setDate(faker.number().randomDigitNotZero());
            mealPlanRequest.setSlot(1);
            mealPlanRequest.setPosition(0);
            mealPlanRequest.setType("INGREDIENTS");

            ingredient.setName("1 banana");
            ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
            ingredientList.add(ingredient);
            value.setIngredients(ingredientList);
            mealPlanRequest.setValue(value);

            mealPlanResponse = given()
                    .spec(mealPlanRequestSpec)
                    .body(mealPlanRequest)
                    .header("x-api-key", xApiKey)
                    .post("/mealplanner/" + connectUserResponse.getUsername() + "/items?hash=" + connectUserResponse.getHash())
                    .then()
                    .spec(mealPlanResponseSpec)
                    .extract().as(MealPlanResponse.class);
        });

        step("Удаление плана питания для тестового пользователя", () -> {

            String status = given()
                    .spec(mealPlanRequestSpec)
                    .when()
                    .header("x-api-key", xApiKey)
                    .delete("/mealplanner/"+ connectUserResponse.getUsername() + "/items/"+ mealPlanResponse.getId()+ "?hash="  + connectUserResponse.getHash())
                    .then()
                    .spec(mealPlanResponseSpec)
                    .extract().path("status");

            assertThat(status).isEqualTo("success");
        });
    }
}