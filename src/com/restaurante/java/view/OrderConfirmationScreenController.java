/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.view;

import com.restaurante.java.controller.CommandRegistration;
import com.restaurante.java.controller.OrderRegistration;
import com.restaurante.java.exception.DbException;
import com.restaurante.java.model.Command;
import com.restaurante.java.model.Item;
import com.restaurante.java.model.Order;
import com.restaurante.java.view.util.Alerts;
import com.restaurante.java.view.util.Constraints;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OrderConfirmationScreenController implements Initializable{
    private static Stage stage;
    private static Scene scene;

    @FXML
    private Label lbItemDescription;

    @FXML
    private TextField tfQty;

    @FXML
    private TextArea taNotes;

    @FXML
    private Button btConfirm;
    
    private static Item item;
    private static int tableId;
    
    private Order order;

    @FXML
    void onConfirm() {
        try {
            OrderRegistration orderReg = new OrderRegistration();
            order = new Order();
            order.setNote(taNotes.getText());
            order.setItem(item);
            order.setQty (Integer.parseInt(tfQty.getText()));
            order.setTableId(tableId);
            orderReg.register(order);
            this.close();
            
        } catch (DbException ex) {
            Logger.getLogger(OrderConfirmationScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void start(Stage owner, Item item, int tableId) {
        try {
            this.item = item;
            this.tableId = tableId;
            
            stage = new Stage();
            Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("viewfxml/OrderConfirmationScreen.fxml"));
            scene = new Scene(fxmlPrincipal);           
            stage.setScene(scene);
            stage.setTitle("Pedido");
            stage.initOwner(owner);
            stage.show();
        } catch (IOException ex) {
            Alerts.showAlert("ERRO", null, "Erro ao abrir tela de confirmação de pedido!\n" + ex.getMessage(), Alert.AlertType.ERROR);
            stage.close();
        }

    }
    public void close(){
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbItemDescription.setText(item.getDescription()+", RS "+item.getPrice());
        Constraints.setTextFieldInteger(tfQty);
        Constraints.setTextFieldMaxLength(tfQty, 2);
    }

}