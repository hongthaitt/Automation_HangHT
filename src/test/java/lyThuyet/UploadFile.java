package lyThuyet;

import common.BaseTest;
import common.CommonUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class UploadFile extends BaseTest {
    CommonUI commonUI;
    String rootPath = System.getProperty("user.dir");
    String dataPath = "\\src\\test\\java\\data\\";
    String image1 = "Anh01.png";
    String image2 = "Anh02.png";
    String image3 = "Anh3.png";

    //D:\HangHT\Git_Project\src\test\java\data\Anh01.png
    //psvm + tab
    String path01 = rootPath + dataPath + image1;
    String path02 = rootPath + dataPath + image2;
    String path03 = rootPath + dataPath + image3;
    String URL = "https://blueimp.github.io/jQuery-File-Upload/";
    By fileName = By.name("files[]");
    By startBtn = By.xpath("//span[text()='Start']");
    // //p[text()= 'Anh01.png'] = //p[text()= ' + image1 + ']

    @Test
    public void uploadFile01() {
        commonUI = new CommonUI(driver);
        commonUI.goToUrl(URL);
        commonUI.sleepInSecond(3);
        //upload file:
        commonUI.upload01File(fileName, path01);
        commonUI.upload01File(fileName, path02);
        commonUI.upload01File(fileName, path03);

        //verify upload thanh cong:
        //C1:
        commonUI.vefifyDisplay(By.xpath("//p[text()= '" + image1 + "']"));
        commonUI.vefifyDisplay(By.xpath("//p[text()= '" + image2 + "']"));
        commonUI.vefifyDisplay(By.xpath("//p[text()= '" + image3 + "']"));

        //Start upload:
        List<WebElement> startBtnList = commonUI.listElement(startBtn);
        for (int i = 0; i < startBtnList.size(); i++) {
            startBtnList.get(i).click();
        }

        //Verify upload thanh cong:
        commonUI.vefifyDisplay(customizeXpathImage(image1));
        commonUI.vefifyDisplay(customizeXpathImage(image2));
        commonUI.vefifyDisplay(customizeXpathImage(image3));

    }

    @Test
    public void uploadFile02(){
        commonUI = new CommonUI(driver);
        commonUI.goToUrl(URL);
        commonUI.sleepInSecond(3);
        //upload file:

        driver.findElement(fileName).sendKeys(path01 + "\n" +path02 + "\n" + path03);
        commonUI.sleepInSecond(10);
        //verify upload thanh cong:
        //C1:
        commonUI.vefifyDisplay(By.xpath("//p[text()= '" + image1 + "']"));
        commonUI.vefifyDisplay(By.xpath("//p[text()= '" + image2 + "']"));
        commonUI.vefifyDisplay(By.xpath("//p[text()= '" + image3 + "']"));

        //Start upload:
        List<WebElement> startBtnList = commonUI.listElement(startBtn);
        for (int i = 0; i < startBtnList.size(); i++) {
            startBtnList.get(i).click();
        }

        //Verify upload thanh cong:
        commonUI.vefifyDisplay(customizeXpathImage(image1));
        commonUI.vefifyDisplay(customizeXpathImage(image2));
        commonUI.vefifyDisplay(customizeXpathImage(image3));
    }
    public By customizeXpathImage(String imageName) {
        return By.xpath("//a[text()= '" + imageName + "']");
    }
}
