package com.st.utopia.cancellationservice.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.st.utopia.cancellationservice.model.Ticket;
import com.st.utopia.cancellationservice.model.TicketIdentity;

@Repository
public interface TicketDao extends JpaRepository<Ticket, TicketIdentity>{
	/**
	 * Get the ticket with a specific flight and row and seat in the plane .
	 *
	 */
	default Ticket getTicket(Integer flight, Integer row, Character seat) {
		final Optional<Ticket> record = findById(new TicketIdentity(flight, row, seat));
		if (record.isPresent()) {
			return record.get();
		} else {
			return null;
		}
	}
	
// @Modifying(clearAutomatically = true)
// @Query("UPDATE Ticket SET reserver = null, reservation_timeout = null, booking_id = null WHERE flight = ?1 and row = ?2 and seat = ?3")
// void UpdateTicket(int flight, int row, char seat);

	default void Cancel(Integer flight, Integer row, Character seat) {
		final TicketIdentity id = new TicketIdentity(flight, row, seat);
		final Optional<Ticket> record = findById(id);
		if (record.isPresent()) {
			final Ticket inner = record.get();
			if (inner.getBooking_id() == null) {
                System.out.println("Ticket does not existe!");
			}
			else {
				inner.setBooking_id(null);
				inner.setReservation_timeout(null);
				inner.setReserver(null);
				save(inner);
			}
		} 
	}

}