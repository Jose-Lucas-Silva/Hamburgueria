package Testes;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Hamburgueria.Mesa;

public class MesaTest {

    private Mesa mesa;

    @Before
    public void setUp() {
        mesa = new Mesa(1, 4);
    }

    @Test
    public void testNumeroMesaValido() {
        assertEquals(5, new Mesa(5, 4).getNumeroMesa());
    }

    @Test
    public void testCapacidadeValida() {
        assertEquals(4, new Mesa(1, 4).getCapacidade());
    }

    @Test
    public void testDisponibilidadeValida() {
        assertTrue(new Mesa(1, 4).isDisponibilidade());
    }

    @Test
    public void testClienteAlocadoValido() {
        Mesa mesa = new Mesa(1, 4);
        mesa.setClienteAlocado("João");
        assertEquals("João", mesa.getClienteAlocado());
    }

    @Test
    public void testNumeroMesaLimiteInferior() {
        assertEquals(1, new Mesa(1, 4).getNumeroMesa());
    }

    @Test
    public void testNumeroMesaLimiteSuperior() {
        assertEquals(100, new Mesa(100, 4).getNumeroMesa());
    }

    @Test
    public void testCapacidadeLimiteInferior() {
        assertEquals(1, new Mesa(1, 1).getCapacidade());
    }

    @Test
    public void testCapacidadeLimiteSuperior() {
        assertEquals(10, new Mesa(1, 10).getCapacidade());
    }

    @Test
    public void testDisponibilidadeLimiteInferior() {
        mesa.setDisponibilidade(false);
        assertFalse(mesa.isDisponibilidade());
    }

    @Test
    public void testDisponibilidadeLimiteSuperior() {
        mesa.setDisponibilidade(true);
        assertTrue(mesa.isDisponibilidade());
    }
}
