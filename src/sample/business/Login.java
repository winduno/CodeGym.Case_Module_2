package sample.business;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Login {
    static List<User> users = new ArrayList<>();
//    public static User user = new User();

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    public void Submit(ActionEvent event) throws IOException {
        users = IOFile.readUserFromFile("users.dat");
//        creatFile();
        String userName = txtUsername.getText();
        String password = txtPassword.getText();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(userName) && users.get(i).getPassword().equals(password)){
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("../view/TeamBusiness.fxml"));
                Scene management = new Scene(root);
                primaryStage.setScene(management);
                primaryStage.show();
//                user = users.get(i);
                break;
            }
            if (i == users.size() - 1){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Wrong");
                alert.setHeaderText("Wrong Username or Password");
                alert.setContentText("Please re-type");
                alert.show();
            }
        }
    }
    public void creatFile(){
        users.add(new User("winduno", "123", "admin"));
        users.add(new User("winduno1", "123", "user"));
        users.add(new User("duong", "123", "user"));
        users.add(new User("duong1", "123", "user"));
        users.add(new User("minhnguyen", "123", "user"));
        IOFile.writeUserToFile(users, "users.dat");
    }
}