package Hamburgueria;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI {
    JFrame frame;
    private JTable produtosTable;
    private DefaultTableModel produtosTableModel;
    private JTable mesasTable;
    private DefaultTableModel mesasTableModel;
    private Menu menu;

    public MenuGUI() {
        menu = new Menu();
     // Criar algumas mesas
        Mesa mesa1 = new Mesa(1, 4);
        Mesa mesa2 = new Mesa(2, 6);
        Mesa mesa3 = new Mesa(3, 2);

        //Adicionar as mesas ao menu
        menu.adicionarMesa(mesa1);
        menu.adicionarMesa(mesa2);
        menu.adicionarMesa(mesa3);
        //Criacao de produtos e bebidas
        Produtos produto1 = new Produtos("Hamburguer Simples", "Pao, carne, queijo e alface", 12.99);
    	Produtos produto2 = new Produtos("Hamburguer Duplo", "Dois hamburgueres, queijo, bacon e tomate", 18.99);
    	Produtos produto3 = new Produtos("Batata Frita", "Porcao de batata frita", 7.99);
    	Produtos produto4 = new Produtos("Refrigerante", "Coca-Cola 500ml", 3.99);
        
        //Adicionando produtos ao menu
        menu.adicionarProduto(produto1);
        menu.adicionarProduto(produto2);
        menu.adicionarProduto(produto3);
        menu.adicionarProduto(produto4);
        
        inicializar();
    }

    private void inicializar() {
        frame = new JFrame("Menu");
        frame.setBounds(100, 100, 900, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painelConteudo = new JPanel();
        frame.getContentPane().add(painelConteudo, BorderLayout.CENTER);
        painelConteudo.setLayout(new GridLayout(2, 1));

        // Tabela para exibir produtos
        produtosTableModel = new DefaultTableModel(new Object[]{"Nome", "Descricao", "Preco"}, 0);
        produtosTable = new JTable(produtosTableModel);
        JScrollPane rolagemTabelaProdutos = new JScrollPane(produtosTable);
        painelConteudo.add(rolagemTabelaProdutos);

        // Tabela para exibir mesas
        mesasTableModel = new DefaultTableModel(new Object[]{"Mesa", "Capacidade", "Disponibilidade"}, 0);
        mesasTable = new JTable(mesasTableModel);
        JScrollPane rolagemTabelaMesas = new JScrollPane(mesasTable);
        painelConteudo.add(rolagemTabelaMesas);

        // Botao para adicionar produto
        JButton botaoAdicionarProduto = new JButton("Adicionar Produto");
        botaoAdicionarProduto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarProduto();
            }
        });

        // Botao para adicionar mesa
        JButton botaoAdicionarMesa = new JButton("Adicionar Mesa");
        botaoAdicionarMesa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarMesa();
            }
        });

        JPanel painelBotoes = new JPanel(new GridLayout(1, 2));
        painelBotoes.add(botaoAdicionarProduto);
        painelBotoes.add(botaoAdicionarMesa);
        frame.getContentPane().add(painelBotoes, BorderLayout.SOUTH);
        carregarProdutosNaTabela();
        carregarMesasNaTabela();
        
     // Botao para criar pedido na mesa
        JButton botaoCriarPedido = new JButton("Criar Pedido");
        botaoCriarPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criarPedidoNaMesa();
            }
        });
     
        // Botao para alternar disponibilidade
        JButton botaoAlternarDisponibilidade = new JButton("Alternar Disponibilidade");
        botaoAlternarDisponibilidade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = mesasTable.getSelectedRow();
                if (linhaSelecionada >= 0) {
                    // Obter o numero da mesa a partir da tabela
                    int numeroMesa = (int) mesasTableModel.getValueAt(linhaSelecionada, 0);
                    
                    // Encontrar a mesa correspondente no menu
                    Mesa mesaSelecionada = null;
                    for (Mesa mesa : menu.getMesas()) {
                        if (mesa.getNumeroMesa() == numeroMesa) {
                            mesaSelecionada = mesa;
                            break;
                        }
                    }
                    
                    if (mesaSelecionada != null) {
                        // Alternar a disponibilidade da mesa
                        mesaSelecionada.setDisponibilidade(!mesaSelecionada.isDisponibilidade());

                        // Atualizar a tabela com a nova disponibilidade
                        mesasTableModel.setValueAt(mesaSelecionada.isDisponibilidade(), linhaSelecionada, 2);
                    }
                }
            }
        });
        
        // Botao para chamar a proxima classe
        JButton botaoExibirArquivo = new JButton("Exibir Arquivo de Texto");
        botaoExibirArquivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exibirArquivoDeTexto();
            }
        });
        // Adicionando os botoes na interface
        painelBotoes.add(botaoAlternarDisponibilidade); 
        painelBotoes.add(botaoCriarPedido);
        painelBotoes.add(botaoExibirArquivo);
    }
    
    private void exibirArquivoDeTexto() {
        ExibirArquivoTextoSwing.main(new String[]{});
    }

    private void carregarProdutosNaTabela() {
        List<Produtos> produtos = menu.getProdutos();
        for (Produtos produto : produtos) {
            produtosTableModel.addRow(new Object[]{produto.getNome(), produto.getDescricao(), produto.getPreco()});
        }
    }

    private void carregarMesasNaTabela() {
        List<Mesa> mesas = menu.getMesas();
        for (Mesa mesa : mesas) {
            mesasTableModel.addRow(new Object[]{mesa.getNumeroMesa(), mesa.getCapacidade(), mesa.isDisponibilidade()});
        }
    }

    private void adicionarProduto() {
        JPanel painelEntradaProduto = new JPanel(new GridLayout(3, 2));
        JTextField campoNome = new JTextField();
        JTextField campoDescricao = new JTextField();
        JTextField campoPreco = new JTextField();
        painelEntradaProduto.add(new JLabel("Nome:"));
        painelEntradaProduto.add(campoNome);
        painelEntradaProduto.add(new JLabel("Descricao:"));
        painelEntradaProduto.add(campoDescricao);
        painelEntradaProduto.add(new JLabel("Preco:"));
        painelEntradaProduto.add(campoPreco);

        int resultado = JOptionPane.showConfirmDialog(frame, painelEntradaProduto, "Adicionar Produto", JOptionPane.OK_CANCEL_OPTION);

        if (resultado == JOptionPane.OK_OPTION) {
            String nome = campoNome.getText();
            String descricao = campoDescricao.getText();
            try {
                double preco = Double.parseDouble(campoPreco.getText());

                // Se a conversao for bem-sucedida, continua com o processamento
                Produtos novoProduto = new Produtos(nome, descricao, preco);
                menu.adicionarProduto(novoProduto);

                produtosTableModel.addRow(new Object[]{novoProduto.getNome(), novoProduto.getDescricao(), novoProduto.getPreco()});
            } catch (NumberFormatException e) {
                // Trate o caso em que o usuario inseriu uma letra no campo de preco
                JOptionPane.showMessageDialog(frame, "Por favor, insira um valor numerico valido para o preco.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void criarPedidoNaMesa() {
        int linhaSelecionada = mesasTable.getSelectedRow();
        if (linhaSelecionada >= 0) {
            int numeroMesa = (int) mesasTableModel.getValueAt(linhaSelecionada, 0);
            Mesa mesa = menu.encontrarMesaPorNumero(numeroMesa);

            if (mesa != null && mesa.isDisponibilidade()) {
                // Obter a lista de produtos disponiveis do menu
                List<Produtos> produtosDispononiveis = menu.getProdutos();
                mesa.setDisponibilidade(false); // Define a mesa como indisponivel
                
                // Atualiza a tabela para mostrar a indisponibilidade
                mesasTableModel.setValueAt(false, linhaSelecionada, 2);
                
                PedidoGUI pedidoGUI = new PedidoGUI(mesa, produtosDispononiveis);
                pedidoGUI.exibir();
            } else {
                JOptionPane.showMessageDialog(frame, "A mesa esta ocupada ou indisponovel.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Selecione uma mesa para criar um pedido.");
        }
    }


    private void adicionarMesa() {
        JPanel painelEntradaMesa = new JPanel(new GridLayout(3, 2));
        JTextField campoNumeroMesa = new JTextField();
        JTextField campoCapacidade = new JTextField();
        JCheckBox campoDisponibilidade = new JCheckBox("Disponivel");
        painelEntradaMesa.add(new JLabel("Numero da Mesa:"));
        painelEntradaMesa.add(campoNumeroMesa);
        painelEntradaMesa.add(new JLabel("Capacidade:"));
        painelEntradaMesa.add(campoCapacidade);
        painelEntradaMesa.add(new JLabel("Disponibilidade:"));
        painelEntradaMesa.add(campoDisponibilidade);

        int resultado = JOptionPane.showConfirmDialog(frame, painelEntradaMesa, "Adicionar Mesa", JOptionPane.OK_CANCEL_OPTION);

        if (resultado == JOptionPane.OK_OPTION) {
            int numeroMesa = Integer.parseInt(campoNumeroMesa.getText());
            int capacidade = Integer.parseInt(campoCapacidade.getText());
            boolean disponibilidade = campoDisponibilidade.isSelected();

            Mesa novaMesa = new Mesa(numeroMesa, capacidade);
            novaMesa.setDisponibilidade(disponibilidade);
            menu.adicionarMesa(novaMesa);

            mesasTableModel.addRow(new Object[]{novaMesa.getNumeroMesa(), novaMesa.getCapacidade(), novaMesa.isDisponibilidade()});
        }
    }
    public void exibir() {
        frame.setVisible(true);
    }
}