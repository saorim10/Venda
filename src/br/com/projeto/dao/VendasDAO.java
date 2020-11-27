package br.com.projeto.dao;
import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VendasDAO {
    private Connection con;
    
    public VendasDAO(){
        this.con = new ConnectionFactory().getConnection();
    }
    
    // Incluir Venda ==========================================================
    public void incluirVenda(Vendas obj){
        try {
            String sql = "INSERT INTO tb_vendas (cliente_id, data_venda, "
                    + "total_venda, observacoes) VALUES (?, ?, ?, ?)";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCliente().getId());
            stmt.setString(2, obj.getDataVenda());
            stmt.setDouble(3, obj.getTotalVenda());
            stmt.setString(4, obj.getObs());
            
            stmt.execute();
            stmt.close();
            
            //JOptionPane.showMessageDialog(null, "Venda registrada com sucesso.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }
    //=========================================================================
    
    // Retornar Última Venda ==================================================
    public int ultimaVenda(){
        int idVenda = 0;
        try {
            String sql = "SELECT MAX(id) id FROM tb_vendas";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                Vendas v = new Vendas();
                v.setId(rs.getInt("id"));
                idVenda = v.getId();
            }
            return idVenda;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            throw new RuntimeException(e);
        }
    }
    
    // Filtrar Vendas por Data ================================================
    public List<Vendas> listarVendasPorPeriodo(LocalDate dataInicio, LocalDate dataFim){
        try {
            List<Vendas> lista = new ArrayList<>();
            
            String sql = "SELECT v.id, "
                       + "date_format(v.data_venda, '%d/%m/%Y') AS dataForm, "
                       + "c.nome, v.total_venda, "
                       + "v.observacoes FROM tb_vendas AS v INNER JOIN "
                       + "tb_clientes AS c ON(v.cliente_id = c.id) WHERE "
                       + "v.data_venda BETWEEN ? AND ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, dataInicio.toString());
            stmt.setString(2, dataFim.toString());
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Vendas vObj = new Vendas();
                Clientes cObj = new Clientes();
                
                vObj.setId(rs.getInt("v.id"));
                vObj.setDataVenda(rs.getString("dataForm"));
                cObj.setNome(rs.getString("c.nome"));
                vObj.setTotalVenda(rs.getDouble("v.total_venda"));
                vObj.setObs(rs.getString("v.observacoes"));
                
                vObj.setCliente(cObj);
                
                lista.add(vObj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            throw new RuntimeException(e);
        }
    }
    
    // Total Vendas por Data ==================================================
    public double vendasPorData(LocalDate dataVenda){
        try {
            double totalVenda = 0;
            
            String sql = "SELECT SUM(total_venda) AS total FROM tb_vendas "
                       + "WHERE data_venda = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, dataVenda.toString());
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                totalVenda = rs.getDouble("total");
            }
            
            return totalVenda;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            throw new RuntimeException(e);
        }
    }
    //=========================================================================
    
}
