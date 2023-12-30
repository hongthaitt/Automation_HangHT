package learn04;

import common.BaseTest;
import common.CommonUI;
import org.checkerframework.checker.units.qual.C;
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

    @Test
    public void popup03(){
        commonUI = new CommonUI(driver);
        String URL = "https://dehieu.vn/";
        String LOGIN_URL = "https://dehieu.vn/dang-nhap";
        By popup = By.xpath("//div[@class='popup-content']");
        By closePopup = By.id("close-popup");
        By loginBtn = By.xpath("//a[text()='Đăng nhập']");

        commonUI.goToUrl(URL);
        commonUI.sleepInSecond(3);

        if(commonUI.listElement(popup).size() > 0){
            if(commonUI.checkDisplay(popup)){
                commonUI.clickOnElement(closePopup);
            }
        }
        else {
            System.out.println("Không có popup");
        }
        commonUI.sleepInSecond(3);
        commonUI.clickOnElement(loginBtn);
        commonUI.sleepInSecond(3);
        commonUI.verifyCurrentUrl(LOGIN_URL);
    }

    @Test
    public void iframeTC04(){
        commonUI = new CommonUI(driver);
        String URL = "https://skills.kynaenglish.vn/";
        String EXPECT_FOLLOW = "172K followers";
        By hotline = By.xpath("//first[text()=' Hotline']");
        By frameFacebook = By.xpath("//div[@class='face-content']/iframe");
        By follow = By.xpath("//a[@title='Kyna.vn']//parent::div//following-sibling::div");
        String iframeChat = "cs_chat_iframe";
        By chatBox = By.xpath("//div[@class='border_overlay meshim_widget_widgets_BorderOverlay']");

        commonUI.goToUrl(URL);
        commonUI.sleepInSecond(3);
        commonUI.scrollToViewElement(hotline);
        //Frame facebook
        Assert.assertTrue(commonUI.listElement(frameFacebook).size() > 0);
        commonUI.switchFrameByWebElement(frameFacebook);
        commonUI.sleepInSecond(3);
        String textValue = commonUI.getText(follow);
        commonUI.verifyMessage(textValue, EXPECT_FOLLOW);
        commonUI.switchToDefaultContent();

        //Chat frame:
        commonUI.switchFrameById(iframeChat);
        commonUI.clickOnElement(chatBox);
        commonUI.sleepInSecond(10);

    }

    @Test
    public void testcase6(){
        commonUI = new CommonUI(driver);
        String URL = "https://automationfc.github.io/basic-form/index.html";
        String EXPECT_TITLE = "Google";
        String EXPECT_TITLE_PARENT = "Selenium WebDriver";
        By googleXpath = By.xpath("//a[text()='GOOGLE']");

        commonUI.goToUrl(URL);
        commonUI.sleepInSecond(2);
        String baseWindow = driver.getWindowHandle();
        commonUI.scrollToViewElement(googleXpath);
        commonUI.clickOnElement(googleXpath);
        commonUI.sleepInSecond(3);

        //Switch sang google
        commonUI.switchWindowById(baseWindow);
        commonUI.sleepInSecond(3);
        commonUI.verifyTitle(EXPECT_TITLE);

        //swich về ngược lại window/tab cha:
        String currentWindow = driver.getWindowHandle();
        commonUI.switchWindowById(currentWindow);
        commonUI.sleepInSecond(3);
        commonUI.verifyTitle(EXPECT_TITLE_PARENT);
    }
    @Test
    public void testcase6_C2(){
        commonUI = new CommonUI(driver);
        String URL = "https://automationfc.github.io/basic-form/index.html";
        String EXPECT_TITLE = "Google";
        String EXPECT_URL = "https://www.google.com.vn/";
        String EXPECT_TITLE_PARENT = "Selenium WebDriver";
        String EXPECT_PARENT_URL = "https://automationfc.github.io/basic-form/index.html";
        By googleXpath = By.xpath("//a[text()='GOOGLE']");

        commonUI.goToUrl(URL);
        commonUI.sleepInSecond(2);
        commonUI.scrollToViewElement(googleXpath);
        commonUI.clickOnElement(googleXpath);
        commonUI.sleepInSecond(3);

        //Switch sang google
        commonUI.switchWindowByTitle(EXPECT_TITLE);
        commonUI.sleepInSecond(3);
        commonUI.verifyCurrentUrl(EXPECT_URL);

        //swich về ngược lại window/tab cha:
        commonUI.switchWindowByTitle(EXPECT_TITLE_PARENT);
        commonUI.sleepInSecond(3);
        commonUI.verifyCurrentUrl(EXPECT_PARENT_URL);
    }
}
