package learn04;

import common.BaseTest;
import common.CommonUI;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PopUp_Frame_Window extends BaseTest {
    CommonUI commonUI;
    String URL_HOME = "https://ngoaingu24h.vn/";
    String ERROR_MESSAGE_INVALID_ACCT = "Tài khoản không tồn tại!";

    By loginBtn = By.xpath("//button[@class='login_ icon-before']");
    By modalLoginDialog = By.xpath("//div[@id='modal-login-v1']//div[@class='modal-content']");
    By accountInput = By.id("account-input");
    By passwordInput = By.id("password-input");
    By doLoginBtn = By.xpath("//button[@class='btn-v1 btn-login-v1 buttonLoading']");
    By errorMessage = By.xpath("//div[@id='modal-login-v1']//div[@class='row error-login-panel']");

    @Test
    public void popup01() {
        commonUI = new CommonUI(driver);
        driver.get("https://ngoaingu24h.vn/");
        commonUI.sleepInSecond(5);
        driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
        commonUI.sleepInSecond(2);
        boolean popup = commonUI.checkDisplay(By.xpath("//div[@id='modal-login-v1']//div[@class='modal-content']"));
        Assert.assertTrue(popup);
        driver.findElement(By.id("account-input")).sendKeys("automationfc");
        driver.findElement(By.id("password-input")).sendKeys("automationfc");
        driver.findElement(By.xpath("//button[@class='btn-v1 btn-login-v1 buttonLoading']")).click();
        String message = driver.findElement(By.xpath("//div[@id='modal-login-v1']//div[@class='row error-login-panel']")).getText();
        Assert.assertTrue(message.equals("Tài khoản không tồn tại!"));
    }

    @Test
    public void popup02() {
        commonUI = new CommonUI(driver);
        driver.get(URL_HOME);
        commonUI.sleepInSecond(5);
        commonUI.clickOnElement(loginBtn);
        commonUI.sleepInSecond(2);
        //C1:
        commonUI.assertTrue(commonUI.checkDisplay(modalLoginDialog));
        /* C2: boolean result = commonUI.checkDisplay(modalLoginDialog);
        Assert.assertTrue(result)
        */
        //C3: Assert.assertTrue(commonUI.checkDisplay(modalLoginDialog));
        commonUI.setText(accountInput, "automationfc");
        commonUI.setText(passwordInput, "automationfc");
        commonUI.clickOnElement(doLoginBtn);
        String message = commonUI.getText(errorMessage);
        commonUI.verifyMessage(message, ERROR_MESSAGE_INVALID_ACCT);
    }
}
