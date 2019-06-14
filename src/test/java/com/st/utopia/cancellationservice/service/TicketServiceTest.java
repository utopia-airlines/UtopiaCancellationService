package com.st.utopia.cancellationservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.st.utopia.cancellationservice.dao.TicketDao;
import com.st.utopia.cancellationservice.model.Ticket;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest {

	@Autowired
	TicketDao ticketdao;
	@Autowired
	TicketService ticketservice;
	
	
    public TicketServiceTest() {
    }
	
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

  
    @Test
    public void CancelReservationTest() {
    	
    	ticketservice.CancelReservation(1, 5, 'B');
    	Ticket t = ticketservice.findTicket(1, 5, 'B');
    	assertEquals(t.getBooking_id(), null);
    	
    }
    
    @Test
    public void findTicketTest() {
    	
    	Ticket t = ticketservice.findTicket(1, 1, 'A');
    	assertEquals(t.getBooking_id(), null);
    	assertTrue(t.getPrice() == 123);
    }
}
