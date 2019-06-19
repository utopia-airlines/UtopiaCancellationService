package com.st.utopia.cancellationservice.dao;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.st.utopia.cancellationservice.model.Ticket;
import com.st.utopia.cancellationservice.model.TicketIdentity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketDaoTest {

	@Autowired
	private TicketDao ticketdao;

	@Test
	public void createTicket() {
		LocalDateTime dout = LocalDateTime.parse("28-01-2019 12:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
		Ticket t = new Ticket(new TicketIdentity(1, 11, 'B'), 11, 1, 1500, dout, "jhytrfgr");
		ticketdao.save(t);
		Ticket createdTicket = ticketdao.getTicket(1, 11, 'B');
		assertEquals(t.getBookingId(), createdTicket.getBookingId());
	}
	
	@Test
	public void createTicketB() {
		LocalDateTime dout = LocalDateTime.parse("28-01-2019 12:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
		Ticket t = new Ticket(new TicketIdentity(1, 12, 'B'), 11, 1, 4500, dout, "abc15");
		ticketdao.save(t);
		Ticket createdTicket = ticketdao.getTicket(1, 12, 'B');
		assertEquals(t.getBookingId(), createdTicket.getBookingId());
	}

	@Test
	public void getTicket() {
		Ticket t = ticketdao.getTicket(1, 1, 'A');
		Integer price = 123;
		assertEquals(price,t.getPrice());
	}
	
	@Test
	public void findTicketByBookingId() {
		Ticket t = ticketdao.findByBookingId("abc157");
		Integer price = 4500;
		assertEquals(price,t.getPrice());
	}
}
