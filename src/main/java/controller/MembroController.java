package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MembroController {
    @FXML
    Button btnNovoMembro, btnCadastrarMembro, btnEditarMembro, btnAlterarMembro, btnBuscarMembro;
    @FXML
    TextField nomeMembro, posicaoMembro, dataMembro;

    @FXML
    private void novoMembro() {
        nomeMembro.setEditable(true);
        posicaoMembro.setEditable(true);
        dataMembro.setEditable(true);
    }

    @FXML
    private void cadastrarMembro() {
        nomeMembro.setEditable(false);
        posicaoMembro.setEditable(false);
        dataMembro.setEditable(false);
        //chamar BD com informações
    }

    @FXML
    private void editarMembro() {

    }

    @FXML
    private void alterarMembro() {

    }

    @FXML
    private void buscarMembro() {

    }

    @FXML
    private void checkDataMembro() {
        if (dataMembro.getText().length() < 9) {
            btnCadastrarMembro.setDisable(true);
        } else {
            btnCadastrarMembro.setDisable(false);
        }
    }
}
