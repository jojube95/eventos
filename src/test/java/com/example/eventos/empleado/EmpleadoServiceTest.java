package com.example.eventos.empleado;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpleadoServiceTest {

    @Mock
    EmpleadoRepository empleadoRepository;

    @InjectMocks
    EmpleadoService empleadoService;

    Empleado empleado;

    @BeforeEach
    public void initEach(){
        empleado = new Empleado("id", "tipo", "nombre", "telefono", true);
    }

    @Test
    void getEmpleadosTest() {
        empleadoService.getEmpleados();
        verify(empleadoRepository, times(1)).findAll();
    }

    @Test
    void getByIdTest() {
        empleadoService.getById(empleado.getId());
        verify(empleadoRepository, times(1)).findEmpleadoById(empleado.getId());
    }

    @Test
    void getByIdTtipo() {
        empleadoService.getByTipo(empleado.getTipo());
        verify(empleadoRepository, times(1)).findEmpleadoByTipo(empleado.getTipo());
    }

    @Test
    void getByIdTtipoAndFijoTest() {
        empleadoService.getByTipoAndFijo("Camarero/a", true);
        verify(empleadoRepository, times(1)).findEmpleadoByTipoAndFijo("Camarero/a", true);
    }

    @Test
    void saveTest() {
        empleadoService.save(empleado);
        verify(empleadoRepository, times(1)).save(empleado);
    }

    @Test
    void deleteTest() {
        empleadoService.delete(empleado);
        verify(empleadoRepository, times(1)).delete(empleado);
    }
}
