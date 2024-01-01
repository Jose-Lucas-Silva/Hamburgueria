package Hamburgueria;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ExibirArquivoTextoSwing {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            criarGUI();
        });
    }

    private static void criarGUI() {
        JFrame frame = new JFrame("Exibir Arquivo de Texto");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JFileChooser fileChooser = new JFileChooser();

        //Definindo o diretorio inicial para o diretorio de trabalho atual
        String diretorioAtual = System.getProperty("user.dir");
        fileChooser.setCurrentDirectory(new File(diretorioAtual));

        int resultado = fileChooser.showOpenDialog(frame);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            try {
                String nomeArquivo = fileChooser.getSelectedFile().getAbsolutePath();
                String conteudo = lerArquivoTexto(nomeArquivo);
                textArea.setText(conteudo);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Erro ao ler o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }

        frame.setVisible(true);
    }
    //Metodo para ler um arquivo em txt
    private static String lerArquivoTexto(String nomeArquivo) throws IOException {
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        }
        return conteudo.toString();
    }
}

