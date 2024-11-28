package fateczl.trabalho.aluguel;

import fateczl.trabalho.Tela;

import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.sql.SQLException;
import java.time.LocalDate;

public class AluguelBondary  implements Tela {

    private TextField[] txt;
    private Label[] lbs;
    private String[] info = {"Cliente ID","Aluguel ID", "Data de Inicio","Data de Fim", "Preço", "Situação"};

    private TableView<Aluguel> tableView = new TableView<>();

    private AluguelController controller = new AluguelController();




    public Pane render() {
        BorderPane bp = new BorderPane();
        Label titulo = new Label("Controler de Aluguel");
        titulo.setFont(new Font(25));
        bp.setTop(titulo);
        GridPane gridPane = new GridPane();
        bp.setCenter(gridPane);
        txt = new TextField[info.length];
        lbs = new Label[info.length];
        int x = 0;
        while(x <info.length){
            gridPane.add(lbs[x] = new Label(info[x]),0,x);
            gridPane.add(txt[x] = new TextField(),1,x);
            x++;
        }
        Button btnGravar = new Button("Gravar Aluguel");
        btnGravar.setOnAction(e ->{
            try {
                controller.criar();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        gridPane.add(btnGravar,0,x);

        Button btnPesqusiarTodos = new Button("Pesquisar Todos");
        btnPesqusiarTodos.setOnAction(e -> {
            try {
                controller.pesquisarTotods();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        gridPane.add(btnPesqusiarTodos,1,x);
        x++;

        Button btnPesquisarPorCliente_id = new Button("Procurar por \n Id do Cliente");
        btnPesquisarPorCliente_id.setOnAction(e ->{
            try {
                controller.pesquisarPorCliente_id();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        gridPane.add(btnPesquisarPorCliente_id,0,x);

        Button btnAtualizar = new Button("Atualziar Aluguel");
        btnAtualizar.setOnAction(e ->{
            try {
                controller.atualizar();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        gridPane.add(btnAtualizar,1,x);
        x++;

        property();
        generateColumns();
        bp.setBottom(tableView);
        return bp;
    }

    private void generateColumns() {
        TableColumn<Aluguel, Integer> col1 = new TableColumn<>("Cliente ID");
        col1.setCellValueFactory(new PropertyValueFactory<Aluguel, Integer>("cliente_id"));

        TableColumn<Aluguel, Integer> col2 = new TableColumn<>("Vestido ID");
        col2.setCellValueFactory(new PropertyValueFactory<Aluguel, Integer>("vestido_id"));

        TableColumn<Aluguel, LocalDate> col3 = new TableColumn<>("Data Inicio");
        col3.setCellValueFactory(new PropertyValueFactory<Aluguel, LocalDate>("dataInicio"));

        TableColumn<Aluguel, LocalDate> col4 = new TableColumn<>("Data Fim");
        col4.setCellValueFactory(new PropertyValueFactory<Aluguel, LocalDate>("dataFim"));

        TableColumn<Aluguel, Double> col5 = new TableColumn<>("Preço");
        col5.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));

        TableColumn<Aluguel, Boolean> col6 = new TableColumn<>("Status");
        col6.setCellValueFactory(new PropertyValueFactory<Aluguel, Boolean>("status"));

        Callback<TableColumn<Aluguel, Void>, TableCell<Aluguel, Void>> callback =
                new  Callback<>() {
                    @Override
                    public TableCell<Aluguel, Void> call(TableColumn<Aluguel, Void> param) {
                        TableCell<Aluguel, Void> tc = new TableCell<>() {
                            final Button btnExcluir = new Button("Apagar");
                            {
                                btnExcluir.setOnAction(
                                        e -> {
                                            Aluguel v = tableView.getItems().get( getIndex() );
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
        TableColumn<Aluguel, Void> col7 = new TableColumn<>("Ações");
        col7.setCellFactory( callback );

        tableView.getColumns().addAll(col1,col2,col3,col4,col5,col6,col7);
        tableView.setItems(controller.getLista());

        tableView.getSelectionModel().selectedItemProperty().addListener( (obs, antigo, novo) -> {
            try {
                controller.entidadeParaTela(novo);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void property() {
        Bindings.bindBidirectional(txt[0].textProperty(),controller.cliente_idProperty(),(StringConverter) new IntegerStringConverter());
        Bindings.bindBidirectional(txt[1].textProperty(),controller.vestido_idProperty(),(StringConverter) new IntegerStringConverter());
        Bindings.bindBidirectional(txt[2].textProperty(),controller.dataInicioProperty(),(StringConverter) new LocalDateStringConverter());
        Bindings.bindBidirectional(txt[3].textProperty(),controller.dataFimProperty(),(StringConverter) new LocalDateStringConverter());
        Bindings.bindBidirectional(txt[4].textProperty(),controller.precoTotalProperty(),(StringConverter) new DoubleStringConverter());
        Bindings.createBooleanBinding(() -> txt[5].getText().equals(controller.statusProperty().getValue() ? "Ativo" : "Inativo"),
                txt[5].textProperty(), controller.statusProperty());

    }
}
