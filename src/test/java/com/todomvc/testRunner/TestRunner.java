package com.todomvc.testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/todomvc/features",
        glue = {"com/todomvc/stepDefinitions"},
        plugin= {"pretty",
        "html:target/site/cucumber-report.html",
        "json:target/cucumber.json"},
        monochrome = true)

public class TestRunner {


}