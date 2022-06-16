package com.ofss.assignments.csv_insert;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class CSV_Insert {

	public static boolean checkIfIdexists(String fileName, String customerId, String accountId)
			throws CsvValidationException, IOException {
		Reader reader = Files.newBufferedReader(Paths.get(fileName));
		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
		// Reading Records One by One in a String array
		String[] nextRecord;
		Boolean IdExists = false;

		while ((nextRecord = csvReader.readNext()) != null) {
			if (nextRecord[0].equals(customerId) || nextRecord[2].equals(accountId)) {
				IdExists = true;
			}

		}
		csvReader.close();
		return IdExists;
	}

	public static String writeToCsv(String filename, String json_input_string)
			throws IOException, CsvValidationException, ParseException, org.json.simple.parser.ParseException {

		// create FileWriter object with file as parameter
		FileWriter outputfile = new FileWriter(filename, true);

		// create CSVWriter object filewriter object as parameter
		CSVWriter writer = new CSVWriter(outputfile);

		// parsing file "JSONExample.json"
		Object obj = new JSONParser().parse(json_input_string);

		// typecasting obj to JSONObject
		JSONObject jo = (JSONObject) obj;

		// getting firstName and lastName
		String customerIdToBeInserted = (String) jo.get("Customer Id");
		String customerNameToBeInserted = (String) jo.get("Customer Name");
		String accountIdToBeInserted = (String) jo.get("Account Id");
		String accountTypeToBeInserted = (String) jo.get("Account Type");
		String AvailableBalanceToBeInserted = (String) jo.get("Available Balance");

		Boolean IdExists = checkIfIdexists(filename, customerIdToBeInserted, accountIdToBeInserted);

		if (IdExists) {
			writer.close();
			return "{\"message\" : \"Customer ID or Account ID already exists in the database\"}";

		} else {
			// add data to csv
			String[] data1 = { customerIdToBeInserted, customerNameToBeInserted, accountIdToBeInserted,
					accountTypeToBeInserted, AvailableBalanceToBeInserted };
			writer.writeNext(data1);

			String message = jo.toString();
			writer.close();
			return message;
		}

	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args)
			throws CsvValidationException, IOException, ParseException, org.json.simple.parser.ParseException {

		JSONObject item = new JSONObject();
		item.put("Customer Id", "5000100007");
		item.put("Customer Name", "Junaid");
		item.put("Account Id", "Q000092876522");
		item.put("Account Type", "Current");
		item.put("Available Balance", "100000.00");

		String json_input_data = item.toString();

		String return_value = writeToCsv("C:\\Users\\junai\\eclipse-workspace\\CSV-Web-Project\\CustomerData.csv",
				json_input_data);
		System.out.println(return_value);
	}
}
