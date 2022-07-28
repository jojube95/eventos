Feature: Calendar page that shows all events

  Scenario: Visit calendar page on julio de 2022
    Given Open Chrome and visit calendar page
    And Calendar will be displayed
    And Date should be julio de 2022
    Then User will see 4 different eventos
    And First event title should be Boda-Cena
    And First event should be placed on day 2
    And Second event title should be Comunion-Comida
    And Second event should be placed on day 10
    And Third event title should be Sopar festes-Cena
    And Third event should be placed on day 16
    And Fourth event title should be Dinar empresa-Comida
    And Fourth event should be placed on day 23

  Scenario: Visit calendar page and navigate month later
    Given Open Chrome and visit calendar page
    When User click next month
    Then Date should be agosto de 2022
    And No event is shown

  Scenario: Visit calendar page and navigate month past
    Given Open Chrome and visit calendar page
    When User click last month
    Then Date should be junio de 2022
    And 2 events is shown

  Scenario: Visit calendar page and return to today
    Given Open Chrome and visit calendar page
    When User click next month
    And User click today
    Then Date should be julio de 2022

  Scenario: Click on event show modal dialog with detail data and three buttons
    Given Open Chrome and visit calendar page
    When User click evento
    Then Event modal detail dialog is shown
    And Event modal detail dialog show correct event data
    And Event modal detail dialog have three buttons

  Scenario: Click on ver redirects to verEvento page

  Scenario: Click on modificar redirects to updateEvento page

  Scenario: Click on cerrar close modal dialog