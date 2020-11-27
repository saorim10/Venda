/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Marcelo Saorim
 */
public class ConnectionFactory {
    private Connection conexao = null;

    /**
     *
     */
    public String url = "jdbc:mysql://127.0.0.1/bdvendas?useTimezone=true&serverTimezone=America/Sao_Paulo";

    /**
     *
     */
    public String user = "usuariocurso";

    /**
     *
     */
    public String pass = "123";
    
    /**
     *
     * @return
     */
    public Connection getConnection(){
        try {
            conexao = DriverManager.getConnection(url, user, pass);
            return conexao;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
