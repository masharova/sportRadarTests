package com.reqres.in.tests.cucumberRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/com.requres.in.tests",
        glue = "classpath:com/reqres/in/tests/steps")
public class RunAllTests {
}
