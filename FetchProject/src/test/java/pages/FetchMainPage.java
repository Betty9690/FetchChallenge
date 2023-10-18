package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class FetchMainPage {
    WebDriver driver;


    public FetchMainPage(){
        driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "left_0")
    public WebElement firstLeftBowl;

    @FindBy(id = "right_0")
    public WebElement firstRightBowl;

   @FindBy(xpath = "//div[@class='coins']//button")
    public List<WebElement> listOfNumbers;

   @FindBy(id = "weigh")
    public WebElement weightButton;

   @FindBy(xpath = "(//div[@class='game-info']//ol/li)")
    public List<WebElement> weightings;

   @FindBy(xpath = "(//button[@id='reset'])[2]")
    public WebElement resetButton;



}
