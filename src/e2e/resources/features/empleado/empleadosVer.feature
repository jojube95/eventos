Feature: Page empleados where user can see empleados and modify, delete and see empleado history
  Scenario: Visit empleados page should show empleados
    Given Open Chrome and logIn as admin
    And Visit empleados page
    Then Table should show three empleados

  Scenario: Click on modificar empleado should redirect to modificar empleado page
    Given Open Chrome and logIn as admin
    And Visit empleados page
    When User click on modificar empleado
    Then Page should redirect to modificar empleado page

  Scenario: Click on historial empleado should redirect to historial empleado page
    Given Open Chrome and logIn as admin
    And Visit empleados page
    When User click on historial empleado
    Then Page should redirect to historial empleado page

  #Scenario: Click on eliminar empleado should show confirm modal
    #Given Open Chrome and logIn as admin
    #And Visit empleados page
    #When User click on eliminar empleado
    #Then Eliminar empleado confirm modal should show

  #@modifyDatabase
  #Scenario: Click on eliminar empleado confirm modal should delete empleado
    #Given Open Chrome and logIn as admin
    #And Visit empleados page
    #When User click on eliminar empleado
    #Then Eliminar empleado confirm modal should show
    #When User click on eliminar confirm button
    #Then Eliminar empleado confirm modal should be hide
    #And Empleado should be deleted
