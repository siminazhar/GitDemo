Feature: Validating place API

@AddPlace
Scenario: Verify if place is added successfully using Add Place API
	Given Add Place Payload with "<name>""<language>""<address>"
	When User calls "AddPlaceAPI" with "POST" HTTP request
	Then API call is success with status code 200
	And "status" in response body is "OK"
	And verify place_id create maps to "<name>" using "GetPlaceAPI"
	
	Examples:
		| name   | language |address				   |
		| simin1 |	English |Business Bay Dubai|

@DeletePlace
Scenario: Verify if Delete Place functionality is working 
	Given Delete place payload
		When User calls "DeletePlaceAPI" with "POST" HTTP request
		Then API call is success with status code 200
		And "status" in response body is "OK"