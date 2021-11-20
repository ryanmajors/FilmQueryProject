package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.menus.Menus;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
//    app.test();
    app.launch();
  }

  private void test() {
    Film film = db.findFilmById(1);
//    System.out.println(film);
    Actor actor = db.findActorById(1);
//    System.out.println(actor);
    List<Actor> actorList = db.findActorsByFilmId(1);
    System.out.println(actorList);
    
  }

  private void launch() {
    Scanner sc = new Scanner(System.in);
    
    startUserInterface(sc);
    
    sc.close();
  }

  private void startUserInterface(Scanner sc) {
    Menus menus = new Menus();
    boolean continueSearch = true;
    System.out.println("Welcome");
    while (continueSearch) {
    menus.startMenu(sc);
    int startMenuChoice = sc.nextInt();
    	switch (startMenuChoice) {
    	case 1: System.out.println("What is the id of the film you are looking for?");
    		int filmId = sc.nextInt();
    		Film film = db.findFilmById(filmId);
    		if (film == null) {
    			System.out.println("Invalid film id.");
    		} else {
    			System.out.println(film);
    		}
    	    continueSearch = menus.searchAgainMenu(sc);
    	    break;
    	case 2: System.out.println("What is the id of the actor you are looking for?");
    		int actorId = sc.nextInt();
    		Actor actor = db.findActorById(actorId);
    		if (actor == null) {
    			System.out.println("Invalid actor id.");
    		} else {
    			System.out.println(actor);
    		}
    		continueSearch = menus.searchAgainMenu(sc);
    		break;
    	case 3: System.out.println("What is the keyword you would like to find a movie by?");
    		String keyword = sc.next();
    		System.out.println(keyword);
    		
    		List<Film> filmByKeyword = db.findFilmBySearchKeyword(keyword);
    		if(filmByKeyword == null)  {
    			System.out.println("Could not find film with keyword provided.");
    		} else {
    			System.out.println(filmByKeyword);
    		}
    		System.out.println(filmByKeyword.size());
    	case 4: System.out.println("Have a great day!"); continueSearch = false; break;
    	}
    }
  }

}
