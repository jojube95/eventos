Feature: Mesa page should show eventos mesas that can be added, edited and deleted, and show mesa invitados

  Scenario: Visit mesas page should display table with mesas and personas
    Given Open Chrome and visit mesas page
    Then Table display all mesas

  Scenario: Click add should display add modal
    Given Open Chrome and visit mesas page
    When User click add mesa button
    Then Add modal mesa should display

  Scenario: Click edit should display edit modal with mesa data
    Given Open Chrome and visit mesas page
    When User click first mesa
    And User click edit mesa button
    Then Edit modal mesa should display
    And Edit modal data should be correct

  Scenario: Click delete should display confirm modal
    Given Open Chrome and visit mesas page
    When User click first mesa
    And User click delete mesa button
    Then Delete modal mesa should display

  Scenario: Click invitados should display invitados modal
    Given Open Chrome and visit mesas page
    When User click first mesa
    And User click invitados mesa button
    Then Invitados modal mesa should display

  @modifyDatabase
  Scenario: Add mesa should display on table
    Given Open Chrome and visit mesas page

  @modifyDatabase
  Scenario: Edit mesa should display changes on table
    Given Open Chrome and visit mesas page

  @modifyDatabase
  Scenario: Delete mesa should delete from table
    Given Open Chrome and visit mesas page

