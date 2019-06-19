package com.st.utopia.cancellationservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
	public void cancelReservationByIdTest() {
		ticketservice.cancelReservationById(1, 11, 'B');
		Ticket t = ticketservice.findTicketById(1, 11, 'B');
		assertNull(t.getBookingId());
	}

	@Test
	public void cancelReservationByBookingIdTest() {
		ticketservice.cancelReservationByBookingId("abc15");
		Ticket t = ticketservice.findTicketById(1, 12, 'B');
		assertNull(t.getBookingId());
	}

	@Test
	public void findTicketTest() {
		Ticket t = ticketservice.findTicketById(1, 1, 'A');
		Integer price = 123;
		assertNull(t.getBookingId());
		assertEquals(price, t.getPrice());
	}
}
