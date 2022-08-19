package com.example.eventos.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "google")
public class ConfiguracionGoogleCalendar {
    private String calenarId;

    public String getCalenarId() {
        return calenarId;
    }

    public void setCalenarId(String calenarId) {
        this.calenarId = calenarId;
    }
}
