package tests;

import io.qameta.allure.Feature;
import models.computeGlycemicLoad.ComputeGlycemicLoadRequest;
import models.computeGlycemicLoad.ComputeGlycemicLoadResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static specs.Spec.requestSpec;
import static specs.Spec.responseSpec;

public class IngredientsTests {

    String xApiKey;

    @BeforeEach
    void getApiKey() {
        xApiKey = System.getProperty("x-api-key", "ab4a8b4cc3bf48c6ad8aedf6e8350394");
    }

    @Test
    @Feature("Ингредиенты")
    @DisplayName("Вычисление гликемической нагрузки")
    void errorCheckWrongRecipe() {

        ComputeGlycemicLoadRequest body = new ComputeGlycemicLoadRequest();

        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("1 kiwi");
        ingredients.add("2 cups rice");
        ingredients.add("2 glasses of water");

        body.setIngredients(ingredients);

        step("Отправляем запрос с 3 продуктами на проверку гликемической нагрузки", () -> {
            ComputeGlycemicLoadResponse computeGlycemicLoadResponse = given()
                    .spec(requestSpec)
                    .body(body)
                    .header("x-api-key", xApiKey)
                    .post("/food/ingredients/glycemicLoad")
                    .then()
                    .spec(responseSpec)
                    .extract().as(ComputeGlycemicLoadResponse.class);

            step("Проверяем, что запрос успешен и отображается общая сумма гликемической нагрузки", () -> {
                assertThat(computeGlycemicLoadResponse.getTotalGlycemicLoad()).isNotZero();
                assertThat(computeGlycemicLoadResponse.getStatus()).isEqualTo("success");
            });

            step("Проверяем, что сумма гликамической нагрузка рассчитана верно", () -> {
                assertThat(String.valueOf(computeGlycemicLoadResponse.getIngredients().get(0).getGlycemicLoad()
                        + computeGlycemicLoadResponse.getIngredients().get(1).getGlycemicLoad()
                        + computeGlycemicLoadResponse.getIngredients().get(2).getGlycemicLoad()).substring(0,6)).isEqualTo(String.valueOf(computeGlycemicLoadResponse.getTotalGlycemicLoad()));
            });
        });
    }
}
