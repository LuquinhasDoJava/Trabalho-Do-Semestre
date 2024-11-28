package fateczl.trabalho;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String DB_CLASS = "org.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/locadora";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456";

    private static Conexao instancia;
    private Connection connection;

    private Conexao() {
        try {
            Class.forName(DB_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Conexao getInstance() {
        if (instancia == null) {
            instancia = new Conexao();
        }
        return instancia;
    }
    public Connection getConnection() {
        try {
            if (this.connection == null || this.connection.isClosed() || !this.connection.isValid(5000)) {
                this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                System.out.println("Conectado");
            }
        } catch (SQLException e) {
            System.out.println("Não Conectado");
            throw new RuntimeException(e);
        }
        return this.connection;
    }

}
