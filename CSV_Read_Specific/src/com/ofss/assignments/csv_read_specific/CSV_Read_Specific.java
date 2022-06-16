package com.ofss.assignments.csv_read_specific;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.simple.JSONObject;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class CSV_Read_Specific {

	private static int getHeaderLocation(String[] headers, String columnName) {
		return Arrays.asList(headers).indexOf(columnName);
	}

	@SuppressWarnings("unchecked")
	public static String readCsv(String filename, String customerId, String heading_name)
			throws IOException, CsvValidationException, ParseException {

		Reader reader = Files.newBufferedReader(Paths.get(filename));
		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(0).build();
		// Reading Records One by One in a String array
		String[] nextRecord;
		JSONArray array = new JSONArray();

		String[] header = csvReader.readNext();
		Integer columnPosition = getHeaderLocation(header, heading_name);

		while ((nextRecord = csvReader.readNext()) != null) {

			if (nextRecord[0].equals(customerId)) {

				JSONObject item = new JSONObject();
				item.put("Customer Id", nextRecord[0]);
				item.put(heading_name, nextRecord[columnPosition]);
				array.put(item);

			}

		}
		String message = array.toString();
		csvReader.close();
		return message;

	}

	public static void main(String[] args) throws CsvValidationException, IOException, ParseException {

		String customer_id = "5000100006";
		String heading_name = "Account Type";
		String return_value = readCsv("C:\\Users\\junai\\eclipse-workspace\\CSV-Web-Project\\CustomerData.csv",
				customer_id, heading_name);
		System.out.println(return_value);
	}
}
