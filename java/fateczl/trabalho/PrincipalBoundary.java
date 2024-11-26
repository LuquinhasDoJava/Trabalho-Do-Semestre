package fateczl.trabalho;

import fateczl.trabalho.cliente.ClienteBoundary;
import fateczl.trabalho.vestido.VestidoBoundary;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;

import java.util.HashMap;
import java.util.Map;

public class PrincipalBoundary extends Application {

    private Map<String, Tela> telas =
            new HashMap<>();

    @Override
    public void start(Stage stage) {
        BorderPane panePrincipal = new BorderPane();
        telas.put("vestido", new VestidoBoundary());
        telas.put("cliente", new ClienteBoundary());

        MenuBar menuBar = new MenuBar();

        Menu mnuCadastro = new Menu("Cadastro");

        MenuItem mnuVestido = new MenuItem("Vestido");
        MenuItem mnuCliente = new MenuItem("Cliente");

        mnuCadastro.getItems().addAll(mnuVestido, mnuCliente);

        menuBar.getMenus().add( mnuCadastro );

        panePrincipal.setTop( menuBar );

        mnuVestido.setOnAction( e -> panePrincipal.setCenter( telas.get("vestido").render() ) );
        mnuCliente.setOnAction( e -> panePrincipal.setCenter( telas.get("cliente").render() ) );

        Scene scn = new Scene( panePrincipal, 600, 400);
        stage.setScene(scn);
        stage.setTitle("Controle da Loja");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(PrincipalBoundary.class, args);
    }
}
