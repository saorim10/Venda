package br.com.projeto.jdbc;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcelo Saorim
 */
public class TestaConexao {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null, "Conectado.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro de Conexão! " + e);
        }
    }
}
