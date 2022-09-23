Feature: Calendar page that shows all events

  Scenario: Visit calendar page on julio de 2022
    Given Open Chrome and logIn as admin
    And Visit root page
    Then Page should redirect to calendar page
    And Calendar will be displayed
    And Date should be julio de 2022
    And User will see 4 different eventos
    And First event title should be Boda-Cena
    And First event should be placed on day 2
    And Second event title should be Comunion-Comida
    And Second event should be placed on day 10
    And Third event title should be Sopar festes-Cena
    And Third event should be placed on day 16
    And Fourth event title should be Dinar empresa-Comida
    And Fourth event should be placed on day 23

  Scenario: Visit calendar page and navigate month later, and month past
    Given Open Chrome and logIn as admin
    And Visit calendar page
    When User click next month
    Then Date should be agosto de 2022
    And No event is shown
    When User click last month
    Then Date should be junio de 2022
    And 2 events is shown

  Scenario: Click on event redirects to eventoVer page
    Given Open Chrome and logIn as admin
    And Visit calendar page
    When User click evento
    Then Redirect to eventoVer page