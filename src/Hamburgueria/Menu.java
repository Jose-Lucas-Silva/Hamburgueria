package Hamburgueria;

import java.util.*;
import java.io.*;

public class Menu {

    private List<Produtos> produtos;
    private List<Mesa> mesas;

    public Menu() {
        produtos = new ArrayList<>();
        mesas = new ArrayList<>();
    }

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produtos produto) {
    	if (produto.getNome() == null) {
            throw new IllegalArgumentException("Erro: Não é permitido adicionar um produto com nome nulo.");
        }

        if (produto.getDescricao() == null) {
            throw new IllegalArgumentException("Erro: Não é permitido adicionar um produto com descrição nula.");
        }

        if (produto.getPreco() < 0) {
            throw new IllegalArgumentException("Erro: Não é permitido adicionar um produto com preço negativo.");
        }
        produtos.add(produto);        
    }


    public void removerProduto(Produtos produto) {
        produtos.remove(produto);
    }
    
    // Obter a lista de mesas do menu
    public List<Mesa> getMesas() {
        return mesas;
    }

    // Adicionar uma mesa ao menu
    public void adicionarMesa(Mesa mesa) {
        mesas.add(mesa);
    }


    public void salvarMenu(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Produtos produto : produtos) {
                writer.write("Produto:" + produto.getNome() + ";" + produto.getDescricao() + ";" + produto.getPreco());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarMenu(String nomeArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            produtos.clear();
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(":");
                if (partes.length == 2) {
                    String[] detalhes = partes[1].split(";");
                    if (partes[0].equals("Produto") && detalhes.length == 3) {
                        Produtos produto = new Produtos(detalhes[0], detalhes[1], Double.parseDouble(detalhes[2]));
                        produtos.add(produto);
                        System.out.println("Prato: " + produto.getNome() + "; " + produto.getDescricao() + "; " + produto.getPreco());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removerItemDoArquivo(String nomeArquivo, String tipo, String nomeItem) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            List<String> linhas = new ArrayList<>();
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }
            reader.close();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
                for (String linhaExistente : linhas) {
                    String[] partes = linhaExistente.split(":");
                    if (partes.length == 2) {
                        String[] detalhes = partes[1].split(";");
                        if (partes[0].equals(tipo) && detalhes.length == 3) {
                            String nome = detalhes[0];
                            if (!nome.equals(nomeItem)) {
                                writer.write(tipo + ":" + detalhes[0] + ";" + detalhes[1] + ";" + detalhes[2]);
                                writer.newLine();
                            }
                        } else {
                            writer.write(linhaExistente);
                            writer.newLine();
                        }
                    } else {
                        writer.write(linhaExistente);
                        writer.newLine();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Mesa encontrarMesaPorNumero(int numeroMesa) {
        for (Mesa mesa : mesas) {
            if (mesa.getNumeroMesa() == numeroMesa) {
                return mesa;
            }
        }
        return null;
    }
}