package APISteps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class GenerateTokenSteps {
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String token;
    @Given("a JWT is generated")
    public void a_jwt_is_generated(){
        RequestSpecification generateTokenRequest = given().header("Content-Type", "application/json").body("{\n" +
                "  \"email\": \"email@gmail.com\",\n" +
                "  \"password\": \"peepeepoopoo\"\n" +
                "}");
        Response generatedTokenResponse = generateTokenRequest.when().post("/generateToken.php");
        generatedTokenResponse.prettyPrint();
        token = "Bearer " + generatedTokenResponse.jsonPath().getString("token");
        System.out.println(token);
    }
}