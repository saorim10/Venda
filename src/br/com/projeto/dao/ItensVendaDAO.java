package br.com.projeto.dao;
import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.ItensVenda;
import br.com.projeto.model.Produtos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ItensVendaDAO {
    private Connection con;
    
    public ItensVendaDAO(){
        this.con = new ConnectionFactory().getConnection();
    }
    
    //=========================================================================
    // Cadastrar Itens
    public void cadastraItem(ItensVenda obj){
        try {
            String sql = "INSERT INTO tb_itensvendas (venda_id, produto_id, "
                    + "qtd, subtotal) VALUE (?, ?, ?, ?)";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getVenda().getId());
            stmt.setInt(2, obj.getProduto().getId());
            stmt.setDouble(3, obj.getQtde());
            stmt.setDouble(4, obj.getSubtotal());
            
            stmt.execute();
            stmt.close();
            
            //JOptionPane.showMessageDialog(null, "Registrado com sucesso.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }
    
    // Listar Itens de uma venda ==============================================
    public List<ItensVenda> listaItensPorVenda(int vendaId) {
        List<ItensVenda> lista = new ArrayList<>();

        try {
            String sql = "SELECT i.id, p.descricao, i.qtd, p.preco, i.subtotal "
                    + "FROM tb_itensvendas AS i "
                    + "INNER JOIN tb_produtos AS p ON(i.produto_id = p.id) "
                    + "WHERE i.venda_id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, vendaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ItensVenda item = new ItensVenda();
                Produtos prod = new Produtos();

                item.setId(rs.getInt("i.id"));
                prod.setDescricao(rs.getString("p.descricao"));
                item.setQtde(rs.getInt("i.qtd"));
                prod.setPreco(rs.getDouble("p.preco"));
                item.setSubtotal(rs.getDouble("i.subtotal"));
                item.setProduto(prod);

                lista.add(item);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            throw new RuntimeException(e);
        }
    } 
}
