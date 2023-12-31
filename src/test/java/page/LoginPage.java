package page;

import common.CommonUI;
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

    public void verifyLoginUrl(){
        verifyCurrentUrl(LOGIN_URL);
    }
    public void login(String us, String pw){
        setText(username, us);
        setText(password, pw);
        clickOnElement(loginBtn);
    }
}
