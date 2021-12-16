/**
 * Sample Skeleton for 'adminPaneTab.fxml' Controller Class
 */

package com.example.threefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class AdminTabController {

    @FXML // fx:id="admintab"
    private SplitPane admintab; // Value injected by FXMLLoader

    @FXML // fx:id="btnManagers"
    private Button btnManagers; // Value injected by FXMLLoader

    @FXML // fx:id="btnProfile"
    private Button btnProfile; // Value injected by FXMLLoader

    @FXML // fx:id="btnStock"
    private Button btnStock; // Value injected by FXMLLoader

    @FXML // fx:id="btnTotalSales"
    private Button btnTotalSales; // Value injected by FXMLLoader

    @FXML // fx:id="imaget"
    private ImageView imaget; // Value injected by FXMLLoader

    @FXML // fx:id="reports"
    private Button reports; // Value injected by FXMLLoader

    @FXML // fx:id="tableview1"
    private TableView<?> tableview1; // Value injected by FXMLLoader

    @FXML // fx:id="tcamount1"
    private TableColumn<?, ?> tcamount1; // Value injected by FXMLLoader

    @FXML // fx:id="tccreated1"
    private TableColumn<?, ?> tccreated1; // Value injected by FXMLLoader

    @FXML // fx:id="tcemail1"
    private TableColumn<?, ?> tcemail1; // Value injected by FXMLLoader

    @FXML // fx:id="tcname1"
    private TableColumn<?, ?> tcname1; // Value injected by FXMLLoader

    @FXML // fx:id="tcpaid1"
    private TableColumn<?, ?> tcpaid1; // Value injected by FXMLLoader

    @FXML // fx:id="tcquanity1"
    private TableColumn<?, ?> tcquanity1; // Value injected by FXMLLoader

    @FXML
    void getReport(ActionEvent event) {

    }

    @FXML
    void managers(ActionEvent event) {

    }

    @FXML
    void profile(ActionEvent event) {

    }

    @FXML
    void sales(ActionEvent event) {

    }

    @FXML
    void stock(ActionEvent event) {

    }

}
