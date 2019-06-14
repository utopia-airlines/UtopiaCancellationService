package com.st.utopia.cancellationservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * An Ticket model.
 *
 * @author Henry Cho
 * @author Al-amine AHMED MOUSSA
 * 
 * Model used for the purpose of the cancellation microservice.
 * 
 * There are no set methods for information about the ticket such as flight number, row/seat, or class.
 * 
 * Upon cancelling the ticket, the microservice needs to set the following fields as null:
 * 	-reserver
 * 	-price
 * 	-reservation_timeout
 * 	-booking_id
 * 
 * This model does not need to define a composite key since the cancellation microservice will search for the ticket via booking_id which is unique.
 */ 

@Entity
@Table(name="tbl_tickets")
public class Ticket{
	/**
	 * The three fields that together constitute the identity of a Ticket.
	 */
	@EmbeddedId
	private TicketIdentity id;
	
	@Column(name="class")
	private Integer tier; //class is a keyword 
	
	@Column(name="reserver")
	private Integer reserver;
	
	@Column(name="price")
	private Integer price;
	
	@Column(name="reservation_timeout")
	private LocalDateTime reservation_timeout;
	
	@Column(name="booking_id")
	private String booking_id;
	/**
	 * the Getters and Setters of the Class
	 */
	public TicketIdentity getId() {
		return id;
	}

	public void setId(TicketIdentity id) {
		this.id = id;
	}

	public Integer getFlight() {
		if (id == null) {
			return null;
		} else {
			return id.getFlight();
		}
	}
	
	public Integer getRow() {
		if (id == null) {
			return null;
		} else {
			return id.getRow();
		}
	}
	
	public char getSeat() {
		if (id == null) {
			return 0;
		} else {
			return id.getSeat();
		}
	}

	public Integer getTier() {
		return tier;
	}

	public void setTier(Integer tier) {
		this.tier = tier;
	}

	public Integer getReserver() {
		return reserver;
	}

	public void setReserver(Integer reserver) {
		this.reserver = reserver;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public LocalDateTime getReservationtimeout() {
		return reservation_timeout;
	}

	public void setReservationtimeout(LocalDateTime reservation_timeout) {
		this.reservation_timeout = reservation_timeout;
	}

	public String getBookingid() {
		return booking_id;
	}

	public void setBookingid(String booking_id) {
		this.booking_id = booking_id;
	}

	public Ticket() {

	}

	public Ticket(TicketIdentity id) {
		this.id = id;
	}

	public Ticket(TicketIdentity id, Integer tier, Integer reserver, Integer price, LocalDateTime reservation_timeout,
			String booking_id) {
		this.id = id;
		this.tier = tier;
		this.reserver = reserver;
		this.price = price;
		this.reservation_timeout = reservation_timeout;
		this.booking_id = booking_id;
	}
	
	public Ticket(final Integer flight , final Integer row, final Character seat,
			Integer tier, Integer reserver, Integer price, LocalDateTime reservation_timeout,String booking_id) {
		if (flight == null && row == null && seat == null) {
			id = null;
		} else {
			id = new TicketIdentity(flight, row,seat);
		}
		// TODO: check that it's nonnegative?
		this.tier = tier;
		this.reserver = reserver;
		this.price = price;
		this.reservation_timeout = reservation_timeout;
		this.booking_id = booking_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Ticket other = (Ticket) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", tier=" + tier + ", reserver=" + reserver + ", price=" + price
				+ ", reservation_timeout=" + reservation_timeout + ", booking_id=" + booking_id + "]";
	}

}
