@SEARCHPRODUCT
Feature: Search Product Module API automation

  Scenario Outline: Verify search product in the application through API
    Given User add header for searchProduct
    When User add request body to get productList by passing "<product>"
    And User send "POST" request for searchProduct endpoint
    Then User verify the status code is 200
    Then User verify the searchProduct response message matches "OK"

    Examples: 
      | product |
      | Nuts    |

 