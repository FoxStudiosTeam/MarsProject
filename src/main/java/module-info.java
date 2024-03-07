module ru.foxstudios.marsproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens ru.foxstudios.marsproject to javafx.fxml;
    exports ru.foxstudios.marsproject;
    exports ru.foxstudios.marsproject.controllers;
    opens ru.foxstudios.marsproject.controllers to javafx.fxml;
}