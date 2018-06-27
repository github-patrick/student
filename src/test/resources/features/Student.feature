@StudentApi
Feature: A client can perform crud operations on a student resource.

  Background:
    Given I am on the students endpoint

  Scenario: A client creates a student
    And I have a valid student
    When I set the header "Content-Type" as "application/json"
    And I send a request to create the student
    Then I should see a 201 response
    And I should see the student exists

  Scenario: A client cannot create a student due to validation constraints
    And I have an invalid student
    When I set the header "Content-Type" as "application/json"
    And I send a request to create the student
    Then I should see a 400 response

  Scenario Outline: A client does a update on a student
    And I have created 2 students
    When I set the header "Content-Type" as "application/json"
    And I set the header "Accept" as "application/json"
    And I have a student to update
    And I set the student first name to "<name>"
    And I send a request to update the student
    Then I should see a 200 response
    And I should see that the student first name is "<name>"
    Examples:
      | name |
      | Paul |
      | Jake |
