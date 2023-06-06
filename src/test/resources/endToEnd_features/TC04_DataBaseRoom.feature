@e2e @database
Feature: Room Database Test

  Scenario: Select Room
    Given connect the database
    Then read room and validate
