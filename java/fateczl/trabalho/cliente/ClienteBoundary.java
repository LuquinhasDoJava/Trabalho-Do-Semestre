package fateczl.trabalho.cliente;


import fateczl.trabalho.Tela;
import javafx.beans.binding.Bindings;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import java.sql.SQLException;
import java.util.Date;

public class ClienteBoundary implements Tela {

    private TextField txtId = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtCpf = new TextField();
    private TextField txtEmail = new TextField();
    private TextField txtTelefone = new TextField();
    private TextField txtDataNas = new TextField();

    private TableView<Cliente> tableView = new TableView<>();

    private ClienteController controller;

    @Override
    public Pane render() {
        controller = new ClienteController();

        BorderPane bordas = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(3.2);
        gridPane.setVgap(5.5);
        Label titulo = new Label("Controle de Clientes");
        titulo.setFont(new Font(25));
        bordas.setTop(titulo);
        bordas.setCenter(gridPane);
        bordas.setBottom(tableView);

        String[] informacoes = {"Id", "Nome","CPF","Email", "Telefone", "Data de Nascimento"};
        int x = 0;
        for(String info : informacoes){
            gridPane.add(new Label(info+":"),0,x);
            x++;
        }

        gridPane.add(txtId,1,0);
        gridPane.add(txtNome,1,1);
        gridPane.add(txtCpf,1,2);
        gridPane.add(txtEmail,1,3);
        gridPane.add(txtTelefone,1,4);
        gridPane.add(txtDataNas,1,5);

        Button btnGravar = new Button("Gravar Cliente");
        btnGravar.setOnAction(e -> {
            try {
                controller.gravar();
                tableView.refresh();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        gridPane.add(btnGravar,0,6);

        Button btnProcurar = new Button("Procurar Nome do Cliente");
        btnProcurar.setOnAction(e -> {
            try {
                controller.procurar();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        gridPane.add(btnProcurar,1,6);

        propertys();
        generateColumns();

        return bordas;
    }

    private void generateColumns() {
        TableColumn<Cliente, Integer> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("id"));

        TableColumn<Cliente, String> col2 = new TableColumn<>("nome");
        col2.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));

        TableColumn<Cliente, String> col3 = new TableColumn<>("CPF");
        col3.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));

        TableColumn<Cliente, String> col4 = new TableColumn<>("Email");
        col4.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));

        TableColumn<Cliente, Integer> col5 = new TableColumn<>("Telefone");
        col5.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("telefone"));

        TableColumn<Cliente, Date> col6 = new TableColumn<>("Data Nascimento");
        col6.setCellValueFactory(new PropertyValueFactory<Cliente, Date>("dataNas"));

        Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>> callback =
                new  Callback<>(){
                    @Override
                    public TableCell<Cliente, Void> call(TableColumn<Cliente, Void> param) {
                        TableCell<Cliente, Void> tc = new TableCell<>() {
                            final Button btnExcluir = new Button("Apagar");
                            {
                                btnExcluir.setOnAction(
                                        e -> {
                                            Cliente c = tableView.getItems().get( getIndex() );
                                            try {
                                                controller.apagar(c);
                                            } catch (SQLException ex) {
                                                throw new RuntimeException(ex);
                                            }
                                        }
                                );
                            }
                            public void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic( null );
                                } else {
                                    setGraphic( btnExcluir );
                                }
                            }
                        };
                        return tc;
                    }
                };
        TableColumn<Cliente, Void> col7 = new TableColumn<>("Ações");
        col7.setCellFactory( callback );

        tableView.getColumns().addAll(col1,col2,col3,col4,col5,col6,col7);
        tableView.setItems(controller.getItems());

        tableView.getSelectionModel().selectedItemProperty().addListener( (obs, antigo, novo) -> {
            controller.entidadeParaTela( novo );
        });
    }

    private void propertys() {
        Bindings.bindBidirectional(txtId.textProperty(), controller.idProperty(),(StringConverter) new IntegerStringConverter());
        Bindings.bindBidirectional(txtNome.textProperty(),controller.nomeProperty());
        Bindings.bindBidirectional(txtCpf.textProperty(),controller.cpfProperty());
        Bindings.bindBidirectional(txtEmail.textProperty(),controller.emailProperty());
        Bindings.bindBidirectional(txtTelefone.textProperty(),controller.telefoneProperty(),(StringConverter) new IntegerStringConverter());
        Bindings.bindBidirectional(txtDataNas.textProperty(),controller.dataNasProperty(),(StringConverter) new LocalDateStringConverter());
    }
}