package tests;

import static io.restassured.RestAssured.given;

//    UserResponseModel responseModel = given()
//            .spec(userRequestSpec)
//            .body(body)
//            .when()
//            .post()
//            .then()
//            .spec(userResponseSpec)
//            .extract().as(UserResponseModel.class);

//                .filter(withCustomTemplates())
//            .baseUri("https://reqres.in")
//            .basePath("/api/users")
//            .log().all()
//            .contentType(JSON);
//
//            .log(all)
//            .expectStatusCode(201)
//            .build();

//step("Проверяем что авторизовались правильно", () -> {
//        step("Логин `random-user`");
//        });

//given()
//        .when()
//        .get("https://selenoid.autotests.cloud/status")
//        .then()
//        .statusCode(200)
//        .body("total", is(20));

import models.SearchRecipes.SearchRecipesResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static io.qameta.allure.Allure.step;
import static io.restassured.http.ContentType.JSON;

public class RecipesTests {

    int idRecipe;
    String titleRecipe;

    @Test
    @DisplayName("Проверка рандомного рецепта")
    void checkRandomRecipe() {

        step("Получение рандомного рецепта (номер и название)", () -> {
            SearchRecipesResponse searchRecipesResponse = given()
                    .log().all()
                    .when()
                    .header("x-api-key","ab4a8b4cc3bf48c6ad8aedf6e8350394")
                    .get("https://api.spoonacular.com/recipes/complexSearch?number=1&offset=100")
                    .then()
                    .log().body()
                    .statusCode(200)
                    .contentType(JSON)
                    .extract().as(SearchRecipesResponse.class);

            idRecipe = searchRecipesResponse.getResults().get(0).getId();
            titleRecipe = searchRecipesResponse.getResults().get(0).getTitle();
        });

        step("Получение описание рандомного рецепта", () -> {
            ArrayList<String> description = given()
                    .log().all()
                    .when()
                    .header("x-api-key","ab4a8b4cc3bf48c6ad8aedf6e8350394")
                    .get("https://api.spoonacular.com/recipes/informationBulk?ids=" + idRecipe)
                    .then()
                    .log().body()
                    .statusCode(200)
                    .contentType(JSON)
                    .extract().path("summary");

            System.out.println(description.get(0));
            System.out.println(description.get(0).replaceAll("[\\'</b>']", ""));
        });

        step("Проверка наименования + описание рецепта на сайте", () -> {

        });
    }
}