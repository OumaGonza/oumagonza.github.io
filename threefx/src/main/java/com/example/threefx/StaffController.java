/**
 * Sample Skeleton for 'staffpanetab.fxml' Controller Class
 */

package com.example.threefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class StaffController implements Initializable {

    @FXML // fx:id="tableview"
    private TableView<?> tableview; // Value injected by FXMLLoader

    @FXML // fx:id="tcamount"
    private TableColumn<?, ?> tcamount; // Value injected by FXMLLoader

    @FXML // fx:id="tccreated"
    private TableColumn<?, ?> tccreated; // Value injected by FXMLLoader

    @FXML // fx:id="tcemail"
    private TableColumn<?, ?> tcemail; // Value injected by FXMLLoader

    @FXML // fx:id="tcname"
    private TableColumn<?, ?> tcname; // Value injected by FXMLLoader

    @FXML // fx:id="tcpaid"
    private TableColumn<?, ?> tcpaid; // Value injected by FXMLLoader

    @FXML // fx:id="tcphone"
    private TableColumn<?, ?> tcphone; // Value injected by FXMLLoader

    @FXML // fx:id="tcquanity"
    private TableColumn<?, ?> tcquanity; // Value injected by FXMLLoader

    @FXML // fx:id="temail"
    private TextField temail; // Value injected by FXMLLoader

    @FXML // fx:id="tname"
    private TextField tname; // Value injected by FXMLLoader

    @FXML // fx:id="tphone"
    private TextField tphone; // Value injected by FXMLLoader

    @FXML // fx:id="tquantity"
    private TextField tquantity; // Value injected by FXMLLoader

    @FXML // fx:id="ttid"
    private TextField ttid; // Value injected by FXMLLoader

    @FXML // fx:id="tyes"
    private ComboBox<?> tyes; // Value injected by FXMLLoader

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void email(ActionEvent event) {

    }

    @FXML
    void invoice(ActionEvent event) {

    }

    @FXML
    void print(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();

    }

    private void loadData() {

    }
}
