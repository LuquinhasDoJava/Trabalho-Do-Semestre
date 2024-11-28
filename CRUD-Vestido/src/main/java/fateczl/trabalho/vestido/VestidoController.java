package fateczl.trabalho.vestido;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;


public class VestidoController {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty cor = new SimpleStringProperty();
    private StringProperty tamanho = new SimpleStringProperty();
    private StringProperty estilo = new SimpleStringProperty();
    private StringProperty material = new SimpleStringProperty();
    private DoubleProperty preco = new SimpleDoubleProperty();
    private StringProperty observacao = new SimpleStringProperty();
    private ObservableList<Vestido> lista = FXCollections.observableArrayList();

    private VestidoInterfaceDAO vestidoInterfaceDAO;

    public VestidoController() {
        vestidoInterfaceDAO = new ImplVestidoInferfaceDAO();
    }

    public void entidadeParaTela(Vestido novo) {
        if(novo!=null){
            this.id.set(novo.getId());
            this.cor.set(novo.getCor());
            this.tamanho.set(novo.getTamanho());
            this.material.set(novo.getMaterial());
            this.estilo.set(novo.getEstilo());
            this.preco.set(novo.getPreco());
            this.observacao.set(novo.getObservacao());
        }

    }

    public void apagar(Vestido v) throws SQLException {
        vestidoInterfaceDAO.deletar(v);
        pesquisarTodos();
    }

    public void gravar() throws SQLException {
        Vestido vestido = new Vestido();
        vestido.setCor(this.cor.get());
        vestido.setTamanho(this.tamanho.get());
        vestido.setEstilo(this.estilo.get());
        vestido.setMaterial(this.material.get());
        vestido.setPreco(this.preco.get());
        vestido.setObservacao(this.observacao.get());
        vestidoInterfaceDAO.adicionar(vestido);
        pesquisarTodos();
        limparCampos();
    }

    public void atualiar() throws SQLException {
        Vestido vestido = new Vestido();
        vestido.setId(this.id.get());
        vestido.setCor(this.cor.get());
        vestido.setTamanho(this.tamanho.get());
        vestido.setEstilo(this.estilo.get());
        vestido.setMaterial(this.material.get());
        vestido.setPreco(this.preco.get());
        vestido.setObservacao(this.observacao.get());
        vestidoInterfaceDAO.atualizar(vestido);
        pesquisarTodos();
    }

    private void limparCampos() {
        this.id.set(0);
        this.cor.set("");
        this.tamanho.set("");
        this.estilo.set("");
        this.material.set("");
        this.preco.set(0.00);
        this.observacao.set("");
    }

    public void procurarPorCor() throws SQLException {
        lista.clear();
        lista.addAll(vestidoInterfaceDAO.pesquisarPorCor(cor.get()));
    }

    public void pesquisarTodos() throws SQLException {
        lista.clear();
        lista.addAll(vestidoInterfaceDAO.pesquisarTodos());
    }

    public IntegerProperty idProperty(){
        return this.id;
    }
    public StringProperty corProperty(){
        return this.cor;
    }
    public StringProperty tamanhoProperty(){
        return  this.tamanho;
    }
    public StringProperty materialProperty(){
        return this.material;
    }
    public StringProperty estiloProperty(){
        return this.estilo;
    }
    public DoubleProperty precoProperty(){
        return this.preco;
    }
    public StringProperty observacaoProperty(){
        return this.observacao;
    }
    public ObservableList<Vestido> getItems() {
        return this.lista;
    }
}