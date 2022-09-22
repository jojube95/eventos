Feature: Evento empleado page should list all evento empleados, add, delete and update evento empleado.

  @modifyDatabase
  Scenario: Add evento empleado should add to list and update progressbar
    Given Open Chrome and logIn as admin
    And Visit evento empleado page
    Then Evento empleado data should be correct
    And Evento empleado select list should be correct
    And Progressbar should be correct
    When User select empleado
    And User click anyadir event empleado
    Then Evento empleado is added to list
    And Progressbar should update new empleado

  @modifyDatabase
  Scenario: Update evento empleado should update on list
    Given Open Chrome and logIn as admin
    And Visit evento empleado page
    When User click on modificar evento empleado
    Then Evento empleado update modal should display
    And Evento empleado update modal should have correct data
    When User modify evento empelado
    And User click evento empleado modificar button
    Then Evento empleado update modal should hide
    And Evento empleado is updated on list
    And Progressbar should update updated empleado

  @modifyDatabase
  Scenario: Delete evento empleado should delete on list
    Given Open Chrome and logIn as admin
    And Visit evento empleado page
    When User click on delete evento empleado
    And Then empleado is deleted on list
    And Progressbar should update with deleted empleado