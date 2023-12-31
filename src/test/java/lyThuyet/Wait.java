package lyThuyet;

import common.BaseTest;
import common.CommonUI;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Wait extends BaseTest {
    CommonUI commonUI;
    String URL = "https://automationfc.github.io/dynamic-loading/";
    String EXEPECT_TEXT = "Hello World!";
    By startBtn = By.xpath("//button[text()='Start']");
    By loadingIcon = By.id("loading");
    By text = By.id("finish");
    @Test
    public void TC01(){
        commonUI = new CommonUI(driver);
        commonUI.goToUrl(URL);
        commonUI.waitUntilVisible(startBtn);
        commonUI.clickOnElement(startBtn);

        //wait invisible loading icon
        commonUI.waitUntilInvisible(loadingIcon);
        //verify
        String actual = commonUI.getText(text);
        commonUI.verifyMessage(actual, EXEPECT_TEXT);

    }

    @Test()
    public void TC03(){
        commonUI = new CommonUI(driver);
        commonUI.goToUrl(URL);
        commonUI.waitUntilVisible(startBtn);
        commonUI.clickOnElement(startBtn);
        commonUI.implicitWait(5);
        //verify
        String actual = commonUI.getText(text);
        commonUI.verifyMessage(actual, EXEPECT_TEXT);
    }
}
