module com.example.realitytips {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.realitytips to javafx.fxml;
    exports com.example.realitytips;
}