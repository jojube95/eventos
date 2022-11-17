Feature: Empleado historial page should show all empleado events
  Scenario: When user acces historial page, correct data should be shown
    Given Open Chrome and logIn as admin
    And Visit empleado historial page
    And Set empleado historial dates to avoid future test fails
    Then Historial data should be correct