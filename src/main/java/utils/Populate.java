package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bot.instagram.GanharNoInsta;
import models.Account;
import models.AccountDTO;

public class Populate {

	private static Scanner sc;

	public static void jsonToArrayOfAccounts(String path) {

		try {
			File file = new File(path);
			sc = new Scanner(file);
			sc.useDelimiter("\\Z");

			String stringJSON = sc.next();

			ObjectMapper mapper = new ObjectMapper();
			AccountDTO[] arrayToConvert = mapper.readValue(stringJSON, AccountDTO[].class);
			List<AccountDTO> accountsDTO = new ArrayList<AccountDTO>(Arrays.asList(arrayToConvert));

			accountsDTO.stream().forEach(accountDTO -> {
				GanharNoInsta.accounts
						.add(new Account(accountDTO.getNickName(), accountDTO.getEmail(), accountDTO.getPassword()));
			});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
