package com.example.divine.bdd.api;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber"},
        features = "src/test/resources/",
        glue = {"com.example.divine.bdd.api.steps",
                "com.example.divine.bdd.api.hooks"})
public class RunCukesTestIT {

}
