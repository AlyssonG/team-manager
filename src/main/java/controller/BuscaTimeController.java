package controller;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import modelo.Time;
import servico.ServiceSingleton;
import servico.TimeAppService;

import java.util.Set;

public class BuscaTimeController {
    @FXML
    TextField time;

    @FXML
    Button seleciona;

    @FXML
    TableView tableTime;

    TableColumn nomeColumn, ligaColumn, selecionarColumn;

    private TimeAppService timeService;

    ObservableList<Time> times;

    @FXML
    void initialize() {
        nomeColumn = new TableColumn("Nome");
        nomeColumn.setCellValueFactory(new PropertyValueFactory<TimeRow, String>("nome"));

        ligaColumn = new TableColumn("Liga");
        ligaColumn.setCellValueFactory(new PropertyValueFactory<TimeRow, String>("liga"));

        selecionarColumn = new TableColumn("Selecionar");


        tableTime.getColumns().addAll(nomeColumn, ligaColumn, selecionarColumn);

        timeService = ServiceSingleton.getInstance().getTimeService();

        Set<Time> times = timeService.recuperaTimesEMembros();
        for (Time t : times) {
            tableTime.getItems().add(new TimeRow(t.getNome(), t.getLiga()));
        }
    }

    @FXML
    public void selecionaTime() {
        seleciona.getParent();
    }

    public class TimeRow {
        private final SimpleStringProperty nome, liga;

        private TimeRow(String nome, String liga) {
            this.nome = new SimpleStringProperty(nome);
            this.liga = new SimpleStringProperty(liga);
        }

        public String getNome() {
            return nome.get();
        }

        public SimpleStringProperty nomeProperty() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome.set(nome);
        }

        public String getLiga() {
            return liga.get();
        }

        public SimpleStringProperty ligaProperty() {
            return liga;
        }

        public void setLiga(String liga) {
            this.liga.set(liga);
        }
    }
}
