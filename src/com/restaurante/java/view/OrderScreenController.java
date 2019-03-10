/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.view;

import com.restaurante.java.controller.ItemRegistration;
import com.restaurante.java.controller.OrderRegistration;
import com.restaurante.java.exception.DbException;
import com.restaurante.java.model.Item;
import com.restaurante.java.model.Order;
import com.restaurante.java.view.util.Alerts;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author teo
 */
public class OrderScreenController implements Initializable {
    private static Stage stage;
    private static Scene scene;
    
    @FXML
    private TableView<Order> tvItems;

    @FXML
    private TableColumn<Object, String> tcItem;

    @FXML
    private TableColumn<Order, Integer> tcOrderId;
    
    @FXML
    private TableColumn<Order, Order> tcDone;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableData();
    }    
    public void start(Stage owner) {
        try {
            
            stage = new Stage();
            Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("viewfxml/OrderScreen.fxml"));
            scene = new Scene(fxmlPrincipal);
            stage.setScene(scene);
            stage.setTitle("Pedidos");
            stage.initOwner(owner);
            stage.show();
        } catch (IOException ex) {
            Alerts.showAlert("ERRO", null, "Erro ao abrir tela de Pedidos!\n" + ex.getMessage(), Alert.AlertType.ERROR);
            stage.close();
        }

    }
    
    
    private void loadTableData() {
        tcItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        
        tcOrderId.setCellValueFactory(new PropertyValueFactory<>("id")); 
        tcDone.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));      
        
        
        tcDone.setCellFactory(param -> new TableCell<Order, Order>() {
            private final Button button = new Button("Pronto!");

            @Override
            protected void updateItem(Order order, boolean empty) {
                super.updateItem(order, empty);
                if (order == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(button);                
                
                button.setOnAction(
                        event -> onDone(order));
            }
        });        
        

        tvItems.setItems(tableData());

    }

    private ObservableList<Order> tableData() {

        try {
            OrderRegistration orderReg = new OrderRegistration();
            List <Order>order = orderReg.findAllNotDone();      
            ObservableList<Order> obsList = FXCollections.observableArrayList(order);
            return obsList;
        } catch (DbException ex) {
            Alerts.showAlert("ERRO!", null, ex.getMessage(), Alert.AlertType.ERROR);
        }
        return null;

    }
    
    public void onDone(Order order){
        try {
            OrderRegistration orderReg = new OrderRegistration();
            order.setDone(true);
            orderReg.update(order);     
            loadTableData();
            
        } catch (DbException ex) {
            Alerts.showAlert("ERRO!", null, ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
