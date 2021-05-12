Feature: Validating Place Api
@AddPlace @Regression
Scenario Outline: Verify if Place is being successfully added using AddPlaceApi
Given Add Place Payload with "<name>" "<language>" "<address>"
When user calls "AddPlaceApi" with "POST" http request
Then the API callis success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id created maps to "<name>" using "getPlaceAPI"
      
      Examples:
      | name    | language | address      |
      | AAhouse | English  | World center |
      
@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working
Given DeletePlace Payload
When user calls "deletePlaceAPI" with "POST" http request
Then the API callis success with status code 200
And "status" in response body is "OK"