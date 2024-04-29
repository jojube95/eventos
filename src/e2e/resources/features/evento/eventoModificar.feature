Feature: Modificar evento page should update evento and show required fields with currect evento data

  @modifyDatabase
  Scenario: User modify event
    Given Open Chrome and logIn as admin
    And Visit eventoUpdate page
    Then Event data should be correct
    When User edit inputs
    And User click modificar evento
    Then Redirect to calendar page

  Scenario: When click fecha field, datepicker should open
    Given Open Chrome and logIn as admin
    And Visit eventoUpdate page
    When User click fecha field
    Then Datepicker should display
    And First day should be monday

  Scenario: When user select tipo option, tipo should be selected. When user select horario option, horario should be selected. When user select sala option, sala should be selected.
    Given Open Chrome and logIn as admin
    And Visit eventoUpdate page
    And User click tipo field
    When Use click tipo option
    Then Option tipo should be selected
    And User click horario field
    When Use click horario option
    Then Option horario should be selected
    And User click sala field
    When Use click sala option
    Then Option sala2 should be selected

  Scenario: When user dont fill fecha or location and click on anyadir, required field label should display
    Given Open Chrome and logIn as admin
    And Visit eventoUpdate page
    When User remove localidad input
    And User click modificar evento
    Then Keep on eventoUpdate page
    And Visit eventoUpdate page
    When User remove fecha input
    And User click modificar evento
    Then Keep on eventoUpdate page