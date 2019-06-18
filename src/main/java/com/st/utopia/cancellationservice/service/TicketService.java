package com.st.utopia.cancellationservice.service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.st.utopia.cancellationservice.dao.TicketDao;
import com.st.utopia.cancellationservice.model.Ticket;
import com.st.utopia.cancellationservice.model.TicketIdentity;

@Service
public class TicketService {
	/**
	 * The DAO for the "Ticket" table.
	 */
	@Autowired
	private TicketDao ticketDao;
	/**
	 * Logger for handling errors in the Service layer.
	 */
	private static final Logger LOGGER = Logger.getLogger(TicketService.class.getName());

	/**
	 * Cancel Ticket function.
	 */
	public void cancelReservation(int flight, int row, char seat) {
		final TicketIdentity id = new TicketIdentity(flight, row, seat);
		final Optional<Ticket> record = ticketDao.findById(id);
		if (record.isPresent()) {
			final Ticket inner = record.get();
			if (inner.getBookingid() == null) {
				LOGGER.log(Level.SEVERE, "Ticket with the Id, flight :" + flight + ",row :" + row + ",seat :" + seat
						+ ", is not reserved in the first place");
			} else {
				inner.setBookingid(null);
				inner.setReservationtimeout(null);
				inner.setReserver(null);
				ticketDao.save(inner);
			}
		} else {
			LOGGER.log(Level.SEVERE,
					"Ticket with the Id, flight :" + flight + ",row :" + row + ",seat :" + seat + ", does not exist");
		}
	}

	/**
	 * find Ticket function.
	 */
	public Ticket findTicket(int flight, int row, char seat) {
		Ticket foundTicket = ticketDao.getTicket(flight, row, seat);
		if (foundTicket == null) {
			LOGGER.log(Level.SEVERE, "Ticket with the Id, flight :" + flight + ",row :" + row + ",seat :" + seat
					+ ", is not reserved in the first place");
			return null;
		} else {
			return foundTicket;
		}

	}
}
