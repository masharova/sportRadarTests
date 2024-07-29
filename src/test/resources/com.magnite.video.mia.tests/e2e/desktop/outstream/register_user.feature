Feature: Register a new user.

  Scenario: Verify if it is possible to successfully create a new user.
    Given the API endpoint is available
    When I send a POST request to "/register" with the following data:
      | email              | password |
      | eve.holt@reqres.in | pistol   |
    Then the response status code should be 200
    And the response should contain the following data:
      | id | token                   |
      | 4  | QpwL5tke4Pnpja7X4pistol |

