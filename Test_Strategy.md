# Test Strategy for [API](https://reqres.in/)

---
## 1.Goals and Objectives

**Goals:** *Ensure that the API functions according to the documentation, data is returned correctly and on time, and
the API is safe to use.*

**Tasks:**

* Check the functionality of all endpoints.
* Test the performance of the API under various loads.
* Assess the security and protection of data transmitted through the API.
----
## 2.Testing Area

**Endpoints and Test Cases**

1. **GET /users**

   _Description:_ Getting a list of users with pagination.

   _Test Cases:_

   _Positive tests:_

   Request for a list of users on the first page.

   Request for a list of users on the second page.

   Checking the structure and content of the response (id, email, first_name, last_name, avatar fields).

   _Negative tests:_

   Request for a page with a non-existent number.

   Request with an invalid value for the page parameter.
2. **POST /users**

   _Description:_ Creating a new user.

   _Test Cases:_

   _Positive tests:_

   Creating a user with valid data (name and job fields).

   Checking that the id, name, job, createdAt fields are present in the response.

   _Negative tests:_

   Creating a user without required fields.

   Creating a user with invalid data (e.g., a very long name).
3. **PUT /users/{id}**

   _Description:_ Updating information about the user with the specified ID.

   _Test cases:_

   _Positive tests:_

   Updating a user with valid data (name and job fields).

   Checking that the response contains updated name, job, updatedAt fields.

   _Negative tests:_

   Updating a non-existent user.

   Updating with invalid data (e.g., a very long name).
4. **DELETE /users/{id}**

   _Description:_ Deleting a user with the specified ID.

   _Test cases:_

   _Positive tests:_

   Deleting an existing user.

   Checking that the response has the 204 No Content status.

   _Negative tests:_

   Attempt to delete a non-existent user.

   Deleting with an invalid ID (e.g., a string instead of a number).
5. **GET /users/{id}**

   _Description:_ Getting information about the user with the specified ID.

   _Test scenarios:_

   _Positive tests:_

   Request for information about an existing user.

   Checking the structure and content of the response (fields id, email, first_name, last_name, avatar).

   _Negative tests:_

   Request for information about a non-existent user.

   Request with an invalid ID (e.g., a string instead of a number).
6. **GET /unknown**

   _Description:_ Getting a list of resources (e.g., color palettes).

   _Test scenarios:_

   _Positive tests:_

   Request for a list of resources.

   Checking the structure and content of the response (fields id, name, year, color, pantone_value).

   _Negative tests:_

   Request for a page with a non-existent number.

   Request with an invalid value for the page parameter.
7. **POST /register**

   _Description:_ Registering a new user.

   _Test scenarios_

   _Positive tests:_

   Registration with valid data (fields email and password).

   Checking that the id and token fields are present in the response.

   _Negative Tests:_

   Registration without required fields.

   Registration with invalid data (e.g. invalid email format).
8. **POST /login**

   _Description:_ User authorization.

   _Test scenarios_

   _Positive Tests:_

   Authentication with valid data (email and password fields).

   Check that the token field is present in the response.

   _Negative Tests:_

   Authentication without required fields.

   Authentication with invalid data (e.g. invalid password).

   _Test Case Details_

   _Positive Test Cases_

   Response Status Correctness:

* Check that the API returns status 200 for successful requests.
* Check that the API returns status 201 for successful resource creation.
* Check that the API returns status 204 for successful resource deletion.

Data Correctness:

* Check that all required fields are present in the response.
* Check that the fields have the correct data types.
* Validate field values ​​(e.g. valid email, valid date format).

Logical Checks:

* Verify that a user being created is actually added to the system.
* Verify that updating user data correctly changes the data.
* Verify that deleting a user actually deletes them from the system.

_Negative Test Cases_

Invalid Input Data:

* Verify that the API returns correct errors (e.g. 400 Bad Request) when invalid data is sent.
* Verify that the API correctly handles invalid request parameters (e.g. a string instead of a number).

Non-Existent Resources:

* Verify that the API returns a 404 status for requests to non-existent resources.
* Verify that the API returns the correct error message for non-existent users or resources.

Field Checks:

* Verify that the API returns an error when required fields are missing.
* Verify that the API correctly handles overly long strings or invalid data formats.

Additional Checks

* Compatibility Check

  _Checking the API to work with different HTTP versions (1.1, 2)._

  _Checking cross-browser compatibility when calling the API from different browsers (if applicable)._

* Documentation Checking

  _Checking that all API documentation is up-to-date and matches the actual behavior of the API._

  _Checking that the request and response examples in the documentation are correct and can be reproduced._

**Conclusion**

The conclusion of this testing area covers all important aspects of  [API](https://reqres.in/), including networking,
negative and additional checks. High-quality and robust test cases provide an API that helps users interact effectively
with the service.
----
## 3. Types of Testing

**3.1. Functional Testing**

Checking the correctness of the response for each endpoint.

Checking the validity of the data (e.g. correct emails, names, ids, etc.).

Checking different HTTP methods (GET, POST, PUT, DELETE).

Testing boundary conditions (e.g. requests with non-existent IDs).

**3.2. Performance Testing**

Checking the response time of the API under different loads.

Testing stability and scalability under high load.

**3.3. Security Testing**

Checking for vulnerabilities (SQL Injection, XSS, CSRF, etc.).

Testing authentication and authorization (if applicable).

**3.4. Usability Testing**

Checking the usability of the API, readability and completeness of the documentation.

---
## 4. Testing Approach

**4.1. Tools**

Postman: for manual API testing.

RestAssured: for automating functional tests.

JMeter: for performance testing.

OWASP ZAP: for security testing.

**4.2. Methodology**

* Automation: Basic functional testing will be automated using RestAssured.

* Manual Testing: Checking edge cases and exploratory testing using Postman.

* Load Testing: Load testing scenarios will be executed using JMeter.
* Security: Using OWASP ZAP to scan for vulnerabilities.
---
## 5. Test Plan

**5.1. Preparation**

* Set up the test environment.
* Prepare test data.
* Set up tools for automated testing.

**5.2. Functional Testing**

* Create test scenarios for each endpoint.
* Implement automated tests using RestAssured.

**5.3. Load Testing**

* Create load testing scenarios.
* Run tests with different load levels (e.g. 50, 100, 500, 1000 concurrent requests).

**5.4. Security Testing**

* Configure OWASP ZAP to scan APIs.
* Perform scanning and analyze results.

**5.5. Usability Testing**
* Review API documentation.
* Feedback from developers and users.
----
## Final Points
The testing strategy for the [API](https://reqres.in/) has been developed keeping in mind all the key aspects of the web service quality and reliability. Test automation ensures the efficiency, accuracy and robustness of the tests, which allows for timely detection and elimination of defects, ensuring high quality of the final product. Regular performance and security testing further contributes to maintaining a high level of security and performance of the API.
During automation, high priority test scenarios were selected. You can find these scenarios at `src/test/resources/com.requres.in.tests`.