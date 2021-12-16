package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.*;
import utils.CommonMethods;
import utils.ConfigReader;

import java.util.Hashtable;
import java.util.List;

public class GPSteps extends CommonMethods{
    @Given("admin user is logged in to Syntax HRMS web app with valid admin credentials")
    public void admin_user_is_logged_in_to_syntax_hrms_web_app_with_valid_admin_credentials() {
        LoginPage login = new LoginPage();
        sendText(login.usernameBox, ConfigReader.getPropertyValue("username"));
        sendText(login.passwordBox, ConfigReader.getPropertyValue("password"));
        click(login.loginBtn);
    }

    @When("admin user navigates to Employee List")
    public void admin_user_navigates_to_employee_list() throws InterruptedException {
        DashboardPage dashboardPage = new DashboardPage();
        click(dashboardPage.pimOption);
        click(dashboardPage.employeeListOption);
    }

    @When("admin user searches an employee by employee id")
    public void admin_user_searches_an_employee_by_employee_id() {
        EmployeeListPage emp = new EmployeeListPage();
        sendText(emp.idEmployee, "21723000");
        click(emp.searchButton);
        click(emp.emID);

    }

    @Then("employee information page is displayed")
    public void employee_information_page_is_displayed() {
        System.out.println("Employee information page is displayed");
    }

    @When("admin user clicks on Dependents tab")
    public void admin_user_clicks_on_dependents_tab() {
        PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage();
        personalDetailsPage.dependentsTab.click();
    }

    @When("admin user clicks on Assigned Dependents Add button")
    public void admin_user_clicks_on_assigned_dependents_add_button() {
        DependentsPage dependentsPage = new DependentsPage();
        click(dependentsPage.btnAddDependent);
        sendText(dependentsPage.dependent_name, "kjahgjk");
        Select relationshipType = new Select(dependentsPage.dependent_relationshipType);
        relationshipType.selectByVisibleText("Child");
        click(dependentsPage.dobPicker);
        click(dependentsPage.dateYear);
        Select year = new Select(dependentsPage.dateYear);
        year.selectByVisibleText("1996");
        dependentsPage.btnSaveDependent.click();
    }

    @Then("Name Relationship and DOB are displayed and editable")
    public void name_relationship_and_dob_are_displayed_and_editable() {
        DependentsPage dependentsPage = new DependentsPage();
        List<WebElement> dataTable = dependentsPage.dependent_list;
        Assert.assertTrue(dataTable.contains("Name"));
        System.out.println("Assertion is true");
    }
}
