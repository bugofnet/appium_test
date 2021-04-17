import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimpleTest {
    AndroidDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");

        File file = new File("src/test/resources/", "app-release.apk");
        desiredCapabilities.setCapability("app", file.getAbsolutePath());

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterTest
    public void close(){
        driver.quit();
    }

    @Test
    public void setGoalTest(){
        AndroidElement newGoalButton = (AndroidElement) driver.findElement(By.id("com.example.howmuchcanisave:id/buttonAddNewSavingGoal"));
        newGoalButton.click();

        AndroidElement fieldNewGoalName = (AndroidElement) driver.findElement(By.id("com.example.howmuchcanisave:id/goalName"));
        fieldNewGoalName.sendKeys("New goal");

        AndroidElement fieldGoalAmount= (AndroidElement) driver.findElement(By.id("com.example.howmuchcanisave:id/amount"));
        fieldGoalAmount.sendKeys("100");

        AndroidElement spinnerCurButton = (AndroidElement) driver.findElement(By.id("com.example.howmuchcanisave:id/currency"));
        spinnerCurButton.click();

        AndroidElement spinnerCurUSD = (AndroidElement) driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='USD']"));
        spinnerCurUSD.click();

        AndroidElement buttonCreateGoal = (AndroidElement) driver.findElement(By.id("com.example.howmuchcanisave:id/create"));
        buttonCreateGoal.click();


        AndroidElement labelCurrentGoalName = (AndroidElement) driver.findElement(By.id("com.example.howmuchcanisave:id/currentGoalName"));
        Assert.assertEquals(labelCurrentGoalName.getText(), "New goal", "Text is not equal.");

        AndroidElement labelCurrentGoalAmount = (AndroidElement) driver.findElement(By.id("com.example.howmuchcanisave:id/currentGoalAmount"));
        Assert.assertEquals(labelCurrentGoalAmount.getText(), "100", "Text is not equal.");

        AndroidElement labelCurrentGoalCurrency = (AndroidElement) driver.findElement(By.id("com.example.howmuchcanisave:id/currentGoalCurrency"));
        Assert.assertEquals(labelCurrentGoalCurrency.getText(), "USD", "Text is not equal.");

        AndroidElement buttonAddNewSave = (AndroidElement) driver.findElement(By.id("com.example.howmuchcanisave:id/addNewSave"));
        Assert.assertTrue(buttonAddNewSave.isDisplayed(), "Button add new save is not displayed.");

    }

}
