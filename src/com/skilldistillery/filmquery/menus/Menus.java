package com.skilldistillery.filmquery.menus;

import java.util.Scanner;

public class Menus {

	public void startMenu(Scanner sc) {
		System.out.println("What would you like to do?\n"
				+ "1. Look up a film by it's id\n"
				+ "2. Look up an actor or actress by their id\n"
				+ "3. Look up a film by a search keyword\n"
				+ "4. Exit the application");
	}
	
	public boolean searchAgainMenu(Scanner sc) {
		boolean continueSearch = true;
		System.out.println("Would you like to do a new search?\n"
				+ "1. Yes\n"
				+ "2. No");
		int searchAgain = sc.nextInt();
		if(searchAgain == 1) {
			return continueSearch;
		} else if(searchAgain == 2) {
			System.out.println("Have a great day!");
			continueSearch = false;
		}
		return continueSearch;
	}

}
