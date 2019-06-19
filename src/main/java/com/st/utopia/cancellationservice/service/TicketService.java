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
	 * Cancel Ticket function.(by flight number, row and seat)
	 */
	public void cancelReservationById(int flight, int row, char seat) {
		final TicketIdentity id = new TicketIdentity(flight, row, seat);
		final Optional<Ticket> record = ticketDao.findById(id);
		if (record.isPresent()) {
			final Ticket inner = record.get();
			if (inner.getBookingId() == null) {
				LOGGER.log(Level.SEVERE, "Ticket with the Id, flight :" + flight + ",row :" + row + ",seat :" + seat
						+ ", is not reserved in the first place");
			} else {
				inner.setBookingId(null);
				inner.setReservationTimeout(null);
				inner.setReserver(null);
				ticketDao.save(inner);
			}
		} else {
			LOGGER.log(Level.SEVERE,
					"Ticket with the Id, flight :" + flight + ",row :" + row + ",seat :" + seat + ", does not exist");
		}
	}

	/**
	 * Cancel Ticket function.(by Booking ID)
	 */
	public void cancelReservationByBookingId(String bookingId) {
		final Ticket record = ticketDao.findByBookingId(bookingId);
		if (record != null) {
			record.setBookingId(null);
			record.setReservationTimeout(null);
			record.setReserver(null);
			ticketDao.save(record);
		} else {
			LOGGER.log(Level.SEVERE, "No reserved Ticket with the Booking ID :" + bookingId + " found.");
		}
	}

	/**
	 * find Ticket function By ID.
	 */
	public Ticket findTicketById(int flight, int row, char seat) {
		Ticket foundTicket = ticketDao.getTicket(flight, row, seat);
		if (foundTicket == null) {
			LOGGER.log(Level.SEVERE,
					"Ticket with the Id, flight :" + flight + ",row :" + row + ",seat :" + seat + ", does not exist");
			return null;
		} else {
			return foundTicket;
		}

	}

	/**
	 * find Ticket function By Booking ID.
	 */
	public Ticket findTicketByBookingId(String bookingId) {
		Ticket foundTicket = ticketDao.findByBookingId(bookingId);
		if (foundTicket == null) {
			LOGGER.log(Level.SEVERE, "No reserved Ticket with the Booking ID :" + bookingId + " found.");
			return null;
		} else {
			return foundTicket;
		}

	}
}
