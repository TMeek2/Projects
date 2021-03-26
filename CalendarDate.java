// Tim Meek II
// COSC 3420
// February 4, 2021
// Description: The purpose of this program is to allow the user to input a date and
// receive the next and previous date, along with the day of the week in return


import java.time.*; 
import java.time.DayOfWeek;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CalendarDate {

	// create variables used throughout the program
	private int month;
	private int day_of_month;
	private int year;
	private int max_days;
	private	String day_of_week;
	private int[] dateArray;
	private static boolean invalidDate;


	public static void main(String[] args){
		
		// Initializes the values to start the loop
		int newMonth = 0;
		int newDay = 0;		
		int newYear = 0;

		Scanner keyboard = new Scanner(System.in);

		// Prompt the user to enter the day they want tested
		System.out.println("\nEnter any date to recieve the next date, previous date and the day of the week.");
		System.out.println("Enter the value \"-1\" at any point to end the program.\n");

		// Allows for the user to input -1 at any point to exit the program
		while(newMonth != -1 || newDay != -1 || newYear != -1){
			invalidDate = false;

			// Reset values to reset while loops for the exception handling
			newMonth = 0;
			newDay = 0;
			newYear = -9999999; // This is a value that will never be entered but still allows for negative year to be tested

			// Receives the month from the user
			System.out.print("Enter the numerical number of the month: ");
			while (newMonth == 0) {
				try{
				newMonth = keyboard.nextInt();
				}
				catch (InputMismatchException a){ // In the instance an integer is not entered
					keyboard.next();
					System.out.println("** Only integers are accepted. Please try again.**");
					System.out.print("Enter the numerical number of the month: ");
				}
			}
			if (newMonth == -1)  // Allows user to exit program when -1 is entered
					break; 

			// Receives the day of month from the user
			System.out.print("Enter the numerical number of the day of the month: ");
			while (newDay == 0) {
				try{
				newDay = keyboard.nextInt();
				}
				catch(InputMismatchException b){ // In the instance an integer is not entered
					keyboard.next();
					System.out.println("** Only integers are accepted. Please try again.**");
					System.out.print("Enter the numerical number of the day of the month: ");
				}
			} // Moves to find the new year
			if (newDay == -1)
					break;
			
			// Receives the year from the user
			System.out.print("Enter the year: ");
			while (newYear == -9999999) {
				try{
				newYear = keyboard.nextInt();
				}
				catch(InputMismatchException c){ // In the instance an integer is not entered
					keyboard.next();
					System.out.println("** Only integers are accepted. Please try again.**");
					System.out.print("Enter the year: ");
				}
			}
			if (newYear == -1)
					break;

			CalendarDate newDate = new CalendarDate(newMonth, newDay, newYear); // Sends values to CalendarDate method

			System.out.println("======================================================"); // Prints line for formatting
		} // while loop
		
		keyboard.close();

	} // main


	private CalendarDate(int monthNum, int monthDay, int yearNum){

		// method creates a CalendarDate object
		month = monthNum;
		day_of_month = monthDay;
		year = yearNum;

		// Uses the checkDate method to make sure the object created is a valid
		// date, if not the date is set to January 1, 2012
		checkDate(month, day_of_month, year);

		// Informs the user that the date they inputed was invalid
		if (invalidDate == true){
			System.out.println("** The date you entered was invalid. **");
			System.out.println("** The date was set to the default date of \"January 1, 2012\". **");
			}

		// Then uses nextDay method to print the string form of the next date
		// I used arrays to store the values of the date in case there were any invalid dates entered
		nextDay(dateArray[0], dateArray[1], dateArray[2]);

		// Then uses the previousDay method to print the string form of the previous date
		previousDay(dateArray[0], dateArray[1], dateArray[2]);

		// Lastly, uses the dayOfWeek method to print the day of the week of the given date
		dayOfWeek(dateArray[0], dateArray[1], dateArray[2]);

	} // CalendateDate method


	private int[] checkDate(int month, int day_of_month, int year){

		// Tests the date to make sure that the given date is a valid date
		// Also used to find the maximum amount of days in a month

		if (month == 1 ||month == 3 || month ==5 || month == 7 || month == 8 || month == 10 || month == 12) // 31 day months
			max_days = 31;
		
		else if (month == 4 || month == 6 || month == 9 || month == 11) // 30 day months
			max_days = 30;
			
		else if (month == 2) // Checks for leap year
			if (year % 4 == 0 && year % 100 != 0)
				max_days = 29;
				
			else // February non-leap year
				max_days = 28;
				
		else{ // Sets to default date of January 1, 2012 (which has 31 days)
			month = 1;
			day_of_month = 1;
			year = 2012;
			max_days = 31;
			invalidDate = true; // This value is used in the CalendarDate method to inform the user their date was invalid
			}

		if (day_of_month > max_days){ // Sets to default date if the month date entered is greater than the max days in that month
			month = 1;
			day_of_month = 1;
			year = 2012;
			System.out.println("** The day of the month entered is not within the range of the maximum days within that month. **"); 
			System.out.println("** The date was set to the default date of \"January 1, 2012\". **");
			}

		else if (day_of_month < 1){ // Sets to default date if the day of month is less than 1
			month = 1;
			day_of_month = 1;
			year = 2012;
			System.out.println("** The day of the month entered is less than 1. **"); 
			System.out.println("** The date was set to the default date of \"January 1, 2012\". **");
			}

		// This arary is created in order to return the date in case an incorrect date is entered
		dateArray = new int[]{month, day_of_month, year};

		return dateArray;

	} // checkDate method


	private String getMonthName(int month){

		// Uses the month number to find the name of the month

		String monthWord;

		if (month == 1)
			monthWord = "January";

		else if (month == 2)
			monthWord = "February";

		else if (month == 3)
			monthWord = "March";

		else if (month == 4)
			monthWord = "April";

		else if (month == 5)
			monthWord = "May";

		else if (month == 6)
			monthWord = "June";

		else if (month == 7)
			monthWord = "July";

		else if (month == 8)
			monthWord = "August";

		else if (month == 9)
			monthWord = "September";

		else if (month == 10)
			monthWord = "October";

		else if (month == 11)
			monthWord = "November";

		else if (month == 12)
			monthWord = "December";

		else
			monthWord = "January"; // Default value in the instance of an incorrect date

		return monthWord;

	} // getMonthName method
	

	private void nextDay(Integer month, Integer day_of_month, Integer year){

		day_of_month += 1; // Adds 1 to the current day of the month

		if (day_of_month > max_days){ // Takes care of the situations dealing with the last day of the month
			day_of_month = 1;
			month += 1;
			}

		if (month == 13){ // Takes care of the instance when the entered date is December 31st
			month = 1; 
			year += 1;
			}

		String nextDate = (getMonthName(month) + " " + Integer.toString(day_of_month) + ", " + Integer.toString(year));

		// Prints out the next date
		System.out.println("\tThe next date is: " + nextDate);

	} // nextDay method


	private void previousDay(Integer month, Integer day_of_month, Integer year){

		day_of_month -= 1; // Subtracts 1 from the current day of the month

		if (day_of_month < 1){ // Takes care of the situations dealing with the first day of the month
			month -= 1;
			checkDate(month,day_of_month,year);  // used to get max_days variable
			day_of_month = max_days;
			}
		
		if (month == 0){ // takes care of the instance when the entered date is Janurary 1st
			month = 12;
			year -= 1;
 			}

		String previousDate = (getMonthName(month) + " " + Integer.toString(day_of_month) + ", " + Integer.toString(year));

		// Prints out the previous date
		System.out.println("\tThe previous date was: " + previousDate);

	} // previousDay method


	private void dayOfWeek(Integer month, Integer day_of_month, Integer year){

		// Uses the DayOfWeek method from the Java Time class to determine the day of the week

		LocalDate localDate = LocalDate.of(year, month, day_of_month);
		DayOfWeek dayOfWeek = DayOfWeek.from(localDate);

		day_of_week = dayOfWeek.name();

		String day_of_weekFirstLetter = day_of_week.substring(0,1); // Gets capital first letter
		day_of_week = day_of_week.toLowerCase(); // sets the rest of the word to the lower case

		// Prints out the day of the week
		System.out.println("\t" + getMonthName(month) + " " + Integer.toString(day_of_month) + ", " + Integer.toString(year) + " fell on a " + day_of_weekFirstLetter + day_of_week.substring(1) + ".");

	} // dayOfWeek method


} // CalendarDate class