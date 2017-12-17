package controller;

import excecao.ExecucaoDeMetodoSemARespectivaPermissaoException;
import excecao.MembroJaExistenteException;
import excecao.TimeNaoEncontradoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Membro;
import modelo.Time;
import servico.MembroAppService;
import servico.ServiceSingleton;
import servico.TimeAppService;
import util.Util;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class MembroController extends GenericController{
    MembroAppService membroService;
    TimeAppService timeService;

    @FXML
    Button btnNovoMembro, btnCadastrarMembro, btnEditarMembro, btnAlterarMembro, btnBuscarMembro;
    @FXML
    TextField nomeMembro, posicaoMembro, dataMembro, timesMembro;

    @FXML
    public void initialize() {
        membroService = ServiceSingleton.getInstance().getMembroService();
        timeService = ServiceSingleton.getInstance().getTimeService();
    }

    @FXML
    private void novoMembro() {
        btnNovoMembro.setDisable(true);
        turnOnFields();
    }

    @FXML
    private void cadastrarMembro() throws TimeNaoEncontradoException, MembroJaExistenteException {
        btnNovoMembro.setDisable(false);
        btnCadastrarMembro.setDisable(true);

        Membro m = new Membro(nomeMembro.getText(),
                posicaoMembro.getText(),
                Util.strToCalendar(dataMembro.getText()),
                getTime(timesMembro.getText()));

        membroService.inclui(m);

        turnOffFields();
        cleanFields();
    }

    private Time getTime(String text) {
        Set<Time> ts = timeService.recuperaTimesEMembros();
        for (Time t : ts) {
            if (t.getNome().equals(text)) {
                return t;
            }
        }
        return null;
    }

    @FXML
    private void editarMembro() {
        btnNovoMembro.setDisable(false);
        btnEditarMembro.setDisable(true);
        turnOnFields();
    }

    @FXML
    private void alterarMembro() {
        btnNovoMembro.setDisable(false);
        turnOffFields();
        cleanFields();
    }

    @FXML
    private void buscarMembro() throws IOException{
        btnNovoMembro.setDisable(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../buscamembro.fxml"));
        Parent parent = loader.load();
        BuscaMembroController controller = loader.getController();
        controller.setController(this);

        Stage stage = new Stage();
        stage.setTitle("Busca Membro");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void buscarTime() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../buscatime.fxml"));
        Parent parent = loader.load();
        BuscaTimeController controller = loader.getController();
        controller.setLastController(this);

        Stage stage = new Stage();
        stage.setTitle("Busca Time");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void checkAllFields() {
        if (nomeMembro.getText().length() == 0 || posicaoMembro.getText().length() == 0 || dataMembro.getText().length() < 9) {
            btnCadastrarMembro.setDisable(true);
        } else {
            btnCadastrarMembro.setDisable(false);
        }
    }

    private ObservableList<String> getTeamsToComboBox() {
        Set<Time> ts = timeService.recuperaTimesEMembros();
        ObservableList<String> timesNome = FXCollections.observableArrayList();
        for (Time t : ts) {
            timesNome.add(t.getNome());
        }
        return timesNome;
    }

    void turnOnFields(){
        nomeMembro.setDisable(false);
        posicaoMembro.setDisable(false);
        dataMembro.setDisable(false);
        timesMembro.setDisable(false);
    }

    void turnOffFields(){
        nomeMembro.setDisable(true);
        posicaoMembro.setDisable(true);
        dataMembro.setDisable(true);
        timesMembro.setDisable(true);
    }

    void cleanFields(){
        nomeMembro.setText("");
        posicaoMembro.setText("");
        dataMembro.setText("");
        timesMembro.setText("");
    }

    public void preenche(String key) throws ExecucaoDeMetodoSemARespectivaPermissaoException {
        List<Membro> membros = membroService.recuperaMembros();
        for(Membro m : membros){
            if(m.getNome().equals(key)){
                nomeMembro.setText(key);
                posicaoMembro.setText(m.getPosicao());
                dataMembro.setText(m.getDataAdimissao().toString());
                timesMembro.setText(m.getTime().getNome());
                return;
            }
        }
    }
    @Override
    public void fillInfo(String key) {
        timesMembro.setText(key);
    }
}
