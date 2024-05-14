@CHANGEPROFILEPIC
Feature: Change ProfilePic Module API automation

  Scenario: Verify change profile pic in the application through API
    Given User add header and bearer authentication for accessing changeProfilePic endpoint
    When User add request body through formdata "profile_picture"
    And User send "POST" request for changeProfilePic endpoint
    Then User verify the status code is 200
    Then User verify the changeProfilePic response message matches "Profile updated Successfully"
    
   