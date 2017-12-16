package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.Time;
import servico.MembroAppService;
import servico.ServiceSingleton;
import servico.TimeAppService;

public class TimeController {
    @FXML
    Button btnNovoTime, btnCadastrarTime, btnEditarTime, btnAlterarTime, btnBuscarTime;
    @FXML
    TextField nomeTime, ligaTime;
    private MembroAppService membroService;
    private TimeAppService timeService;

    @FXML
    public void initialize() {
        membroService = ServiceSingleton.getInstance().getMembroService();
        timeService = ServiceSingleton.getInstance().getTimeService();
        turnOffFields();
    }

    @FXML
    private void novoTime() {
        btnNovoTime.setDisable(true);
        turnOnFields();
    }

    @FXML
    private void cadastrarTime() {
        btnNovoTime.setDisable(false);
        btnCadastrarTime.setDisable(true);
        Time time = new Time(nomeTime.getText(), ligaTime.getText());
        timeService.inclui(time);
    }

    @FXML
    private void editarTime() {
        btnNovoTime.setDisable(false);
        btnEditarTime.setDisable(true);
        turnOnFields();
    }

    @FXML
    private void alterarTime() {
        btnNovoTime.setDisable(false);
        turnOffFields();
        cleanFields();
    }

    @FXML
    private void buscarTime() {
        btnNovoTime.setDisable(false);

    }

    @FXML
    private void checkFields() {
        if (nomeTime.getText().length() == 0 || ligaTime.getText().length() == 0)
            btnCadastrarTime.setDisable(true);
        else {
            btnCadastrarTime.setDisable(false);
        }
    }

    private void turnOnFields() {
        nomeTime.setDisable(false);
        ligaTime.setDisable(false);
    }

    private void turnOffFields() {
        nomeTime.setDisable(true);
        ligaTime.setDisable(true);
    }

    private void cleanFields() {
        nomeTime.setText("");
        ligaTime.setText("");
    }
}
