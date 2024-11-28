package fateczl.trabalho.aluguel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;

public class AluguelController {
    private IntegerProperty cliente_id = new SimpleIntegerProperty();
    private IntegerProperty vestido_id = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate> dataInicio = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> dataFim = new SimpleObjectProperty<>();
    private DoubleProperty precoTotal = new SimpleDoubleProperty();
    private BooleanProperty status = new SimpleBooleanProperty();
    private ObservableList<Aluguel> lista = FXCollections.observableArrayList();

    private AluguelInterfaceDAO aluguelInterfaceDAO;

    public AluguelController(){
        aluguelInterfaceDAO = new ImplAluguelInterfaceDAO();
    }

    public void entidadeParaTela(Aluguel a) throws SQLException {
        if (a != null) {
            this.cliente_id.set(a.getCliente_id());
            this.vestido_id.set(a.getVestido_id());
            this.dataInicio.set(a.getDataInicio());
            this.dataFim.set(a.getDataFim());
            this.precoTotal.set(a.getPrecoTotal());
            this.status.set(a.getStatus());
        }
    }

        public void criar() throws SQLException {
            Aluguel a = new Aluguel();
            a.setCliente_id(this.cliente_id.get());
            a.setVestido_id(this.vestido_id.get());
            a.setDataInicio(this.dataInicio.get());
            a.setDataFim(this.dataFim.get());
            a.setPrecoTotal(this.precoTotal.get());
            a.setStatus(this.status.get());
            aluguelInterfaceDAO.adicionar(a);
            pesquisarTotods();
            limparCampos();
        }

        public void apagar(Aluguel a) throws SQLException {
            aluguelInterfaceDAO.deletar(a);
            pesquisarTotods();
        }

        public void atualizar() throws SQLException {
            Aluguel a = new Aluguel();
            a.setCliente_id(this.cliente_id.get());
            a.setVestido_id(this.vestido_id.get());
            a.setDataInicio(this.dataInicio.get());
            a.setDataFim(this.dataFim.get());
            a.setPrecoTotal(this.precoTotal.get());
            a.setStatus(this.status.get());
            aluguelInterfaceDAO.atualizar(a);
            pesquisarTotods();
        }

    public void pesquisarTotods() throws SQLException {
        lista.clear();
        lista.addAll(aluguelInterfaceDAO.pesquisarTodos());
    }

    public void pesquisarPorCliente_id() throws SQLException {
        lista.clear();
        lista.addAll(aluguelInterfaceDAO.pesquisarPorCliente_id(cliente_id.get()));
    }

    private void limparCampos() {
        this.cliente_id.set(0);
        this.vestido_id.set(0);
        this.dataInicio.set(LocalDate.parse("00/00/000"));
        this.dataFim.set(LocalDate.parse("00/00/0000"));
        this.precoTotal.set(0.00);
        this.status.set(false);
    }

    public IntegerProperty cliente_idProperty() {
        return cliente_id;
    }

    public IntegerProperty vestido_idProperty() {
        return vestido_id;
    }

    public ObjectProperty<LocalDate> dataInicioProperty() {
        return dataInicio;
    }

    public ObjectProperty<LocalDate> dataFimProperty() {
        return dataFim;
    }

    public DoubleProperty precoTotalProperty() {
        return precoTotal;
    }

    public BooleanProperty statusProperty() {
        return status;
    }

    public ObservableList<Aluguel> getLista() {
        return lista;
    }
}
