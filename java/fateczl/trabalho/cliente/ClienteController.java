package fateczl.trabalho.cliente;


import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;


public class ClienteController {

    private ObservableList<Cliente> lista = FXCollections.observableArrayList();

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty nome = new SimpleStringProperty();
    private StringProperty cpf = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private IntegerProperty telefone = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate> dataNas = new SimpleObjectProperty<>(LocalDate.now());

    private ClienteInferfaceDAO clienteInferfaceDAO;

    int contador = 0;

    public ClienteController() {
        clienteInferfaceDAO = new ImplClienteInferfaceDAO();
    }

    public void entidadeParaTela(Cliente novo) {
        if(novo!=null){
            this.id.set(novo.getId());
            this.nome.set(novo.getNome());
            this.cpf.set(novo.getCpf());
            this.email.set(novo.getEmail());
            this.telefone.set(novo.getTelefone());
            this.dataNas.set(novo.getDataNas());
        }
    }

    public void apagar(Cliente c) throws SQLException {
        System.out.println(c.toString()+" apagado com sucesso!");
        clienteInferfaceDAO.deletar(c);
        pesquisarTodos();
    }

    public void gravar() throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome.get());
        cliente.setCpf(this.cpf.get());
        cliente.setEmail(this.email.get());
        cliente.setTelefone(this.telefone.get());
        cliente.setDataNas(this.dataNas.get());

        if (id.get() == 0) {
            contador++;
            cliente.setId(contador);
            clienteInferfaceDAO.adicionar(cliente);
        } else {
            cliente.setId( id.get() );
            clienteInferfaceDAO.atualizar(cliente);
        }
        pesquisarTodos();
        limparCampos();
        System.out.println("Lista cpf: " + lista.size());

    }

    private void limparCampos() {
        this.id.set(0);
        this.nome.set("");
        this.cpf.set("");
        this.email.set("");
        this.telefone.set(0);
        this.dataNas.set(null);
    }

    public void procurar() throws SQLException {
        lista.clear();
        lista.addAll(clienteInferfaceDAO.pesquisarNome(nome.get()));
    }

    public void pesquisarTodos() throws SQLException {
        lista.clear();
        lista.addAll(clienteInferfaceDAO.pesquisarTodos());
    }

    public IntegerProperty idProperty(){
        return this.id;
    }
    public StringProperty nomeProperty(){
        return this.nome;
    }
    public StringProperty cpfProperty(){
        return  this.cpf;
    }
    public StringProperty emailProperty(){
        return this.email;
    }
    public IntegerProperty telefoneProperty(){
        return this.telefone;
    }
    public ObjectProperty<LocalDate> dataNasProperty(){
        return this.dataNas;
    }
    public ObservableList<Cliente> getItems() {
        return (ObservableList<Cliente>) this.lista;
    }
}