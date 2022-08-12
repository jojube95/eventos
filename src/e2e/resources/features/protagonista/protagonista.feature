Feature: Page protagonista that shows eveto protagonistas and can add and delete them

  Scenario: Visit protagonistas page should show protagonistas
    Given Given Open Chrome and visit protagonistas page
    Then Table should show two protagonists

  Scenario: Click Add protagonista should redirect to add protagonista page
    Given Given Open Chrome and visit protagonistas page
    When User click add button
    Then Redirect to protagonista anyadir page

  @modifyDatabase
  Scenario: Add protagonista should add protagonista
    Given Given Open Chrome and visit protagonistas page
    When User click add button
    Then Redirect to protagonista anyadir page
    When User fill all protagonista fields
    And User click add protagonista button
    Then Redirect to protagonistas page
    And Added protagonista should display

  @modifyDatabase
  Scenario: Delete protagonista should delete protagonista
    Given Given Open Chrome and visit protagonistas page
    When User click delete button
    Then Redirect to protagonistas page
    And Deleted protagonista shouldnt display