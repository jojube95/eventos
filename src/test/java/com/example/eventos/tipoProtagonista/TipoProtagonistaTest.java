package com.example.eventos.tipoProtagonista;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TipoProtagonistaTest {
    TipoProtagonista tipoProtagonista;

    @BeforeEach
    public void initEach(){
        tipoProtagonista = new TipoProtagonista("Novio");
    }

    @Test
    void constructorTest(){
        assertEquals("Novio", tipoProtagonista.getValue());
    }

    @Test
    void toStringTest(){
        assertEquals("TipoProtagonista{value='Novio'}", tipoProtagonista.toString());
    }
}
