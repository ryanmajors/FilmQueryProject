package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DataBindingException;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private String user = "student";
	private String pass = "student";

	// load driver
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";

		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				actor = new Actor();
				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));
				actor.setFilms(findFilmsByActorId(actorId));
			}
			conn.close();
			stmt.close();
			actorResult.close();
		} catch (SQLException e) {
			System.out.println("The actor ID you provided does not exist.");
			e.printStackTrace();
		}

		return actor;
	}
	
	private List<Film> findFilmsByActorId(int actorId) {
		List<Film> filmList = new ArrayList<>();
		Film film = null;
		String sql = "SELECT film.title "
				+ "FROM film "
				+ "JOIN film_actor ON film.id = film_actor.film_id "
				+ "JOIN actor ON actor.id = film_actor.actor_id "
				+ "WHERE actor.id = ?";
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet filmResult = stmt.executeQuery();

			while (filmResult.next()) {
				film = new Film();
				film.setTitle(filmResult.getString("title"));

				filmList.add(film);
			}
			conn.close();
			stmt.close();
			filmResult.close();
		} catch (SQLException e) {
			System.out.println("The actor ID you provided does not exist.");
			e.printStackTrace();
		}
		return filmList;
	}
	
	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String sql = "SELECT film.id, language.name, film.language_id, title, description, release_year, rental_duration, rental_rate, length, replacement_cost, rating, special_features "
				+ "FROM film "
				+ "JOIN language ON language.id = film.language_id "
				+ "WHERE film.id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				film = new Film();
				film.setId(filmResult.getInt("film.id"));
				film.setTitle(filmResult.getString("title"));
				film.setLanguage(filmResult.getString("language.name"));
				film.setDescription(filmResult.getString("description"));
				film.setReleaseYear(filmResult.getInt("release_year"));
				film.setRentalDuration(filmResult.getInt("rental_duration"));
				film.setRentalRate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setReplacementCost(filmResult.getDouble("replacement_cost"));
				film.setRating(filmResult.getString("rating"));
				film.setSpecialFeatures(filmResult.getString("special_features"));
				film.setActors(findActorsByFilmId(filmId));
			}
			conn.close();
			stmt.close();
			filmResult.close();
		} catch (SQLException e) {
			System.out.println("The film ID entered is not valid.");
			e.printStackTrace();
		}
		return film;
	}
	
	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actorList = new ArrayList<>();
		Actor actor = null;
		String sql = "SELECT actor.first_name, actor.last_name, actor.id "
				+ "FROM actor "
				+ "JOIN film_actor ON film_actor.actor_id = actor.id " 
				+ "JOIN film ON film.id = film_actor.film_id "
				+ "WHERE film.id = ?";
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet actorResult = stmt.executeQuery();

			while (actorResult.next()) {
				actor = new Actor();
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));

				actorList.add(actor);
			}
			conn.close();
			stmt.close();
			actorResult.close();
		} catch (SQLException e) {
			System.out.println("The actor ID you provided does not exist.");
			e.printStackTrace();
		}
		return actorList;
	}

	@Override
	public List<Film> findFilmBySearchKeyword(String keyword) {
		List<Film> filmList = new ArrayList<>();
		Film film = null;
		String sql = "SELECT film.id, language.name, film.language_id, title, description, release_year, rental_duration, rental_rate, length, replacement_cost, rating, special_features "
				+ "FROM film "
				+ "JOIN language ON language.id = film.language_id "
				+ "WHERE title LIKE ? OR description LIKE ?";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			keyword = "%" + keyword + "%";
			stmt.setString(1, keyword);
			stmt.setString(2, keyword);
			ResultSet filmResult = stmt.executeQuery();
			while (filmResult.next()) {
				film = new Film();
				film.setId(filmResult.getInt("film.id"));
				film.setTitle(filmResult.getString("film.title"));
				film.setLanguage(filmResult.getString("language.name"));
				film.setDescription(filmResult.getString("description"));
				film.setReleaseYear(filmResult.getInt("release_year"));
				film.setRentalDuration(filmResult.getInt("rental_duration"));
				film.setRentalRate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setReplacementCost(filmResult.getDouble("replacement_cost"));
				film.setRating(filmResult.getString("rating"));
				film.setSpecialFeatures(filmResult.getString("special_features"));
				film.setActors(findActorsByFilmId(film.getId()));
				filmList.add(film);
			}
			conn.close();
			stmt.close();
			filmResult.close();
		} catch (SQLException e) {
			System.out.println("The film ID entered is not valid.");
			e.printStackTrace();
		}
		return filmList;
	}
	
//	private String findLanguageByFilmId(int filmId) {
//		String language = null;
//		return language;
//	}

}
