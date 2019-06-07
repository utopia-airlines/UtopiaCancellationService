package com.st.utopia.cancellationservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * An airport model.
 *
 * @author Henry Cho
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
	@Column(name="flight")
	Integer flight;
	@Column(name="row")
	Integer row;
	@Column(name="seat")
	Character seat;
	@Column(name="class")
	Integer tier; //class is a keyword =(
	@Column(name="reserver")
	Integer reserver;
	@Column(name="price")
	Integer price;
	@Column(name="reservation_timeout")
	LocalDateTime reservation_timeout;
	@Column(name="booking_id")
	String booking_id;
	
	public Ticket(Integer flight, Integer row, Character seat, Integer tier, Integer reserver, Integer price,
			LocalDateTime reservation_timeout, String booking_id) {
		super();
		this.flight = flight;
		this.row = row;
		this.seat = seat;
		this.tier = tier;
		this.reserver = reserver;
		this.price = price;
		this.reservation_timeout = reservation_timeout;
		this.booking_id = booking_id;
	}
	
	public Integer getFlight() {
		return flight;
	}

	public Integer getRow() {
		return row;
	}

	public Character getSeat() {
		return seat;
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
	
	public LocalDateTime getReservation_timeout() {
		return this.reservation_timeout;
	}
	
	public void setReservation_timeout(LocalDateTime reservation_timeout) {
		this.reservation_timeout = reservation_timeout;
	}
	
	public String getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
	}
}
