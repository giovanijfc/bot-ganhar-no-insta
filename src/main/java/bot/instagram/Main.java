package bot.instagram;

import java.util.concurrent.TimeUnit;

import utils.Populate;

public class Main {

	private static final int POSITION_QUANTITY_FOLLOWS_IN_INTERVAL = 0;
	private static final int POSITION_INTERVAL_TIME = 1;
	private static final int POSITION_INTERVAL_FOLLOW_BLOCK_TIME = 2;
	private static final int EMAIL_POSITION = 3;
	private static final int PASSWORD_POSITION = 4;

	public static void main(String[] args) {

		GanharNoInsta.INTERVAL_FOLLOW = Integer.parseInt(args[POSITION_INTERVAL_TIME]) * 1000;
		GanharNoInsta.QUANTITY_FOLLOWS_IN_INTERVAL_TIME = Integer.parseInt(args[POSITION_QUANTITY_FOLLOWS_IN_INTERVAL]);
		GanharNoInsta.INTERVAL_NONE_FOLLOW_BLOCK_TIME = Integer.parseInt(args[POSITION_INTERVAL_FOLLOW_BLOCK_TIME])
				* 1000;
		GanharNoInsta.EMAIL = args[EMAIL_POSITION];
		GanharNoInsta.PASSWORD = args[PASSWORD_POSITION];

		System.out.println("Quantidade de seguidores em cada intervalo de tempo: "
				+ GanharNoInsta.QUANTITY_FOLLOWS_IN_INTERVAL_TIME);
		System.out.println("Intervalo de tempo de espera após seguir: " + GanharNoInsta.INTERVAL_FOLLOW / 1000 + " segundos");
		System.out.println("Intervalo de tempo de espera após tomar block de seguir: "
				+ GanharNoInsta.INTERVAL_NONE_FOLLOW_BLOCK_TIME / 1000 + " segundos");
		System.out.println("Email: " + GanharNoInsta.EMAIL);
		System.out.println("Senha: " + GanharNoInsta.PASSWORD);

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

			Driver.access.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

			GanharNoInsta.login();
			GanharNoInsta.startFollow();
		} catch (Exception e) {
			Driver.access.close();
			Driver.access.quit();
			main(args);
		}
	}
}
