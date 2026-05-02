package testcases;

import common.Constant;
import common.LoggerUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        LoggerUtil.info("=== SETUP BASE TEST ===");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        LoggerUtil.info("1. Khởi tạo ChromeDriver...");
        try {
            WebDriver driver = new ChromeDriver(options);
            LoggerUtil.info("2. ChromeDriver khởi tạo thành công.");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            Constant.setDriver(driver);
        } catch (Exception e) {
            LoggerUtil.error("LỖI khởi tạo ChromeDriver trong BaseTest: " + e.getMessage());
            throw e;
        }
        LoggerUtil.info("=== SETUP COMPLETED ===");
    }
    
    @AfterMethod
    public void tearDown() {
        LoggerUtil.info("❌ Close browser");
        
        if (Constant.getDriver() != null) {
            Constant.getDriver().quit();
        }
    }
}




