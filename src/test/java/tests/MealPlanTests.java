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
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MealPlanTests {

    Faker faker = new Faker();

    ConnectUserRequest connectUserRequest = new ConnectUserRequest();
    ConnectUserResponse connectUserResponse = new ConnectUserResponse();
    MealPlanRequest mealPlanRequest = new MealPlanRequest();
    Ingredient ingredient = new Ingredient();
    Value value = new Value();
    MealPlanResponse mealPlanResponse = new MealPlanResponse();

    @BeforeEach
    void generateData() {

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
                    .log().all()
                    .when()
                    .body(connectUserRequest)
                    .contentType(JSON)
                    .header("x-api-key", "ab4a8b4cc3bf48c6ad8aedf6e8350394")
                    .post("https://api.spoonacular.com/users/connect")
                    .then()
                    .log().body()
                    .statusCode(200)
                    .contentType(JSON)
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
                    .log().all()
                    .when()
                    .body(mealPlanRequest)
                    .contentType(JSON)
                    .header("x-api-key", "ab4a8b4cc3bf48c6ad8aedf6e8350394")
                    .post("https://api.spoonacular.com/mealplanner/" + connectUserResponse.getUsername() + "/items?hash=" + connectUserResponse.getHash())
                    .then()
                    .log().body()
                    .statusCode(200)
                    .contentType(JSON)
                    .extract().as(MealPlanResponse.class);
        });

        step("Удаление плана питания для тестового пользователя", () -> {

            String status = given()
                    .log().all()
                    .when()
                    .header("x-api-key", "ab4a8b4cc3bf48c6ad8aedf6e8350394")
                    .delete("https://api.spoonacular.com/mealplanner/"+ connectUserResponse.getUsername() + "/items/"+ mealPlanResponse.getId()+ "?hash="  + connectUserResponse.getHash())
                    .then()
                    .log().body()
                    .statusCode(200)
                    .contentType(JSON)
                    .extract().path("status");

            assertThat(status).isEqualTo("success");
        });
    }
}