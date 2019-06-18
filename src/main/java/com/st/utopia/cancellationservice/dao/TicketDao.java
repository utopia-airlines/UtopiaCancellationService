package com.st.utopia.cancellationservice.dao;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.utopia.cancellationservice.model.Ticket;
import com.st.utopia.cancellationservice.model.TicketIdentity;


@Repository
public interface TicketDao extends JpaRepository<Ticket, TicketIdentity>{
	/**
	 * Logger for handling errors in the DAO layer.
	 */
	static final Logger LOGGER = Logger.getLogger(TicketDao.class.getName());
	/**
	 * Get the ticket with a specific flight and row and seat in the plane .
	 *
	 */
	default Ticket getTicket(Integer flight, Integer row, Character seat) {
		final Optional<Ticket> record = findById(new TicketIdentity(flight, row, seat));
		return record.orElse(null);
	}
	/**
	 * Cancel a ticket of a specific flight and row and seat, change the value of the reserver,ReservationTimeout
	 *  and BookingId to null and.
	 *
	 */
	default void cancel(Integer flight, Integer row, Character seat) {
		final TicketIdentity id = new TicketIdentity(flight, row, seat);
		final Optional<Ticket> record = findById(id);
		if (record.isPresent()) {
			final Ticket inner = record.get();
			if (inner.getBookingid() == null) {
				LOGGER.log(Level.SEVERE, "Ticket with the Id, flight :"+flight+",row :"+row+",seat :"+seat+", is not reserved in the first place");
			}
			else {
				inner.setBookingid(null);
				inner.setReservationtimeout(null);
				inner.setReserver(null);
				save(inner);
			}
		}
		else {
			LOGGER.log(Level.SEVERE, "Ticket with the Id, flight :"+flight+",row :"+row+",seat :"+seat+", does not exist");
		}
	}

}