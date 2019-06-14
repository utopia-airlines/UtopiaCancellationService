package com.st.utopia.cancellationservice.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.st.utopia.cancellationservice.model.Ticket;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketDaoTest {
	
	@Autowired
	TicketDao ticketdao;
	
    public TicketDaoTest() {
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
    public void CreateTicket() {
    	LocalDateTime dout = LocalDateTime.parse("28-01-2019 12:00",DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    	Ticket t = new Ticket(1 , 11, 'B',11, 1, 1500, dout,"jhytrfgr");
    	ticketdao.save(t);
    	
    }

    @Test
    public void GetTicket() {
    	Ticket t = ticketdao.getTicket(1, 1, 'A'); 
    	assertTrue(t.getPrice() == 123);
    }
    
    @Test
    public void CancelTicket() {
    	ticketdao.Cancel(1, 1, 'A');
    	Ticket t = ticketdao.getTicket(1, 1, 'A');
    	assertEquals(t.getBooking_id(), null);
    	
    }

}





