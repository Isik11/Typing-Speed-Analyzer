module sample.typer4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.apache.commons.lang3;

    opens sample.typer4 to javafx.fxml;
    exports sample.typer4;
}