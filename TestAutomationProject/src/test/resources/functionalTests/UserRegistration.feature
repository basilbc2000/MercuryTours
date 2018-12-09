Feature: User Registration

@testing
Scenario Outline: User performs registration
	Given user opens the home page
	And register link is clicked
	And enters basic information for customer "<customer>"
	When submit is clicked
	Then confirmation page is opened

Examples:
|customer|
|Test01|
