package fateczl.trabalho.aluguel;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplAluguelInterfaceDAO implements AluguelInterfaceDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/locadora";
    private static final String USER = "root";
    private static final String PASS = "123456";
    private Connection con = null;

    public ImplAluguelInterfaceDAO(){
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conectado com sucesso!");
        } catch ( SQLException e) {
            e.printStackTrace();
            System.out.println("Não foi conectado!");
        }
    }

    @Override
    public void adicionar(Aluguel a) throws SQLException {
        try {
            String SQL = """
                    INSERT INTO aluguel (cliente_id, vestido_id, dataInicio, dataFim, precoTotal,status)
                    VALUES (?, ?, ?, ?, ?, ?)
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setInt(1,a.getCliente_id());
            stm.setInt(2, a.getVestido_id());
            stm.setDate(3, Date.valueOf(a.getDataInicio()));
            stm.setDate(4, Date.valueOf(a.getDataFim()));
            stm.setDouble( 5,a.getPrecoTotal());
            stm.setBoolean(6,a.getStatus());
            int i = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Aluguel a) throws SQLException {
        try {
            String SQL = """
                    UPDATE aluguel SET dataInicio=?, dataFim=?, precoTotal=?, status=? 
                    WHERE cliente_id=? AND vestido_id=? AND dataInicio=?
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setDate(1, Date.valueOf(a.getDataInicio()));
            stm.setDate(2, Date.valueOf(a.getDataFim()));
            stm.setDouble(3,a.getPrecoTotal());
            stm.setBoolean(4,a.getStatus());
            stm.setInt(5,a.getCliente_id());
            stm.setInt(6,a.getVestido_id());
            stm.setDate(7, Date.valueOf(a.getDataInicio()));
            int i = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Aluguel a) throws SQLException {
        try {
            String SQL = """
                    DELETE FROM aluguel WHERE cliente_id = ? AND vestido_id = ? AND dataInicio = ?
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setInt(1,a.getCliente_id());
            stm.setInt(2,a.getVestido_id());
            stm.setDate(3, Date.valueOf(a.getDataInicio()));
            int i = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Aluguel> pesquisarPorCliente_id(int cliente_id) throws SQLException {
        List<Aluguel> lista = new ArrayList<>();
        try {
            String SQL = """
                    SELECT * FROM aluguel 
                    WHERE cliente_id = ?      
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setInt(1,cliente_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Aluguel a = new Aluguel();
                a.setCliente_id(rs.getInt("cliente_id"));
                a.setVestido_id(rs.getInt("vestido_id"));
                a.setDataInicio(rs.getDate("dataInicio").toLocalDate());
                a.setDataFim(rs.getDate("dataFim").toLocalDate());
                a.setPrecoTotal(rs.getDouble("precoTotal"));
                a.setStatus(rs.getBoolean("status"));
                lista.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Aluguel> pesquisarTodos() throws SQLException {
        List<Aluguel> lista = new ArrayList<>();
        try {
            String SQL = """
                    SELECT * FROM aluguel
                    """;
            PreparedStatement stm = con.prepareStatement(SQL);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Aluguel a = new Aluguel();
                a.setCliente_id(rs.getInt("cliente_id"));
                a.setVestido_id(rs.getInt("vestido_id"));
                a.setDataInicio(rs.getDate("dataInicio").toLocalDate());
                a.setDataFim(rs.getDate("dataFim").toLocalDate());
                a.setPrecoTotal(rs.getDouble("precoTotal"));
                a.setStatus(rs.getBoolean("status"));
                lista.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
