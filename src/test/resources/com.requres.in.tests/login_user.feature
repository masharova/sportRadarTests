Feature: Login user feature.

  Scenario: Verify if it is possible to successfully login for existed user with valid password and email.
    Given the API endpoint is available
    When I send a POST request to "/login" with the following data:
      | email              | password |
      | eve.holt@reqres.in | pistol   |
    Then the response status code should be 200
    And the response should contain the following data:
      | token             |
      | QpwL5tke4Pnpja7X4 |

  Scenario: Verify error message when to login for existed user without password.
    Given the API endpoint is available
    When I send a POST request to "/login" with the following data:
      | email              | password |
      | eve.holt@reqres.in |          |
    Then the response status code should be 400
    And the response should contain the following error message:
      | error            |
      | Missing password |

  Scenario: Verify error message when to login for existed user without email.
    Given the API endpoint is available
    When I send a POST request to "/login" with the following data:
      | email | password |
      |       | pistol   |
    Then the response status code should be 400
    And the response should contain the following error message:
      | error                     |
      | Missing email or username |