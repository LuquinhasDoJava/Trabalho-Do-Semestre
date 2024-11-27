package fateczl.trabalho.vestido;


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
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import java.sql.SQLException;

public class VestidoBoundary implements Tela {

    private Label txtId = new Label();
    private TextField txtCor = new TextField();
    private TextField txtTamanho = new TextField();
    private TextField txtMaterial = new TextField();
    private TextField txtPreco = new TextField();
    private TextArea txtObservacao = new TextArea();

    private TableView<Vestido> tableView = new TableView<>();

    private VestidoController controller;

    @Override
    public Pane render() {
        controller = new VestidoController();

        BorderPane bordas = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(3.2);
        gridPane.setVgap(5.5);
        Label titulo = new Label("Controle de Vestidos");
        titulo.setFont(new Font(25));
        bordas.setTop(titulo);
        bordas.setCenter(gridPane);
        bordas.setBottom(tableView);

        String[] informacoes = {"Id", "Cor","Tamanho","Material", "Preco", "Observacao"};
        int x = 0;
        for(String info : informacoes){
            gridPane.add(new Label(info+":"),0,x);
            x++;
        }

        gridPane.add(txtId,1,0);
        gridPane.add(txtCor,1,1);
        gridPane.add(txtTamanho,1,2);
        gridPane.add(txtMaterial,1,3);
        gridPane.add(txtPreco,1,4);
        gridPane.add(txtObservacao,1,5);

        Button btnGravar = new Button("Gravar Vestido");
        btnGravar.setOnAction(e -> {
            try {
                controller.gravar();
                tableView.refresh();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        gridPane.add(btnGravar,0,6);

        Button btnProcurar = new Button("Procurar Cor do Vestido");
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
        try {
            controller.pesquisarTodos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bordas;
    }

    private void generateColumns() {
        TableColumn<Vestido, Integer> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory(new PropertyValueFactory<Vestido, Integer>("id"));

        TableColumn<Vestido, String> col2 = new TableColumn<>("Cor");
        col2.setCellValueFactory(new PropertyValueFactory<Vestido, String>("cor"));

        TableColumn<Vestido, String> col3 = new TableColumn<>("Tamanho");
        col3.setCellValueFactory(new PropertyValueFactory<Vestido, String>("tamanho"));

        TableColumn<Vestido, String> col4 = new TableColumn<>("Material");
        col4.setCellValueFactory(new PropertyValueFactory<Vestido, String>("material"));

        TableColumn<Vestido, Double> col5 = new TableColumn<>("Preço");
        col5.setCellValueFactory(new PropertyValueFactory<Vestido, Double>("preco"));

        TableColumn<Vestido, String> col6 = new TableColumn<>("Observação");
        col6.setCellValueFactory(new PropertyValueFactory<Vestido, String>("observacao"));

        Callback<TableColumn<Vestido, Void>, TableCell<Vestido, Void>> callback =
                new  Callback<>() {
                    @Override
                    public TableCell<Vestido, Void> call(TableColumn<Vestido, Void> param) {
                        TableCell<Vestido, Void> tc = new TableCell<>() {
                            final Button btnExcluir = new Button("Apagar");
                            {
                                btnExcluir.setOnAction(
                                        e -> {
                                            Vestido v = tableView.getItems().get( getIndex() );
                                            try {
                                                controller.apagar(v);
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
        TableColumn<Vestido, Void> col7 = new TableColumn<>("Ações");
        col7.setCellFactory( callback );

        tableView.getColumns().addAll(col1,col2,col3,col4,col5,col6,col7);
        tableView.setItems(controller.getItems());

        tableView.getSelectionModel().selectedItemProperty().addListener( (obs, antigo, novo) -> {
                    controller.entidadeParaTela( novo );
                });
    }

    private void propertys() {
        Bindings.bindBidirectional(txtId.textProperty(), controller.idProperty(),(StringConverter) new IntegerStringConverter());
        Bindings.bindBidirectional(txtCor.textProperty(),controller.corProperty());
        Bindings.bindBidirectional(txtTamanho.textProperty(),controller.tamanhoProperty());
        Bindings.bindBidirectional(txtMaterial.textProperty(),controller.materialProperty());
        Bindings.bindBidirectional(txtPreco.textProperty(),controller.precoProperty(),(StringConverter) new DoubleStringConverter());
        Bindings.bindBidirectional(txtObservacao.textProperty(),controller.observacaoProperty());
    }
}