package com.st.utopia.cancellationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.st.utopia.cancellationservice.model.Ticket;
import com.st.utopia.cancellationservice.service.TicketService;

import javassist.NotFoundException;



@RestController
@RequestMapping("/Cancel")
public class CancellationServiceController {
	
	@Autowired
	TicketService ticketservice;
	
	
	@GetMapping("/FindTicket/{flightNumber}/{rowNumber}/{seat}")
	public ResponseEntity<Ticket> getPublisher(@PathVariable int flightNumber, @PathVariable int rowNumber,@PathVariable char seat) throws NotFoundException {
		Ticket ticket = ticketservice.findTicket(flightNumber, rowNumber, seat);
		if(ticket == null) {
			throw new NotFoundException("ticket with flightNumber=" + flightNumber + " not found");
		}
		
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}
	
	
	@PutMapping("/ticket/flight/{flightNumber}/rowNumber/{rowNumber}/seat/{seat}")
	public ResponseEntity<Ticket> CancelResirvation(@PathVariable int flightNumber, @PathVariable int rowNumber,@PathVariable char seat) 
	throws NotFoundException {
		
		Ticket ticket = ticketservice.findTicket(flightNumber, rowNumber, seat);
		if(ticket == null) {
			throw new NotFoundException("cancellation failed. ticket with flight number=" + flightNumber + " not found");
		}
		
		ticketservice.CancelReservation(flightNumber, rowNumber, seat);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
