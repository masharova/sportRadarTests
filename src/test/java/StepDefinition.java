import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class StepDefinition {

    private Response response;

    @Given("the API endpoint is available")
    public void the_api_endpoint_is_available() {
        RestAssured.baseURI = "https://reqres.in/api";
    }


    @When("I send a POST request to {string} with the following data:")
    public void iSendAPOSTRequestToWithTheFollowingData(String endpoint, DataTable dataTable) {

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> requestData = data.get(0);

        response = given()
                .contentType(ContentType.JSON)
                .body(requestData)
                .when()
                .post(endpoint);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("the response should contain the following data:")
    public void the_response_should_contain_the_following_data(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        Map<String, String> expectedData = rows.get(0);

        Map<String, Object> actualData = response.jsonPath().getMap("");

        for (Map.Entry<String, String> entry : expectedData.entrySet()) {
            String key = entry.getKey().trim();
            String expectedValue = entry.getValue().trim();
            Object actualValue = actualData.get(key);

            switch (key) {
                case "id":
                    if (actualValue instanceof Number) {
                        try {
                            int expectedId = Integer.parseInt(expectedValue);
                            assertEquals(expectedId, ((Number) actualValue).intValue(), "Value for key" + key + " does not match");
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("Expected value for key " + key + " is not a real integer", e);
                        }
                    } else {
                        throw new IllegalArgumentException("Actual value for the key " + key + " is not a number");
                    }
                    break;

                case "token":
                    assertEquals(expectedValue, actualValue.toString(), "Value for key " + key + " does not match");
                    break;

                default:
                    throw new IllegalArgumentException("Unused or unsupported key: " + key);
            }
        }
    }
}
