package Testes;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.junit.Test;

import Hamburgueria.Menu;
import Hamburgueria.Mesa;
import Hamburgueria.Produtos;

public class MenuTest {

    @Test
    public void testAdicionarProduto() {
        Menu menu = new Menu();

        assertTrue(menu.getProdutos().isEmpty()); //Verifica se a lista está vazia inicialmente.

        //Adicionar um produto válido
        Produtos produtoValido = new Produtos("Hamburguer", "Delicioso hamburguer", 10.0);
        menu.adicionarProduto(produtoValido);
        assertTrue(menu.getProdutos().contains(produtoValido));

        //Tentando adicionar um produto com nome nulo
        try {
            Produtos produtoNomeNulo = new Produtos(null, "Descricao", 5.0);
            menu.adicionarProduto(produtoNomeNulo);
            fail("Deveria ter lançado uma exceção de nome nulo");
        } catch (IllegalArgumentException e) {
            assertEquals("Erro: Não é permitido adicionar um produto com nome nulo.", e.getMessage());
        }
        //Tentando adicionar um produto com descricao vazia
        try {
            Produtos produtoDescricaoNula = new Produtos("Produto", null, 5.0);
            menu.adicionarProduto(produtoDescricaoNula);
            fail("Deveria ter lançado uma exceção de descrição nula");
        } catch (IllegalArgumentException e) {
            assertEquals("Erro: Não é permitido adicionar um produto com descrição nula.", e.getMessage());
        }

        //Tentando adicionar um produto com preço negativo
        try {
            Produtos produtoPrecoNegativo = new Produtos("Produto2", "Descrição", -5.0);
            menu.adicionarProduto(produtoPrecoNegativo);
            fail("Deveria ter lançado uma exceção para preço negativo");
        } catch (IllegalArgumentException e) {
            assertEquals("Erro: Não é permitido adicionar um produto com preço negativo.", e.getMessage());
        }


        //Adicionando um produto com o menor nome possivel
        Produtos produtoNomeMinimo = new Produtos("A", "Descricao", 5.0);
        menu.adicionarProduto(produtoNomeMinimo);
        assertTrue(menu.getProdutos().contains(produtoNomeMinimo));

        //Adicionando um produto com o maior nome possivel
        Produtos produtoNomeMaximo = new Produtos("NomeMuitoGrandeQueExcedeOlimite", "Descricao", 5.0);
        menu.adicionarProduto(produtoNomeMaximo);
        assertTrue(menu.getProdutos().contains(produtoNomeMaximo));

        //Adicionando um produto com o menor preço possivel
        Produtos produtoPrecoMinimo = new Produtos("Produto", "Descricao", 0.01);
        menu.adicionarProduto(produtoPrecoMinimo);
        assertTrue(menu.getProdutos().contains(produtoPrecoMinimo));

        //Adicionando um produto com o maior preco possivel
        Produtos produtoPrecoMaximo = new Produtos("Produto", "Descricao", Double.MAX_VALUE);
        menu.adicionarProduto(produtoPrecoMaximo);
        assertTrue(menu.getProdutos().contains(produtoPrecoMaximo));
    }

    @Test
    public void testRemoverProduto() {
        Menu menu = new Menu();
  
        assertTrue(menu.getProdutos().isEmpty()); // Verifica se a lista esta vazia inicialmente.

        //Adicionando um produto para testar a remocao
        Produtos produto = new Produtos("Produto", "Descricao", 5.0);
        menu.adicionarProduto(produto);
        assertTrue(menu.getProdutos().contains(produto));

        //Removendo um produto existente
        menu.removerProduto(produto);
        assertFalse(menu.getProdutos().contains(produto));

        //Tentando remover um produto que nao esta na lista
        Produtos produtoNaoExistente = new Produtos("NaoExistente", "Descricao", 10.0);
        menu.removerProduto(produtoNaoExistente);
        assertFalse(menu.getProdutos().contains(produtoNaoExistente));
    }
    @Test
    public void testGetProdutos() {
        Menu menu = new Menu();
        
        //Testando com lista vazia
        assertTrue(menu.getProdutos().isEmpty());
        
        //Adicionando produtos a lista
        Produtos produto1 = new Produtos("Produto1", "Descricao1", 10.0);
        Produtos produto2 = new Produtos("Produto2", "Descricao2", 15.0);
        menu.adicionarProduto(produto1);
        menu.adicionarProduto(produto2);

        //Testando com lista contendo produtos
        List<Produtos> produtosRetornados = menu.getProdutos();
        assertEquals(2, produtosRetornados.size());
        assertTrue(produtosRetornados.contains(produto1));
        assertTrue(produtosRetornados.contains(produto2));

        //Adicionando mais produtos a lista
        Produtos produto3 = new Produtos("Produto3", "Descricao3", 20.0);
        Produtos produto4 = new Produtos("Produto4", "Descricao4", 25.0);
        menu.adicionarProduto(produto3);
        menu.adicionarProduto(produto4);

        //Testando com lista contendo mais produtos
        produtosRetornados = menu.getProdutos();
        assertEquals(4, produtosRetornados.size());
        assertTrue(produtosRetornados.contains(produto1));
        assertTrue(produtosRetornados.contains(produto2));
        assertTrue(produtosRetornados.contains(produto3));
        assertTrue(produtosRetornados.contains(produto4));
    }
    @Test
    public void testGetMesas() {
        Menu menu = new Menu();
        
        // Testando com lista de mesas vazia
        assertTrue(menu.getMesas().isEmpty());
        
        // Adicionando mesas a lista
        Mesa mesa1 = new Mesa(1,4);
        Mesa mesa2 = new Mesa(2,3);
        menu.adicionarMesa(mesa1);
        menu.adicionarMesa(mesa2);

        // Testando com lista contendo mesas
        List<Mesa> mesasRetornadas = menu.getMesas();
        assertEquals(2, mesasRetornadas.size());
        assertTrue(mesasRetornadas.contains(mesa1));
        assertTrue(mesasRetornadas.contains(mesa2));

        // Adicionando mais mesas a lista
        Mesa mesa3 = new Mesa(3,2);
        Mesa mesa4 = new Mesa(4,5);
        menu.adicionarMesa(mesa3);
        menu.adicionarMesa(mesa4);

        // Testando com lista contendo mais mesas
        mesasRetornadas = menu.getMesas();
        assertEquals(4, mesasRetornadas.size());
        assertTrue(mesasRetornadas.contains(mesa1));
        assertTrue(mesasRetornadas.contains(mesa2));
        assertTrue(mesasRetornadas.contains(mesa3));
        assertTrue(mesasRetornadas.contains(mesa4));
    }
    @Test
    public void testAdicionarMesa() {
        Menu menu = new Menu();

        // Testando com lista vazia
        assertTrue(menu.getMesas().isEmpty());

        // Adicionando uma mesa à lista
        Mesa mesa1 = new Mesa(1, 4);
        menu.adicionarMesa(mesa1);

        // Testando com lista contendo uma mesa
        List<Mesa> mesasRetornadas1 = menu.getMesas();
        assertEquals(1, mesasRetornadas1.size());
        assertTrue(mesasRetornadas1.contains(mesa1));

        // Adicionando mais mesas a lista
        Mesa mesa2 = new Mesa(2, 4);
        Mesa mesa3 = new Mesa(3, 4);
        menu.adicionarMesa(mesa2);
        menu.adicionarMesa(mesa3);

        // Testando com lista com mais de uma mesa
        List<Mesa> mesasRetornadas2 = menu.getMesas();
        assertEquals(3, mesasRetornadas2.size());
        assertTrue(mesasRetornadas2.contains(mesa1));
        assertTrue(mesasRetornadas2.contains(mesa2));
        assertTrue(mesasRetornadas2.contains(mesa3));

        // Adicionando uma mesa com numero ja existente
        Mesa mesaDuplicada = new Mesa(1, 4);
        menu.adicionarMesa(mesaDuplicada);
    }
    @Test
    public void testSalvarMenu() {
        Menu menu = new Menu();
        Produtos produto1 = new Produtos("Hamburguer", "Delicioso hamburguer", 10.0);
        Produtos produto2 = new Produtos("Batata Frita", "Porção de batata frita", 5.0);
        menu.adicionarProduto(produto1);
        menu.adicionarProduto(produto2);
        String nomeArquivo = "test_menu.txt";

        // Caso 1: Salvar um menu vazio
        menu.salvarMenu(nomeArquivo);
        assertTrue(arquivoExiste(nomeArquivo));
        String conteudoEsperadoCaso1 = "";
        for (Produtos produto : menu.getProdutos()) {
            conteudoEsperadoCaso1 += "Produto:" + produto.getNome() + ";" + produto.getDescricao() + ";" + produto.getPreco() + "\n";
        }
        assertEquals(conteudoEsperadoCaso1, lerConteudoArquivo(nomeArquivo));

        // Caso 2: Salvar um menu com produtos
        menu.salvarMenu(nomeArquivo);
        String conteudoEsperado = "Produto:Hamburguer;Delicioso hamburguer;10.0\nProduto:Batata Frita;Porção de batata frita;5.0\n";
        assertEquals(conteudoEsperado, lerConteudoArquivo(nomeArquivo));

        // Limpar o arquivo de teste
        deletarArquivo(nomeArquivo);
    }

    @Test
    public void testCarregarMenu() {
        Menu menu = new Menu();
        String nomeArquivo = "test_menu_carregar.txt";
        String conteudoArquivo = "Produto:Hamburguer;Delicioso hamburguer;10.0\nProduto:Batata Frita;Porção de batata frita;5.0";
        criarArquivo(nomeArquivo, conteudoArquivo);

        menu.carregarMenu(nomeArquivo);
        assertEquals(2, menu.getProdutos().size());

        deletarArquivo(nomeArquivo);
    }

    @Test
    public void testRemoverItemDoArquivo() {
        Menu menu = new Menu();
        String nomeArquivo = "test_menu_remover.txt";
        String conteudoArquivo = "Produto:Hamburguer;Delicioso hamburguer;10.0\nProduto:Batata Frita;Porcao de batata frita;5.0\n";
        criarArquivo(nomeArquivo, conteudoArquivo);

        // Removendo um item do arquivo
        menu.removerItemDoArquivo(nomeArquivo, "Produto", "Hamburguer");
        String conteudoEsperado = "Produto:Batata Frita;Porcao de batata frita;5.0\n";

        // Ignorar espaços em branco e caracteres especiais
        String conteudoLido = lerConteudoArquivo(nomeArquivo).replaceAll("\\s+", "").toLowerCase();
        String conteudoEsperadoSemEspacos = conteudoEsperado.replaceAll("\\s+", "").toLowerCase();

        assertEquals(conteudoEsperadoSemEspacos, conteudoLido);

        // Tentando remover um item inexistente
        menu.removerItemDoArquivo(nomeArquivo, "Produto", "Pizza");

        // Ignorar espaços em branco e caracteres especiais novamente
        conteudoLido = lerConteudoArquivo(nomeArquivo).replaceAll("\\s+", "").toLowerCase();
        conteudoEsperadoSemEspacos = conteudoEsperado.replaceAll("\\s+", "").toLowerCase();

        assertEquals(conteudoEsperadoSemEspacos, conteudoLido);

        // Limpando o arquivo de teste
        deletarArquivo(nomeArquivo);
    }
    
    private boolean arquivoExiste(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        return arquivo.exists();
    }

    private String lerConteudoArquivo(String nomeArquivo) {
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conteudo.toString();
    }

    private void criarArquivo(String nomeArquivo, String conteudo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            writer.println(conteudo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deletarArquivo(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        arquivo.delete();
    }	
}