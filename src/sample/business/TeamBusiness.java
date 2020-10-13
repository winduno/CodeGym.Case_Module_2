package sample.business;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.models.FootballPlayer;

public class TeamBusiness {
    List<FootballPlayer> playersList= new ArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtPlayerName;

    @FXML
    private TextField txtPlayerNationality;

    @FXML
    private TextField txtPlayerBirthday;

    @FXML
    private TextField txtPlayerHeight;

    @FXML
    private TextField txtPlayerWeight;

    @FXML
    private Button btnChooseImage;

    @FXML
    private TextField txtPlayerNumber;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnSearch;

    @FXML
    private TableView<FootballPlayer> tableView;

    @FXML
    private TableColumn<?, ?> posCollum;

    @FXML
    private TableColumn<?, ?> playerNameCollum;

    @FXML
    private TableColumn<?, ?> playerNumberCollum;

    @FXML
    private Button btnExit;
    @FXML
    private ComboBox<String> choiceBox;

    String[] choiceBoxList = {"GK", "DF", "MF", "ST"};

    @FXML
    void addPlayer(ActionEvent event) {

    }

    @FXML
    void chooseImage(ActionEvent event) {

    }

    @FXML
    void editPlayer(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void removePlayer(ActionEvent event) {

    }

    @FXML
    void searchPlayer(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtPlayerName != null : "fx:id=\"txtPlayerName\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert txtPlayerNationality != null : "fx:id=\"txtPlayerNationality\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert txtPlayerBirthday != null : "fx:id=\"txtPlayerBirthday\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert txtPlayerHeight != null : "fx:id=\"txtPlayerHeight\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert txtPlayerWeight != null : "fx:id=\"txtPlayerWeight\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert btnChooseImage != null : "fx:id=\"btnChooseImage\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert txtPlayerNumber != null : "fx:id=\"txtPlayerNumber\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert btnRemove != null : "fx:id=\"btnRemove\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert btnSearch != null : "fx:id=\"btnRemove1\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert posCollum != null : "fx:id=\"posCollum\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert playerNameCollum != null : "fx:id=\"playerNameCollum\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert playerNumberCollum != null : "fx:id=\"playerNumberCollum\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        choiceBox.getItems().addAll(choiceBoxList);
    }
}
