package com.example.eventos.empleado;

import com.example.eventos.tipoEmpleado.TipoEmpleado;
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
        empleado = new Empleado("id", new TipoEmpleado("camarero"), "nombre", "telefono", true);
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
    void getByIdTtipoAndFijoTest() {
        empleadoService.getByTipoAndFijo(new TipoEmpleado("camarero"), true);
        verify(empleadoRepository, times(1)).findEmpleadoByTipoAndFijo(new TipoEmpleado("camarero"), true);
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
