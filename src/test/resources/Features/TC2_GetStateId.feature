@GETSTATEID
Feature: GetStateId Module API automation

  Scenario: Get State Id from stateList endpoint
    Given User add header for stateList
    When User send "GET" request for stateList endpoint
    Then User verify the status code is 200
    Then User verify the stateList response body matches "Tamil Nadu" and save the stateId
