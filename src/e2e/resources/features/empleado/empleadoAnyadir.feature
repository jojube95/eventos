Feature: Anyadir empleado page should add empleado
  @modifyDatabase
  Scenario: User adds empleado
    Given Open Chrome and logIn as admin
    And Visit empleadoAnyadir page
    When User fill all empleado fields
    And User click add empleado
    Then Redirect to empleados page
    And Created empleado is shown

  Scenario: When user select empleado tipo option, tipo should be selected. When user select empleado fijo option, fijo should be selected
    Given Open Chrome and logIn as admin
    And Visit empleadoAnyadir page
    And User click empleado tipo field
    When User click empleado tipo option
    Then Option empelado tipo should be selected
    And User click empleado fijo field
    When User click empleado fijo option
    Then Option empelado fijo should be selected