package fateczl.trabalho.aluguel;

import java.sql.SQLException;
import java.util.List;


public interface  AluguelInterfaceDAO {
    void adicionar(Aluguel v) throws SQLException;
    void atualizar(Aluguel v) throws SQLException;
    void deletar(Aluguel v) throws  SQLException;
    List<Aluguel> pesquisarPorCliente_id(int cliente_id) throws SQLException;
    List<Aluguel> pesquisarTodos() throws SQLException;
}