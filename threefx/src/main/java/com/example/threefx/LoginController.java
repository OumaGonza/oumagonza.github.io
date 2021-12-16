/**
 * Sample Skeleton for 'login4.fxml' Controller Class
 */

package com.example.threefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML // fx:id="btn_signup"
    private Button btn_signup; // Value injected by FXMLLoader

    @FXML // fx:id="btnlogin"
    private Button btnlogin; // Value injected by FXMLLoader

    @FXML // fx:id="l_btn_login"
    private Button l_btn_login; // Value injected by FXMLLoader

    @FXML // fx:id="l_choice"
    private ImageView l_choice; // Value injected by FXMLLoader

    @FXML // fx:id="l_error"
    private Text l_error; // Value injected by FXMLLoader

    @FXML // fx:id="l_password"
    private PasswordField l_password; // Value injected by FXMLLoader

    @FXML // fx:id="l_type"
    private ComboBox l_type; // Value injected by FXMLLoader

    @FXML // fx:id="pane_login"
    private Pane pane_login; // Value injected by FXMLLoader

    @FXML // fx:id="pane_signup"
    private AnchorPane pane_signup; // Value injected by FXMLLoader

    @FXML // fx:id="s_signUp"
    private Button s_signUp; // Value injected by FXMLLoader

    @FXML // fx:id="si_choice"
    private ComboBox si_choice; // Value injected by FXMLLoader

    @FXML // fx:id="si_email"
    private TextField si_email; // Value injected by FXMLLoader

    @FXML // fx:id="si_errortxt"
    private Text si_errortxt; // Value injected by FXMLLoader

    @FXML // fx:id="si_password"
    private PasswordField si_password; // Value injected by FXMLLoader

    @FXML // fx:id="si_username"
    private TextField si_username; // Value injected by FXMLLoader

    @FXML // fx:id="l_username"
    private TextField l_username; // Value injected by FXMLLoader

    @FXML // fx:id="l_forgot_password"
    private Hyperlink l_forgot_password; // Value injected by FXMLLoader

    @FXML
    void forgotpassword(ActionEvent event) {

    }
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
   private void login(ActionEvent event) throws Exception {
    conn=MysqlConnect.connectDb();
    String sql="Select * from users where username=? and password=? and type=? ";
    try{
        pst=conn.prepareStatement(sql);
        pst.setString(1,l_username.getText());
        pst.setString(2,l_password.getText());
        pst.setString(3,l_type.getValue().toString());
        rs= pst.executeQuery();
        if(rs.next()){
            JOptionPane.showMessageDialog(null,"username and password is correct");
//            if(l_type.getValue().toString()=="Admin"){
//
//            } try to login to admin only or the different option
//            l_btn_login.getScene().getWindow().hide();
//            Parent root= FXMLLoader.load(getClass().getResource("Copilot.fxml"));
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Copilot.fxml"));
            root= loader.load();
            NavController nav=loader.getController();
            nav.check(l_type.getValue().toString(),l_username.getText());

            stage=(Stage)((Node) event.getSource()).getScene().getWindow();


            scene=new Scene(root);
            stage.setScene(scene);//
            stage.show();
        }else{
            JOptionPane.showMessageDialog(null,"Invalid username or password ");
        }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,"unable to process request");
    }

    }

    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;


    public void loginPaneShow(){
        pane_login.setVisible(true);
        pane_signup.setVisible(false);
    }

    public void signUpPaneShow(){
        pane_login.setVisible(false);
        pane_signup.setVisible(true);
    }
    @FXML
    public void add_user(ActionEvent event){
    conn=MysqlConnect.connectDb();
    String sql="insert into users (username,password,email,type ) values (?,?,?,?)";
    try{
        pst=conn.prepareStatement(sql);
        pst.setString(1,si_username.getText());
        pst.setString(2,si_password.getText());
        pst.setString(3,si_email.getText());
        pst.setString(4,si_choice.getValue().toString());
        pst.execute();

        JOptionPane.showMessageDialog(null,"saved");

    }catch(Exception e){
        JOptionPane.showMessageDialog(null,event);
    }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        l_type.getItems().addAll("Admin","order","Staff","Manager","Staff","Production_Manager");
        si_choice.getItems().addAll("order");


    }
}
