package page;

import common.CommonUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static common.CommonUI.*;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver _driver){
        driver = _driver;
        new CommonUI(driver);
    }
    String LOGIN_URL = "https://yody.vn/account/login";
    By username = By.id("customer_email");
    By password = By.id("customer_password");
    By loginBtn = By.xpath("//button[@type='submit'][text()='Đăng nhập']");

    @Step("Verify login url")
    public void verifyLoginUrl(){
        verifyCurrentUrl(LOGIN_URL);
    }
    @Step("Login with username: {0} and password: {1} ")
    public void login(String us, String pw){
        setText(username, us);
        setText(password, pw);
        clickOnElement(loginBtn);
    }
}
