package common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CommonUI {
    public WebDriver driver;
    public CommonUI(WebDriver y){
        driver = y;
    }
    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Sleep in " + timeInSecond + " seconds");
    }
    public void scrollToViewElement(By by){
        WebElement element = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public boolean checkDisplay(By by){
        return driver.findElement(by).isDisplayed();
    }
    public void clickOnElement(By by){
        driver.findElement(by).click();
    }
    public void goToUrl(String url){
        driver.get(url);
    }
    public void setText(By by, String value){
        driver.findElement(by).sendKeys(value);
    }
    public String getText(By by){
        return driver.findElement(by).getText();
    }
    public void verifyMessage(String actualMessage, String expectMessage){
        Assert.assertTrue(actualMessage.equals(expectMessage));
    }
    public void assertTrue(boolean condition){
        Assert.assertTrue(condition);
    }
}
