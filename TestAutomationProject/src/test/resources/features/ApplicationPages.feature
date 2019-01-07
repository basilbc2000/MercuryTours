Feature: Sample Feature
Description: Demo of a feature

Scenario Outline: User performs login
	Given user opens the home page
	And enters username "<user name>"
	And enters password "<password>"
	When login button is clicked
	Then signon page is opened
	
Examples:
|user name|password|
|sample|test|
#|simple|toast|
#|sea|coast|

#Scenario: User opens registration page
#	Given user opens the home page
#	When register link is clicked
#	Then registration page is opened


	
	