module org.example {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;

    opens a4.domain to javafx.base;
    opens org.example to javafx.fxml;
    exports org.example;
}