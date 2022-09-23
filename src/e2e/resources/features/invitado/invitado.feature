Feature: Invitados modal should show invitados table with correct information and add, edit, delete them

  @modifyDatabase
  Scenario: Add invitado should add to the table
    Given Open Chrome and logIn as admin
    And Visit mesas page
    And User click first mesa
    And User click invitados mesa button
    And Invitados modal mesa should display
    Then Invitados content should be correct
    When User click add invitado button
    Then Add invitado modal should display
    When User fill add invitado form
    And User click add invitado modal button
    Then Add invitado modal should hide
    And New invitado should display on table

  @modifyDatabase
  Scenario: Edit invitado should edit on table
    Given Open Chrome and logIn as admin
    And Visit mesas page
    And User click first mesa
    And User click invitados mesa button
    And Invitados modal mesa should display
    When User click first invitado
    And User click edit invitado button
    Then Edit invitado modal should display
    When User fill edit invitado form
    And User click edit invitado modal button
    Then Edit invitado modal should hide
    And Edited invitado should display on table

  @modifyDatabase
  Scenario: Delete invitado should delete on table
    Given Open Chrome and logIn as admin
    And Visit mesas page
    And User click first mesa
    And User click invitados mesa button
    And Invitados modal mesa should display
    When User click first invitado
    And User click delete invitado button
    Then Delete invitado modal should display
    When User click delete invitado modal button
    Then Delete invitado modal should hide
    And Deleted invitado shouldnt display on table