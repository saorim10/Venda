
package br.com.projeto.dao;
import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {
    
    private Connection con;
    
    public ProdutosDAO(){
        this.con = new ConnectionFactory().getConnection();
    }
    
    // ======================================================================================================
    public void cadastrar(Produtos obj){
        try {
            String sql = "INSERT INTO tb_produtos (descricao, preco, qtd_estoque, for_id) VALUES (?, ?, ?, ?)";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtde());
            stmt.setInt(4, obj.getFornecedor().getId());
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }
    
    // ======================================================================================================
    
    // Listar todos os Produtos
    public List<Produtos> listarProdutos(){
        try {
            List<Produtos> lista = new ArrayList<>();
            
            String sql = "SELECT "
                       + "p.id, "
                       + "p.descricao, "
                       + "p.preco, "
                       + "p.qtd_estoque, "
                       + "f.nome "
                       + "FROM "
                       + "tb_produtos AS p "
                       + "INNER JOIN "
                       + "tb_fornecedores AS f "
                       + "ON (p.for_id = f.id)";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtde(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString("f.nome"));
                obj.setFornecedor(f);
                
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }
    // ======================================================================================================
    // Alterar
    public void alterar(Produtos obj){
        try {
            String sql = "UPDATE tb_produtos SET descricao=?, preco=?, qtd_estoque=?, for_id=? WHERE id=?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtde());
            
            stmt.setInt(4, obj.getFornecedor().getId());
            
            stmt.setInt(5, obj.getId());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Produto alterado com sucesso");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }
    
    // ========================================================================
    // Excluir
    public void excluirProdutos(Produtos obj){
        try{
        String sql = "DELETE FROM tb_produtos WHERE id=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, obj.getId());
        stmt.execute();
        stmt.close();
        JOptionPane.showMessageDialog(null, "Produto Excuído.");
        
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
    }
    
    // Consulta por Nome Fornecedor ===========================================
    public Produtos consultaPorDescricao(String descricao) {
        try {
            String sql = "SELECT * FROM tb_produtos WHERE descricao=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, descricao);
            
            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();
            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setPreco(rs.getDouble("preco"));
                obj.setQtde(rs.getInt("qtd_estoque"));
                
                
            }
            return obj;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            return null;
        }
    }
    
    // Consulta Produto por Descrição =========================================
    public Produtos consultaPorCodigo(int numero){
        try {
            
            String sql = "SELECT p.id, p.descricao, p.preco, p.qtd_estoque, "
                    + "f.nome FROM tb_produtos AS p INNER JOIN tb_fornecedores "
                    + "AS f ON (p.for_id = f.id) WHERE p.id = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, numero);
            
            ResultSet rs = stmt.executeQuery();
            Produtos prod = new Produtos();
            Fornecedores forn = new Fornecedores();
            
            if(rs.next()){
                prod.setId(rs.getInt("p.id"));
                prod.setDescricao(rs.getString("p.descricao"));
                prod.setPreco(rs.getDouble("p.preco"));
                prod.setQtde(rs.getInt("p.qtd_estoque"));
                forn.setNome(rs.getString("f.nome"));
                prod.setFornecedor(forn);
            }
            return prod;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
        return null;
    }
    
    //=========================================================================
    // Atualiza Estoque
    public void baixaEstoque(int id, int qtdeAtual){
        try {
            String sql = "UPDATE tb_produtos SET qtd_estoque=? "
                       + "WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, qtdeAtual);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }
    //=========================================================================
    // Retorna Estoque Atual
    public int retornaEstoqueAtual(int id){
        try {
            int qtdeEstoque=0;
            
            String sql = "SELECT qtd_estoque FROM tb_produtos WHERE id=?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                qtdeEstoque=(rs.getInt("qtd_estoque"));
            }
            return qtdeEstoque;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            throw new RuntimeException(e);
        }
    }
    
    // Adiciona no Estoque ====================================================
    public void aumentaEstoque(int qtde, int id_produto){
        try {
            String sql = "UPDATE tb_produtos SET qtd_estoque = ? "
                       + "WHERE id = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, qtde);
            stmt.setInt(2, id_produto);
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }
}
