# Singtel
BDD scenarios for todomvc.com

## Requirements
JDK version is 1.8 or higher  
Maven
Cucumber
Gherkin
Selenium
junit
log4j
Firefox browser 70 or higher  
Chrome browser 80 or higher  
Safari browser  
Microsoft Edge browser & mgsedgedriver 80.xx (same version)  

## Test structure

Properties file - src/main/resources/config.<envname>.properties 
Properties file contains all the URLs, environment, default browser type and other configs  

Page Objects - src/main/java/com/todomvc/pageObjects/*.*  
This folder contains all the page object files which is an object repository for storing all web elements 

Base - src/main/java/com/todomvc/utilities/Base.java  
This files is the base file and it initializes the driver and all other settings 

Feature files - src/test/java/com/todomvc/features/*.*
This folder contains all the feature files to be run

Step Definitions - src/test/java/com/todomvc/stepDefinitions/*.*
This folder contains all the step definitions(Glue) for the feature files

Test Runner - src/test/java/com/todomvc/testRunner/TestRunner.java
This test runner class file executes all the feature files using junit annotation

## Running Test

We can run the tests from maven command line or from Intellij

#### 1. To run from maven you can use the below command:  

mvn clean test -f pom.xml -Dbrowser="Edge" -Denvname="sit"

Parameters:  
envname = sit | uat 
karate.browsertype = Chrome | Firefox | Safari | Edge | Opera

#### 2. To run from Intellij  

You can right click on the Test Runner class file or the pom.xml file and select ‘Run’. If cucumber is installed, we can also right click on the feature files and run them directly.  

## Reports

Reports will be generated in the below location after the test run is completed:  
/target/surefire-reports/   
/target/cucumber-report-html/
/target/site/cucumber-report.html
