Feature: Ver evento page should display event data and correct button actions

  Scenario: Click on modificar button should redirect to eventoUpdate page with current evento id
    Given Open Chrome and logIn as admin
    And Visit eventoVer page
    When User click action button
    And User click modificar action
    Then Page should redirect to modificar page with currect evento


  Scenario: Click on mesas button should redirect to mesas page. Click on protagonistas button should redirect to protagonistas page. Click on empleados button should redirect to mesas page.
    Given Open Chrome and logIn as admin
    And Visit eventoVer page
    When User click action button
    And User click mesas action
    Then Page should redirect to mesas page with currect evento
    Given Visit eventoVer page
    When User click action button
    And User click protagonistas action
    Then Page should redirect to protagonistas page with currect evento
    Given Visit eventoVer page
    When User click action button
    And User click empleados action
    Then Page should redirect to empelados page with currect evento

  @modifyDatabase
  Scenario: Click on modal calcular personas modal dialog aceptar should change event personas
    Given Open Chrome and logIn as admin
    And Visit eventoVer page
    When User click action button
    And User click calcular personas action
    Then Calcular personas modal should display
    And Calcular personas modal should display correct data
    When User click aceptar
    Then Modal calcular personas should hide
    And Personas is asigned


  Scenario: Click on modal calcular personas modal dialog cancelar dont change event personas
    Given Open Chrome and logIn as admin
    And Visit eventoVer page
    When User click action button
    And User click calcular personas action
    Then Calcular personas modal should display
    And Calcular personas modal should display correct data
    And User click cancelar
    Then Modal calcular personas should hide
    And Personas still same

  @modifyDatabase
  Scenario: Click on eliminar modal dialog aceptar should delete event
    Given Open Chrome and logIn as admin
    And Visit eventoVer page
    Then Event should display correct data
    When User click action button
    And User click eliminar action
    Then Eliminar evento modal should display
    And Eliminar evento modal content should be correct
    When User click eliminar confirm
    Then Redirect to calendar page

  Scenario: Click on eliminar modal dialog cerrar dont delete event
    Given Open Chrome and logIn as admin
    And Visit eventoVer page
    When User click action button
    And User click eliminar action
    Then Eliminar evento modal should display
    And Eliminar evento modal content should be correct
    When User click cerrar confirm
    Then Eliminar modal should hide