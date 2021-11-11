Feature: Buscar alojamientos

  @searchInColombiaCity
  Scenario: Busqueda de alojamientos disponibles en un una ciudad de Colombia
    Given He ingreasado a www.airbnb.com.co
    When He ingresado la ubicacion <ubicacion> sobre la barra de busqueda
    And He seleccionado las fechas Actual mas <llegada> dias / Actual mas <salida> dias
    And He establecido el numero de adultos en <nAdultos>
    And He establecido el numero de ninnos en <nNinnos>
    And He establecido el numero de bebes en <nBebes>
    And Presiono el boton del icono con lupa
    Then Soy redirigido a la pagina de resultados
    And Se presentan los alojamientos disponibles con su informacion
    And Se presentan las opciones de filtrado
    And Se presenta un mapa con la ubicacion de los alojamientos

    Examples: 
      | ubicacion                        | llegada | salida | nAdultos | nNinnos | nBebes | 
      | Taganga, Santa Marta - Magdalena | 0       | 2      | 2        | 0       | 0      | 
      | Cali - Valle del Cauca           | 1       | 3      | 2        | 1       | 1      | 
      | Nuquí - Chocó                    | 2       | 4      | 3        | 0       | 0      |
      
	 @searchWithEmptyLocation   
	 Scenario Outline: Busqueda de alojamientos cuando el campo ubicacion esta vacio
    Given He ingreasado a www.airbnb.com.co
    When He dejado vacio el campo de ubicacion
    And He seleccionado las fechas Actual mas <llegada> dias / Actual mas <salida> dias
    And He establecido el numero de adultos en <nAdultos>
    And He establecido el numero de ninnos en <nNinnos>
    And He establecido el numero de bebes en <nBebes>
    And Presiono el boton del icono con lupa
    Then El sistema realiza el focus sobre el campo ubicacion para escribir algo
  
    Examples: 
      | llegada | salida | nAdultos | nNinnos | nBebes | 
      | 0       | 2      | 2        | 0       | 0      | 
      | 1       | 3      | 2        | 1       | 1      |
