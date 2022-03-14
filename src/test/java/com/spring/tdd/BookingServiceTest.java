package com.spring.tdd;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.tdd.model.BookingModel;
import com.spring.tdd.repository.BookingRepository;
import com.spring.tdd.service.BookingService;


@RunWith(SpringRunner.class) 
public class BookingServiceTest {
	
	@TestConfiguration //isso faz com que tudo seja apenas um teste
	static class BookingServiceTestConfiguration{	//para funcionar o BookingService 
		
		@Bean 
		public BookingService bookingService() {
			return new BookingService();
		}
	}
	
	@Autowired
	BookingService bookingService;
	
	@MockBean
	BookingRepository bookingRepository;
	
	@Test
	public void bookingTestServiceDaysCalculator() {
		String name = "THIAGO"; //simular a busca no banco pelo nome
		int days = bookingService.daysCalculatorWithDatabase(name); //encontrar os dias da reserva
		
		Assertions.assertEquals(days, 10);	//10 dias esperados
		
	}
	
	//PARA REALIZAR UM TESTE NO CONTROLLER SEM ME PREOCUPAR COM O BANCO	
	@Before
	public void setup() {
		LocalDate checkIn = LocalDate.parse("2020-11-10");
		LocalDate checkOut = LocalDate.parse("2020-11-20");
		BookingModel bookingModel = new BookingModel("1", "THIAGO", checkIn, checkOut, 2);
		
		Mockito.when(bookingRepository.findByReserveName(bookingModel.getReserveName())) //QUANDO A GENTE PRECISAR DE UMA INSTÂNCIA DE...
		.thenReturn(java.util.Optional.of(bookingModel)); //retorno a simulacao criada acima
	}
}
