package com.st.utopia.cancellationservice.service;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.st.utopia.cancellationservice.model.Ticket;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest {

	@Autowired
	private TicketService ticketservice;

	@Test
	public void cancelReservationTest() {
		ticketservice.cancelReservation(1, 5, 'B');
		Ticket t = ticketservice.findTicket(1, 5, 'B');
		assertNull(t.getBookingid());
	}

	@Test
	public void findTicketTest() {
		Ticket t = ticketservice.findTicket(1, 1, 'A');
		Integer price = 123;
		assertNull(t.getBookingid());
		assertTrue(t.getPrice().equals(price));
	}
}
