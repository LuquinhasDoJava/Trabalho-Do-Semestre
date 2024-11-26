package fateczl.trabalho.vestido;

import java.sql.SQLException;
import java.util.List;

public interface VestidoInterfaceDAO {
    void adicionar(Vestido v) throws SQLException;
    void atualizar(Vestido v) throws SQLException;
    void deletar(Vestido v) throws  SQLException;
    List<Vestido> pesquisarPorCor(String cor) throws SQLException;
    List<Vestido> pesquisarTodos() throws SQLException;
}
