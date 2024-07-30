package com.reqres.in.tests.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.Keys;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class StepDefinition {

    private Response response;
    private String userId;

    @Given("the API endpoint is available")
    public void the_api_endpoint_is_available() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @When("I send a POST request to {string} with the following data:")
    public void i_send_a_POST_request_to_with_the_following_data(String endpoint, DataTable dataTable) {
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

            if (Keys.ID.equals(key)) {
                if (actualValue instanceof Integer) {
                    try {
                        int expectedId = Integer.parseInt(expectedValue);
                        assertEquals(expectedId, (Integer) actualValue, "Value for key " + key + " does not match");
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Expected value for key " + key + " is not a valid integer", e);
                    }
                } else if (actualValue instanceof String) {
                    try {
                        int actualId = Integer.parseInt((String) actualValue);
                        int expectedId = Integer.parseInt(expectedValue);
                        assertEquals(expectedId, actualId, "Value for key " + key + " does not match");
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Actual value for key " + key + " is not a valid integer", e);
                    }
                } else {
                    throw new IllegalArgumentException("Actual value for the key " + key + " is not a number");
                }
            } else {
                assertEquals(expectedValue, actualValue.toString(), "Value for key " + key + " does not match");
            }
        }
    }

    @And("the response should contain the following error message:")
    public void the_response_should_contain_the_following_error_message(DataTable dataTable) {
        Map<String, String> expectedErrorMessages = dataTable.asMaps(String.class, String.class).get(0);

        String actualErrorMessage = response.jsonPath().getString(Keys.ERROR);
        for (Map.Entry<String, String> entry : expectedErrorMessages.entrySet()) {
            String key = entry.getKey();
            String expectedErrorMessage = entry.getValue();

            assertEquals(expectedErrorMessage, actualErrorMessage, "Error message does not match for key " + key);
        }
    }

    @When("I send a POST request to {string}")
    public void i_send_a_POST_request_to(String endpoint) {
        response = given()
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint);
    }

    @When("I send a POST request to {string} for create a new user with the following data:")
    public void i_send_a_POST_request_to_for_create_a_new_user_with_the_following_data(String endpoint, DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> requestData = data.get(0);

        response = given()
                .contentType(ContentType.JSON)
                .body(requestData)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();

        userId = response.jsonPath().getString(Keys.ID);
    }

    @Then("I send a DELETE request to {string} with the created userId:")
    public void i_send_a_DELETE_request_to_with_the_id(String endpoint) {
        response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete(endpoint.concat(userId));
    }

    @When("I send a PUT request to {string} with the created userId with the following data:")
    public void i_send_a_PUT_request_to_with_the_created_user_id(String endpoint, DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> requestData = data.get(0);

        response = given()
                .contentType(ContentType.JSON)
                .body(requestData)
                .when()
                .put(endpoint.concat(userId));
    }

    @When("I send a GET request to {string} for get single user by id {string}")
    public void i_send_a_GET_request_to_for_get_single_user_by_id(String endpoint, String id) {
        response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint.concat(id));

    }

    @And("the response in data should contain the following data :")
    public void the_response_in_data_should_contain_the_following_data(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> expectedData = rows.get(0);

        Map<String, Object> actualData = response.jsonPath().getMap(Keys.DATA);

        for (Map.Entry<String, String> entry : expectedData.entrySet()) {
            String key = entry.getKey().trim();
            String expectedValue = entry.getValue().trim();
            Object actualValue = actualData.get(key);

            if (Keys.ID.equals(key)) {
                if (actualValue instanceof Integer) {
                    try {
                        int expectedId = Integer.parseInt(expectedValue);
                        assertEquals(expectedId, (Integer) actualValue, "Value for key " + key + " does not match");
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Expected value for key " + key + " is not a valid integer", e);
                    }
                } else {
                    throw new IllegalArgumentException("Actual value for the key " + key + " is not an integer");
                }
            } else {
                assertEquals(expectedValue, actualValue.toString(), "Value for key " + key + " does not match");
            }
        }
    }
}
