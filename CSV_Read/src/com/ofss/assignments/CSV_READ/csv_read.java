package com.ofss.assignments.CSV_READ;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class csv_read {

	@SuppressWarnings("unchecked")
	public static String readCsv(String filename, String customerId, String accountId)
			throws IOException, CsvValidationException, ParseException {
		Reader reader = Files.newBufferedReader(Paths.get(filename));
		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
		// Reading Records One by One in a String array
		String[] nextRecord;
		JSONArray array = new JSONArray();

		while ((nextRecord = csvReader.readNext()) != null) {
			if (nextRecord[0].equals(customerId) && nextRecord[2].equals(accountId)) {

				JSONObject item = new JSONObject();
				item.put("Customer Id", nextRecord[0]);
				item.put("Customer Name", nextRecord[1]);
				item.put("Account Id", nextRecord[2]);
				item.put("Account Type", nextRecord[3]);
				item.put("Available Balance", nextRecord[4]);
				array.put(item);

			}

		}
		String message = array.toString();
		csvReader.close();
		return message;

	}

	public static void main(String[] args) throws IOException, ParseException, CsvValidationException {

		String customer_id = "5000100006";
		String accountId = "Q000092876521";
		String return_value = readCsv("C:\\Users\\junai\\eclipse-workspace\\CSV-Web-Project\\CustomerData.csv",
				customer_id, accountId);
		System.out.println(return_value);
	}
}
