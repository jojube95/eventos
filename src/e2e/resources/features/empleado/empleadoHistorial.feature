Feature: Empleado historial page should show all empleado events
  Scenario: When user acces historial page, correct data should be shown
    Given Open Chrome and logIn as admin
    And Visit empleado historial page
    Then Historial data should be correct