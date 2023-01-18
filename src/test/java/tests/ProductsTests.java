package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Feature;
import models.Products.ProductsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.ProductsSpec.productsRequestSpec;
import static specs.ProductsSpec.productsResponseSpec;

public class ProductsTests extends BaseTests {

    Faker faker = new Faker();

    String xApiKey, titleProduct, generatedTextProduct;
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
                    .get("/food/products/" + faker.number().numberBetween(20000, 300000))
                    .then()
                    .spec(productsResponseSpec)
                    .extract().as(ProductsResponse.class);

            idProduct = productsResponse.getId();
            titleProduct = productsResponse.getTitle();
            generatedTextProduct = productsResponse.getGeneratedText();
        });

        step("Открыть страницу тестового рецепта", () -> {
            open("/products/" + titleProduct.replace(" ", "-")+ "-" + idProduct);
        });

        step("Проверить, что наименования продукта корректно отображается", () -> {
            $("[itemprop=name]").shouldBe(text(titleProduct));
        });

        if (generatedTextProduct != null) {
            step("Проверить, что описание продукта корректно отображается", () -> {
                $(".summary.panel").shouldBe(text(generatedTextProduct));
            });
        }
    }
}