module fateczl.crud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.incubator.vector;
    requires java.sql;


    opens fateczl.trabalho.cliente to javafx.base;
    exports fateczl.trabalho.cliente;

    exports fateczl.trabalho.vestido;
    opens fateczl.trabalho.vestido to javafx.fxml;

    exports fateczl.trabalho;
    opens fateczl.trabalho to javafx.fxml;

    exports fateczl.trabalho.aluguel;
    opens fateczl.trabalho.aluguel to javafx.base;



}