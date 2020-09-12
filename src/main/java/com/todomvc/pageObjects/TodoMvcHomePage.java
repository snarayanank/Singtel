package com.todomvc.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TodoMvcHomePage {

    public WebDriver driver;

    public TodoMvcHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath= "//h1[contains(text(),'todos')]")
    WebElement header;
    public WebElement getHeader() { return header; }

    @FindBy(xpath= "//input[@placeholder='What needs to be done?']")
    WebElement todoTextBox;
    public void enterTodoItem(String item) { todoTextBox.sendKeys(item); }
    public void clickTodoTxtBox() { todoTextBox.click(); }

    @FindBy(xpath= "//li[1]//div[1]//button[1]")
    WebElement removeTodo;
    public void removeTodo() { removeTodo.click(); }
    public WebElement getRemoveTodo() { return removeTodo; }

    @FindBy(xpath= "//button[@class='clear-completed']")
    WebElement clearCompleted;
    public void clickClearCompleted() { clearCompleted.click(); }

    @FindBy(xpath= "//ul[@class='todo-list']")
    WebElement listItems;
    public WebElement getListItems() { return listItems; }

    String listItem;
    public String getListItem(String num){ return "//li[" + num + "]//div[1]//input[1]"; }

    String labelItem;
    public String getLabelItem(String num){ return "//li[" + num + "]//div[1]//label[1]"; }

    @FindBy(xpath= "//span[@class='todo-count']")
    WebElement todoCount;
    public String getTodoCount() { return todoCount.getText(); }

    @FindBy(xpath= "//a[contains(text(),'All')]")
    WebElement allList;
    public void clickAllList() { allList.click(); }

    @FindBy(xpath= "//a[contains(text(),'Active')]")
    WebElement activeList;
    public void clickActiveList() { activeList.click(); }

    @FindBy(xpath= "//a[contains(text(),'Completed')]")
    WebElement completedList;
    public void clickCompletedList() { completedList.click(); }

    @FindBy(xpath= "//label[contains(text(),'Mark all as complete')]")
    WebElement markAllComplete;
    public void clickMarkAllComplete() { markAllComplete.click(); }

}
