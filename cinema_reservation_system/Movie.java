package cinema_reservation_system;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import week11.Employee;

public class Movie {
	private int id;
	private String name;
	private float rating;
	
	private Session session;
	
	public Movie() {
		
	}

	public Movie(int id, String name, float rating) {
		this.id = id;
		this.name = name;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}

	
	public void listMovies() {
		List movies = session.createQuery("FROM Movie").getResultList();
		for (Iterator iterator = movies.iterator(); iterator.hasNext();) {
			Movie movie = (Movie) iterator.next();
			System.out.print("Name: " + movie.getName());
			System.out.print("Rating: " + movie.getRating());
		}
	}
}
