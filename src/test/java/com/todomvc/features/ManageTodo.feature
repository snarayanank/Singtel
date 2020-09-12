Feature: In order to remember the things I want to do as a todoMVC user I want to manage my todo list

  Background:

  Scenario: Create a list of multiple todo items and verify active list
    Given User is on todomvc.com
    When User enters an item in the textbox and press enter
      |Item1|
      |Item2|
      |Item3|
    Then Task get's added to the todo list
    And Displays the items as an active todo item

  Scenario: Create a list of 3 todo items and then remove 2 and validate only 1 item is left in the list
    Given User is on todomvc.com
    When User enters an item in the textbox and press enter
      |Item1|
      |Item2|
      |Item3|
    Then User removes 2 items from the todo list
    And Verify only 1 todo item is left in the list

  Scenario: Create a list of 3 todo items and mark them all completed 1 by 1 and verify completed list
    Given User is on todomvc.com
    When User enters an item in the textbox and press enter
      |Item1|
      |Item2|
      |Item3|
    Then Mark all the items as completed 1 by 1
    And Verify only 0 todo item is left in the list

  Scenario: Create a list of 3 todo items and mark 1 of them completed and clear completed

    Given User is on todomvc.com
    When User enters an item in the textbox and press enter
      |Item1|
      |Item2|
      |Item3|
    Then User marks 1 item from the todo list as completed
    And Clears the completed items
    And Verify only 2 todo item is left in the list

  Scenario: Create a list of 3 todo items and mark all of them completed and clear completed
    Given User is on todomvc.com
    When User enters an item in the textbox and press enter
      |Item1|
      |Item2|
      |Item3|
    Then User marks all items from the todo list as completed
    And Clears the completed items
    And Verify only 0 todo item is left in the list

  Scenario: Create a list of 3 todo items and mark all of them completed and mark them active again
    Given User is on todomvc.com
    When User enters an item in the textbox and press enter
      |Item1|
      |Item2|
      |Item3|
    Then User marks all items from the todo list as completed
    And Mark all the items as active again
    And Verify only 3 todo item is left in the list

  Scenario: Create a list of 3 todo items and mark 2 of them completed and mark 1 of them active again
    Given User is on todomvc.com
    When User enters an item in the textbox and press enter
      |Item1|
      |Item2|
      |Item3|
    Then User marks 2 item from the todo list as completed
    And Mark 1 item as active again
    And Verify only 2 todo item is left in the list

  Scenario: Verify Completed filter

  Scenario: Verify Active filter

  Scenario: Verify maximum number of items that can be added to the todo list
