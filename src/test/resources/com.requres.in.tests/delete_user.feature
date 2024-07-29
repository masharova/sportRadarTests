Feature: Delete user feature.

  Scenario: Verify if it is possible to successfully delete existed user by id.
    Given the API endpoint is available
    When I send a POST request to "/users" for create a new user with the following data:
      | name | job |
      | Anna | qa  |
    Then the response status code should be 201
    When I send a DELETE request to "/users/" with the created userId:
    Then the response status code should be 204
