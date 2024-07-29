Feature: Register a new user.

  Scenario: Verify if it is possible to successfully register a new user with valid password and email.
    Given the API endpoint is available
    When I send a POST request to "/register" with the following data:
      | email              | password |
      | eve.holt@reqres.in | pistol   |
    Then the response status code should be 200
    And the response should contain the following data:
      | id | token             |
      | 4  | QpwL5tke4Pnpja7X4 |

  Scenario: Verify error message when register a new user without password.
    Given the API endpoint is available
    When I send a POST request to "/register" with the following data:
      | email              | password |
      | eve.holt@reqres.in |          |
    Then the response status code should be 400
    And the response should contain the following error message:
      | error            |
      | Missing password |


  Scenario: Verify error message when register a new user without email.
    Given the API endpoint is available
    When I send a POST request to "/register" with the following data:
      | email | password |
      |       | pistol   |
    Then the response status code should be 400
    And the response should contain the following error message:
      | error                     |
      | Missing email or username |