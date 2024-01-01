package Hamburgueria;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class PedidoGUI {
    private JFrame frame;
    private Pedidos pedido;
    private double total;
    private JTable produtosTable;
    private DefaultTableModel produtosTableModel;

    public PedidoGUI(Mesa mesa, List<Produtos> produtosDisponiveis) {
        pedido = new Pedidos(mesa);
        new DefaultTableModel(new Object[]{"Nome", "Descricao", "Preco"}, 0);

        frame = new JFrame("Pedido da Mesa " + mesa.getNumeroMesa());
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painelConteudo = new JPanel(new BorderLayout());

        // Tabela para exibir produtos disponiveis
        produtosTableModel = new DefaultTableModel(new Object[]{"Nome", "Descricao", "Preco"}, 0);
        produtosTable = new JTable(produtosTableModel);
        JScrollPane rolagemTabelaProdutos = new JScrollPane(produtosTable);
        painelConteudo.add(rolagemTabelaProdutos, BorderLayout.CENTER);

        JPanel botoesPanel = new JPanel(new GridLayout(1, 2));

        // Botao para adicionar produtos ao pedido
        JButton botaoAdicionarProduto = new JButton("Adicionar Produto");
        botaoAdicionarProduto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarProdutoAoPedido();
            }
        });
        botoesPanel.add(botaoAdicionarProduto);

        // Botao para calcular o total
        JButton botaoCalcularTotal = new JButton("Calcular Total");
        botaoCalcularTotal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularTotal();
            }
        });
        
        // Botao para imprimir o pedido
        JButton botaoImprimirPedido = new JButton("Imprimir Pedido");
        botaoImprimirPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                imprimirPedido();
            }
        });
        botoesPanel.add(botaoImprimirPedido);
        
        botoesPanel.add(botaoCalcularTotal);
        painelConteudo.add(botoesPanel, BorderLayout.SOUTH);
        frame.add(painelConteudo);
        
        carregarProdutosDisponiveis(produtosDisponiveis);
    }

    public void exibir() {
        frame.setVisible(true);
    }

    private void carregarProdutosDisponiveis(List<Produtos> produtosDisponiveis) {
        for (Produtos produto : produtosDisponiveis) {
            produtosTableModel.addRow(new Object[]{produto.getNome(), produto.getDescricao(), produto.getPreco()});
        }
    }

    private void adicionarProdutoAoPedido() {
        int linhaSelecionada = produtosTable.getSelectedRow();
        if (linhaSelecionada >= 0) {
            String nome = (String) produtosTableModel.getValueAt(linhaSelecionada, 0);
            String descricao = (String) produtosTableModel.getValueAt(linhaSelecionada, 1);
            double preco = (Double) produtosTableModel.getValueAt(linhaSelecionada, 2);
            pedido.adicionarProduto(new Produtos(nome, descricao, preco)); 
            JOptionPane.showMessageDialog(frame, "Produto adicionado ao pedido.");
        } else {
            JOptionPane.showMessageDialog(frame, "Selecione um produto para adicionar ao pedido.");
        }
    }
    
    private void imprimirPedido() {
        String nomeArquivo = "pedido_mesa_" + pedido.getMesa().getNumeroMesa() + ".txt";
        pedido.imprimir(nomeArquivo);
        JOptionPane.showMessageDialog(frame, "Pedido impresso com sucesso.");
    }
    
    private void calcularTotal() {
        total = pedido.calcularTotal();
        DecimalFormat df = new DecimalFormat("#.00");
        JOptionPane.showMessageDialog(frame, "Total do Pedido: R$" + df.format(total));
    }
}
