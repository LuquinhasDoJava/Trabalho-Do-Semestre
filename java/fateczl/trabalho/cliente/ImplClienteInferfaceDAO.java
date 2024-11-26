package fateczl.trabalho.cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplClienteInferfaceDAO implements ClienteInferfaceDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/locadora";
    private static final String USER = "root";
    private static final String PASS = "123456";
    private Connection con = null;

    public ImplClienteInferfaceDAO(){
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conectado com sucesso!");
        } catch ( SQLException e) {
            e.printStackTrace();
            System.out.println("Não foi conectado!");
        }
    }

    @Override
    public void adicionar(Cliente c) throws SQLException {
        try {
            String SQL = """
                    INSERT INTO cliente (nome, cpf, email, telefone, dataNas) VALUES
                    VALUES (?, ?, ?, ?, ?)
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setString(1,c.getNome());
            stm.setString(2, c.getCpf());
            stm.setString(3, c.getEmail());
            stm.setInt(4, c.getTelefone());
            stm.setDate( 5, Date.valueOf(c.getDataNas()));
            int i = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void atualizar(Cliente  c) throws SQLException {
        try {
            String SQL = """
                    UPDATE cliente SET nome=?, cpf=?, email=?, telefone=? 
                    WHERE id=?
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setString(1,c.getNome());
            stm.setString(2, c.getCpf());
            stm.setString(3, c.getEmail());
            stm.setInt(4, c.getTelefone());
            stm.setInt(5, c.getId());
            int i = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Cliente c) throws SQLException {
        try {
            String SQL = """
                    DELETE FROM cliente WHERE id = ?
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setInt(1, c.getId());
            int i = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Cliente > pesquisarNome(String nome) throws SQLException {
        List<Cliente > lista = new ArrayList<>();
        try {
            String SQL = """
                    SELECT * FROM cliente WHERE nome LIKE ?       
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setString(1, "'%" + nome + "%'");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente ();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getInt("telefone"));
                c.setDataNas(rs.getDate("dataNas").toLocalDate());
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Cliente > pesquisarTodos() throws SQLException {
        List<Cliente > lista = new ArrayList<>();
        try {
            String SQL = """
                    SELECT * FROM cliente 
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente ();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getInt("telefone"));
                c.setDataNas(rs.getDate("dataNas").toLocalDate());
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
