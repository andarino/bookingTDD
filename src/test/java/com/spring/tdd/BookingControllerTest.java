package com.spring.tdd;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.tdd.model.BookingModel;
import com.spring.tdd.repository.BookingRepository;
import com.spring.tdd.service.BookingService;

@RunWith(SpringRunner.class)
@SpringBootTest 
@AutoConfigureMockMvc	//para testar o controller 
public class BookingControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	BookingRepository bookingRepository;
	
	@Test
	public void bookingTestGetAll() throws Exception{
		mockMvc.perform(get("/bookings"))
			.andExpect(status().isOk());
	}
	
	@Test 
	public void bookingTestSave() throws Exception {
		LocalDate checkIn = LocalDate.parse("2020-11-10");
		LocalDate checkOut = LocalDate.parse("2020-11-20");		
		
		BookingModel bookingModel = new BookingModel("2", "JOSE", checkIn, checkOut, 4);
		
		mockMvc.perform(post("/bookings")
			.contentType("application/json")	//TIPO A SER RECEBIDO
			.content(objectMapper.writeValueAsString(bookingModel))) //SIMULAR UM JSON PRA DENTRO DA INSTALL 
			.andExpect(status().isOk());								//ESPERAMAOS O OK 
	}
	
	
}
