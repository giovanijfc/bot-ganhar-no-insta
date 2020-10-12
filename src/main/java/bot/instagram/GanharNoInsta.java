package bot.instagram;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import models.Account;

public class GanharNoInsta {

	public final static long INTERVAL_FOLLOW = 1800000;
	public final static int QUANTITY_FOLLOWS_IN_INTERVAL_TIME = 5;
	public static List<Account> accounts = new ArrayList<Account>();

	static void login() {
		new WebDriverWait(Driver.access, 10).until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
		Driver.access.findElement(By.name("email")).sendKeys("gabrielachiodi666@gmail.com");
		Driver.access.findElement(By.name("senha")).sendKeys("Biladi123@");

		Driver.access.findElement(By.xpath("//button[contains(text(), 'Efetuar Login')]")).click();
	}

	static void toTabRealizarAcoes() {
		Driver.access.get("https://www.ganharnoinsta.com/painel/");
		String xpathRealizarAcoes = "//span[contains(text(), 'Realizar Ações')]";
		new WebDriverWait(Driver.access, 10)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathRealizarAcoes)));
		Driver.access.findElement(By.xpath(xpathRealizarAcoes)).click();
	}

	static void startFollow() {
		while (true) {
			accounts.forEach(account -> {
				if (account.getLastDateFollowFlow() == null) {
					accountFollowFlow(account);
				} else if (!account.getCanFollowNow()) {
					if (lastDateFollowFlowIsManyInterval(account)) {
						System.out.println("Passou mais de " + INTERVAL_FOLLOW
								+ " deis da ultima vez que rodou o flow com a " + account.getNickName());
						accountFollowFlow(account);
					}
				}
			});

			sleep(5000);
			System.out.println("START_FOLLOW_FINISH");
		}
	}

	private static void accountFollowFlow(Account account) {
		Boolean isBlocked = false;

		System.out.println("Logando a " + account.getNickName());
		Instagram.login(account.getEmail(), account.getPassword());

		toTabRealizarAcoes();

		Select optionsAccounts = new Select(Driver.access.findElement(By.id("contaig")));

		WebElement optionToSelect = optionsAccounts.getOptions().stream()
				.filter(option -> option.getText().equals(account.getNickName())).collect(Collectors.toList()).get(0);
		WebElement buttonStart = Driver.access.findElement(By.id("btn_iniciar"));

		optionToSelect.click();
		buttonStart.click();

		sleep(1000);

		for (int i = 1; i <= QUANTITY_FOLLOWS_IN_INTERVAL_TIME; i++) {
			new WebDriverWait(Driver.access, 9999999)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-acessar")));
			sleep(200);

			WebElement buttonSeeProfile = Driver.access.findElement(By.id("btn-acessar"));
			buttonSeeProfile.click();

			while (Driver.access.getWindowHandles().size() > 1) {
				if (Driver.access.getWindowHandles().size() >= 3) {
					isBlocked = true;
				}
			}

			if (isBlocked) {
				break;
			}

			WebElement buttonConfirmationAction = Driver.access.findElement(By.id("btn-confirmar"));
			buttonConfirmationAction.click();

			System.out.println("Seguiu " + i + " vez " + account.getNickName());
		}

		WebElement buttonStop = Driver.access.findElement(By.id("btn_pausar"));

		if (isBlocked) {
			System.out.println("O perfil da " + account.getNickName() + " não esta podendo seguir no momento.");
			account.setCanFollowNow(false);
			account.setLastDateFollowFlow(new Date(new Date().getTime() + INTERVAL_FOLLOW));
		} else {
			account.setLastDateFollowFlow(new Date());
			account.setCanFollowNow(false);
		}

		buttonStop.click();
		Instagram.logout(10);
		System.out.println("Deslogando a " + account.getNickName());
	}

	private static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static boolean lastDateFollowFlowIsManyInterval(Account account) {
		long diferrenceMillisecond = new Date().getTime() - account.getLastDateFollowFlow().getTime();

		return diferrenceMillisecond >= INTERVAL_FOLLOW;
	}
}
