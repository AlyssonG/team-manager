package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import javafx.util.Callback;
import modelo.Time;
import servico.ServiceSingleton;
import servico.TimeAppService;

import java.io.IOException;
import java.util.Set;

public class BuscaTimeController {
    @FXML
    TextField time;

    GenericController lastController;

    @FXML
    TableView tableTime;

    TableColumn nomeColumn, ligaColumn, selecionarColumn;

    private TimeAppService timeService;

    @FXML
    void initialize() {
        nomeColumn = new TableColumn("Nome");
        nomeColumn.setCellValueFactory(new PropertyValueFactory<TimeRow, String>("nome"));

        ligaColumn = new TableColumn("Liga");
        ligaColumn.setCellValueFactory(new PropertyValueFactory<TimeRow, String>("liga"));

        selecionarColumn = new TableColumn("Selecionar");
        selecionarColumn.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn, TableCell<TimeRow, String>> cellFactory = new Callback<TableColumn, TableCell<TimeRow, String>>() {
            @Override
            public TableCell<TimeRow, String> call(TableColumn param) {
                TableCell<TimeRow, String> cell = new TableCell<TimeRow, String>() {
                    final Button btn = new Button("Selecionar");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    TimeRow data = (TimeRow) tableTime.getItems().get(getIndex());
                                    preencheInfoTime(data.getNome());
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        selecionarColumn.setCellFactory(cellFactory);
        tableTime.getColumns().addAll(nomeColumn, ligaColumn, selecionarColumn);
        timeService = ServiceSingleton.getInstance().getTimeService();
        Set<Time> times = timeService.recuperaTimesEMembros();
        for (Time t : times) {
            tableTime.getItems().add(new TimeRow(t.getNome(), t.getLiga()));
        }
    }

    public void setLastController(GenericController controller) {
        this.lastController = controller;
    }

    void preencheInfoTime(String nomeTime) {
        lastController.fillInfo(nomeTime);
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
