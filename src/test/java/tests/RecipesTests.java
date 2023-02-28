package tests;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;

import com.github.javafaker.Faker;
import helpers.RandomData;
import io.qameta.allure.Feature;
import models.getRecipeInformation.GetRecipeInformationResponseError;
import models.searchRecipes.SearchRecipesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static specs.Spec.*;

public class RecipesTests extends BaseTests {

    Faker faker = new Faker();
    RandomData randomData = new RandomData();

    int idRecipe, idRecipeError = 333333333, totalResults;
    String titleRecipe, descriptionRecipe, xApiKey;
    ArrayList<String> allRecipe;

    @BeforeEach
    void getApiKey() {
        xApiKey = System.getProperty("x-api-key", "ab4a8b4cc3bf48c6ad8aedf6e8350394");
    }

    @Test
    @Feature("Поиск рецептов")
    @DisplayName("Проверка рандомного рецепта")
    void checkRandomRecipe() {

        step("Получение рандомного рецепта (номер={idRecipe} и название={titleRecipe})", () -> {
            SearchRecipesResponse searchRecipesResponse = given()
                    .spec(requestSpec)
                    .header("x-api-key", xApiKey)
                    .get("/recipes/complexSearch?number=1&offset=" + faker.number().numberBetween(1, 5000))
                    .then()
                    .spec(responseSpec)
                    .extract().as(SearchRecipesResponse.class);

            idRecipe = searchRecipesResponse.getResults().get(0).getId();
            titleRecipe = searchRecipesResponse.getResults().get(0).getTitle();
        });

        step("Получение описание рандомного рецепта", () -> {
            ArrayList<String> description = given()
                    .spec(requestSpec)
                    .header("x-api-key", xApiKey)
                    .get("/recipes/informationBulk?ids=" + idRecipe)
                    .then()
                    .spec(responseSpec)
                    .extract().path("summary");

            descriptionRecipe = description.get(0).replaceAll("[\\'</b>']", "");
        });

        step("Проверка наименования + описание рецепта на сайте", () -> {
            open("/recipes/-" + idRecipe);

            $("[itemprop=name]").shouldBe(text(titleRecipe));
//            $("[itemprop=description]").shouldBe(text(descriptionRecipe));
        });
    }

    @Test
    @Feature("Поиск рецептов")
    @DisplayName("Ошибка при поиске рецепта - несуществующий id рецепта")
    void errorCheckWrongRecipe() {

        step("Проверяем, что id рецепта = {idRecipeError} действительно не существует", () -> {
            GetRecipeInformationResponseError getRecipeInformationResponseError = given()
                    .spec(requestSpec)
                    .header("x-api-key", xApiKey)
                    .get("/recipes/" + idRecipeError + "/information")
                    .then()
                    .spec(responseError404Spec)
                    .extract().as(GetRecipeInformationResponseError.class);
        });

        step("Поиск рецепта по несуществующему id рецепта", () -> {
            open("/recipes/-" + idRecipeError);

            $(".small-12.medium-12.large-12.column")
                    .shouldBe(text("Error"))
                    .shouldBe(text("This recipe does not exist. If you think this is our mistake, please let us know!"));
        });
    }

    @Test
    @Feature("Поиск рецептов")
    @DisplayName("Поиск всех рецептов блюда по наименованию (query)")
    void searchAllRecipe() {

        step("Поиск всех рецептов блюда", () -> {
            allRecipe = given()
                    .spec(requestSpec)
                    .header("x-api-key", xApiKey)
                    .get("/recipes/autocomplete?number=25&query=burger")
                    .then()
                    .spec(responseSpec)
                    .extract().path("title");
        });

        step("Проверка наличия всех рецептов на сайте", () -> {
            open("/recipes");
            $(".input__field.input__field--makiko").click();
            $(".input__field.input__field--makiko").setValue("burger").pressEnter();

            for (String recipe : allRecipe) {
                $$(".ss360-n-section.ss360-group.ss360-group-recipes.ss360-group--active .ss360-n-section.ss360-suggests__header")
                        .findBy(text(recipe));
            }
        });
    }

    @Test
    @Feature("Поиск рецептов")
    @DisplayName("Неуспешный поиск всех рецептов блюда по наименованию (query)")
    void unsuccessfulSearchAllRecipe() {

        step("Неуспешный поиск всех рецептов блюда по наименованию (query)", () -> {
            totalResults = given()
                    .spec(requestSpec)
                    .header("x-api-key", xApiKey)
                    .get("/recipes/complexSearch?query=Notpasta")
                    .then()
                    .spec(responseSpec)
                    .extract().path("totalResults");
        });

        step("Проверка наличия всех рецептов на сайте", () -> {
            assertThat(totalResults).isEqualTo(0);
        });

        step("", () -> {
            open();
        });
    }

    @Test
    @Feature("Поиск рецептов")
    @DisplayName("Поиск всех рецептов блюда по стране или континенту")
    void searchAllRecipeByCuisine() {

        String randomCuisine = randomData.randomCuisine();

        step("Поиск всех рецептов блюда по рандомной стране или континенту", () -> {
            allRecipe = given()
                    .spec(requestSpec)
                    .header("x-api-key", xApiKey)
                    .get("/recipes/complexSearch?cuisine=" + randomCuisine)
                    .then()
                    .spec(responseSpec)
                    .extract().path("results.title");
        });

        step("Проверка наличия всех рецептов на сайте", () -> {
            open("/recipes");
            $(".input__field.input__field--makiko").click();
            $(".input__field.input__field--makiko").setValue(randomCuisine).pressEnter();

            for (String recipe : allRecipe) {
                $$(".ss360-n-section.ss360-group.ss360-group-recipes.ss360-group--active .ss360-n-section.ss360-suggests__header")
                        .findBy(text(recipe));
            }
        });
    }
}