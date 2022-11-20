package com.example.eventos.parametros;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ParametrosTest {
    Parametros parametros;

    @BeforeEach
    public void initEach(){
        parametros = new Parametros(1.1F, 1.2F, 1.3F, 1.4F);
    }

    @Test
    void constructorTest(){
        assertEquals(1.1F, parametros.getPrecioNinyosBodaComunion());
        assertEquals(1.2F, parametros.getPrecioNinyosOtros());
        assertEquals(1.3F, parametros.getRatioBeneficios());
        assertEquals(1.4F, parametros.getRatioCamarerosEvento());
    }

    @Test
    void equalsTestTrue(){
        Parametros parametrosTest = new Parametros(1.1F, 1.2F, 1.3F, 1.4F);
        assertEquals(parametros, parametrosTest);
    }


    @Test
    void equalsTestFalse(){
        Parametros parametrosTest = new Parametros(1.2F, 1.2F, 1.3F, 1.4F);
        assertNotEquals(parametros, parametrosTest);
    }
}
