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
	Integer flight;
	/**
	 * the row of the seat .
	 */
	@Column(name="seat_row")
	Integer row;
	/**
	 * the seat group.
	 */
	@Column(name="seat")
	Character seat;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flight == null) ? 0 : flight.hashCode());
		result = prime * result + ((row == null) ? 0 : row.hashCode());
		result = prime * result + ((seat == null) ? 0 : seat.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicketIdentity other = (TicketIdentity) obj;
		if (flight == null) {
			if (other.flight != null)
				return false;
		} else if (!flight.equals(other.flight))
			return false;
		if (row == null) {
			if (other.row != null)
				return false;
		} else if (!row.equals(other.row))
			return false;
		if (seat == null) {
			if (other.seat != null)
				return false;
		} else if (!seat.equals(other.seat))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TicketIdentity [flight=" + flight + ", row=" + row + ", seat=" + seat + "]";
	}
	
	
}
