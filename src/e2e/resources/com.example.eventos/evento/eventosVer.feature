Feature: Table with eventos and diferent filteres

  Scenario: User enter page and see all events sort by date and all the posible filters
    Given Open Chrome and visit verEventos page
    Then Table display all events with correct data
    And Fecha min is void
    And Fecha max is void
    And Search is void
    And Tipo not selected
    And Horario not selected
    And Localidad not selected
    And Confirmar not selected
    And Tipo options correct
    And Horario options correct
    And Localidad options correct
    And Confirmar options correct

  Scenario: User enters min date and max date and filter events
    Given Open Chrome and visit verEventos page

  Scenario: User enter tipo and events filters
    Given Open Chrome and visit verEventos page

  Scenario: User enter horario and events filters
    Given Open Chrome and visit verEventos page

  Scenario: User enter localidad and events filters
    Given Open Chrome and visit verEventos page

  Scenario: User enter confirmado and events filters
    Given Open Chrome and visit verEventos page

  Scenario: User click event and redirect to verEvento page with clicked evento
    Given Open Chrome and visit verEventos page

