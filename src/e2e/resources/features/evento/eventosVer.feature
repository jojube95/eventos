Feature: Table with eventos and diferent filteres

  Scenario: User enter page and see all events sort by date and all the posible filters
    Given Open Chrome and logIn as admin
    And Visit verEventos page
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
    Given Open Chrome and logIn as admin
    And Visit verEventos page
    When User enter min date 2022-07-10
    Then Three events shown
    When User enter max date 2022-07-16
    Then Two events shown


  Scenario: User enter tipo and events filters
    Given Open Chrome and logIn as admin
    And Visit verEventos page
    When User enter tipo Boda
    Then Evento boda is shown

  Scenario: User enter horario and events filters
    Given Open Chrome and logIn as admin
    And Visit verEventos page
    When User enter horario Comida
    Then Evento comida is shown

  Scenario: User enter localidad and events filters
    Given Open Chrome and logIn as admin
    And Visit verEventos page
    When User enter localidad Aielo de Malferit
    Then Evento Aielo de Malferit is shown

  Scenario: User enter confirmado and events filters
    Given Open Chrome and logIn as admin
    And Visit verEventos page
    When User enter confirmado Si
    Then Evento confirmado is shown

  Scenario: When user enter multiple filters, filter with all
    Given Open Chrome and logIn as admin
    And Visit verEventos page
    When User enter horario Comida and localidad Olleria
    Then Evento Comida and Olleria is shown

  Scenario: User click event and redirect to verEvento page with clicked evento
    Given Open Chrome and logIn as admin
    And Visit verEventos page
    When User click first event
    Then Redirect to verEvento page with currect evento

