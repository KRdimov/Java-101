package cinema_reservation_system;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
	private static SessionFactory factory;
	
	public static void main(String[] args) {
		try {
			factory = new Configuration().configure("/resources/reservation_system/hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		Session session = factory.openSession();
		Movie movies = new Movie();
		movies.setSession(session);
		movies.listMovies();
		Reservation reservations = new Reservation();
		reservations.setSession(session);
		reservations.listReservations();
	}
}
