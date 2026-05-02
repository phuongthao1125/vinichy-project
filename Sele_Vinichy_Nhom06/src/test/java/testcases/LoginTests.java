package testcases;

import common.Constant;
import common.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;

import static common.Constant.getDriver;
import static common.Constant.setDriver;

public class LoginTests {

    LoginPage loginPage = new LoginPage();

    @BeforeMethod
    public void beforeMethod() {
        LoggerUtil.info("=== SETUP BEFORE METHOD ===");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        
        LoggerUtil.info("1. Khởi tạo ChromeDriver...");
        try {
            setDriver(new ChromeDriver(options));
            LoggerUtil.info("2. ChromeDriver khởi tạo thành công.");
        } catch (Exception e) {
            LoggerUtil.error("LỖI khởi tạo ChromeDriver: " + e.getMessage());
            throw e;
        }
        
        getDriver().manage().window().maximize();
        LoggerUtil.info("3. Điều hướng tới URL: " + Constant.URL);
        getDriver().get(Constant.URL);
        
        LoggerUtil.info("4. Mở Modal đăng nhập");
        loginPage.openAccountMenu();
        LoggerUtil.info("=== SETUP COMPLETED ===");
    }

    @AfterMethod
    public void afterMethod() {
        LoggerUtil.info("Quitting getDriver()");
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    @Test(description = "TC05-F0001 - Đăng nhập thành công")
    public void TC05_F0001() {
        LoggerUtil.info("START TEST: TC05-F0001");
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        
        HomePage homePage = new HomePage();
        boolean isDisplayed = homePage.isWelcomeDisplayed();
        
        String expected = "Đăng nhập thành công";
        String actual = isDisplayed ? "Đăng nhập thành công" : "Đăng nhập thất bại";
        LoggerUtil.info("Result: Exp=" + expected + " | Act=" + actual);
        
        Assert.assertTrue(isDisplayed, "Welcome message should be displayed after successful login");
    }

    @Test(description = "TC05-F0002 - Trống email")
    public void TC05_F0002() {
        LoggerUtil.info("START TEST: TC05-F0002");
        loginPage.login("", Constant.PASSWORD);
        
        String actualMsg = loginPage.getGeneralErrorMsg().trim();
        String expectedMsg = "Không được để trống ô này!";
        
        LoggerUtil.info("Result: Exp=" + expectedMsg + " | Act=" + actualMsg);
        Assert.assertTrue(actualMsg.contains(expectedMsg), "Thông báo lỗi trống email không đúng");
    }

    @Test(description = "TC05-F0003 - Trống mật khẩu")
    public void TC05_F0003() {
        LoggerUtil.info("START TEST: TC05-F0003");
        loginPage.login(Constant.USERNAME, "");
        
        String actualMsg = loginPage.getGeneralErrorMsg().trim();
        String expectedMsg = "Không được để trống ô này!";
        
        LoggerUtil.info("Result: Exp=" + expectedMsg + " | Act=" + actualMsg);
        Assert.assertTrue(actualMsg.contains(expectedMsg), "Thông báo lỗi trống mật khẩu không đúng");
    }

    @Test(description = "TC05-F0004 - Trống cả 2")
    public void TC05_F0004() {
        LoggerUtil.info("START TEST: TC05-F0004");
        loginPage.login("", "");
        
        String actualMsg = loginPage.getGeneralErrorMsg().trim();
        String expectedMsg = "Không được để trống ô này!";
        
        LoggerUtil.info("Result: Exp=" + expectedMsg + " | Act=" + actualMsg);
        Assert.assertTrue(actualMsg.contains(expectedMsg), "Thông báo lỗi trống cả 2 trường không đúng");
    }

    @Test(description = "TC05-F0005 - Email sai định dạng")
    public void TC05_F0005() {
        LoggerUtil.info("START TEST: TC05-F0005");
        loginPage.login("phuongthaogmail.com", Constant.PASSWORD);
        
        String actualMsg = loginPage.getGeneralErrorMsg().trim();
        String expectedMsg = "Tên đăng nhập hoặc mật khẩu không hợp lệ. Vui lòng nhập lại";
        
        LoggerUtil.info("Result: Exp=" + expectedMsg + " | Act=" + actualMsg);
        Assert.assertTrue(actualMsg.contains(expectedMsg), "Thông báo lỗi định dạng email không đúng");
    }

    @Test(description = "TC05-F0006 - Email không tồn tại")
    public void TC05_F0006() {
        LoggerUtil.info("START TEST: TC05-F0006");
        loginPage.login("abcdef@gmail.com", Constant.PASSWORD);
        
        String actualMsg = loginPage.getGeneralErrorMsg().trim();
        String expectedMsg = "Tên đăng nhập hoặc mật khẩu không hợp lệ. Vui lòng nhập lại";
        
        LoggerUtil.info("Result: Exp=" + expectedMsg + " | Act=" + actualMsg);
        Assert.assertTrue(actualMsg.contains(expectedMsg), "Thông báo lỗi email không tồn tại không đúng");
    }

    @Test(description = "TC05-F0007 - Sai mật khẩu")
    public void TC05_F0007() {
        LoggerUtil.info("START TEST: TC05-F0007");
        loginPage.login(Constant.USERNAME, "111111");
        
        String actualMsg = loginPage.getGeneralErrorMsg().trim();
        String expectedMsg = "Tên đăng nhập hoặc mật khẩu không hợp lệ. Vui lòng nhập lại";
        
        LoggerUtil.info("Result: Exp=" + expectedMsg + " | Act=" + actualMsg);
        Assert.assertTrue(actualMsg.contains(expectedMsg), "Thông báo lỗi sai mật khẩu không đúng");
    }

    @Test(description = "TC05-F0008 - Chuyển trang quên mật khẩu")
    public void TC05_F0008() {
        LoggerUtil.info("START TEST: TC05-F0008");
        loginPage.clickForgetPassword();

        WebElement popup = Constant.getDriver().findElement(By.id("forgotPopup"));
        boolean isActive = popup.getAttribute("class").contains("active");
        LoggerUtil.info("Result: forgotPopup active=" + isActive);
        Assert.assertTrue(isActive, "Should show forgot password popup");
    }

    @Test(description = "TC05-F0009 - Chuyển trang đăng ký")
    public void TC05_F0009() {
        LoggerUtil.info("START TEST: TC05-F0009");
        loginPage.clickRegister();

        WebElement popup = Constant.getDriver().findElement(By.id("registerPopup"));
        boolean isActive = popup.getAttribute("class").contains("active");
        LoggerUtil.info("Result: registerPopup active=" + isActive);
        Assert.assertTrue(isActive, "Should show register popup");
    }

    @Test(description = "TC05-UI001 - Kiểm tra các thành phần hiển thị của popup đăng nhập")
    public void TC05_UI001() {
        LoggerUtil.info("START TEST: TC05-UI001");
        
        Assert.assertEquals(loginPage.getLblTitle().getText().trim(), "ĐĂNG NHẬP", "Tiêu đề popup không đúng");
        Assert.assertEquals(loginPage.getLblDescription().getText().trim(), "Nhập email và mật khẩu của bạn", "Mô tả popup không đúng");
        
        Assert.assertEquals(loginPage.getLblEmail().getText().trim(), "Tên đăng nhập");
        Assert.assertEquals(loginPage.getTxtEmail().getAttribute("placeholder"), "Nhập email");
        
        Assert.assertEquals(loginPage.getLblPassword().getText().trim(), "Mật khẩu");
        Assert.assertEquals(loginPage.getTxtPassword().getAttribute("placeholder"), "Nhập mật khẩu");
        Assert.assertEquals(loginPage.getTxtPassword().getAttribute("type"), "password");
        
        Assert.assertEquals(loginPage.getLinkForgetPassword().getText().trim(), "Quên mật khẩu");
        Assert.assertTrue(loginPage.getBtnLogin().getText().contains("Đăng nhập"), "Nút Đăng nhập không đúng text");
        Assert.assertTrue(loginPage.getLinkRegister().getText().contains("Đăng ký tài khoản"), "Link đăng ký không đúng text");
    }
}






