Feature: Anyadir evento page should add evento and show required fields

  @modifyDatabase
  Scenario: User adds event
    Given Open Chrome and visit anyadirEvento page
    When User fill all fields
    And User click add evento
    Then Redirect to calendar page
    And Created event is shown
    And Select event show created data

  Scenario: When click fecha field, datepicker should open
    Given Open Chrome and visit anyadirEvento page
    When User click fecha field
    Then Datepicker should display
    And First day should be monday

  Scenario: When user select tipo option, tipo should be selected, and options should be hidden
    Given Open Chrome and visit anyadirEvento page
    And User click tipo field
    When Use click tipo option
    Then Option tipo should be selected

  Scenario: When user select horario option, horario should be selected, and options should be hidden
    Given Open Chrome and visit anyadirEvento page
    And User click horario field
    When Use click horario option
    Then Option horario should be selected

  Scenario: When user dont fill fecha and click on anyadir, required field label should display
    Given Open Chrome and visit anyadirEvento page
    And User fill localidad input
    When User click add evento
    Then Required field label fecha should display
    And Keep on anyadirEvento page

  Scenario: When user dont fill localidad and click on anyadir, required field label should display
    Given Open Chrome and visit anyadirEvento page
    And User fill fecha input
    When User click add evento
    Then Required field label localidad should display
    And Keep on anyadirEvento page

  Scenario: When user select tipo to comunion, titulo should be Comunion-Comida, readonly
    Given Open Chrome and visit anyadirEvento page
    When User select tipo to comunion
    Then Titulo should be Comunion-Comida
    And Titulo should be readonly

  Scenario: When user select horario to cena, titulo should be Boda-Cena, readonly
    Given Open Chrome and visit anyadirEvento page
    When User select horario to cena
    Then Titulo should be Boda-Cena
    And Titulo should be readonly

  Scenario: When user select tipo to Evento individual, titulo should be editable
    Given Open Chrome and visit anyadirEvento page
    When User select tipo to Evento individual
    Then Titulo should be editable

  Scenario: When user select tipo to Evento comunal, titulo should be editable
    Given Open Chrome and visit anyadirEvento page
    When User select tipo to Evento comunal
    Then Titulo should be editable

  Scenario: When titulo is editable, and user edit titulo, when click off titulo, titulo fill with selected horario
    Given Open Chrome and visit anyadirEvento page
    When User select tipo to Evento comunal
    And User fill titulo input
    And User click off titulo input
    Then Titulo fill with selected horario


