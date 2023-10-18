package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    static WebDriver driver;


    public static WebDriver getDriver() {
        String browser = ConfigReader.getProperty("browser");
        if (driver == null || ((RemoteWebDriver) driver).getSessionId() == null) {
            if (browser.equals("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if (browser.equals("edge")) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            } else if (browser.equals("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if (browser.equals("ie")) {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            } else if (browser.equals("opera")) {
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
            }
        } else {
            return driver;
        }
        assert driver != null;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

}
