package bot.instagram;

import java.io.IOException;

import utils.Populate;

public class Main {

	public static void main(String[] args) {
		Populate.jsonToArrayOfAccounts("C:/bots/instagram/contas.txt");

		try {
			Runtime.getRuntime()
					.exec("chrome.exe --remote-debugging-port=9222 --user-data-dir=\"C:\\bots\\instagram\"");
			new Driver("9222");

			try {
				Instagram.logout(3);
			} catch (Exception e) {
				System.out.print("Não tinha conta logada");
			}

			Driver.access.get("https://www.ganharnoinsta.com/painel/");

			GanharNoInsta.login();
			GanharNoInsta.startFollow();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
