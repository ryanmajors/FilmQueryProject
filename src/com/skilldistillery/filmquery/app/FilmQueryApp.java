package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;
import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.menus.Menus;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private void launch() {
		Scanner sc = new Scanner(System.in);
		startUserInterface(sc);
		sc.close();
	}

	private void startUserInterface(Scanner sc) {
		Menus menus = new Menus();
		menus.banner();
		boolean continueSearch = true;
		System.out.println("\n                                                              Welcomes you!\n\n\n");
		while (continueSearch) {
			menus.startMenu(sc);
			int startMenuChoice = sc.nextInt();
			switch (startMenuChoice) {
			case 1:
				System.out.println("\tWhat is the id of the film you are looking for?");
				int filmId = 0;
				filmId = sc.nextInt();
				Film film = db.findFilmById(filmId);
				if (film == null) {
					System.out.println("\tCould not find film matching that id.");
				} else {
					System.out.println(film);
				}
				continueSearch = menus.searchAgainMenu(sc);
				break;
			case 2:
				System.out.println("\tWhat is the keyword you would like to find a movie by?");
				String keyword = sc.next();
				List<Film> filmByKeyword = db.findFilmBySearchKeyword(keyword);
				if (filmByKeyword == null || filmByKeyword.isEmpty()) {
					System.out.println("\tCould not find film or description containing keyword provided.");
				} else {
					System.out.println(filmByKeyword);
				}
				continueSearch = menus.searchAgainMenu(sc);
				break;
			case 3:
				System.out.println("\tHave a great day!");
				continueSearch = false;
				break;
			}
		}
	}

}
