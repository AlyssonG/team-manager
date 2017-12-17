package controller;

import excecao.ExecucaoDeMetodoSemARespectivaPermissaoException;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelo.Membro;
import servico.MembroAppService;
import servico.ServiceSingleton;

import java.util.List;

public class BuscaMembroController {

    MembroController controller;
    MembroAppService membroService;

    @FXML
    TextField nome;

    @FXML
    TableView tableMembro;
    TableColumn nomeColumn, posicaoColumn, admissaoColumn, timeColumn, selecionarColumn;

    @FXML
    void initialize() throws ExecucaoDeMetodoSemARespectivaPermissaoException {
        nomeColumn = new TableColumn("Nome");
        nomeColumn.setCellValueFactory(new PropertyValueFactory<MembroRow, String>("nome"));

        posicaoColumn = new TableColumn("Posicao");
        posicaoColumn.setCellValueFactory(new PropertyValueFactory<MembroRow, String>("posicao"));

        admissaoColumn = new TableColumn("Admissao");
        admissaoColumn.setCellValueFactory(new PropertyValueFactory<MembroRow, String>("admissao"));

        timeColumn = new TableColumn("Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<MembroRow, String>("time"));

        selecionarColumn = new TableColumn("Selecionar");
        selecionarColumn.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn, TableCell<MembroRow, String>> cellFactory = new Callback<TableColumn, TableCell<MembroRow, String>>() {
            @Override
            public TableCell<MembroRow, String> call(TableColumn param) {
                TableCell<MembroRow, String> cell = new TableCell<MembroRow, String>() {
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
                                    MembroRow data = (MembroRow) tableMembro.getItems().get(getIndex());
                                    try {
                                        preencherMembro(data.getNome());
                                    } catch (ExecucaoDeMetodoSemARespectivaPermissaoException e) {
                                        e.printStackTrace();
                                    }
                                    Stage stage = (Stage) btn.getParent().getScene().getWindow();
                                    stage.close();
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
        membroService = ServiceSingleton.getInstance().getMembroService();
        tableMembro.getColumns().addAll(nomeColumn, posicaoColumn, admissaoColumn, timeColumn,selecionarColumn);
        List<Membro> membros = membroService.recuperaMembros();
        for (Membro m : membros) {
            tableMembro.getItems().add(new MembroRow(m.getNome(), m.getPosicao(), m.getDataAdimissao().getTime().toString(), m.getTime().getNome()));
        }

    }

    private void preencherMembro(String nome) throws ExecucaoDeMetodoSemARespectivaPermissaoException {
        controller.preenche(nome);
    }

    public void setController(MembroController controller) {
        this.controller = controller;
    }

    public class MembroRow {
        private final SimpleStringProperty nome, posicao, admissao, time;

        private MembroRow(String nome, String posicao, String admissao, String time) {
            this.nome = new SimpleStringProperty(nome);
            this.posicao = new SimpleStringProperty(posicao);
            this.admissao = new SimpleStringProperty(admissao);
            this.time = new SimpleStringProperty(time);
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

        public String getPosicao() {
            return posicao.get();
        }

        public SimpleStringProperty posicaoProperty() {
            return posicao;
        }

        public void setPosicao(String posicao) {
            this.posicao.set(posicao);
        }

        public String getAdmissao() {
            return admissao.get();
        }

        public SimpleStringProperty admissaoProperty() {
            return admissao;
        }

        public void setAdmissao(String admissao) {
            this.admissao.set(admissao);
        }

        public String getTime() {
            return time.get();
        }

        public SimpleStringProperty timeProperty() {
            return time;
        }

        public void setTime(String time) {
            this.time.set(time);
        }
    }
}
