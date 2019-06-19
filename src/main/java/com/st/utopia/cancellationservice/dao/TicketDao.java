package com.st.utopia.cancellationservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.utopia.cancellationservice.model.Ticket;
import com.st.utopia.cancellationservice.model.TicketIdentity;

@Repository
public interface TicketDao extends JpaRepository<Ticket, TicketIdentity> {

	/**
	 * Get the ticket with a specific flight and row and seat in the plane .
	 *
	 */
	default Ticket getTicket(Integer flight, Integer row, Character seat) {
		return findById(new TicketIdentity(flight, row, seat)).orElse(null);
	}

}