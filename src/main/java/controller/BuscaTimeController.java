package controller;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import modelo.Time;

public class BuscaTimeController {
    @FXML
    TextField time;

    @FXML
    Button seleciona;

    @FXML
    TableView tableTime;

    @FXML
    TableColumn nomeColumn, ligaColumn, selectColumn;

    ObservableList<Time> times;

    @FXML
    void initialize() {
        nomeColumn.setCellFactory(new PropertyValueFactory<Time, String>("nome"));
        ligaColumn.setCellFactory(new PropertyValueFactory<Time, String>("liga"));
        selectColumn.setCellFactory((Callback<TableColumn.CellDataFeatures<Time, Boolean>, ObservableValue<Boolean>>) param ->
                new SimpleBooleanProperty((param.getValue() != null)));
        times = getTimes();
        tableTime.setItems(times);
    }

    private ObservableList<Time> getTimes() {
        return null;
    }

    @FXML
    public void selecionaTime() {
        seleciona.getParent();
    }
}
