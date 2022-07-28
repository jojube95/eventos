Feature: Anyadir evento page should add evento and show required fields

  @modifyDatabase
  Scenario: User adds event
    Given Open Chrome and visit anyadirEvento page
    When User fill al fields
    And User click add evento
    Then Redirect to calendar page
    And Created event is shown
    And Select event show created data