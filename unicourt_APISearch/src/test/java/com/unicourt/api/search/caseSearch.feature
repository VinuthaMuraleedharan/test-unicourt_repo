Feature: Case search

Background:
#* def accessToken = token

Scenario Outline: To perform court case seach based on case name.

Given url baseUrl
And path '/caseSearch'
And param q = 'caseName:'+<case>
And param sort = 'filedDate'
And param order = 'desc'
And param pageNumber = 1
And header Authorization = 'Bearer '+ token
When method GET
* print response
Then status 200
* def casename = karate.jsonPath(response,"$..caseName")
* print casename
* match each karate.lowerCase(casename) contains karate.lowerCase(<case>)

Examples:
|case    |
|'Google'|



