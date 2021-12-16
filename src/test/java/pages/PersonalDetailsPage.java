package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class PersonalDetailsPage extends CommonMethods {
    @FindBy(id = "personal_txtEmpFirstName")
    public WebElement personalPageFirstName;

    @FindBy(id = "personal_txtEmpMiddleName")
    public WebElement personalPageMiddleName;

    @FindBy(id = "personal_txtEmpLastName")
    public WebElement personalPageLastName;

    @FindBy(xpath = "//div[@id='profile-pic']/h1")
    public WebElement employeeVerification;

    @FindBy(linkText = "Dependents")
    public WebElement dependentsTab;

    public PersonalDetailsPage(){
        PageFactory.initElements(driver, this);
    }
}