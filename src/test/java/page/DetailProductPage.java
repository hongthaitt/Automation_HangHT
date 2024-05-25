package page;

import common.CommonUI;
import constanst.CommonConstanst;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import object.Product;
import org.openqa.selenium.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import static common.CommonUI.*;

public class DetailProductPage {
    private WebDriver driver;

    public DetailProductPage(WebDriver _driver) {
        driver = _driver;
        new CommonUI(driver);
    }

    By header = By.xpath("//*[@class='search-product' or @class='search-product aa']");
    By productName = By.xpath("//*[@class='title-head mb-1']");
    By sizeOption = By.xpath("//*[@id='swatch-1-s']");
    By buyNow = By.xpath("//*[@class='btn-mua d-none d-lg-block']//button[2]");
    By price = By.xpath("//*[@class='price-box clearfix d-flex align-items-center']//*[@class='price product-price']");
    By sizeValue = By.xpath("//div[@class='swatch-size swatch clearfix']//div[@class='options-title']//span");
//    By color = By.xpath("//div[@class='swatch-color swatch clearfix']//input[@id='swatch-header-0-trang']");
    By color = By.xpath("//div[@class='swatch-color swatch clearfix ']//div[@class='options-title']//span");
    By qty = By.xpath("//*[@id='qty']");
    By closePopUp = By.xpath("//button[@aria-label='Đóng']");
    By qtyPlus = By.xpath("//*[@id='btn-plus']");
    By addToCart = By.xpath("//*[@class='btn-mua d-none d-lg-block']//*[@id=\"add-to-cart-wrapper\"]/button[1]/span");
    private List<String> productNameList = new ArrayList<>();

    @Step("Click to select size of shirt")
    public void selectSize() {
        WebElement radioBtn = driver.findElement(sizeOption);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioBtn);
    }

    public void getInfoPrd(String qty) {
        waitUntilVisible(productName);
        selectSize();
        inputQty(qty);
        getInfo();
    }

    public void clickBuyNow() {
        scrollToView(sizeOption);
        clickOnElement(buyNow);

    }
    @Step("Input qty of shirt to buy: {0}")
    public void inputQty(String qtyPrd) {
        scrollToView(color);
        if(qtyPrd!= "1") {
            sleepInSecond(5);
            for (int i = 1; i < Integer.parseInt(qtyPrd); i++) {
                if(driver.findElements(By.xpath("//span[text()='x']")).size() > 0){
                    clickOnElement(By.xpath("//span[text()='x']"));
                }
                sleepInSecond(2);
                clickOnElementByJs(qtyPlus);
            }
        }
    }
    @Step("Get info shirt product in detail page")
    public void getInfo() {
//        int i = 0;
//        while (i < 15) {
//            if (driver.findElements(closePopUp).size() > 0) {
//                clickOnElement(closePopUp);
//                i = 15;
//            }
//            sleepInSecond(3);
//            i++;
//        }
        Product product = new Product();
        product.setColor(getText(color));
        product.setName(getText(productName));
        product.setPrice(getText(price));
        product.setQty(getValue(qty));
        product.setSize(getText(sizeValue));
        CommonConstanst.productList.add(product);
        System.out.println("prd name:" + product.getName());
        System.out.println("prd all size list:" + product.getSize());
        System.out.println("prd all size list:" + product.getSize());
        System.out.println("color:" + product.getColor());
        System.out.println("prd all size list:" + product.getSize());
        Allure.addAttachment("Image: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
    public void setAddToCart() {
        scrollToView(qty);
        clickOnElement(addToCart);
        Allure.addAttachment("Image: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        refreshPage();
    }
}
