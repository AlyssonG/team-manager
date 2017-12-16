import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class TeamManagerController {

    @FXML
    Pane membroPane;

    @FXML
    Button btnNovoMembro, btnCadastrarMembro, btnEditarMembro, btnAlterarMembro, btnBuscarMembro;
    @FXML
    TextField nomeMembro, posicaoMembro, dataMembro;

    @FXML
    Button btnNovoTime, btnCadastrarTime, btnEditarTime, btnAlterarTime, btnBuscarTime;
    @FXML
    TextField nomeTime, ligaTime;

    public void initialize(){
//        membroPane.setVisible(true);
//        membroPane.setDisable(false);
    }

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
    private void checkDataMembro(){
        if(dataMembro.getText().length() < 9){
            btnCadastrarMembro.setDisable(true);
        } else{
            btnCadastrarMembro.setDisable(false);
        }
    }
}
