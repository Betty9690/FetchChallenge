package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utilities.Driver;

public class Hooks {
    WebDriver driver;

    @Before
    public void setUp(){
        driver=Driver.getDriver();
        System.out.println("Before Scenario Method");
    }

    @After
    public void tearDown(){
        driver.quit();
        System.out.println("After Scenario Method");
    }
}
