Feature: Page protagonista that shows eveto protagonistas and can add and delete them

  @modifyDatabase
  Scenario: Add protagonista should add protagonista
    Given Open Chrome and logIn as admin
    And Visit protagonistas page
    Then Table should show two protagonists
    When User click add button
    Then Redirect to protagonista anyadir page
    When User fill all protagonista fields
    And User click add protagonista button
    Then Redirect to protagonistas page
    And Added protagonista should display

  @modifyDatabase
  Scenario: Delete protagonista should delete protagonista
    Given Open Chrome and logIn as admin
    And Visit protagonistas page
    When User click delete button
    Then Redirect to protagonistas page
    And Deleted protagonista shouldnt display