package com.example.eventos;

import com.example.eventos.config.ConfiguracionGoogleCalendar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties(ConfiguracionGoogleCalendar.class)
public class EventosApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EventosApplication.class, args);
	}

	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
}