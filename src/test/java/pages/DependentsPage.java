package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.CommonMethods;

import java.util.List;

public class DependentsPage extends CommonMethods {
    @FindBy(css = "input#btnAddDependent")
    public WebElement btnAddDependent;

    @FindBy(id = "dependent_name")
    public WebElement dependent_name;

    @FindBy(id = "dependent_relationshipType")
    public WebElement dependent_relationshipType;

    @FindBy(id = "dependent_dateOfBirth")
    public WebElement dependent_dateOfBirth;

    @FindBy(id = "btnSaveDependent")
    public WebElement btnSaveDependent;

    @FindBy(className = "ui-datepicker-div")
    public WebElement dobPicker;

    @FindBy(className = "ui-datepicker-year")
    public WebElement dateYear;

    @FindBy(id = "dependent_list")
    public List<WebElement> dependent_list;
}