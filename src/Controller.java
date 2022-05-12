import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class Controller implements Initializable {

    private final ObservableList<users> usersList = FXCollections.observableArrayList();

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_signup;

    @FXML
    private PasswordField confirm_password_signup;

    @FXML
    private TextField email_signup;

    @FXML
    private TextField firstname_signup;

    @FXML
    private TextField lastname_signup;

    @FXML
    private AnchorPane login_pane;

    @FXML
    private AnchorPane welcomePane;

    @FXML
    private PasswordField password_signup;

    @FXML
    private AnchorPane signup_pane;

    @FXML
    private Button submit_btn;

    @FXML
    private Button exit;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_username;

    @FXML
    private Label invalid;

    @FXML
    private ListView<users> listView;

    @FXML
    private Label hello;

    @FXML
    private Label sign_warning;

    @FXML
    private TextField username_signup;

    public void getAllEntries() {
        try {
            usersList.setAll(queries.getAllUsers());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    Connection connection = null;
    ResultSet res = null;
    CallableStatement cstmt = null;

    @FXML
    public void Login(ActionEvent event) throws Exception {
        connection = mysqlConnector.ConnectDB();
        String loginCall = "{call check_login(?, ?)}";

        try {
            cstmt = connection.prepareCall(loginCall);
            cstmt.setString(1, txt_username.getText());
            cstmt.setString(2, txt_password.getText());
            res = cstmt.executeQuery();

            if (res.next()) {
                login_pane.setVisible(false);
                welcomePane.setVisible(true);
                hello.setText("Hello " + txt_username.getText());
            } else {
                invalid.setText("Invalid Username or Password");
                displayAlert(AlertType.ERROR, "Wrong Attempt", "Login username or password incorrect!");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    private final mysqlConnector queries = new mysqlConnector();

    public void add_user(ActionEvent event) throws Exception {
        int result = queries.addUser(
                firstname_signup.getText(),
                lastname_signup.getText(),
                username_signup.getText(),
                email_signup.getText(),
                password_signup.getText());

        if (result == 1) {
            displayAlert(AlertType.INFORMATION, "Successful", "You have signed up successfully");
            getAllEntries();
            login_pane.setVisible(false);
            signup_pane.setVisible(false);
            welcomePane.setVisible(true);

            hello.setText("Hello " + txt_username.getText());
        } else {
            sign_warning.setText("Please Fill all the fields Correctly");
            displayAlert(AlertType.ERROR, "Entity not added", "Please provide a correct input");
        }

    }

    private void displayAlert(AlertType type, String title, String Message) {
        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setContentText(Message);
        alert.show();

    }

    public void exitButton(ActionEvent event) throws Exception {
        welcomePane.setVisible(false);
        login_pane.setVisible(true);
    }

    public void signUpPane() {
        signup_pane.setVisible(true);
        login_pane.setVisible(false);
    }

    public void LoginPane() {
        login_pane.setVisible(true);
        signup_pane.setVisible(false);

    }

    @Override
    public void initialize(URL param1, ResourceBundle param2) {
        listView.setItems(usersList);
        getAllEntries();
    }

}
