package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.http.ContentType.JSON;

public class ProductsMapIngredientsSpec {

    public static RequestSpecification productsMapIngredientsRequestSpec = with()
            .filter(withCustomTemplates())
            .baseUri("https://api.spoonacular.com")
            .log().all()
            .contentType(JSON);

    public static ResponseSpecification productsMapIngredientsResponseSpec = new ResponseSpecBuilder()
            .log(BODY)
            .expectStatusCode(200)
            .expectContentType(JSON)
            .build();
}