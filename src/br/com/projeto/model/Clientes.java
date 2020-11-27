package br.com.projeto.model;

import java.util.Objects;

/**
 *
 * @author Marcelo Saorim
 */
public class Clientes {
    
    // ========================================================================
 
    private int    id;
    private String nome;
    private String rg;
    private String cpf;
    private String email;
    private String telefone;
    private String celular;
    private String cep;
    private String endereco;
    private int    numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    
    // ========================================================================

    /**
     *
     * @return
     */

    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public String getRg() {
        return rg;
    }

    /**
     *
     * @param rg
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     *
     * @return
     */
    public String getCpf() {
        return cpf;
    }

    /**
     *
     * @param cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     *
     * @param telefone
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     *
     * @return
     */
    public String getCelular() {
        return celular;
    }

    /**
     *
     * @param celular
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     *
     * @return
     */
    public String getCep() {
        return cep;
    }

    /**
     *
     * @param cep
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     *
     * @return
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     *
     * @param endereco
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     *
     * @return
     */
    public int getNumero() {
        return numero;
    }

    /**
     *
     * @param numero
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     *
     * @return
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     *
     * @param complemento
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     *
     * @return
     */
    public String getBairro() {
        return bairro;
    }

    /**
     *
     * @param bairro
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     *
     * @return
     */
    public String getCidade() {
        return cidade;
    }

    /**
     *
     * @param cidade
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     *
     * @return
     */
    public String getUf() {
        return uf;
    }

    /**
     *
     * @param uf
     */
    public void setUf(String uf) {
        this.uf = uf;
    }
    
    @Override
    public String toString(){
        return this.getNome();
    }
    // ========================================================================

//    @Override
//    public int hashCode() {
//        int hash = 3;
//        hash = 53 * hash + Objects.hashCode(this.cpf);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Clientes other = (Clientes) obj;
//        if (!Objects.equals(this.cpf, other.cpf)) {
//            return false;
//        }
//        return true;
//    }
    
}
    
