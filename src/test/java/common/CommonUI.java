package common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Set;

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
        System.out.println("Actual Message: " + actualMessage);
        System.out.println("expectMessage: " + expectMessage);
        Assert.assertTrue(actualMessage.equals(expectMessage));
    }
    public void assertTrue(boolean condition){
        Assert.assertTrue(condition);
    }
    public List<WebElement> listElement(By by){
        return driver.findElements(by);
    }

    public void verifyCurrentUrl(String expectUrl){
        Assert.assertTrue(driver.getCurrentUrl().equals(expectUrl));
    }
    public void verifyTitle(String expectTitle){
        Assert.assertTrue(driver.getTitle().equals(expectTitle));
    }

    public void switchFrameByWebElement(By by){
        WebElement frame = driver.findElement(by);
        driver.switchTo().frame(frame);
    }
    public void switchFrameById(String id){
        driver.switchTo().frame(id);
    }
    public void switchToDefaultContent(){
        driver.switchTo().defaultContent();
    }

    //Chỉ dùng trong trường hợp script có mỗi 2 window/tab
    public void switchWindowById(String otherID){
        Set<String> listWindow = driver.getWindowHandles();
        for(String id : listWindow){
            if(!id.equals(otherID)){
                driver.switchTo().window(id);
            }
        }
    }

    //Dùng  trong trường hợp có nhiều hơn 2 windown/tab
    public void switchWindowByTitle(String expectTitle){
        Set<String> listWindow = driver.getWindowHandles();
        for(String id : listWindow){
            driver.switchTo().window(id);
            if(driver.getTitle().equals(expectTitle)){
                sleepInSecond(2);
                break;
            }
        }
    }
}
