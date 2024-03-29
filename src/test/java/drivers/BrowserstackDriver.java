package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserstackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {

    protected static final BrowserstackConfig browserstackConfig = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("browserstack.user", browserstackConfig.getUserName());
        caps.setCapability("browserstack.key", browserstackConfig.getAccessKey());

        caps.setCapability("app", browserstackConfig.getAppUrl());

        caps.setCapability("device", browserstackConfig.getDeviceModel());
        caps.setCapability("os_version", browserstackConfig.getDeviceVersion());

        caps.setCapability("project", browserstackConfig.getProject());
        caps.setCapability("build", browserstackConfig.getBuild());
        caps.setCapability("name", browserstackConfig.getName());

        try {
            return new RemoteWebDriver(
                    new URL(browserstackConfig.getURL()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}