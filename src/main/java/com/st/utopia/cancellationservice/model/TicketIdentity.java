package com.st.utopia.cancellationservice.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * A class to be the primary key of a {@link Ticket} for JPA, which requires every
 * Entity to have a single primary key.
 *
 * <p>Declared to implement Serializable because "Composite-id class must implement Serializable."
 *
 * @author Al-amine AHMED MOUSSA
 */

@Embeddable
public class TicketIdentity implements Serializable {
	/**
	 * Serialization version. Increment on any change to class structure that is
	 * pushed to production.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * the flight ID.
	 */
	@Column(name="flight")
	private Integer flight;
	/**
	 * the row of the seat .
	 */
	@Column(name="seat_row")
	private Integer row;
	/**
	 * the seat group.
	 */
	@Column(name="seat")
	private Character seat;
	/**
	 * the Getters and Setters of the Class
	 */
	public Integer getFlight() {
		return flight;
	}

	public Integer getRow() {
		return row;
	}
	
	public Character getSeat() {
		return seat;
	}
	
	/**
	 * the Constructors of the Class
	 */

	public TicketIdentity() {
		
	}

	public TicketIdentity(Integer flight, Integer row, Character seat) {
		this.flight = flight;
		this.row = row;
		this.seat = seat;
	}

	@Override
	public String toString() {
		return "TicketIdentity [flight=" + flight + ", row=" + row + ", seat=" + seat + "]";
	}
	
	
}