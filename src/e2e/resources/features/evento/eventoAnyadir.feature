Feature: Anyadir evento page should add evento and show required fields

  @modifyDatabase
  Scenario: User adds event
    Given Open Chrome and logIn as admin
    And Visit eventoAnyadir page
    When User fill all fields
    And User select tipo to comunion
    And User click add evento
    Then Redirect to calendar page
    And Created event is shown

  Scenario: When click fecha field, datepicker should open
    Given Open Chrome and logIn as admin
    And Visit eventoAnyadir page
    When User click fecha field
    Then Datepicker should display
    And First day should be monday

  Scenario: When user select tipo option, tipo should be selected. When user select horario option, horario should be selected. When user select sala option, sala should be selected
    Given Open Chrome and logIn as admin
    And Visit eventoAnyadir page
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
    And Visit eventoAnyadir page
    And User fill localidad input
    When User click add evento
    Then Keep on eventoAnyadir page
    And Visit eventoAnyadir page
    And User fill fecha input
    When User click add evento
    Then Keep on eventoAnyadir page

  Scenario: When user select tipo to comunion or boda, titulo should be Comunion-Comida or Boda-Cena, readonly
    Given Open Chrome and logIn as admin
    And Visit eventoAnyadir page
    When User select tipo to boda
    And User select horario to cena
    Then Titulo should be Boda-Cena
    And Titulo should be readonly
    When User select tipo to comunion
    And User select horario to comida
    Then Titulo should be Comunion-Comida
    And Titulo should be readonly

  Scenario: When user select tipo to Evento individual or Evento comunal, titulo should be editable
    Given Open Chrome and logIn as admin
    And Visit eventoAnyadir page
    When User select tipo to Evento individual
    Then Titulo should be editable
    When User select tipo to Evento comunal
    Then Titulo should be editable

  Scenario: When titulo is editable, and user edit titulo, when click off titulo, titulo fill with selected horario
    Given Open Chrome and logIn as admin
    And Visit eventoAnyadir page
    When User select tipo to Evento comunal
    And User fill titulo input
    And User click off titulo input
    Then Titulo fill with selected horario