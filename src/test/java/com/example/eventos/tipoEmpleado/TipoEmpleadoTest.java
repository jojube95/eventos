package com.example.eventos.tipoEmpleado;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TipoEmpleadoTest {
    TipoEmpleado tipoEmpleado;

    @BeforeEach
    public void initEach(){
        tipoEmpleado = new TipoEmpleado("Camarero");
    }

    @Test
    void constructorTest(){
        assertEquals("Camarero", tipoEmpleado.getValue());
    }

    @Test
    void toStringTest(){
        assertEquals("TipoEmpleado{value='Camarero'}", tipoEmpleado.toString());
    }
}
