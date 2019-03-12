/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.view;

import com.restaurante.java.controller.CommandRegistration;
import com.restaurante.java.exception.DbException;
import com.restaurante.java.model.Command;
import com.restaurante.java.model.Item;
import com.restaurante.java.model.Order;
import com.restaurante.java.view.util.Alerts;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author teo
 */
public class CloseCommandScreenController implements Initializable {
    private static Scene scene;
    private static Stage stage;
    @FXML
    private TableView<Order> tvOrders;

    @FXML
    private TableColumn<Item,String> tcItems;

    @FXML
    private TableColumn<Order,Item > tcPrice;
    
    @FXML
    private TableColumn<Order,Integer> tcQty;
    
    @FXML
    private Button btPay;

    @FXML
    private Label lbTotal;

    @FXML
    private Label lbQtyItems;
    
    private static int tableId;
    
    private static float total;
    private static int totalItems;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableData();
        lbTotal.setText(Float.toString(total));
        lbQtyItems.setText(Integer.toString(totalItems));
        
    }    

    public void start(Stage owner, int tableId) {
        
        try {
            CommandRegistration commandReg = new CommandRegistration();
            Command command= commandReg.getCommand(tableId);
            total = command.getTotal();
            totalItems = command.getTotalItems();
            
            this.tableId = tableId;
            stage = new Stage();
            Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("viewfxml/CloseCommandScreen.fxml"));
            scene = new Scene(fxmlPrincipal);
            stage.setScene(scene);
            stage.setTitle("FECHAR CONTA");
            stage.setResizable(false);
            stage.initOwner(owner);
            stage.show();
        } catch (IOException ex) {
            Alerts.showAlert("ERRO", null, "Erro ao abrir tela de Pagamento!\n" + ex.getMessage(), Alert.AlertType.ERROR);
            stage.close();
        } catch (DbException ex) {
            Alerts.showAlert("ERRO!", null, ex.getMessage(), Alert.AlertType.ERROR);
        }

    }
    private void loadTableData() {
       
        tcItems.setCellValueFactory(new PropertyValueFactory<>("item"));        
        tcQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        tvOrders.setItems(tableData()); 

    }

    private ObservableList<Order> tableData() {

        try {
            CommandRegistration commandReg = new CommandRegistration();
            List <Order>order = commandReg.getCommand(tableId).getOrders();
            ObservableList<Order> obsList = FXCollections.observableArrayList(order);
            return obsList;
        } catch (DbException ex) {
            Alerts.showAlert("ERRO!", null, ex.getMessage(), Alert.AlertType.ERROR);
        }
        return null;

    }
    
    
    
    public void onPay(ActionEvent event) {
        try {
            CommandRegistration commandReg = new CommandRegistration();
            commandReg.closeCommand(tableId);
            stage.close();
        } catch (DbException ex) {
            Alerts.showAlert("ERRO!", null, ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
