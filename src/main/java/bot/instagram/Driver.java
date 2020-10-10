package bot.instagram;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {
	static WebDriver access;

	public Driver(String doorToConnect) {
		System.setProperty("webdriver.chrome.driver", "C:/bots/instagram/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("debuggerAddress", "127.0.0.1:" + doorToConnect);
		access = new ChromeDriver(options);
	}
}
