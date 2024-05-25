package page;

import common.CommonUI;
import net.datafaker.Faker;
import org.apache.poi.xwpf.usermodel.IBody;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static common.CommonUI.*;

public class RegisterPage {
    WebDriver driver;

    public RegisterPage(WebDriver _driver) {
        driver = _driver;
        new CommonUI(driver);
    }

    String SUCCESS_MESSAGE = "Đăng ký thành công";
    String URL_ACCOUNT = "https://yody.vn/account";

    By firstName = By.id("first_name");
    By phoneTxt = By.id("phone");
    By emailTxt = By.id("iptEmail");
    By passwordTxt = By.id("password");
    By submitBtn = By.id("btnSubmit");
    By messageSuccess = By.id("toast-content");
    By accountBtn = By.xpath("//span[text()='Cá nhân']");
    By listValue = By.xpath("//div[contains(@class, 'form-signup')]/p");

    Faker fake = new Faker();

    public String generateValidPhone() {
        String phone = "097" + fake.number().digits(7);
        return phone;
    }

    public String generateValidEmail(String prefix) {
        String email = prefix + fake.number().digits(5) + "@gmail.com";
        return email;
    }

    public void registerSuccess(String name, String preEmail, String pw) {
        setText(firstName, name);
        String validEmail = generateValidEmail(preEmail);
        setText(phoneTxt, generateValidPhone());
        setText(emailTxt, validEmail);
        setText(passwordTxt, pw);
        attachImage();
        clickOnElement(submitBtn);
        waitUntilVisible(accountBtn);
        waitUntilVisible(messageSuccess);
        Assert.assertTrue(isDisplay(messageSuccess));
        verifyMessage(getText(messageSuccess), SUCCESS_MESSAGE);
        verifyRegisterSuccess(name, validEmail);
    }

    public void verifyRegisterSuccess(String name, String email) {
        clickOnElement(accountBtn);
        verifyCurrentUrl(URL_ACCOUNT);
        List<WebElement> valueList = driver.findElements(listValue);
        System.out.println("size list: " + valueList.size());
        for (int i = 0; i < valueList.size(); i++) {
            System.out.println(i + valueList.get(i).getText());
            if (i == 0) {
                String expectValue = "Họ và tên: " + name;
                Assert.assertTrue(valueList.get(i).getText().equals(expectValue));
            }
            if (i == 1) {
                String expectValue = "Địa chỉ email: " + email;
                Assert.assertTrue(valueList.get(i).getText().equals(expectValue));
            }
        }
    }
}