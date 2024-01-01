package Hamburgueria;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Mesa {

    private int numeroMesa;
    private int capacidade;
    private boolean disponibilidade;
    private String clienteAlocado;

    public Mesa(int numeroMesa, int capacidade) {
        this.numeroMesa = numeroMesa;
        this.capacidade = capacidade;
        this.disponibilidade = true;
        this.clienteAlocado = null;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getClienteAlocado() {
        return clienteAlocado;
    }

    public void setClienteAlocado(String clienteAlocado) {
        this.clienteAlocado = clienteAlocado;
    }

    public void imprimir(String nomeArquivo) {
        try (PrintWriter writer = new PrintWriter(nomeArquivo)) {
            writer.println("Detalhes da Mesa:");
            writer.println("Numero da Mesa: " + numeroMesa);
            writer.println("Capacidade: " + capacidade);
            writer.println("Disponibilidade: " + (disponibilidade ? "Disponível" : "Indisponível"));
            writer.println("Cliente Alocado: " + (clienteAlocado != null ? clienteAlocado : "Nenhum"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
