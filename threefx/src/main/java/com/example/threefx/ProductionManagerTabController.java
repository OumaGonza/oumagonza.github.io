/**
 * Sample Skeleton for 'productionManagerTab.fxml' Controller Class
 */

package com.example.threefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ProductionManagerTabController {

    @FXML // fx:id="btnStock"
    private Button btnStock; // Value injected by FXMLLoader

    @FXML // fx:id="btnprofile"
    private Button btnprofile; // Value injected by FXMLLoader

    @FXML // fx:id="productiontab"
    private SplitPane productiontab; // Value injected by FXMLLoader

    @FXML // fx:id="tableview11"
    private TableView<?> tableview11; // Value injected by FXMLLoader

    @FXML // fx:id="tcamount11"
    private TableColumn<?, ?> tcamount11; // Value injected by FXMLLoader

    @FXML // fx:id="tccreated11"
    private TableColumn<?, ?> tccreated11; // Value injected by FXMLLoader

    @FXML // fx:id="tcemail11"
    private TableColumn<?, ?> tcemail11; // Value injected by FXMLLoader

    @FXML // fx:id="tcname11"
    private TableColumn<?, ?> tcname11; // Value injected by FXMLLoader

    @FXML // fx:id="tcquanity11"
    private TableColumn<?, ?> tcquanity11; // Value injected by FXMLLoader

    @FXML // fx:id="ttotal"
    private Label ttotal; // Value injected by FXMLLoader

    @FXML
    void profile(ActionEvent event) {

    }

    @FXML
    void stock(ActionEvent event) {

    }

}
