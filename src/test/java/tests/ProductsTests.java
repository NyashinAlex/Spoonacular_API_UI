package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Feature;
import models.Products.ProductsResponse;
import models.SearchRecipes.SearchRecipesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.ProductsSpec.productsRequestSpec;
import static specs.ProductsSpec.productsResponseSpec;

public class ProductsTests extends BaseTests {

    Faker faker = new Faker();

    String xApiKey, titleProduct, title, descriptionProduct;
    int idProduct;

    @BeforeEach
    void getApiKey() {
        xApiKey = System.getProperty("x-api-key", "ab4a8b4cc3bf48c6ad8aedf6e8350394");
    }

    @Test
    @Feature("Поиск продуктов")
    @DisplayName("Проверка рандомного продукта")
    void checkRandomRecipe() {

        step("Получение рандомного продукта через API", () -> {
            ProductsResponse productsResponse = given()
                    .spec(productsRequestSpec)
                    .header("x-api-key", xApiKey)
                    .get("/food/products/" + faker.number().numberBetween(1, 50000))
                    .then()
                    .spec(productsResponseSpec)
                    .extract().as(ProductsResponse.class);

            idProduct = productsResponse.getId();
            titleProduct = productsResponse.getTitle();
            descriptionProduct = productsResponse.getDescription();

            char[] result = titleProduct.toCharArray();
            for (int i = 0; i < result.length; i++) {
                title = result[0] + "-";
            }
        });

        step("Открыть страницу тестового рецепта", () -> {
            open("/products/" + title + idProduct);
        });
    }
}