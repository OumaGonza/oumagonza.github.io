module com.example.threefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.threefx to javafx.fxml;
    exports com.example.threefx;
}