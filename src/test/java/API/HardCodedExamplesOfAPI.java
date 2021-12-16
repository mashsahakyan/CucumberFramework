package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamplesOfAPI {

    /*
    Given - precondition - prepare the request
    When - Action - sending the request/hitting the endpoint
    Then - result - verify response
     */

    //rest assured consider baseURL as baseURI
    //Base URL: http://hrm.syntaxtechs.net/syntaxapi/api
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzkzMzAwNzUsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYzOTM3MzI3NSwidXNlcklkIjoiMzI2MCJ9.Pf72x_Y6W7CHWgUPw0Y69nr99CblCexLRchXOZb5mNA";
    static String employee_id;

    @Test
    public void dgetDetailsOfOneEmployee(){
        //given
        RequestSpecification requestSpecification = given().header("Authorization", token)
                .header("Content-Type", "application/json")
                .queryParam("employee_id", "25568A");

        //when - hitting the endpoint
        Response response = requestSpecification.when().get("/getOneEmployee.php");
        System.out.println(response.asString());

        //then - result/assertion
        response.then().assertThat().statusCode(200);
    }


    @Test
    public void acreateEmployee(){
        RequestSpecification requestSpecification = given().header("Authorization", token)
                .header("Content-Type", "application/json").body("{\n" +
                        "    \"emp_firstname\" : \"qwertabcd\",\n" +
                        "    \"emp_lastname\" : \"abcdqwert\",\n" +
                        "    \"emp_middle_name\" : \"nuttersools\",\n" +
                        "    \"emp_gender\" : \"F\",\n" +
                        "    \"emp_birthday\" : \"2002-08-18\",\n" +
                        "    \"emp_status\" : \"Active\",\n" +
                        "    \"emp_job_title\" : \"CEO\"\n" +
                        "}");

        //when
        Response response = requestSpecification.when().post("/createEmployee.php");
        response.prettyPrint();
        //this prettyPrint does the same job as sout
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);

        //then
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("Employee.emp_firstname", equalTo("qwertabcd"));
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        response.then().assertThat().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");

    }

    @Test
    public void bgetCreatedEmployee(){
        RequestSpecification requestSpecification = given().header("Authorization", token)
                .header("Content-Type", "application/json")
                .queryParam("employee_id", employee_id);
        Response response = requestSpecification.when().get("/getOneEmployee.php");
        String empID = response.jsonPath().getString("Employee.employee_id");

        boolean comparingEmpID = empID.contentEquals(employee_id);
        Assert.assertTrue(comparingEmpID);

        String firstName = response.jsonPath().getString("Employee.emp_firstname");
        Assert.assertTrue(firstName.contentEquals("qwertabcd"));

        String lastname = response.jsonPath().getString("Employee.emp_lastname");
        Assert.assertTrue(lastname.contentEquals("abcdqwert"));
    }
@Test
    public void cupdateCreatedEmployee(){
        RequestSpecification requestSpecification = given().header("Authorization", token)
                .header("Content-Type", "application/json").body("{\n" +
                        "    \"employee_id\": \"" + employee_id + "\", \n" +
                        "    \"emp_firstname\" : \"hsdgajgsdjadsa\",\n" +
                        "    \"emp_lastname\" : \"alhdkagfjagjhagj\",\n" +
                        "    \"emp_middle_name\" : \"oiuygfvbjkuytrdfghj\",\n" +
                        "    \"emp_gender\" : \"F\",\n" +
                        "    \"emp_birthday\" : \"1998-12-01\",\n" +
                        "    \"emp_status\" : \"Active\",\n" +
                        "    \"emp_job_title\" : \"CEO\"\n" +
                        "}");
        Response response = requestSpecification.when().put("/updateEmployee.php");
        response.prettyPrint();
    }

    @Test
    public void getAllEmployees(){
        RequestSpecification requestSpecification = given().header("Authorization", token)
                .header("Content-Type", "application/json");
        Response response = requestSpecification.when().get("/getAllEmployees.php");

//        String allEmployees = response.prettyPrint();
//        JsonPath jsonPath = new JsonPath(allEmployees);

        JsonPath jsonPath = response.jsonPath();
        int count = jsonPath.getInt("Employees.size()");
        System.out.println(count);

        for(int i = 0; i < count; i++){
            String employeeIDs = jsonPath.getString("Employees[" + i + "].employee_id");
            System.out.println(employeeIDs);
        }
    }
}
