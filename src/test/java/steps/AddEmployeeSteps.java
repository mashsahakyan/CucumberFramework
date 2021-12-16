package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import org.junit.Assert;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.PersonalDetailsPage;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReading;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        DashboardPage dashboardPage = new DashboardPage();
        click(dashboardPage.pimOption);
    }

    @When("user clicks on Add Employee button")
    public void user_clicks_on_add_employee_button() {
        DashboardPage dashboardPage = new DashboardPage();
        click(dashboardPage.addEmployeeButton);
    }

    @When("user enters firstname middlename and lastname")
    public void user_enters_firstname_middlename_and_lastname() {
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        sendText(addEmployeePage.firstName, "John40");
        sendText(addEmployeePage.middleName, "B");
        sendText(addEmployeePage.lastName, "Snow40");
    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        click(addEmployeePage.saveBtn);
    }

    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee added successfully");
        PersonalDetailsPage personalDetailPage = new PersonalDetailsPage();
        String personalName = personalDetailPage.personalPageFirstName.getText();
        Assert.assertTrue(personalDetailPage.personalPageFirstName.isDisplayed());
    }

    @When("user deletes employee id")
    public void user_deletes_employee_id() {
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        addEmployeePage.employeeId.clear();
    }

    @When("user selects checkbox")
    public void user_selects_checkbox() {
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        click(addEmployeePage.createLoginCheckBox);
    }

    @When("user enters username password and confirm password")
    public void user_enters_username_password_and_confirm_password() {
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        sendText(addEmployeePage.createUsername, "johnsnow40");
        sendText(addEmployeePage.createPassword, "Hum#123123");
        sendText(addEmployeePage.rePassword, "Hum#123123");
    }

    @When("user enters {string} {string} and {string}")
    public void user_enters_and(String firstName, String middleName, String lastName) {
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        sendText(addEmployeePage.firstName, firstName);
        sendText(addEmployeePage.middleName, middleName);
        sendText(addEmployeePage.lastName, lastName);
    }

    @And("user enters {string} {string} and {string} for an employee")
    public void userEntersAndForAnEmployee(String firstName, String middleName, String lastName) {
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        sendText(addEmployeePage.firstName, firstName);
        sendText(addEmployeePage.middleName, middleName);
        sendText(addEmployeePage.lastName, lastName);
    }

    @When("I add multiple employees and verify them that user has been added successfully")
    public void i_add_multiple_employees_and_verify_them_that_user_has_been_added_successfully(DataTable employees) throws InterruptedException {

        List<Map<String, String>> employeeNames = employees.asMaps();
        for (Map<String, String> employeeName : employeeNames) {
            String valueFirstName = employeeName.get("firstName");
            String valueMiddleName = employeeName.get("middleName");
            String valueLastName = employeeName.get("lastName");

            AddEmployeePage addEmployeePage = new AddEmployeePage();
            sendText(addEmployeePage.firstName, valueFirstName);
            sendText(addEmployeePage.middleName, valueMiddleName);
            sendText(addEmployeePage.lastName, valueLastName);

            click(addEmployeePage.saveBtn);

            // assert for HW
            PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage();
            Assert.assertTrue(personalDetailsPage.personalPageFirstName.isDisplayed());

            Thread.sleep(2000);

            DashboardPage dashboardPage = new DashboardPage();
            click(dashboardPage.addEmployeeButton);

        }
    }

    @When("user clicks on PIM option and Add Employee option")
    public void user_clicks_on_pim_option_and_add_employee_option() {
        DashboardPage dashboardPage = new DashboardPage();
        click(dashboardPage.pimOption);
        click(dashboardPage.addEmployeeButton);
    }

    @Then("user is navigated to add employee page")
    public void user_is_navigated_to_add_employee_page() {
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        Assert.assertTrue(addEmployeePage.addEmployeeHeader.isDisplayed());

        //another way of verification
        String actualText = addEmployeePage.addEmployeeHeader.getText();
        String expectedText = "Add Employee";
        Assert.assertEquals("Values don't match",expectedText, actualText);
    }

    @When("user adds multiple employees from excel file using {string} sheet and verify the added employee")
    public void user_adds_multiple_employees_from_excel_file_using_sheet_and_verify_the_added_employee(String sheetName) {
        List<Map<String, String >> newEmployees = ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH, sheetName);

        DashboardPage dashboardPage = new DashboardPage();
        AddEmployeePage addEmployeePage = new AddEmployeePage();

        Iterator<Map<String, String>> iterator = newEmployees.iterator();
        while(iterator.hasNext()){
            Map<String, String> mapNewEmployees = iterator.next();
            sendText(addEmployeePage.firstName, mapNewEmployees.get("FirstName"));
            sendText(addEmployeePage.middleName, mapNewEmployees.get("MiddleName"));
            sendText(addEmployeePage.lastName, mapNewEmployees.get("LastName"));
            click(addEmployeePage.saveBtn);

            //Assertion in HW
//            PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage();
//            String verificationMessageExpected = valueFirstName + " " + valueMiddleName + " " + valueLastName;
//            System.out.println(verificationMessageExpected);
//            String verificationMessageActual = personalDetailsPage.employeeVerification.getText();
//            System.out.println(verificationMessageActual);
//            Assert.assertEquals(verificationMessageExpected, verificationMessageActual);
//            System.out.println("Verification /employee is added/ is successful");


            click(dashboardPage.addEmployeeButton);
        }
    }
}