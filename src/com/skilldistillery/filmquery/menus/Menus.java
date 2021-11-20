package com.skilldistillery.filmquery.menus;

import java.util.Scanner;

public class Menus {
	
	public void banner() {

	System.out.println("	 .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------."); 
	System.out.println("	| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |");
	System.out.println("	| |  _________   | || |     _____    | || |   _____      | || | ____    ____ | || |  ________    | || |   ______     | |");
	System.out.println("	| | |_   ___  |  | || |    |_   _|   | || |  |_   _|     | || ||_   \\  /   _|| || | |_   ___ `.  | || |  |_   _ \\    | |");
	System.out.println("	| |   | |_  \\_|  | || |      | |     | || |    | |       | || |  |   \\/   |  | || |   | |   `. \\ | || |    | |_) |   | |");
	System.out.println("	| |   |  _|      | || |      | |     | || |    | |   _   | || |  | |\\  /| |  | || |   | |    | | | || |    |  __'.   | |");
	System.out.println("	| |  _| |_       | || |     _| |_    | || |   _| |__/ |  | || | _| |_\\/_| |_ | || |  _| |___.' / | || |   _| |__) |  | |");
	System.out.println("	| | |_____|      | || |    |_____|   | || |  |________|  | || ||_____||_____|| || | |________.'  | || |  |_______/   | |");
	System.out.println("	| |              | || |              | || |              | || |              | || |              | || |              | |");
	System.out.println("	| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |");
	System.out.println("	 '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'"); 

	}

	public void startMenu(Scanner sc) {
		System.out.println("\tWhat would you like to do?\n\n" 
				+ "\t1. Look up a film by it's id\n"
				+ "\t2. Look up a film by a search keyword\n" 
				+ "\t3. Exit the application");
	}

	public boolean searchAgainMenu(Scanner sc) {
		boolean continueSearch = true;
		System.out.println("\n\tWould you like to do a new search?\n\n" 
				+ "\t1. Yes\n" 
				+ "\t2. No");
		int searchAgain = sc.nextInt();
		if (searchAgain == 1) {
			return continueSearch;
		} else if (searchAgain == 2) {
			System.out.println("\tHave a great day!");
			continueSearch = false;
		}
		return continueSearch;
	}

}
