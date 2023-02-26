package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.http.ContentType.JSON;

public class Spec {

    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .baseUri("https://api.spoonacular.com")
            .log().all()
            .contentType(JSON);

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .log(BODY)
            .expectStatusCode(200)
            .expectContentType(JSON)
            .build();

    public static ResponseSpecification responseError404Spec = new ResponseSpecBuilder()
            .log(BODY)
            .expectStatusCode(404)
            .expectContentType(JSON)
            .build();
}