Feature: Logout user feature.

  Scenario: Verify if it is possible to successfully logout for existed user.
    Given the API endpoint is available
    When I send a POST request to "/logout"
    Then the response status code should be 200
