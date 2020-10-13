package sample.business;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.models.FootballPlayer;

public class TeamBusiness {
    List<FootballPlayer> playersList= new ArrayList();
    String imagePath = " ";
    int clickedIndex;

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
    private ComboBox<String> choiceBox;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnSearch;

    @FXML
    private ImageView imgPlayerImage;

    @FXML
    private TableView<FootballPlayer> tableView;

    @FXML
    private TableColumn<FootballPlayer, String> posColumn;

    @FXML
    private TableColumn<FootballPlayer, String> playerNameColumn;

    @FXML
    private TableColumn<FootballPlayer, String> playerNumberColumn;

    @FXML
    private Button btnExit;

    String[] choiceBoxList = {"GK", "DF", "MF", "ST"};

    public void reloadTable(){
        tableView.getItems().clear();
        for (FootballPlayer player : playersList) {
            tableView.getItems().add(player);
        }
    }

    @FXML
    void addPlayer(ActionEvent event) {
        if (checkUser()){
            FootballPlayer footballPlayer = new FootballPlayer();
            footballPlayer.setName(txtPlayerName.getText());
            footballPlayer.setNationality(txtPlayerNationality.getText());
            footballPlayer.setDateOfBirth(txtPlayerBirthday.getText());
            footballPlayer.setHeight(txtPlayerHeight.getText());
            footballPlayer.setWeight(txtPlayerWeight.getText());
            footballPlayer.setNumber(txtPlayerNumber.getText());
            footballPlayer.setPosition(choiceBox.getSelectionModel().selectedItemProperty().getValue());
            footballPlayer.setImagePath(imagePath);
            for (FootballPlayer player : playersList) {
                boolean exist = player.getName().equals(txtPlayerName.getText()) || player.getNumber().equals(txtPlayerNumber.getText());
                boolean blank = txtPlayerName.getText().equals("") || txtPlayerNationality.getText().equals("") ||
                        txtPlayerBirthday.getText().equals("") || txtPlayerHeight.getText().equals("") || txtPlayerWeight.getText().equals("") ||
                        txtPlayerNumber.getText().equals("") || choiceBox.getSelectionModel().selectedItemProperty().getValue().equals("");
                if (exist || blank){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("System Information");
                    alert.setContentText("This player is exist or something is blank! Please try again");
                    alert.show();
                    return;
                }
            }
            playersList.add(footballPlayer);
            reloadTable();
            IOFile.writePlayerToFile(playersList, "Players.dat");
            clearField();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong");
            alert.setContentText("You do not have right to do this");
            alert.show();
        }
    }

    public void clearField(){
        txtPlayerNumber.clear();
        txtPlayerWeight.clear();
        txtPlayerHeight.clear();
        txtPlayerBirthday.clear();
        txtPlayerNationality.clear();
        txtPlayerName.clear();

        File file = new File("src/sample/data/default.png");
        imagePath = file.toURI().toString();
        Image image = new Image(imagePath);
        imgPlayerImage.setImage(image);
    }

    @FXML
    void chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("D:/Codegym/CodeGym.Case_Module_2/src/sample/data"));

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            imagePath = file.toURI().toString();
            Image image = new Image(imagePath);
            imgPlayerImage.setImage(image);
        }
    }

    @FXML
    void editPlayer(ActionEvent event) {
        if (checkUser()){
            if (clickedIndex != -1){
                playersList.get(clickedIndex).setName(txtPlayerName.getText());
                playersList.get(clickedIndex).setNationality(txtPlayerNationality.getText());
                playersList.get(clickedIndex).setDateOfBirth(txtPlayerBirthday.getText());
                playersList.get(clickedIndex).setHeight(txtPlayerHeight.getText());
                playersList.get(clickedIndex).setWeight(txtPlayerWeight.getText());
                playersList.get(clickedIndex).setNumber(txtPlayerNumber.getText());
                playersList.get(clickedIndex).setImagePath(imagePath);
                playersList.get(clickedIndex).setPosition(choiceBox.getSelectionModel().selectedItemProperty().getValue());
                reloadTable();
                IOFile.writePlayerToFile(playersList, "Players.dat");
                clearField();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong");
            alert.setContentText("You do not have right to do this");
            alert.show();
        }
    }

    @FXML
    void onClickItem(MouseEvent event) {
        clickedIndex = -1;
        clickedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (clickedIndex != -1){
            txtPlayerNumber.setText(playersList.get(clickedIndex).getNumber());
            txtPlayerWeight.setText(playersList.get(clickedIndex).getWeight());
            txtPlayerHeight.setText(playersList.get(clickedIndex).getHeight());
            txtPlayerBirthday.setText(playersList.get(clickedIndex).getDateOfBirth());
            txtPlayerNationality.setText(playersList.get(clickedIndex).getNationality());
            txtPlayerName.setText(playersList.get(clickedIndex).getName());
            if (!playersList.get(clickedIndex).getImagePath().equals(" ")){
                imagePath = playersList.get(clickedIndex).getImagePath();
                imgPlayerImage.setImage(new Image(imagePath));
            }
            choiceBox.getSelectionModel().select(playersList.get(clickedIndex).getPosition());
        }
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, " ", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("You are returning LOGIN page...");
        alert.setContentText("Continue??");
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES){
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
            Scene management = new Scene(root);
            primaryStage.setScene(management);
            primaryStage.show();
        }
    }

    @FXML
    void removePlayer(ActionEvent event) {
        if (checkUser()){
            FootballPlayer player = tableView.getSelectionModel().getSelectedItem();
            playersList.remove(player);
            reloadTable();
            IOFile.writePlayerToFile(playersList, "Players.dat");
            clearField();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong");
            alert.setContentText("You do not have right to do this");
            alert.show();
        }
    }

    @FXML
    void searchPlayer(ActionEvent event) {

    }

    @FXML
    void initialize() {
//        assert txtPlayerName != null : "fx:id=\"txtPlayerName\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
//        assert txtPlayerNationality != null : "fx:id=\"txtPlayerNationality\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
//        assert txtPlayerBirthday != null : "fx:id=\"txtPlayerBirthday\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
//        assert txtPlayerHeight != null : "fx:id=\"txtPlayerHeight\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
//        assert txtPlayerWeight != null : "fx:id=\"txtPlayerWeight\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
//        assert btnChooseImage != null : "fx:id=\"btnChooseImage\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
//        assert txtPlayerNumber != null : "fx:id=\"txtPlayerNumber\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
//        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
//        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
//        assert btnRemove != null : "fx:id=\"btnRemove\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
//        assert btnSearch != null : "fx:id=\"btnRemove1\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
//        assert posColumn != null : "fx:id=\"posCollum\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
//        assert playerNameColumn != null : "fx:id=\"playerNameCollum\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
//        assert playerNumberColumn != null : "fx:id=\"playerNumberCollum\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
//        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'TeamBusiness.fxml'.";

        try{
            playersList = IOFile.readPlayerFromFile("Players.dat");
        }   catch (Exception e){
            e.printStackTrace();
        }
        posColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        playerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        playerNumberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        tableView.getItems().addAll(playersList);
        choiceBox.getItems().addAll(choiceBoxList);
    }

    boolean checkUser(){
        if (Login.user.getRole().equals("admin")) return true;
        else return false;
    }
}