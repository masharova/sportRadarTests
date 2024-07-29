Feature: Get single user bu id feature.

  Scenario: Verify if it is possible to successfully get existed user by id.
    Given the API endpoint is available
    When I send a GET request to "/users/" for get single user by id "4"
    Then the response status code should be 200
    And the response in data should contain the following data :
      | id | email              |
      | 4  | eve.holt@reqres.in |

  Scenario: Verify if it is possible to get non-existed user.
    Given the API endpoint is available
    When I send a GET request to "/users/" for get single user by id "40"
    Then the response status code should be 404