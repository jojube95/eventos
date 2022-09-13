Feature: Ver evento page should display event data and correct button actions

  Scenario: Visit verEvento page should display correct event data
    Given Open Chrome and logIn as admin
    And Visit verEvento page
    Then Event should display correct data

  Scenario: Click on modificar button should redirect to updateEvento page with current evento id
    Given Open Chrome and logIn as admin
    And Visit verEvento page
    When User click action button
    And User click modificar action
    Then Page should redirect to modificar page with currect evento


  Scenario: Click on mesas button should redirect to mesas page with currect evento id
    Given Open Chrome and logIn as admin
    And Visit verEvento page
    When User click action button
    And User click mesas action
    Then Page should redirect to mesas page with currect evento

  Scenario: Click on protagonistas button should redirect to protagonistas page with currect evento id
    Given Open Chrome and logIn as admin
    And Visit verEvento page
    When User click action button
    And User click protagonistas action
    Then Page should redirect to protagonistas page with currect evento

  Scenario: Click on empleados button should redirect to mesas page with currect evento id
    Given Open Chrome and logIn as admin
    And Visit verEvento page
    When User click action button
    And User click empleados action
    Then Page should redirect to empelados page with currect evento

  Scenario: Click on calcular personas button should show modal with the correct personas
    Given Open Chrome and logIn as admin
    And Visit verEvento page
    When User click action button
    And User click calcular personas action
    Then Calcular personas modal should display
    And Calcular personas modal should display correct data

  @modifyDatabase
  Scenario: Click on modal calcular personas modal dialog aceptar should change event personas
    Given Open Chrome and logIn as admin
    And Visit verEvento page
    When User click action button
    And User click calcular personas action
    When User click aceptar
    Then Modal calcular personas should hide
    And Personas is asigned


  Scenario: Click on modal calcular personas modal dialog cancelar dont change event personas
    Given Open Chrome and logIn as admin
    And Visit verEvento page
    When User click action button
    And User click calcular personas action
    And User click cancelar
    Then Modal calcular personas should hide
    And Personas still same

  Scenario: Click on eliminar should show eliminar modal dialog
    Given Open Chrome and logIn as admin
    And Visit verEvento page
    When User click action button
    And User click eliminar action
    Then Eliminar evento modal should display
    And Eliminar evento modal content should be correct


  @modifyDatabase
  Scenario: Click on eliminar modal dialog aceptar should delete event
    Given Open Chrome and logIn as admin
    And Visit verEvento page
    When User click action button
    And User click eliminar action
    Then Eliminar evento modal should display
    When User click eliminar confirm
    Then Redirect to calendar page

  Scenario: Click on eliminar modal dialog cerrar dont delete event
    Given Open Chrome and logIn as admin
    And Visit verEvento page
    When User click action button
    And User click eliminar action
    Then Eliminar evento modal should display
    When User click cerrar confirm
    Then Eliminar modal should hide