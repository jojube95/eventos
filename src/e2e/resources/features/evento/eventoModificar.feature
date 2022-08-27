Feature: Modificar evento page should update evento and show required fields with currect evento data

  Scenario: When user visit updateEvento page, evento data should be correct
    Given Open Chrome and logIn as admin
    And Visit updateEvento page
    Then Event data should be correct

  @modifyDatabase
  Scenario: User modify event
    Given Open Chrome and logIn as admin
    And Visit updateEvento page
    When User edit inputs
    And User click modificar evento
    Then Redirect to verEventos page
    And Set dates to avoid future test fails
    And Edited content should be correct

  Scenario: When click fecha field, datepicker should open
    Given Open Chrome and logIn as admin
    And Visit updateEvento page
    When User click fecha field
    Then Datepicker should display
    And First day should be monday

  Scenario: When user select tipo option, tipo should be selected
    Given Open Chrome and logIn as admin
    And Visit updateEvento page
    And User click tipo field
    When Use click tipo option
    Then Option tipo should be selected

  Scenario: When user select horario option, horario should be selected
    Given Open Chrome and logIn as admin
    And Visit updateEvento page
    And User click horario field
    When Use click horario option
    Then Option horario should be selected

  Scenario: When user select sala option, sala should be selected
    Given Open Chrome and logIn as admin
    And Visit updateEvento page
    And User click sala field
    When Use click sala option
    Then Option sala2 should be selected

  Scenario: When user dont fill fecha and click on anyadir, required field label should display
    Given Open Chrome and logIn as admin
    And Visit updateEvento page
    When User remove localidad input
    And User click modificar evento
    Then Keep on updateEvento page

  Scenario: When user dont fill localidad and click on anyadir, required field label should display
    Given Open Chrome and logIn as admin
    And Visit updateEvento page
    When User remove fecha input
    And User click modificar evento
    Then Keep on updateEvento page

  Scenario: When user select tipo to comunion, titulo should be Comunion-Comida, readonly
    Given Open Chrome and logIn as admin
    And Visit updateEvento page
    When User select tipo to comunion
    Then Titulo should be Comunion-Cena
    And Titulo should be readonly

  Scenario: When user select horario to cena, titulo should be Boda-Cena, readonly
    Given Open Chrome and logIn as admin
    And Visit updateEvento page
    When User select horario to comida
    Then Titulo should be Boda-Comida
    And Titulo should be readonly

  Scenario: When user select tipo to Evento individual, titulo should be editable
    Given Open Chrome and logIn as admin
    And Visit updateEvento page
    When User select tipo to Evento individual
    Then Titulo should be editable

  Scenario: When user select tipo to Evento comunal, titulo should be editable
    Given Open Chrome and logIn as admin
    And Visit updateEvento page
    When User select tipo to Evento comunal
    Then Titulo should be editable

  Scenario: When titulo is editable, and user edit titulo, when click off titulo, titulo fill with selected horario
    Given Open Chrome and logIn as admin
    And Visit updateEvento page
    When User select tipo to Evento comunal
    And User fill titulo input
    And User click outside titulo input
    Then Titulo fill with selected horario Sopar nadal-Cena