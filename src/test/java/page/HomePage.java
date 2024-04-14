package page;

import common.CommonUI;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import report.AllureManager;

import static common.CommonUI.*;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver _driver){
        driver = _driver;
        new CommonUI(driver);
    }

    String URL_HOMEPAGE = "https://yody.vn/";
    String REGISTER_URL ="https://yody.vn/account/register";
    String EXPECT_MESS_HTML5= "Please fill out this field.";
    By loginBtn = By.xpath("//a[text()='ĐĂNG NHẬP']");
    By accountBtn = By.xpath("//a[@class='logined']//span[text()='Cá nhân']");
    By inputKey = By.xpath("//form[@id='header-search-product']//input[@name='query']");
    By iconSearch = By.xpath("//form[@id='header-search-product']//button[@class='btn icon-fallback-text input-group-btn']");
    By registerBtn = By.xpath("//a[text()='ĐĂNG KÝ']");

    @Step("Open yody page:")
    public void goToHomePage(){
        goToUrl(URL_HOMEPAGE);
        AllureManager.saveTextLog("URL: "+URL_HOMEPAGE);
        AllureManager.saveScreenshotPNG();

    }

    @Step("Click on login btn")
    public void clickOnLoginBtn(){
        clickOnElement(loginBtn);
    }
    @Step("Verify login success")
    public void verifyLoginSuccess(){
        waitUntilVisible(accountBtn);
    }

    @Step("Input key: {0} to search")
    public void inputKeySearch(String key){
         waitUntilVisible(inputKey);
         setText(inputKey, key);
    }
    @Step("Click on search element")
    public void clickOnSearchBtn(){
        clickOnElement(iconSearch);
    }
    @Step("Click on search element")
    public void pressToSpace(){
        pressSpace(inputKey);
    }
    @Step("Verify Null Key")
    public void verifyNullKey(){
        String actual = getHtml5Message(inputKey);
        verifyMessage(actual, EXPECT_MESS_HTML5);
    }

    @Step("Click on register btn:")
    public void clickOnRegister(){
        clickOnElement(registerBtn);
        verifyCurrentUrl(REGISTER_URL);
    }

}
