package fateczl.trabalho.cliente;

import fateczl.trabalho.cliente.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteInferfaceDAO {
    void adicionar(Cliente c) throws SQLException;
    void atualizar(Cliente c) throws SQLException;
    void deletar(Cliente c) throws  SQLException;
    List<Cliente> pesquisarNome(String nome) throws SQLException;
    List<Cliente> pesquisarTodos() throws SQLException;
}
