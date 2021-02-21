package Selenium.TestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Dataprovider2 {

    private WebDriver driver;
    private Actions actions;
    private Select select;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        actions = new Actions(driver);
    }

    @Test(dataProvider = "patientInfo")
    public void registerPatientTest(String firstName, String lastName, String gender, int birthDay, String birthMonth, int birthYear, String address, long phoneNumber, String relative) {
        driver.get("https://demo.openmrs.org/");
        login();
        WebElement registerPatientButton = driver.findElement(By.id("referenceapplication-registrationapp-registerPatient-homepageLink-referenceapplication-registrationapp-registerPatient-homepageLink-extension"));
        actions.click(registerPatientButton).perform();
        //patient name
        WebElement givenName = driver.findElement(By.name("givenName"));
        givenName.sendKeys(firstName);
        //patient last name
        WebElement familyName = driver.findElement(By.name("familyName"));
        familyName.sendKeys(lastName);
        WebElement nextButton = driver.findElement(By.id("next-button"));
        nextButton.click();
        //select gender
        WebElement genderDD = driver.findElement(By.id("gender-field"));
        select = new Select(genderDD);
        select.selectByVisibleText(gender);
        nextButton.click();
        //dob
        WebElement birthDayBox = driver.findElement(By.id("birthdateDay-field"));
        birthDayBox.sendKeys(String.valueOf(birthDay));
        WebElement birthMonthBox = driver.findElement(By.id("birthdateMonth-field"));
        select = new Select(birthMonthBox);
        select.selectByVisibleText(birthMonth);
        WebElement birthYearBox = driver.findElement(By.id("birthdateYear-field"));
        birthYearBox.sendKeys(String.valueOf(birthYear));
        actions.click(nextButton).perform();
        //address
        WebElement addressBox = driver.findElement(By.id("address1"));
        addressBox.sendKeys(address);
        actions.click(nextButton).perform();
        //phone
        WebElement phoneNumberBox = driver.findElement(By.name("phoneNumber"));
        phoneNumberBox.sendKeys(String.valueOf(phoneNumber));
        actions.click(nextButton).perform();
        //relatives
        WebElement relativeDD = driver.findElement(By.id("relationship_type"));
        select = new Select(relativeDD);
        select.selectByVisibleText(relative);
        actions.click(nextButton).perform();
        WebElement submitButton = driver.findElement(By.id("submit"));
        actions.click(submitButton).perform();
    }

    @DataProvider(name = "patientInfo")
    public Object[][] getPatientData() {
        return new Object[][]{
                {"John", "Smith", "Male", 31, "August", 1971, "2200 E Devon ave", 8881112222L, "Sibling"}
        };
    }

    private void login() {
        WebElement usernameBox = driver.findElement(By.id("username"));
        WebElement passwordBox = driver.findElement(By.id("password"));
        usernameBox.sendKeys("admin");
        passwordBox.sendKeys("Admin123");
        WebElement option = driver.findElement(By.id("Inpatient Ward"));
        option.click();
        WebElement login = driver.findElement(By.id("loginButton"));
        login.click();

    }
}