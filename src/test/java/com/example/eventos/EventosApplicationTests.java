package com.example.eventos;

import com.example.eventos.calendario.CalendarioController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EventosApplicationTests {

	@Autowired
	private CalendarioController calendarioController;

	@Test
	void contextLoads() {
		assertNotNull(calendarioController);
	}

}
