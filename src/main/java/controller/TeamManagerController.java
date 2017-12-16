package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class TeamManagerController {

    @FXML
    Pane rootPane;

    @FXML
    private void openMembroPane() throws IOException {
        createPopUp("membro");
    }

    @FXML
    private void openTimePane() throws IOException {
        createPopUp("time");
    }

    void createPopUp(String name) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../" + name + ".fxml"));
        Stage stage = new Stage();
        stage.setTitle(name);
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
