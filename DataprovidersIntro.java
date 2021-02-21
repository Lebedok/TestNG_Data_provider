package Selenium.TestNG;

        import io.github.bonigarcia.wdm.WebDriverManager;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.testng.annotations.AfterMethod;
        import org.testng.annotations.BeforeMethod;
        import org.testng.annotations.DataProvider;
        import org.testng.annotations.Test;

public class DataprovidersIntro {

    protected WebDriver driver;


    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }


    @Test(dataProvider = "MRSTestData")
    public void test(String username, String password){
        driver.get("https://demo.openmrs.org/");
        WebElement userName = driver.findElement(By.id("username"));

        WebElement Password = driver.findElement(By.id("password"));
        userName.sendKeys(username);
        Password.sendKeys(password);
        WebElement option = driver.findElement(By.id("Inpatient Ward"));
        option.click();
        WebElement login = driver.findElement(By.id("loginButton"));
        login.click();
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    @DataProvider(name = "MRSTestData")
    public Object [][] getTestData() {
        return new Object[][]{
                {"admin123", "invalidPassword"},
                {"invalidUserName", "admin123"},
                {" ", " "},
                {" ", "Admin123"}};

    }

}


