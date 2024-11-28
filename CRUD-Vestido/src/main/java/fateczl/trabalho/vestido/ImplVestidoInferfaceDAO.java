package fateczl.trabalho.vestido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplVestidoInferfaceDAO implements VestidoInterfaceDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/locadora";
    private static final String USER = "root";
    private static final String PASS = "123456";
    private Connection con = null;

    public ImplVestidoInferfaceDAO(){
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conectado com sucesso!");
        } catch ( SQLException e) {
            e.printStackTrace();
            System.out.println("Não foi conectado!");
        }
    }

    @Override
    public void adicionar(Vestido v) throws SQLException {
        try {
            String SQL = """
                    INSERT INTO vestido (cor, tamanho, material, preco, observacao, estilo)
                    VALUES (?, ?, ?, ?, ?,?)
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setString(1,v.getCor());
            stm.setString(2, v.getTamanho());
            stm.setString(3, v.getMaterial());
            stm.setDouble(4, v.getPreco());
            stm.setString( 5,v.getObservacao());
            stm.setString(6,v.getEstilo());
            int i = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Vestido v) throws SQLException {
        try {
            String SQL = """
                    UPDATE vestido SET cor=?, tamanho=?, estilo=?, material=?,preco=?, observacao=? 
                    WHERE id=?
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setString(1,v.getCor());
            stm.setString(2, v.getTamanho());
            stm.setString(3,v.getEstilo());
            stm.setString(4, v.getMaterial());
            stm.setDouble(5, v.getPreco());
            stm.setString( 6,v.getObservacao());
            stm.setInt(7,v.getId());
            int i = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Vestido v) throws SQLException {
        try {
            String SQL = """
                    DELETE FROM vestido WHERE id = ?
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setInt(1, v.getId());
            int i = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Vestido> pesquisarPorCor(String cor) throws SQLException {
        List<Vestido> lista = new ArrayList<>();
        try {
            String SQL = """
                    SELECT * FROM vestido WHERE cor LIKE ?       
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setString(1, "%" + cor + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Vestido v = new Vestido();
                v.setId(rs.getInt("id"));
                v.setCor(rs.getString("cor"));
                v.setTamanho(rs.getString("tamanho"));
                v.setEstilo(rs.getString("estilo"));
                v.setMaterial(rs.getString("material"));
                v.setPreco(rs.getDouble("preco"));
                v.setObservacao(rs.getString("observacao"));
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Vestido> pesquisarTodos() throws SQLException {
        List<Vestido> lista = new ArrayList<>();
        try {
            String SQL = """
                    SELECT * FROM vestido
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Vestido v = new Vestido();
                v.setId(rs.getInt("id"));
                v.setCor(rs.getString("cor"));
                v.setTamanho(rs.getString("tamanho"));
                v.setEstilo(rs.getString("estilo"));
                v.setMaterial(rs.getString("material"));
                v.setPreco(rs.getDouble("preco"));
                v.setObservacao(rs.getString("observacao"));
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
