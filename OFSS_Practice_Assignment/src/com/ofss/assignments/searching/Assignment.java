package com.ofss.assignments.searching;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;

public class Assignment {

	// function to sort hashmap by values
	/**
	 * The sortByHashMap program implements an application that sorts a Hashmap by
	 * value in ascending or descending order.
	 * 
	 * @param hm      Pass the hashmap to be sorted.
	 * @param reverse This is a boolean value that is define the order of the output
	 *                hashmap.
	 * @author Junaid Girkar
	 * @version 1.0
	 * @since 2022-06-10
	 */
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm, boolean reverse) {
		// Create a list from elements of HashMap
		List<HashMap.Entry<String, Integer>> list = new LinkedList<HashMap.Entry<String, Integer>>(hm.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<HashMap.Entry<String, Integer>>() {
			public int compare(HashMap.Entry<String, Integer> o1, HashMap.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// put data from sorted list to hashmap
		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();

		if (reverse) {
			Collections.reverse(list);
		}

		for (HashMap.Entry<String, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;

	}

	// Function to sort map by Key
	/**
	 * The sortByKey program implements an application that sorts a Hashmap by key.
	 * This function prints the sorted hashmap in ascending and descending order.
	 * 
	 * @param hm Pass the hashmap to be sorted.
	 * @author Junaid Girkar
	 * @version 1.0
	 * @since 2022-06-10
	 */
	public static void sortByKey(HashMap<String, Integer> hm) {
		// TreeMap to store values of HashMap
		TreeMap<String, Integer> sorted = new TreeMap<>();

		// Copy all data from hashMap into TreeMap
		sorted.putAll(hm);

		// TreeMap is already sorted
		System.out.print("Ascending by Key : ");
		System.out.println(sorted);

		// view map containing reverse view of mapping
		Map<String, Integer> reverseMap = sorted.descendingMap();

		// After reverse
		System.out.println("Descending by Key : " + reverseMap);
	}

	/**
	 * The printFirstHashMapValue program implements an program that prints the
	 * first key value pair of a hashmap.
	 * 
	 * @param hm Pass the hashmap whose first value is to be printed.
	 * @author Junaid Girkar
	 * @version 1.0
	 * @since 2022-06-10
	 */
	public static void printFirstHashMapValue(HashMap<String, Integer> hm) {

		System.out.print(hm.keySet().toArray()[0] + " = ");
		System.out.println(hm.values().toArray()[0]);
	}

	/**
	 * The readFileToHashmap program implements an program that prints the first key
	 * value pair of a hashmap.
	 * 
	 * @param filename   Pass the path of the text file.
	 * @param searchWord This is a string keyword that has to be searched in the
	 *                   textfile.
	 * @author Junaid Girkar
	 * @version 1.0
	 * @since 2022-06-10
	 */
	public static HashMap<String, Integer> readFileToHashmap(String filename, String searchWord) {
		File file = new File(filename);
		Scanner sc; // Declare the scanner variable
		HashMap<String, Integer> hashMap = new HashMap<>();
		int wordCount = 0;

		try {
			sc = new Scanner(file);
			// Gets each line till end of file is reached
			while (sc.hasNextLine()) {
				// Splits each line into words
				String words[] = sc.nextLine().split(" ");

				for (String word : words) {
					// Words are case insensitive
					word = word.toUpperCase();
					if (word.contains(searchWord)) {
						wordCount += 1;
					}
					// Increment value if exists else add to hashmap
					if (hashMap.containsKey(word))
						hashMap.put(word, hashMap.get(word) + 1);

					else
						hashMap.put(word, 1);
				}

			}

			System.out.println("Words conatining " + searchWord + " is : " + wordCount);

			// Closing the scanner
			sc.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found error");

		}
		return hashMap;
	}

	// Driver Code
	public static void main(String[] args) {

		String input_file_path = "";

		try (InputStream input = Assignment.class.getClassLoader().getResourceAsStream("config.properties")) {

			Properties prop = new Properties();

			if (input == null) {
				System.out.println("Sorry, unable to find config.properties");
				return;
			}

			// load a properties file from class path, inside static method
			prop.load(input);
			input_file_path = prop.getProperty("input_file");
			HashMap<String, Integer> hashMap = readFileToHashmap(
					"C:\\Users\\junai\\eclipse-workspace\\OFSS_Practice_Assignment\\src\\input.txt", "COMPUTER");
			System.out.println("Only Computer Count : " + hashMap.get("COMPUTER"));

			// Sorting the HashMap now
			HashMap<String, Integer> ascendingHashMap = sortByValue(hashMap, false);
			HashMap<String, Integer> descendingHashMap = sortByValue(hashMap, true);

			System.out.print("Max key value pair : ");
			printFirstHashMapValue(descendingHashMap);

			// Printing the HashMap
			System.out.print("\nASCENDING by Value : ");
			System.out.println(ascendingHashMap);
			System.out.print("DESCENDING by Value : ");
			System.out.println(descendingHashMap);
			System.out.println();

			sortByKey(hashMap);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Couldn't find properties file");
		}

	}
}