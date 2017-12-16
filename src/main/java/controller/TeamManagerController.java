package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;

public class TeamManagerController {

    @FXML
    Pane rootPane, membroPane, timePane;

    public void initialize() {
        disablePane(membroPane);
        disablePane(timePane);
    }

    private void disablePane(Pane pane) {
        pane.setDisable(true);
        pane.setVisible(false);
    }

    @FXML
    private void openMembroPane(){
        Stage newStage = new Stage();

        Scene scene = new Scene(membroPane,450,300);
        newStage.setScene(scene);
        newStage.show();
    }
}
