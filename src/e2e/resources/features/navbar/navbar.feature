Feature: Navbar should navigate to other pages

  Scenario: Click on calendario. Click on anyadir evento. Click on ver evento. Click on anyadir empleado. Click on ver empleados.
    Given Open Chrome and logIn as admin
    And Visit eventosVer page
    When User click on calendario navbar
    Then Calendar page is shown
    Given Visit calendar page
    When User click on eventos navbar
    Then Dropdown buttons navbar is shown
    When User click on anyadir navbar
    Then Anyadir evento page is shown
    Given Visit calendar page
    When User click on eventos navbar
    Then Dropdown buttons navbar is shown
    When User click on ver navbar
    Then Ver eventos page is shown
    Given Visit calendar page
    When User click on empleado navbar
    Then Dropdown buttons empleado navbar is shown
    When User click on anyadir empleado navbar
    Then Anyadir empleado page is shown
    Given Visit calendar page
    When User click on empleado navbar
    Then Dropdown buttons empleado navbar is shown
    When User click on ver empleado navbar
    Then Ver empleados page is shown

  Scenario: Click on logout should close sesion and redirect to login page
    Given Open Chrome and logIn as admin
    And Visit calendar page
    When User click logout button
    Then Redirect to login page
    And Logout message shows