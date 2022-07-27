Feature: Navbar should navigate to other pages

  Scenario: Click on calendario from other page
    Given Open Chrome and visit verEventos page
    When User click on calendario navbar
    Then Calendar page is shown

  Scenario: Click on anyadir evento from other page
    Given Open Chrome and visit calendar page
    When User click on eventos navbar
    Then Dropdown buttons navbar is shown
    When User click on anyadir navbar
    Then Anyadir evento page is shown

  Scenario: Click on ver evento from other page
    Given Open Chrome and visit calendar page
    When User click on eventos navbar
    Then Dropdown buttons navbar is shown
    When User click on ver navbar
    Then Ver eventos page is shown
