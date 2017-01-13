package cinema_reservation_system;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

public class Reservation {
	private int id;
	private String username;
	private int projection_id;
	private int row;
	private int col;

	private Session session;

	public Reservation(int id, String username, int projection_id, int row, int col) {
		this.id = id;
		this.username = username;
		this.projection_id = projection_id;
		this.row = row;
		this.col = col;
	}


	public Reservation() {
	
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public int getProjection_id() {
		return projection_id;
	}


	public void setProjection_id(int projection_id) {
		this.projection_id = projection_id;
	}


	public int getRow() {
		return row;
	}


	public void setRow(int row) {
		this.row = row;
	}


	public int getCol() {
		return col;
	}


	public void setCol(int col) {
		this.col = col;
	}


	public Session getSession() {
		return session;
	}


	public void setSession(Session session) {
		this.session = session;
	}


	public void listReservations() {
		List reservations = session.createQuery("FROM Reservation").getResultList();
		for (Iterator iterator = reservations.iterator(); iterator.hasNext();) {
			Reservation reservation = (Reservation) iterator.next();
			System.out.print("Username: " + reservation.getUsername());
		}
	}
}
