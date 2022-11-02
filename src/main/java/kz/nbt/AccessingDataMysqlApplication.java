package kz.nbt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@SpringBootApplication
public class AccessingDataMysqlApplication {

	public static void main(String[] args) throws TelegramApiException {
		SpringApplication.run(AccessingDataMysqlApplication.class, args);
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
		telegramBotsApi.registerBot(new Bot());
	}
}
