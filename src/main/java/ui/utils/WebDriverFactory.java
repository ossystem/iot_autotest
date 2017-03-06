package ui.utils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    public static WebDriver createInstance(String browserName, Boolean useGrid) {
        URL hostURL = null;
        try {
            hostURL = new URL("http://vzhost4:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        DesiredCapabilities capability = null;
        WebDriver driver = null;
        if (useGrid) {
            if (browserName.toLowerCase().contains("firefox")) {
                capability = DesiredCapabilities.firefox();
                capability.setBrowserName("firefox");
                capability.setPlatform(Platform.LINUX);
            }
            if (browserName.toLowerCase().contains("ie")) {
                capability = DesiredCapabilities.internetExplorer();
            }
            if (browserName.toLowerCase().contains("chrome")) {
                capability = DesiredCapabilities.chrome();
                capability.setBrowserName("chrome");
                capability.setPlatform(Platform.LINUX);
            }
            driver = new RemoteWebDriver(hostURL, capability);
        }
        else{
            if (browserName.toLowerCase().contains("firefox")) {
                driver = new FirefoxDriver();
                System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
            }
            if (browserName.toLowerCase().contains("ie32")) {
                System.setProperty("webdriver.ie.driver", "IEDriverServer32.exe");
                driver = new InternetExplorerDriver();
            }
            if (browserName.toLowerCase().contains("ie64")) {
                System.setProperty("webdriver.ie.driver", "IEDriverServer64.exe");
                driver = new InternetExplorerDriver();
            }
            if (browserName.toLowerCase().contains("chrome")) {
                driver = new ChromeDriver();
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            }
        }


        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}