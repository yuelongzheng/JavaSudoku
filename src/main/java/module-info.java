module com.example.javasudoku {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.fasterxml.jackson.databind;
    requires org.jsoup;
    requires java.datatransfer;
    requires blazingchain;
    requires java.desktop;

    opens com.example.javasudoku to javafx.fxml;
    exports com.example.javasudoku;
}