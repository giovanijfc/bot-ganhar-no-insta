package bot.instagram;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import utils.Populate;

public class Main {

	public static void main(String[] args) {
		Populate.jsonToArrayOfAccounts("C:/bots/instagram/contas.txt");

		try {
			Runtime.getRuntime()
					.exec("chrome.exe --remote-debugging-port=9222 --user-data-dir=\"C:\\bots\\instagram\"");
			new Driver("9222");
			Driver.access.get("https://www.ganharnoinsta.com/painel/");

			Driver.access.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			GanharNoInsta.login();
			GanharNoInsta.startFollow();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
