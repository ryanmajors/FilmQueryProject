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
	public DatabaseAccessorObject() throws ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");

	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String sql = "SELECT id, title, description, release_year, rental_duration, rental_rate, length, replacement_cost, rating, special_features FROM film WHERE id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				film = new Film();
				film.setId(filmResult.getInt("id"));
				film.setTitle(filmResult.getString("title"));
				film.setDescription(filmResult.getString("description"));
				film.setReleaseYear(filmResult.getInt("release_year"));
				film.setRentalDuration(filmResult.getInt("rental_duration"));
				film.setRentalRate(filmResult.getInt("rental_rate"));
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
			System.out.println("The film ID you provided does not exist.");
			e.printStackTrace();
		}
		return film;
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
		} catch (SQLException e) {
			System.out.println("The actor ID you provided does not exist.");
			e.printStackTrace();
		}

		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actorList = new ArrayList<>();
		return actorList;
	}

	@Override
	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> filmList = new ArrayList<>();
		return filmList;
	}

	@Override
	public String findLanguageByFilmId(int filmId) {
		String language = null;
		return language;
	}

}
