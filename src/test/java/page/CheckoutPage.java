package page;

import common.CommonUI;
import constanst.CommonConstanst;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import static common.CommonUI.*;


public class CheckoutPage {
    private WebDriver driver;

    public CheckoutPage(WebDriver _driver) {
        driver = _driver;
        new CommonUI(driver);
    }

    private String URL_CART = "https://yody.vn/cart";
    private String ERROR_NULL_NAME = "Vui lòng nhập họ tên";
    private String ERROR_INVALID_NAME = "Họ tên không hợp lệ";
    private String ERROR_INVALID_PHONE = "Số điện thoại không hợp lệ";
    private String ERROR_NULL_PHONE = "Vui lòng nhập số điện thoại";
    private String ERROR_NULL_ADDRESS = "Vui lòng nhập địa chỉ";
    private String ERROR_PAYMENT = "Bạn cần chọn phương thức thanh toán";
    private String URL_LOGIN = "https://yody.vn/account/login";
    private String URL_DIRECT_VN_PAY_METHOD = "https://pay.vnpay.vn/Transaction/PaymentMethod";
    private String ERROR_VOUCHER = "Mã khuyến mãi không hợp lệ";
    private String FREE_SHIP = "Miễn phí";
    private String PAYMENT_FAIL = "THANH TOÁN THẤT BẠI";
    private String URL_DIRECT_VN_PAY_QR = "retry_payment";
    private String URL_DIRECT_MOMO = "https://payment.momo.vn/v2/gateway";
    private List<String> productNameList = new ArrayList<>();
    By sizeOption = By.xpath("//*[@id='swatch-1-s']");
    By qty = By.xpath("//*[@id='qty']");
    By qtyPlus = By.xpath("//*[@id='btn-plus']");
    By nameBilling = By.xpath("//*[@id='billingName']");
    By phoneBilling = By.xpath("//*[@id='billingPhone']");
    By addressBilling = By.xpath("//*[@id='billingAddress']");
    By provinceSelect = By.xpath("//*[@aria-labelledby='select2-billingProvince-container']");
    By districtSelect = By.xpath("//*[@aria-labelledby='select2-billingDistrict-container']");
    By wardSelect = By.xpath("//*[@aria-labelledby='select2-billingWard-container']");
    By appVNPayMethod = By.xpath("//*[text()='Thanh toán qua thẻ thanh toán, ứng dụng ngân hàng VNPAY']");
    By feeShip = By.xpath("//*[@class='content-box__emphasis price']");
    By totalPrice = By.xpath("//*[@class='payment-due__price']");
    By listPrdName = By.xpath("//span[@class='product__description__name']");

    @Step("Input qty of shirt to buy: {0}")
    public void inputQty(String qtyPrd) {
//        scrollToView(qtyPlus);
        scrollToView(sizeOption);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i < Integer.parseInt(qtyPrd); i++) {
            clickOnElement(qtyPlus);
        }
    }


    public By selectProviceBilling(String prv) {
        String xpath = "//*[@id='select2-billingProvince-results']//*[text()='" + prv + "']";
        return By.xpath(xpath);
    }

    public By selectDistrictBilling(String dist) {
        String xpath = "//*[@id='select2-billingDistrict-results']//*[text()='" + dist + "']";
        return By.xpath(xpath);
    }

    public By selectWardBilling(String ward) {
        String xpath = "//*[@id='select2-billingWard-results']//*[text()='" + ward + "']";
        return By.xpath(xpath);
    }

    @Step("Input name billing")
    public void setname(String name) {
        setText(nameBilling, name);
        Allure.addAttachment("Image: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Input phone number billing")
    public void setPhone(String phone) {
        setText(phoneBilling, phone);
        Allure.addAttachment("Image: ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Verify grand total in checkout page ")
    public void verifyGrandTotal() {
        int total = 0;
        for(int i =0; i < CommonConstanst.productList.size(); i ++){
            int qty = Integer.parseInt(CommonConstanst.productList.get(i).getQty());
            String price = CommonConstanst.productList.get(i).getPrice().replace(".", "").replace("đ", "");
            System.out.println("price : " + price + "   qty:  " + qty);
            total = total + Integer.parseInt(price) * qty;
        }
        if (!getText(feeShip).equals("Miễn phí")) {
            String fee = getText(feeShip);
            String feeShip = fee.replace(".", "").replace("đ", "");
            total = total + Integer.parseInt(feeShip);
        }
        System.out.println("total : " +total);
        String actualTotal = getText(totalPrice).replace(".", "").replace("đ", "");
        System.out.println("actualTotal : " + actualTotal);
        Assert.assertTrue(String.valueOf(total).equals(actualTotal));
    }

    @Step("Select address in billing")
    public void selectAddress(String address, String prv, String dist, String ward) {
        clearText(addressBilling);
        setText(addressBilling, address);
        clickOnElement(provinceSelect);
        clickOnElement(selectProviceBilling(prv));
        clickOnElement(districtSelect);
        clickOnElement(selectDistrictBilling(dist));
        clickOnElement(wardSelect);
        clickOnElement(selectWardBilling(ward));
    }

    public String handlePrice(String pricePerPrd, String qty){
        int priceInt = Integer.parseInt(pricePerPrd.replace(".", "").replace("đ",""));
        int qtyInt = Integer.parseInt(qty);
        int total = priceInt * qtyInt;
        return total+"đ";
    }
    public  void verifyInfoOrder(){
        By prdNameList = By.xpath("//th[@class='product__description']//span[@class='product__description__name']");
        By prdQtyList = By.xpath("//tr[@class='product']//td[@class='product__quantity visually-hidden']");
        By prdPriceList = By.xpath("//tr[@class='product']//td[@class='product__price']");
        By prdColorAndSizeList = By.xpath("//span[@class='product__description__property' and contains(text(), '/')]");
         List<WebElement> nameList = driver.findElements(prdNameList);
         List<WebElement> priceList = driver.findElements(prdPriceList);
         List<WebElement> qtyList = driver.findElements(prdQtyList);
         List<WebElement> colorAndSizeList = driver.findElements(prdColorAndSizeList);

        for (int i =0; i < nameList.size(); i ++){
            String qty = qtyList.get(i).getText();
            System.out.println("qty: " +qty);

            Assert.assertEquals(nameList.get(i).getText().toLowerCase(), CommonConstanst.productList.get(i).getName().toLowerCase());
            Assert.assertEquals(qtyList.get(i).getText(), CommonConstanst.productList.get(i).getQty());
            System.out.println("expect price: " +handlePrice(CommonConstanst.productList.get(i).getPrice(),CommonConstanst.productList.get(i).getQty()));
            System.out.println("actual price: "+priceList.get(i).getText().replace(".",""));
            System.out.println("expect size: "+colorAndSizeList.get(i).getText().toLowerCase());
            System.out.println("actual size: "+CommonConstanst.productList.get(i).getName().toLowerCase());
            System.out.println("actual price: "+priceList.get(i).getText().replace(".",""));

            Assert.assertEquals(handlePrice(CommonConstanst.productList.get(i).getPrice(),CommonConstanst.productList.get(i).getQty()), priceList.get(i).getText().replace(".",""));

            System.out.println("Test1: " +colorAndSizeList.get(i).getText().toLowerCase());
            System.out.println("Test2: " +CommonConstanst.productList.get(i).getColor().toLowerCase() + " / " +CommonConstanst.productList.get(i).getSize().toLowerCase());
            Assert.assertEquals(colorAndSizeList.get(i).getText().toLowerCase(), CommonConstanst.productList.get(i).getColor().toLowerCase() + " / " +CommonConstanst.productList.get(i).getSize().toLowerCase());
        }
    }
    public void checkoutSuccessWithVnPayMethod(String name, String phone, String address, String prv, String dist, String ward) {
        waitUntilVisible(nameBilling);
        clearText(nameBilling);
        setname(name);
        clearText(phoneBilling);
        setPhone(phone);
        selectAddress(address, prv, dist, ward);
        clickOnElement(appVNPayMethod);
        verifyInfoOrder();
        verifyGrandTotal();
    }

}
