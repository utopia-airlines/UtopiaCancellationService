package com.st.utopia.cancellationservice.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.st.utopia.cancellationservice.dao.TicketDao;
import com.st.utopia.cancellationservice.model.Ticket;

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
	public void cancelReservation(int flight, int row, char seat){
		ticketDao.cancel(flight, row, seat);
     }
	/**
	 * find Ticket function.
	 */
	public Ticket findTicket(int flight, int row, char seat){
			Ticket foundTicket = ticketDao.getTicket(flight, row, seat);
			if (foundTicket == null ) {
				LOGGER.log(Level.SEVERE, "Ticket with the Id, flight :"+flight+",row :"+row+",seat :"+seat+", is not reserved in the first place");
				return null;
			 }
			else {
				return foundTicket;
	         }
	
      }
}	
