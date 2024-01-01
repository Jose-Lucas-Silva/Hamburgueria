package Hamburgueria;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

    	// Criando um menu
    	Menu menu = new Menu();

    	// Criando produtos para a hamburgueria
    	Produtos produto1 = new Produtos("Hamburguer Simples", "Pao, carne, queijo e alface", 12.99);
    	Produtos produto2 = new Produtos("Hamburguer Duplo", "Dois hamburgueres, queijo, bacon e tomate", 18.99);
    	Produtos produto3 = new Produtos("Batata Frita", "Porcao de batata frita", 7.99);
    	Produtos produto4 = new Produtos("Refrigerante", "Coca-Cola 500ml", 3.99);

    	// Adicionando produtos ao menu
    	menu.adicionarProduto(produto1);
    	menu.adicionarProduto(produto2);
    	menu.adicionarProduto(produto3);
    	menu.adicionarProduto(produto4);

    	// Criando algumas mesas
    	Mesa mesa1 = new Mesa(1, 4);
    	Mesa mesa2 = new Mesa(2, 6);
    	Mesa mesa3 = new Mesa(3, 2);

    	// Adicionando as mesas ao menu
    	menu.adicionarMesa(mesa1);
    	menu.adicionarMesa(mesa2);
    	menu.adicionarMesa(mesa3);

    	// Salvando o menu em um arquivo
    	menu.salvarMenu("Menu.txt");
    	System.out.println("Menu:");
    	menu.carregarMenu("Menu.txt");
    	
    	System.out.println();

    	// Removendo alguns itens do menu
    	menu.removerItemDoArquivo("Menu.txt", "Produto", "Hamburguer Simples");
    	menu.removerItemDoArquivo("Menu.txt", "Produto", "Batata Frita");
    	System.out.println("Menu apos remover alguns produtos:");
    	menu.carregarMenu("Menu.txt");

    	// Criando um pedido para a mesa 1
    	Pedidos pedidoMesa1 = new Pedidos(mesa1);
    	pedidoMesa1.adicionarProduto(produto1);
    	pedidoMesa1.adicionarProduto(produto3);

    	// Imprimindo e salvando o pedido em um arquivo txt
    	pedidoMesa1.imprimir("PedidoMesa1.txt");
        
    	//Para nao rodar a Interface Grafica e necessario colocar o trecho abaixo como comentario
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuGUI window = new MenuGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
   }

