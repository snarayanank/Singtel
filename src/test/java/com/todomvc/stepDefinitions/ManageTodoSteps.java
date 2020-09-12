package com.todomvc.stepDefinitions;

import com.todomvc.pageObjects.TodoMvcHomePage;
import com.todomvc.utilities.Base;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.util.List;

public class ManageTodoSteps extends Base {
    WebDriverWait wait;
    TodoMvcHomePage mvcHomePage;
    Actions action;
    private static Logger log = LogManager.getLogger(ManageTodoSteps.class.getName());

    @Before
    public void initialize() throws IOException {
        driver = initializeDriver();
        mvcHomePage = new TodoMvcHomePage(driver);
        action = new Actions(driver);
        wait = new WebDriverWait(driver, 20);
    }

    @Given("User is on todomvc.com")
    public void user_is_on_todomvc_com() throws IOException {
        //Open the todomvc.com home page
        driver.get(prop.getProperty("todoMvcUrl"));
        System.out.println(driver.getTitle());
        //Assert.assertEquals(driver.getTitle(), "Vue.js • TodoMVC");
        String actualString = driver.getTitle();
        Assert.assertTrue(actualString.contains("TodoMVC"));
    }

    @When("User enters an item in the textbox and press enter")
    public void user_enters_an_item_in_the_textbox_and_press_enter(List<String> itemListNames) throws IOException, InterruptedException {
        //Add todo list items
        for(String str:itemListNames) {
            mvcHomePage.enterTodoItem(str);
            action.sendKeys(Keys.ENTER).perform();
            log.info("Added " + str + " to the todo list");
        }
    }

    @Then("Task get's added to the todo list")
    public void task_get_s_added_to_the_todo_list() {
        //Verify the items are added to the list
        String itemCount = mvcHomePage.getTodoCount();
        log.info("Total todo items added to the list is " + itemCount);
        int todoCount = Integer.parseInt((itemCount.split(" ")[0]).trim());
        if (todoCount == 3) {
            log.info("All the todo items are added successfully");
        }else
        {
            Assert.fail("All the todo items are not added to the list");
        }
    }

    @Then("Displays the items as an active todo item")
    public void displays_the_items_as_an_active_todo_item() {
        //All the items are added as active items
        mvcHomePage.clickActiveList();
        String itemCount = mvcHomePage.getTodoCount();
        log.info("Total todo items in the active list is " + itemCount);
        int todoCount = Integer.parseInt((itemCount.split(" ")[0]).trim());
        if (todoCount == 3) {
            log.info("All the todo items are displayed in the active list");
        }else
        {
            Assert.fail("All the todo items are not in the active list");
        }
    }

    @Then("User removes {int} items from the todo list")
    public void user_removes_items_from_the_todo_list(Integer int1) {
        // Remove <no> items from the todo list
        String element = mvcHomePage.getLabelItem("1");
        for (int i=0; i< int1 ; i++){
            action.moveToElement(driver.findElement(By.xpath(element))).build().perform();
            mvcHomePage.removeTodo();
        }
        log.info("Removed " + int1 + " items from the todo list");
    }

    @Then("Verify only {int} todo item is left in the list")
    public void verify_only_todo_item_is_left_in_the_list(Integer int1) throws InterruptedException {
        // Verify <no> of left items in the list
        String itemCount = mvcHomePage.getTodoCount();
        log.info("Total todo items left in the list is " + itemCount);
        if (!itemCount.isEmpty()) {
            int todoCount = Integer.parseInt((itemCount.split(" ")[0]).trim());
            if (todoCount == int1) {
                log.info("The todo items left in the active todo list is " + todoCount);
            } else {
                Assert.fail("There is a mismatch in the left items in the active todo list");
            }
        }
        else {
            if (int1 == 0){
                log.info("The todo items left in the active todo list is " + int1);
            }
        }
    }

    @Then("Mark all the items as completed 1 by 1")
    public void mark_all_the_items_as_completed_1_by_1() throws InterruptedException {
        // Mark all the items as completed one by one
        String element;
        element = mvcHomePage.getListItem("1");
        driver.findElement(By.xpath(element)).click();
        element = mvcHomePage.getListItem("2");
        driver.findElement(By.xpath(element)).click();
        element = mvcHomePage.getListItem("3");
        driver.findElement(By.xpath(element)).click();
    }

    @Then("User marks {int} item from the todo list as completed")
    public void user_marks_item_from_the_todo_list_as_completed(Integer int1) throws InterruptedException {
        // User marks <no> item from the todo list as completed
        String element;
        for (int i=1; i<= int1 ; i++){
            element = mvcHomePage.getListItem(String.valueOf(i));
            action.moveToElement(driver.findElement(By.xpath(element))).build().perform();
            driver.findElement(By.xpath(element)).click();
        }
        log.info("Marked " + int1 + " item from the todo list as completed");
    }

    @Then("Clears the completed items")
    public void clears_the_completed_items() {
        mvcHomePage.clickClearCompleted();
        log.info("Cleared all the completed todo items from the list");
    }

    @Then("User marks all items from the todo list as completed")
    public void user_marks_all_items_from_the_todo_list_as_completed() {
        // Mark all the items as completed in the todo list
        mvcHomePage.clickMarkAllComplete();
    }

    @Then("Mark all the items as active again")
    public void mark_all_the_items_as_active_again() throws InterruptedException {
        // Mark all completed items as active again
        mvcHomePage.clickAllList();
        List<WebElement> links = mvcHomePage.getListItems().findElements(By.tagName("li"));
        log.info("There are " + links.size() + " todo items in total in the list");
        String element;
        for (int i=1; i<= links.size() ; i++){
            element = mvcHomePage.getListItem(String.valueOf(i));
            action.moveToElement(driver.findElement(By.xpath(element))).build().perform();
            driver.findElement(By.xpath(element)).click();
        }
        log.info("Marked all the " + links.size() + " todo items in the list as active again");
    }

    @Then("Mark {int} item as active again")
    public void mark_item_as_active_again(Integer int1) throws InterruptedException {
        // Mark <no> completed item as active again
        String element;
        for (int i=1; i<= int1 ; i++){
            element = mvcHomePage.getListItem(String.valueOf(i));
            action.moveToElement(driver.findElement(By.xpath(element))).build().perform();
            driver.findElement(By.xpath(element)).click();
        }
        log.info("Marked " + int1 + " item from the todo list as active again");
    }

    @After
    public void tearDown() throws IOException{
        driver.close();
        driver=null;
    }
}
