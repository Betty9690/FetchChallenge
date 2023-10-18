package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.rules.Timeout;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.FetchMainPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.lang.module.FindException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FetchSteps {
    WebDriver driver= Driver.getDriver();
    FetchMainPage fetchMainPage=new FetchMainPage();
    List<String> fakeGoldBar=new ArrayList<>();


    @Given("User navigates to Fetch Application")
        public void user_navigates_to_fetch_application() {
            driver.get(ConfigReader.getProperty("FetchURL"));
        }

        @When("User input gold bar numbers")
        public void user_input_gold_bar_numbers() throws InterruptedException {
        int count=0;
            LOOP:
            for(int i=0;i<fetchMainPage.listOfNumbers.size()-1;i++){
                count++;
                String numbers= (fetchMainPage.listOfNumbers.get(i).getText());
                for(int j=i+1;j<fetchMainPage.listOfNumbers.size();j++){
                    String secondNumbers= (fetchMainPage.listOfNumbers.get(j).getText());
                    fetchMainPage.firstLeftBowl.sendKeys(numbers);
                    fetchMainPage.firstRightBowl.sendKeys(secondNumbers);
                    fetchMainPage.weightButton.click();
                    Thread.sleep(4000);
                    for(int b=0;b<fetchMainPage.weightings.size();b++){
                        count=b;
                    }System.out.println(fetchMainPage.weightings.get(count).getText());
                    fetchMainPage.resetButton.click();
                    String value=fetchMainPage.weightings.get(count).getText();
                    String sign=fetchMainPage.weightings.get(count).getText().charAt(7)+"";
                    char sign2=value.charAt(4);
                    if ((sign2=='>') || (sign2=='<')){
                        fakeGoldBar.add(sign);
                        break LOOP;
                    }
                }
            }
            System.out.println("Fake gold bar is found: "+fakeGoldBar);
            String fake= (fakeGoldBar.get(0).substring(0,1));
            for(int i=0;i<fetchMainPage.listOfNumbers.size();i++){
                if(fake.equals(fetchMainPage.listOfNumbers.get(i).getText())){
                    fetchMainPage.listOfNumbers.get(i).click();
                    break;
                }
            }
        }

        @Then("User find the fake gold bar weight")
        public void user_find_the_fake_gold_bar_weight(){
           driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            Alert alert=driver.switchTo().alert();
            String alertMessage=alert.getText();
            System.out.println(alertMessage);
            String actualMessage="Yay! You find it!";
            Assert.assertEquals(actualMessage,alertMessage);


        }


}
