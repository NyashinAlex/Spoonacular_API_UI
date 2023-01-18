package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.http.ContentType.JSON;

public class ProductsSpec {

    public static RequestSpecification productsRequestSpec = with()
            .filter(withCustomTemplates())
            .baseUri("https://api.spoonacular.com")
            .log().all();

    public static ResponseSpecification productsResponseSpec = new ResponseSpecBuilder()
            .log(BODY)
            .expectStatusCode(200)
            .expectContentType(JSON)
            .build();
}
