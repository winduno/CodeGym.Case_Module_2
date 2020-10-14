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
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.models.FootballPlayer;

public class TeamBusiness {
    static List<FootballPlayer> playersList= new ArrayList();
    static List<FootballPlayer> currentList = new ArrayList<>();
    String imagePath = " ";
    int clickedIndex;
    String anchorNumber = "";

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
        for (FootballPlayer player : currentList) {
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
                boolean exist = player.getNumber().equals(txtPlayerNumber.getText());
                boolean blank = txtPlayerName.getText().equals("") || txtPlayerNationality.getText().equals("") ||
                        txtPlayerBirthday.getText().equals("") || txtPlayerHeight.getText().equals("") || txtPlayerWeight.getText().equals("") ||
                        txtPlayerNumber.getText().equals("") || choiceBox.getSelectionModel().isEmpty();
                if (exist || blank){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("System Information");
                    alert.setContentText("This player is exist or something is blank! Please try again");
                    alert.show();
                    return;
                }
            }
            playersList.add(footballPlayer);
            currentList = playersList;
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
        choiceBox.setValue(null);

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

    boolean checkPlayer(){

        for (FootballPlayer player : playersList) {
            boolean exist = player.getNumber().equals(txtPlayerNumber.getText());
            boolean blank = txtPlayerName.getText().equals("") || txtPlayerNationality.getText().equals("") ||
                    txtPlayerBirthday.getText().equals("") || txtPlayerHeight.getText().equals("") || txtPlayerWeight.getText().equals("") ||
                    txtPlayerNumber.getText().equals("") || choiceBox.getSelectionModel().selectedItemProperty().getValue().equals("");
            if (exist || blank){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("System Information");
                alert.setContentText("This player is exist or something is blank! Please try again");
                alert.show();
                return false;
            }
        }
        return true;
    }

    @FXML
    void editPlayer(ActionEvent event) {
        if (checkUser()){
                for (FootballPlayer player : playersList) {
                    boolean exist = player.getNumber().equals(txtPlayerNumber.getText()) && !player.getNumber().equals(anchorNumber);
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
                playersList.get(clickedIndex).setName(txtPlayerName.getText());
                playersList.get(clickedIndex).setNationality(txtPlayerNationality.getText());
                playersList.get(clickedIndex).setDateOfBirth(txtPlayerBirthday.getText());
                playersList.get(clickedIndex).setHeight(txtPlayerHeight.getText());
                playersList.get(clickedIndex).setWeight(txtPlayerWeight.getText());
                playersList.get(clickedIndex).setNumber(txtPlayerNumber.getText());
                playersList.get(clickedIndex).setImagePath(imagePath);
                playersList.get(clickedIndex).setPosition(choiceBox.getSelectionModel().selectedItemProperty().getValue());
                currentList = playersList;
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
    void onClickItem(MouseEvent event) {
        clickedIndex = -1;
        clickedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (clickedIndex != -1){
            txtPlayerNumber.setText(currentList.get(clickedIndex).getNumber());
            txtPlayerWeight.setText(currentList.get(clickedIndex).getWeight());
            txtPlayerHeight.setText(currentList.get(clickedIndex).getHeight());
            txtPlayerBirthday.setText(currentList.get(clickedIndex).getDateOfBirth());
            txtPlayerNationality.setText(currentList.get(clickedIndex).getNationality());
            txtPlayerName.setText(currentList.get(clickedIndex).getName());
            if (!currentList.get(clickedIndex).getImagePath().equals(" ")){
                imagePath = currentList.get(clickedIndex).getImagePath();
                imgPlayerImage.setImage(new Image(imagePath));
            }
            choiceBox.getSelectionModel().select(currentList.get(clickedIndex).getPosition());
            anchorNumber = txtPlayerNumber.getText();
        }
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, " ", ButtonType.YES, ButtonType.NO);
        alert.setTitle("CONFIRMATION!!");
        alert.setHeaderText("You are returning LOGIN page...");
        alert.setContentText("Continue??");
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES){
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
            Scene management = new Scene(root);
            primaryStage.setScene(management);
            primaryStage.setX(primaryStage.getX() + 200);
            primaryStage.setY(primaryStage.getY() + 100);
            primaryStage.show();
        }
    }

    @FXML
    void removePlayer(ActionEvent event) {
        if (checkUser()){
            FootballPlayer removePlayer = tableView.getSelectionModel().getSelectedItem();
            for (int i = 0; i < playersList.size(); i++) {
                if (removePlayer.getNumber().equals(playersList.get(i).getNumber())){
                    playersList.remove(playersList.get(i));
                }
            }
            currentList = playersList;
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
    void openSearchWindow(ActionEvent event) throws IOException {
        Stage secondaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/Search.fxml"));
        Scene search = new Scene(root);
        secondaryStage.setTitle("Search");
        secondaryStage.setScene(search);
        secondaryStage.setX(((Node) event.getSource()).getScene().getWindow().getX() + 300);
        secondaryStage.setY(((Node) event.getSource()).getScene().getWindow().getY() + 200);
        secondaryStage.initModality(Modality.APPLICATION_MODAL);
        secondaryStage.initOwner(((Node) event.getSource()).getScene().getWindow());
        secondaryStage.setResizable(false);
        secondaryStage.showAndWait();
        searchPlayer(Search.playerName, Search.playerNumber, Search.playerPostition);
    }

    public void searchPlayer(String playerName, String playerNumber, String playerPosition){
        ArrayList<FootballPlayer> searchResult = new ArrayList<>();
        if (!playerNumber.equals("")){
            for (FootballPlayer player : playersList) {
                if (player.getNumber().equals(playerNumber)) {
                    searchResult.add(player);
                    break;
                }
            }
        }
        else if (!playerName.equals("")){
            if (!playerPosition.equals("")){
                for (FootballPlayer player : playersList) {
                    if (player.getName().toLowerCase().contains(playerName) && player.getPosition().toLowerCase().equals(playerPosition)) {
                        searchResult.add(player);
                    }
                }
            }
            else{
                for (FootballPlayer player : playersList) {
                    if (player.getName().toLowerCase().contains(playerName)) {
                        searchResult.add(player);
                    }
                }
            }
        }
        else {
            if (!playerName.equals("")){
                for (FootballPlayer player : playersList) {
                    if (player.getName().equals(playerName) && player.getPosition().toLowerCase().equals(playerPosition)) {
                        searchResult.add(player);
                    }
                }
            }
            else{
                for (FootballPlayer player : playersList) {
                    if (player.getPosition().toLowerCase().equals(playerPosition)) {
                        searchResult.add(player);
                    }
                }
            }
        }
        if (searchResult.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong");
            alert.setContentText("Player not found!!");
            alert.show();
        }
        else {
            currentList = searchResult;
            reloadTable();
//            searchResult.clear();
        }
    }

    @FXML
    void loadPlayerList(ActionEvent event) {
        currentList = playersList;
        reloadTable();
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
        currentList = playersList;
        tableView.getItems().addAll(currentList);
        choiceBox.getItems().addAll(choiceBoxList);
    }

    boolean checkUser(){
        if (Login.user.getRole().equals("admin")) return true;
        else return false;
    }
}