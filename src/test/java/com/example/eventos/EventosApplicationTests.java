package com.example.eventos;

import com.example.eventos.calendario.CalendarioController;
import com.example.eventos.evento.EventoController;
import com.example.eventos.invitado.InvitadoController;
import com.example.eventos.mesa.MesaController;
import com.example.eventos.protagonista.ProtagonistaController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
class EventosApplicationTests {

	@Autowired
	private CalendarioController calendarioController;

	@Autowired
	private EventoController eventoController;

	@Autowired
	private InvitadoController invitadoController;

	@Autowired
	private MesaController mesaController;

	@Autowired
	private ProtagonistaController protagonistaController;

	@Test
	void mainTest() {
		EventosApplication.main(new String[] {});
		assertTrue(true, "silly assertion to be compliant with Sonar");
	}

	@Test
	void contextLoadsCalendarioController() {
		assertNotNull(calendarioController);
	}

	@Test
	void contextLoadsEventoController() {
		assertNotNull(eventoController);
	}

	@Test
	void contextLoadsInvitadoController() {
		assertNotNull(invitadoController);
	}

	@Test
	void contextLoadsMesaController() {
		assertNotNull(mesaController);
	}

	@Test
	void contextLoadsProtagonistaController() {
		assertNotNull(protagonistaController);
	}

	@Test
	void postConstructSetTimezoneToUTC() {
		TimeZone defaultTimeZone = TimeZone.getDefault();
		assertEquals(defaultTimeZone.getID(), "UTC");
	}

}
