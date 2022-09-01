Feature: Modificar empleado page should modify empleado
  @modifyDatabase
  Scenario: User modify empleado
    Given Open Chrome and logIn as admin
    And Visit modificar empleado page
    Then Empleado data should be fill
    When User edit empleado fields
    And User click modificar empleado button
    Then Redirect to empleados page
    And Modificado empleado is shown

  Scenario: When user select empleado tipo option, tipo should be selected
    Given Open Chrome and logIn as admin
    And Visit modificar empleado page
    And User click empleado tipo field
    When User click empleado tipo option
    Then Option empelado tipo should be selected

  Scenario: When user select empleado fijo option, fijo should be selected
    Given Open Chrome and logIn as admin
    And Visit modificar empleado page
    And User click empleado fijo field
    When User click empleado fijo option
    Then Option empelado fijo should be selected