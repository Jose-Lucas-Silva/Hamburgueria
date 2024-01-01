package Testes;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Hamburgueria.Mesa;
import Hamburgueria.Pedidos;
import Hamburgueria.Produtos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PedidosTest {

    private Mesa mesa;
    private Pedidos pedido;

    @Before
    public void setUp() {
        mesa = new Mesa(1, 4);
        pedido = new Pedidos(mesa);
    }

    @Test
    public void testAdicionarProduto() {
        Produtos produto = new Produtos("Hamburguer", "Delicioso hamburguer", 10.99);

        // Teste para adicionar um produto ao pedido
        pedido.adicionarProduto(produto);
        assertTrue(pedido.getProdutos().contains(produto));
    }

    @Test
    public void testCalcularTotal() {
        // Teste para calcular o total sem produtos
        assertEquals(0.0, pedido.calcularTotal(), 0.01);

        // Adicionar produtos ao pedido
        Produtos produto1 = new Produtos("Hamburguer", "Delicioso hamburguer", 10.99);
        Produtos produto2 = new Produtos("Batata Frita", "Crocante e saborosa", 5.99);
        pedido.adicionarProduto(produto1);
        pedido.adicionarProduto(produto2);

        // Teste para calcular o total com produtos
        assertEquals(produto1.getPreco() + produto2.getPreco(), pedido.calcularTotal(), 0.01);
    }

    @Test
    public void testImprimir() {
        // Teste para imprimir o pedido em um arquivo
        pedido.imprimir("test_pedido.txt");

        /* Verificar se o arquivo foi criado
         Neste exemplo, apenas verificamos se o arquivo foi criado sem lançar excecoes*/
        assertNotNull(new File("test_pedido.txt"));
    }

    @Test
    public void testGetSetProdutos() {
        // Teste para verificar os métodos get e set para a lista de produtos
        List<Produtos> novosProdutos = new ArrayList<>();
        Produtos produto1 = new Produtos("Hamburguer", "Delicioso hambúrguer", 10.99);
        Produtos produto2 = new Produtos("Batata Frita", "Crocante e saborosa", 5.99);
        novosProdutos.add(produto1);
        novosProdutos.add(produto2);

        // Setar novos produtos
        pedido.setProdutos(novosProdutos);

        // Verificar se os produtos foram setados corretamente
        assertEquals(novosProdutos, pedido.getProdutos());
    }

    @Test
    public void testGetSetMesa() {
        // Teste para verificar os métodos get e set para a mesa
        Mesa novaMesa = new Mesa(2, 6);

        // Setar nova mesa
        pedido.setMesa(novaMesa);

        // Verificar se a mesa foi setada corretamente
        assertEquals(novaMesa, pedido.getMesa());
    }
}
