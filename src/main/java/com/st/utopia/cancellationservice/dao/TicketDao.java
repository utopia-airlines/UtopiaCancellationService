package com.st.utopia.cancellationservice.dao;

import java.util.logging.Logger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.utopia.cancellationservice.model.Ticket;
import com.st.utopia.cancellationservice.model.TicketIdentity;

@Repository
public interface TicketDao extends JpaRepository<Ticket, TicketIdentity> {
	/**
	 * Logger for handling errors in the DAO layer.
	 */
	static final Logger LOGGER = Logger.getLogger(TicketDao.class.getName());

	/**
	 * Get the ticket with a specific flight and row and seat in the plane .
	 *
	 */
	default Ticket getTicket(Integer flight, Integer row, Character seat) {
		return findById(new TicketIdentity(flight, row, seat)).orElse(null);
	}

}