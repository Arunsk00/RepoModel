@ADDRESS
Feature: Address Module API automation

  Scenario Outline: Verify add user address to the application through API
    Given User add header and bearer authentication for accessing addUserAddress endpoint
    When User add request body for add new address "<first_name>","<last_name>","<mobile>","<apartment>", <state> , <city> , <country> ,"<zipcode>","<address>" and "<address_type>"
    And User send "POST" request for addUserAddress endpoint
    Then User verify the status code is 200
    Then User verify the addUserAddress response message matches "Address added successfully" and save the addressId

    Examples: 
      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address   | address_type |
      | Saral      | Kumar     | 9876543210 | ELITE     |    72 | 1234 |     321 |  607103 | Cuddalore | Home         |

  Scenario Outline: Verify update user address to the application through API
    Given User add header and bearer authentication for accessing updateUserAddress endpoint
    When User add request body for update address by passing "addressId", "<first_name>","<last_name>","<mobile>","<apartment>", <state> , <city> , <country> ,"<zipcode>","<address>" and "<address_type>"
    And User send "PUT" request for updateUserAddress endpoint
    Then User verify the status code is 200
    Then User verify the updateUserAddress response message matches "Address updated successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address   | address_type |
      | Saral      | Kumar     | 9876543210 | ELITE     |    72 | 1234 |     321 |  607103 | Cuddalore | Home         |

  Scenario: Verify get user address to the application through API
    Given User add header and bearer authentication for accessing getUserAddress endpoint
    When User send "GET" request for getUserAddress endpoint
    Then User verify the status code is 200
    Then User verify the getUserAddress response message matches "OK"

  Scenario: Verify delete user address to the application through API
    Given User add header and bearer authentication for accessing deleteUserAddress endpoint
    When User add request body for delete address by addressId
    And User send "DELETE" request for deleteAddress endpoint
    Then User verify the status code is 200
    Then User verify the deleteUserAddress response message matches "Address deleted successfully"
