package Testes;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Hamburgueria.Produtos;

public class ProdutosTest {

    private Produtos produto;

    @Before
    public void setUp() {
        produto = new Produtos("Hamburguer", "Delicioso hamburguer", 10.99);
    }

    @Test
    public void testGetNome() {
        // Test getNome
        assertEquals("Hamburguer", produto.getNome());
    }

    @Test
    public void testSetNome() {
        // Test setNome
        produto.setNome("Cheeseburguer");
        assertEquals("Cheeseburguer", produto.getNome());
    }

    @Test
    public void testGetPreco() {
        // Test getPreco
        assertEquals(10.99, produto.getPreco(), 0.01);
    }

    @Test
    public void testSetPreco() {
        // Test setPreco
        produto.setPreco(12.99);
        assertEquals(12.99, produto.getPreco(), 0.01);
    }

    @Test
    public void testGetDescricao() {
        // Test getDescricao
        assertEquals("Delicioso hamburguer", produto.getDescricao());
    }

    @Test
    public void testSetDescricao() {
        // Test setDescricao
        produto.setDescricao("Um saboroso cheeseburguer");
        assertEquals("Um saboroso cheeseburguer", produto.getDescricao());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetPrecoNegativo() {
        // Test setPreco com valor negativo (limite inferior)
        produto.setPreco(-1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPrecoZero() {
        // Test setPreco com valor zero (limite inferior)
        produto.setPreco(0.0);
    }

    @Test
    public void testSetPrecoMaximo() {
        // Test setPreco com valor máximo (limite superior)
        produto.setPreco(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, produto.getPreco(), 0.01);
    }
}
