package com.st.utopia.cancellationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.st.utopia.cancellationservice.model.Ticket;
import com.st.utopia.cancellationservice.service.TicketService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/cancel")
public class CancellationServiceController {

	@Autowired
	private TicketService ticketservice;

	/**
	 * Only the holder of the Ticket should be able to cancel the reservation, or
	 * the agent if the ticket is booked from the counter.using the composite ID
	 * 
	 */
	@PutMapping("/ticket/flight/{flightNumber}/row/{rowNumber}/seat/{seat}")
	public ResponseEntity<Ticket> cancelReservationById(@PathVariable int flightNumber, @PathVariable int rowNumber,
			@PathVariable char seat) throws NotFoundException {
		Ticket ticket = ticketservice.findTicketById(flightNumber, rowNumber, seat);
		if (ticket == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ticketservice.cancelReservationById(flightNumber, rowNumber, seat);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Only the holder of the Ticket should be able to cancel the reservation, or
	 * the agent if the ticket is booked from the counter. using the Booking ID 
	 * 
	 */
	@PutMapping("/ticket/booking-id/{bookingId}")
	public ResponseEntity<Ticket> cancelReservationByBookingId(@PathVariable String bookingId) throws NotFoundException {
		Ticket ticket = ticketservice.findTicketByBookingId(bookingId);
		if (ticket == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ticketservice.cancelReservationByBookingId(bookingId);;
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
