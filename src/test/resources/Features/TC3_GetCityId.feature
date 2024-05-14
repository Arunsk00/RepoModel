@GETCITYID
Feature: GetCityId Module API automation

  Scenario: Get State Id from cityList endpoint
    Given User add header for cityList
    When User add request body to get cityList by passing stateId
    And User send "POST" request for cityList endpoint
    Then User verify the status code is 200
    Then User verify the cityList response body matches "Panruti" and save the cityId

   