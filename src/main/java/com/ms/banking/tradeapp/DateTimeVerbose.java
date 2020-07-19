package com.ms.banking.tradeapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;

/**
 * Write a program that accepts as input from the user the date in following
 * pattern dd-mm-yyyy hh:mm:ss a AM will be treated as Morning PM will be
 * treated as Evening
 * 
 * The output should be the date and time expressed in words. For example
 * 
 * Input date time : 03-07-2020 06:07:59 AM
 * 
 * Output:
 * 
 * Valid Date : 3rd-July -Twenty Twenty
 * 
 * Valid Time : Six Hours Seven Minutes Ten Seconds - Morning
 * 
 * Day of week : Friday
 *
 * @author Mayur Doshi
 */
public class DateTimeVerbose {

	// Following are literal names for numericals. These would be used to
	// express day/month/year/hours/min/sec numericals into text literals.

	private static final String[] verboseDayNames = { "", " first", " second", " third", " fourth", " fifth", " sixth",
			" seventh", " eighth", " nineth", " tenth", " eleventh", " twelveth", " thirteenth", " fourteenth",
			" fifteenth", " sixteenth", " seventeenth", " eighteenth", " nineteenth", " twenth", " twenty first",
			" twenty second", " twenty third", " twenty fourth", " twenty fifth", " twenty sixth", " twenty seventh",
			" twenty eighth", " twenty nineth", " thirty", " thirty first"

	};

	private static final String[] specialNames = { "", " thousand" };

	private static final String[] verboseTensNames = { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty",
			" seventy", " eighty", " ninety" };

	private static final String[] numNames = { "", " one", " two", " three", " four", " five", " six", " seven",
			" eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen",
			" seventeen", " eighteen", " nineteen", "twenty", "twenty one", "twenty two", "twenty three", "twenty four",
			"twenty five", "twenty six", "twenty seven", "twenty eight", "twenty nine", "thirty", "thirty one",
			"thirty two", "thirty three", "thirty four", "thirty five", "thirty six", "thirty seven", "thirty eight",
			"thirty nine", "forty", "forty one", "forty two", "forty three", "forty four", "forty five", "forty six",
			"forty seven", "forty eight", "forty nine", "fifty", "fifty one", "fifty two", "fifty three", "fifty four",
			"fifty five", "fifty six", "fifty seven", "fifty eight", "fifty nine", };

	public DateTimeVerbose() {

		// ask for user input
		System.out.println("Please enter a date in this format --> dd-mm-yyyy hh:mm:ss a");
		Scanner in = new Scanner(System.in);
		String strDateTime = in.nextLine();

		if (validateDate(strDateTime)) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
			
			//We need both Date and Time component
			LocalDateTime dateTime = LocalDateTime.parse(strDateTime, formatter);

			//Get Day
			int day = dateTime.getDayOfMonth();

			// Get Day in words eg Third
			String strDateToWords = getMonthDay(day);
			strDateToWords += " of ";
			
			// Get Month Text Value
			strDateToWords += dateTime.getMonth();
			
			int year = dateTime.getYear();
			// Get Year Text Value
			strDateToWords += " " + convert(year);

			//Print the entire Date in words
			System.out.println("Valid Date " + strDateToWords.toLowerCase());

			// Get Hour , Minute and Sec in words.
			int hour = dateTime.getHour();
			if(hour > 12) {
				hour = hour -12;
			}
			String strTimeToWords = getHourText(hour);

			strTimeToWords += " hours ";

			int minutes = dateTime.getMinute();
			strTimeToWords += getHourText(minutes);
			strTimeToWords += " minutes ";

			int seconds = dateTime.getSecond();
			strTimeToWords += getHourText(seconds);
			strTimeToWords += " seconds ";
			
			//Logic for AM = morning and PM = Evening
			if (strDateTime.contains("AM")) {
				strTimeToWords += " - MORNING";
			} else if (strDateTime.contains("PM")) {
				strTimeToWords += " - EVENING";
			}
			//Print the entire Time in words
			System.out.println("Valid Time " + strTimeToWords.toLowerCase());
			
			//Finally get the text value of Day of week
			DayOfWeek dayofWeek = dateTime.getDayOfWeek();

			String weekDay = dayofWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
			System.out.println("Day of week " + weekDay);

		} else {
			System.out.println("Wrongt! Please enter date in this format --> dd-MM-yyyy hh:mm:ss a");
		}
	}
	
	/*
	 * This function translates the numerical value of month, into text/words and returns that word
	 */
	private String getMonthDay(int day) {
		return verboseDayNames[day];
	}

	/*
	 * This function translates the numerical value of Hour, into text/words and returns that word
	 */
	private String getHourText(int hour) {
		return numNames[hour];
	}

	private String convertLessThanOneThousand(int number) {
		String current;

		if (number % 100 < 20) {
			current = numNames[number % 100];
			number /= 100;
		} else {
			current = numNames[number % 10];
			number /= 10;

			current = verboseTensNames[number % 10] + current;
			number /= 10;
		}
		if (number == 0) {
			return current;
		}
		return numNames[number] + " hundred" + current;
	}
	/*
	 * This function translates the numerical value of year into words.
	 */
	private String convert(int number) {

		if (number == 0) {
			return "zero";
		}

		String prefix = "";

		String current = "";
		int place = 0;

		if (number >= 1 && number < 2000) {
			do {
				int n = number % 100;
				if (n != 0) {
					String s = convertLessThanOneThousand(n);
					current = s + current;
				}
				place++;
				number /= 100;
			} while (number > 0);
		} else {
			do {
				int n = number % 1000;
				if (n != 0) {
					String s = convertLessThanOneThousand(n);
					current = s + specialNames[place] + current;
				}
				place++;
				number /= 1000;
			} while (number > 0);
		}

		return (prefix + current).trim();
	}

	private boolean validateDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
		try {
			sdf.setLenient(false);
			sdf.parse(date);
			return true;
		} catch (ParseException ex) {
			System.out.println("Invalid Date Time " + date);
			return false;
		}
	}

	public static void main(String[] args) {
		new DateTimeVerbose();

	}

}