package sample.typer4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;

public class PopUpController {
    @FXML
    private TextField userName;

    public void submit(ActionEvent ae) throws IOException {
        String name = userName.getText();
        FileWriter myObj = new FileWriter("username.txt");
        myObj.write(name);
        myObj.close();

        Main m = new Main();
        m.changeScene("sample.fxml");
    }

}
