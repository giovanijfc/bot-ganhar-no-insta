package bot.instagram;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Instagram {

	public static void login(String email, String password) {
		String xpathRealizarAcoes = "//span[contains(text(), 'Realizar Ações')]";

		new WebDriverWait(Driver.access, 15)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathRealizarAcoes)));
		Driver.access.get("https://www.instagram.com/");

		new WebDriverWait(Driver.access, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
		Driver.access.findElement(By.name("username")).sendKeys(email);
		Driver.access.findElement(By.name("password")).sendKeys(password);

		Driver.access.findElement(By.xpath("//div[contains(text(), 'Entrar')]")).click();

		String xpathInputSearch = "//span[contains(text(), 'Pesquisar')]";
		new WebDriverWait(Driver.access, 15)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathInputSearch)));
	}

	public static void logout(long timeToAwait) {
		Driver.access.get("https://www.instagram.com/");
		String xpathInputSearch = "//span[contains(text(), 'Pesquisar')]";
		new WebDriverWait(Driver.access, timeToAwait)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathInputSearch)));

		Driver.access.manage().deleteCookieNamed("sessionid");

		sleep(100);

		Driver.access.get("https://www.ganharnoinsta.com/painel/");
	}

	private static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
