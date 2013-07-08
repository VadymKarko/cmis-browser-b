Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: User goes to the cmis-browser home page

Given user goes on page by url 'http://localhost:8080/browser/home.xhtml'
Then page is shown

Scenario: User clicks tree folder, moves next page, clicks other tree folder, moves Back and see selected previous folder

Given user goes on page by url 'http://localhost:8080/browser/home.xhtml'
When user clicks in the tree folder by xpath './/*[@id='form:tree:0:link']'
And user clicks in the tree folder by xpath './/*[@id='form:tree:1:link']'
And user clicks navigation button Back
Then in the tree folder by previous xpath is selected

Scenario: User clicks tree folder, moves next page, clicks other tree folder, moves Back, clicks other tree folder, moves Forward and see selected current folder

Given user goes on page by url 'http://localhost:8080/browser/home.xhtml'
When user clicks in the tree folder by xpath './/*[@id='form:tree:0:link']'
And user clicks in the tree folder by xpath './/*[@id='form:tree:1:link']'
And user clicks navigation button Back
And user clicks in the tree folder by xpath './/*[@id='form:tree:1:link']'
And user clicks navigation button Forward
Then in the tree folder by current xpath is selected

