//package sample.business;
//
//        import java.net.URL;
//        import java.util.ResourceBundle;
//
//        import javafx.event.ActionEvent;
//        import javafx.fxml.FXML;
//        import javafx.scene.Node;
//        import javafx.scene.control.Button;
//        import javafx.scene.control.ComboBox;
//        import javafx.scene.control.TextField;
//        import javafx.scene.input.KeyCode;
//        import javafx.scene.input.KeyEvent;
//        import javafx.stage.Stage;
//
//public class Search {
//    static String playerName = "";
//    static String playerNumber = "";
//    static String playerPostition = "";
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private TextField txtSearchByName;
//
//    @FXML
//    private TextField txtSearchByNumber;
//
//
//    @FXML
//    private ComboBox<String> searchChoices;
//
//    String[] choiceBoxList = {"GK", "DF", "MF", "ST"};
//
//    @FXML
//    private Button btnSearchDetail;
//
//    @FXML
//    void searchOnManagement(ActionEvent event) {
//        playerName = txtSearchByName.getText().trim().toLowerCase();
//        playerNumber = txtSearchByNumber.getText().trim().toLowerCase();
//        playerPostition = searchChoices.getSelectionModel().selectedItemProperty().getValue().toLowerCase();
//
//        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        thisStage.close();
//
//    }
//
//    @FXML
//    void initialize() {
//        assert txtSearchByName != null : "fx:id=\"txtSearchByName\" was not injected: check your FXML file 'Search.fxml'.";
//        assert txtSearchByNumber != null : "fx:id=\"txtSearchByNumber\" was not injected: check your FXML file 'Search.fxml'.";
//        assert searchChoices != null : "fx:id=\"txtSearchByPosition\" was not injected: check your FXML file 'Search.fxml'.";
//        assert btnSearchDetail != null : "fx:id=\"btnSearchDetail\" was not injected: check your FXML file 'Search.fxml'.";
//
//        searchChoices.getItems().addAll(choiceBoxList);
//
//    }
//}

package sample.business;

        import java.net.URL;
        import java.util.ResourceBundle;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.Node;
        import javafx.scene.control.Button;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;

public class Search {
    static String playerName = "";
    static String playerNumber = "";
    static String playerPostition = "";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtSearchByName;

    @FXML
    private TextField txtSearchByNumber;

    @FXML
    private ComboBox<String> searchChoices;
    String[] choiceBoxList = {"GK", "DF", "MF", "ST"};

    @FXML
    private Button btnSearchDetail;

    @FXML
    void searchOnManagement(ActionEvent event) {
        playerName = txtSearchByName.getText().trim().toLowerCase();
        playerNumber = txtSearchByNumber.getText().trim().toLowerCase();
        if (searchChoices.getSelectionModel().selectedItemProperty().getValue() != null){
            playerPostition = searchChoices.getSelectionModel().selectedItemProperty().getValue().toLowerCase();
        }

        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        thisStage.close();
    }

    @FXML
    void initialize() {
        assert txtSearchByName != null : "fx:id=\"txtSearchByName\" was not injected: check your FXML file 'Search.fxml'.";
        assert txtSearchByNumber != null : "fx:id=\"txtSearchByNumber\" was not injected: check your FXML file 'Search.fxml'.";
        assert searchChoices != null : "fx:id=\"txtSearchByPosition\" was not injected: check your FXML file 'Search.fxml'.";
        assert btnSearchDetail != null : "fx:id=\"btnSearchDetail\" was not injected: check your FXML file 'Search.fxml'.";

        searchChoices.getItems().addAll(choiceBoxList);
    }
}

