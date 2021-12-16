/**
 * Sample Skeleton for 'copilot.fxml' Controller Class
 */

package com.example.threefx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NavController implements Initializable {


    @FXML // fx:id="admintab"
    private Tab admintab; // Value injected by FXMLLoader

    @FXML // fx:id="customertab"
    private Tab customertab; // Value injected by FXMLLoader

    @FXML // fx:id="l_user_in"
    private Label l_user_in; // Value injected by FXMLLoader

    @FXML // fx:id="managerstab"
    private Tab managerstab; // Value injected by FXMLLoader

    @FXML // fx:id="memberstab"
    private Tab memberstab; // Value injected by FXMLLoader

    @FXML // fx:id="paid"
    private ToggleGroup paid; // Value injected by FXMLLoader

    @FXML // fx:id="productiontab"
    private Tab productiontab; // Value injected by FXMLLoader

    @FXML // fx:id="reports"
    private Button reports; // Value injected by FXMLLoader

    @FXML // fx:id="stafftab"
    private Tab stafftab; // Value injected by FXMLLoader

    @FXML // fx:id="tableview"
    private TableView<order> tableview; // Value injected by FXMLLoader

    @FXML // fx:id="tableview1"
    private TableView<?> tableview1; // Value injected by FXMLLoader

    @FXML // fx:id="tableview11"
    private TableView<?> tableview11; // Value injected by FXMLLoader

    @FXML // fx:id="tcamount"
    private TableColumn<?, ?> tcamount; // Value injected by FXMLLoader

    @FXML // fx:id="tcamount1"
    private TableColumn<?, ?> tcamount1; // Value injected by FXMLLoader

    @FXML // fx:id="tcamount11"
    private TableColumn<?, ?> tcamount11; // Value injected by FXMLLoader

    @FXML // fx:id="tccreated"
    private TableColumn<?, ?> tccreated; // Value injected by FXMLLoader

    @FXML // fx:id="tccreated1"
    private TableColumn<?, ?> tccreated1; // Value injected by FXMLLoader

    @FXML // fx:id="tccreated11"
    private TableColumn<?, ?> tccreated11; // Value injected by FXMLLoader

    @FXML // fx:id="tcemail"
    private TableColumn<?, ?> tcemail; // Value injected by FXMLLoader

    @FXML // fx:id="tcemail1"
    private TableColumn<?, ?> tcemail1; // Value injected by FXMLLoader

    @FXML // fx:id="tcemail11"
    private TableColumn<?, ?> tcemail11; // Value injected by FXMLLoader

    @FXML // fx:id="tcid"
    private TableColumn<?, ?> tcid; // Value injected by FXMLLoader

    @FXML // fx:id="tcname"
    private TableColumn<?, ?> tcname; // Value injected by FXMLLoader

    @FXML // fx:id="tcname1"
    private TableColumn<?, ?> tcname1; // Value injected by FXMLLoader

    @FXML // fx:id="tcname11"
    private TableColumn<?, ?> tcname11; // Value injected by FXMLLoader

    @FXML // fx:id="tcpaid"
    private TableColumn<?, ?> tcpaid; // Value injected by FXMLLoader

    @FXML // fx:id="tcpaid1"
    private TableColumn<?, ?> tcpaid1; // Value injected by FXMLLoader

    @FXML // fx:id="tcphone"
    private TableColumn<?, ?> tcphone; // Value injected by FXMLLoader

    @FXML // fx:id="tcquanity"
    private TableColumn<?, ?> tcquanity; // Value injected by FXMLLoader

    @FXML // fx:id="tcquanity1"
    private TableColumn<?, ?> tcquanity1; // Value injected by FXMLLoader

    @FXML // fx:id="tcquanity11"
    private TableColumn<?, ?> tcquanity11; // Value injected by FXMLLoader

    @FXML // fx:id="temail"
    private TextField temail; // Value injected by FXMLLoader

    @FXML // fx:id="tname"
    private TextField tname; // Value injected by FXMLLoader

    @FXML // fx:id="tno"
    private RadioButton tno; // Value injected by FXMLLoader

//    @FXML // fx:id="tpay"
//    private RadioButton tpay; // Value injected by FXMLLoader

    @FXML // fx:id="tphone"
    private TextField tphone; // Value injected by FXMLLoader

    @FXML // fx:id="tquantity"
    private TextField tquantity; // Value injected by FXMLLoader

    @FXML // fx:id="ttid"
    private TextField ttid; // Value injected by FXMLLoader
//
//    @FXML // fx:id="tyes"
//    private RadioButton tyes; // Value injected by FXMLLoader
    @FXML
    void allinvoice(ActionEvent event) {

    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void email(ActionEvent event) {

    }

    @FXML
    void home(ActionEvent event) {

    }

    @FXML
    void invoice(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void members(ActionEvent event) {
        memberstab.getTabPane();

    }

    @FXML
    void print(ActionEvent event) {

    }
    String query=null;
    Connection connection=null;
    PreparedStatement preparedstatement=null;
    ResultSet resultSet=null;

    ObservableList<order> customersList = FXCollections.observableArrayList();

    @FXML // fx:id="tyes"
    private ComboBox<?> tyes; // Value injected by FXMLLoader

    @FXML
    void save(ActionEvent event) {
        connection=MysqlConnect.connectDb();
        String sql="insert into users (username,phone,paid,email,type,quantity ) values (?,?,?,?,?,?)";
        try{
            preparedstatement=connection.prepareStatement(sql);
            preparedstatement.setString(1,tname.getText());
            preparedstatement.setString(2, String.valueOf(Integer.parseInt(tphone.getText())));
            preparedstatement.setString(3,temail.getText());
            preparedstatement.setString(4, String.valueOf(Integer.parseInt(tquantity.getText())));
            preparedstatement.setString(5,tyes.getValue().toString());
            preparedstatement.execute();

            JOptionPane.showMessageDialog(null,"saved");

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,event);
        }

    }

    @FXML
    void update(ActionEvent event) {

    }

    public void check(String type,String name) {
        boolean access = false;
        try {
            if (type.equals("Admin")) {
                admintab.disableProperty().setValue(access);
                l_user_in.setText("Welcome : "+name);
//                refreshtable();
            } else if (type.equals("Staff")) {
                stafftab.disableProperty().setValue(access);
//                 refreshtable();
//                stafftab.getContent();
                l_user_in.setText("Welcome : "+name);
            } else if (type.equals("order")) {
                customertab.disableProperty().setValue(access);
                l_user_in.setText("Welcome : "+name);
            } else if (type.equals("Manager")) {
                managerstab.disableProperty().setValue(access);
                l_user_in.setText("Welcome : "+name);
            } else if (type.equals("production manager")) {
                productiontab.disableProperty().setValue(access);
                l_user_in.setText("Welcome : "+name);
            } else {
                JOptionPane.showMessageDialog(null, "No access for this user");
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in handling request");
        }
    }


    
    @FXML
    void refreshtable() {
        try {
            customersList.clear();
            query="Select *from Customer";
            preparedstatement=connection.prepareStatement(query);
            resultSet=preparedstatement.executeQuery();


            while(resultSet.next()){
                customersList.add(new order(
//                      resultSet.getInt("customer_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getInt("quantity"),
                        resultSet.getBoolean("paid"),
                        resultSet.getInt("amount"),
                        resultSet.getInt("phone"),
                        resultSet.getDate("created_at")
                 ));
                tableview.setItems(customersList);
//                loadDate();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    loadDate();
    }
        private void loadDate(){

            connection= MysqlConnect.connectDb();
            refreshtable();
//associating data with columns
//            tcid.setCellValueFactory(new PropertyValueFactory<>("id"));
            tcname.setCellValueFactory(new PropertyValueFactory<>("name"));
            tcemail.setCellValueFactory(new PropertyValueFactory<>("email"));
           tcphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            tcquanity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            tcamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            tccreated.setCellValueFactory(new PropertyValueFactory<>("created_at"));

}
    @FXML // fx:id="pane_report"
    private AnchorPane pane_report; // Value injected by FXMLLoader

    @FXML // fx:id="pane_admin"
    private AnchorPane pane_admin; // Value injected by FXMLLoader

    public void getReport(ActionEvent actionEvent) {
        pane_report.setVisible(true);
        pane_admin.setVisible(false);
    }
}

