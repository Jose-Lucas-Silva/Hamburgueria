package Hamburgueria;

import java.util.*;
import java.io.*;

public class Pedidos implements Imprimir {
    private List<Produtos> produtos;
    private Mesa mesa;
    private double total = 0.0;

    // CONSTRUTOR
    public Pedidos(Mesa mesa) {
        produtos = new ArrayList<>();
        this.mesa = mesa;
    }

    // ADICIONAR PRODUTO
    public void adicionarProduto(Produtos produto) {
        produtos.add(produto);
    }

    // CALCULAR TOTAL
    public double calcularTotal() {
    	total = 0.0;
        for (Produtos produto : produtos) {
            total += produto.getPreco();
        }
        return total;
    }

    // GETS E SETS
    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
    
    //Metodo para imprimir o pedido em arquivo txt
    public void imprimir(String nomeArquivo) {
        try (PrintWriter writer = new PrintWriter(nomeArquivo)) {
            writer.println("Detalhes do Pedido:");
            writer.println("Mesa: " + mesa.getNumeroMesa());
            writer.println("Capacidade: " + mesa.getCapacidade());
            writer.println("Disponibilidade: " + (mesa.isDisponibilidade() ? "Disponivel" : "Indisponivel"));

            writer.println("Produtos no Pedido:");
            for (Produtos produto : produtos) {
                writer.println("Nome: " + produto.getNome());
                writer.println("Descricao: " + produto.getDescricao());
                writer.println("Preco: " + produto.getPreco());
            }

            writer.println("Total do Pedido: R$" + calcularTotal());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
