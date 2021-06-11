Feature: Smoke Test Execution For ePermit Internal Application
  #Jyoti
  @ePermitInternals_SmokeTest
  Scenario: All tabs are rendered without errors in Internal application
    Given User is on the login page
    Then User verify the username and password field on login page
    Then User login into the application by entering username and password
      | username | password |
      | p8admin_demo  | V3ga123456 |
    Then Title of the home page is "CT DEEP Permits and Licenses"
    Then User check following tabs are present into the application
    |TabName|
    |Supervisor Tasks|
    |Program Tasks   |
    |Personal Tasks  |
    Then User able to render on "Supervisor Tasks" tab without error in internal application
    Then User able to render on "Program Tasks" tab without error in internal application
    Then User able to render on "Personal Tasks" tab without error in internal application

  @ePermitInternals_SmokeTest
  Scenario: User can open any existing case and didi not get errors on Supervisor Tasks tabs
    Given User is on the login page
    Then User verify the username and password field on login page
    Then User login into the application by entering username and password
      | username | password |
      | p8admin_demo  | V3ga123456 |
    Then User is on the home page
    Then User click on "Supervisor Tasks" tab.
    Then User checks existing case is present in Supervisor Tasks having status not Draft
#    Then User open the existing case
    Then User open the existing case from the "Supervisor Tasks" tab.
    Then User check following tabs are present into the application
      |TabName|
      |Common Properties|
      |Application      |
      |Documents|
      |History/Comments|
    Then User able to render on "Common Properties" tab without error in internal application
    Then User able to render on "Application" tab without error in internal application
    Then User able to render on "Documents" tab without error in internal application
    Then User able to render on "History/Comments" tab without error in internal application


  @ePermitInternals_SmokeTest
  Scenario: User can open any existing case and didi not get errors on Personal Tasks tabs
    Given User is on the login page
    Then User verify the username and password field on login page
    Then User login into the application by entering username and password
      | username | password |
      | p8admin_demo  | V3ga123456 |
    Then User is on the home page
    Then User click on "Personal Tasks" tab.
    Then User checks existing case is present in my application having status not Draft
    Then User open the existing case from the "Personal Tasks" tab.
    Then User check following tabs are present into the application
      |TabName|
      |Common Properties|
      |Application      |
      |Documents|
      |History/Comments|
    Then User able to render on "Common Properties" tab without error in internal application
    Then User able to render on "Application" tab without error in internal application
    Then User able to render on "Documents" tab without error in internal application
    Then User able to render on "History/Comments" tab without error in internal application

  @ePermitInternals_SmokeTest
  Scenario: User can open any existing case and didi not get errors on Program Tasks tabs
    Given User is on the login page
    Then User verify the username and password field on login page
    Then User login into the application by entering username and password
      | username | password |
      | p8admin_demo  | V3ga123456 |
    Then User is on the home page
    Then User click on "Program Tasks" tab.
    Then User checks existing case is present in Supervisor Tasks having status not Draft
#    Then User open the existing case
    Then User open the existing case from the "Program Tasks" tab.
    Then User check following tabs are present into the application
      |TabName|
      |Common Properties|
      |Application      |
      |Documents|
      |History/Comments|
    Then User able to render on "Common Properties" tab without error in internal application
    Then User able to render on "Application" tab without error in internal application
    Then User able to render on "Documents" tab without error in internal application
    Then User able to render on "History/Comments" tab without error in internal application
