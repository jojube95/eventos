package com.example.eventos.distribucion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DistribucionTest {
    Distribucion distribucion1;
    Distribucion distribucion2;

    @BeforeEach
    public void initEach(){
        distribucion1 = new Distribucion("distribucion1");
        distribucion2 = new Distribucion("distribucion2");
    }

    @Test
    void toStringTest(){
        assertEquals("Distribucion{distribucion='distribucion1'}",  distribucion1.toString());
        assertEquals("Distribucion{distribucion='distribucion2'}", distribucion2.toString());
    }

    @Test
    void equalsTestFalse(){
        assertNotEquals(distribucion1, distribucion2);
    }

    @Test
    void equalsTestTrue(){
        distribucion2.setDistribucion("distribucion1");
        assertEquals(distribucion1, distribucion2);
    }
}
