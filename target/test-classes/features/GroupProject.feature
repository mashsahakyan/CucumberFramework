Feature: TestCase3
  @BBproject
  Scenario:Admin should be able to add employee's multiple dependents
    Given admin user is logged in to Syntax HRMS web app with valid admin credentials
    When admin user navigates to Employee List
    And admin user searches an employee by employee id
    Then employee information page is displayed
    When admin user clicks on Dependents tab
    And admin user clicks on Assigned Dependents Add button
    Then Name Relationship and DOB are displayed and editable