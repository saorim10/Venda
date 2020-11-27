package br.com.projeto.model;

/**
 *
 * @author Marcelo Saorim
 */
public class Funcionarios extends Clientes{
    // Atributos
    private String senha;
    private String cargo;
    private String nivel_acesso;
    
    // Getters & Setters

    /**
     *
     * @return
     */

    public String getSenha() {
        return senha;
    }

    /**
     *
     * @param senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     *
     * @return
     */
    public String getCargo() {
        return cargo;
    }

    /**
     *
     * @param cargo
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     *
     * @return
     */
    public String getNivel_acesso() {
        return nivel_acesso;
    }

    /**
     *
     * @param nivel_acesso
     */
    public void setNivel_acesso(String nivel_acesso) {
        this.nivel_acesso = nivel_acesso;
    }
    
    
}
