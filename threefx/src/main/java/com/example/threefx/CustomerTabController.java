/**
 * Sample Skeleton for 'customertabpane.fxml' Controller Class
 */

package com.example.threefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class CustomerTabController {

    @FXML // fx:id="amount"
    private TextField amount; // Value injected by FXMLLoader

    @FXML // fx:id="btnProfile"
    private Button btnProfile; // Value injected by FXMLLoader

    @FXML // fx:id="customertab"
    private SplitPane customertab; // Value injected by FXMLLoader

    @FXML // fx:id="imaget"
    private ImageView imaget; // Value injected by FXMLLoader

    @FXML // fx:id="phone"
    private TextField phone; // Value injected by FXMLLoader

    @FXML // fx:id="quantity"
    private TextField quantity; // Value injected by FXMLLoader

    @FXML // fx:id="tpay"
    private RadioButton tpay; // Value injected by FXMLLoader

    @FXML // fx:id="username"
    private TextField username; // Value injected by FXMLLoader

    @FXML
    void profile(ActionEvent event) {

    }

    @FXML
    void submit(ActionEvent event) {

    }

}
