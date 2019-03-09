/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.view;

import com.restaurante.java.controller.ItemRegistration;
import com.restaurante.java.exception.DbException;
import com.restaurante.java.model.Item;
import com.restaurante.java.view.listeners.DataChangeListener;
import com.restaurante.java.view.util.Alerts;
import com.restaurante.java.view.util.Constraints;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author teo
 */
public  class VerifyItemRegScreenController implements Initializable{
    private static List <DataChangeListener> dataChangeListeners= new ArrayList<>();
       
    @FXML
    private Label lbTitle;
    
    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfPrice;

    @FXML
    private Button btConfirm;

    @FXML
    private Button btCancel;
    @FXML
    private AnchorPane apWindow;
    @FXML
    private TextField tfId;
    @FXML
    private Button btFind;
    
        
    private static Scene popup;
    private static Stage stage;
    
    private static String title;
    private static int id;
    private static String Descricao;
    private static double preco;
    private static Item oldItemData;
    private static Item itemData;
    
    private static boolean isUpdate;

    public enum ScreenType {VISUALIZATION,REGISTRATION, UPDATE, DELETE}; //implementar update without search
    private static ScreenType scType;
    
    ItemRegistration itemReg;
    
     public VerifyItemRegScreenController(){
        try {
            this.itemReg = new ItemRegistration();
        } catch (DbException ex) {
            Alerts.showAlert("ERRO!", null, ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Constraints.setTextFieldInteger(tfId);
        Constraints.setTextFieldMaxLength(tfId, 3);
        screenConfiguration();
        if(ScreenType.REGISTRATION == scType){
            loadData();
        }
        
    }
    public void subscribeDataChangeListener(DataChangeListener listener){
        dataChangeListeners.add(listener);
    }
    private void notifyDataChcangeListeners() {
        for(DataChangeListener listener: dataChangeListeners){
            listener.onDataChanged();
        }
    }
    
   
    private void loadData(){
        lbTitle.setText(title);        
        tfDescription.setText(itemData.getDescription());
        tfPrice.setText(Double.toString(itemData.getPrice()));  
        tfId.setText(Integer.toString(itemData.getId()));
    
    }
    private Item getDataForm(){
        Item item = new Item();
        if(tfDescription.getText() == null || tfDescription.getText().trim().equals("")|| tfId.getText()==null){
            throw new IllegalStateException("Todos os campos deve está devidamente preenchidos!");
        }
        item.setDescription(tfDescription.getText());
        item.setId(Integer.parseInt(tfId.getText()));
        try{
            item.setPrice(Double.parseDouble(tfPrice.getText()));
        }catch(NumberFormatException e){
            throw new IllegalStateException ("O preço digitado é invalido!");
        }        
        return item;
    }
    
    
    public void find(){
        if(tfId.getText() != null){
            try {
                itemData = itemReg.findById(Integer.parseInt(tfId.getText()));
                if(itemData==null){
                    throw new IllegalStateException ("Código Não Encontrado");
                }
                loadData();
                btConfirm.setDisable(false);
                tfId.setDisable(true);
                tfDescription.setEditable(true);
                tfDescription.setDisable(false);
                tfPrice.setEditable(true);
                tfPrice.setDisable(false);
                btFind.setDisable(true);
            } catch (DbException ex) {
                Alerts.showAlert("ERRO!", null, ex.getMessage(), Alert.AlertType.ERROR);
            }
        }        
    }
    public void update(ActionEvent e){
                
        try{
            ItemRegistration itemReg = new ItemRegistration();
            itemReg.update(getDataForm());
            notifyDataChcangeListeners();
            close();
        }catch(IllegalStateException ex){
            Alerts.showAlert("ATENÇÃO!", null, ex.getMessage(), Alert.AlertType.WARNING);
        } catch (DbException ex) {
            Alerts.showAlert("ERRO!", null, ex.getMessage(), Alert.AlertType.ERROR);
        }
        
    }
    public void delete(ActionEvent e){
        try {
            ItemRegistration itemReg = new ItemRegistration();
            itemReg.delete(getDataForm());
            notifyDataChcangeListeners();    
            close();
        } catch (DbException ex) {
            Alerts.showAlert("ERRO!", null, ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    public void start (Stage owner, Item item, ScreenType screenType){
        itemData = item;
        scType= screenType;       
        try {
            stage= new Stage();
            Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("viewfxml/VerifyItemRegScreen.fxml"));
            popup = new Scene(fxmlPrincipal);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(popup);
            stage.initOwner(owner);
            stage.show();
        } catch (IOException ex) {
            Alerts.showAlert("ERRO", null, "Erro ao abrir tela de registro de Itens!\n"+ex.getMessage(), Alert.AlertType.ERROR);
        }
        
                
   }
    private void screenConfiguration(){
        switch(scType){
            case VISUALIZATION:
                
                title = "ITEM JÁ CADASTRADO!"; 
                //isUpdate=true;
                btConfirm.setVisible(false);
                tfDescription.setDisable(true);
                tfPrice.setDisable(true);
                tfId.setDisable(true);
                btFind.setVisible(false);
                break;
            case REGISTRATION:            
                title = "ITEM CADASTRADO COM SUCESSO!";
                //isUpdate = false;
                btConfirm.setVisible(false);
                tfDescription.setDisable(true);
                tfDescription.setEditable(true);
                btFind.setVisible(false);
                break;
            case UPDATE:
                updateConfiguration();
                break;
            case DELETE:
                deleteConfiguration();
                break;
            default:
                throw new IllegalStateException("Erro no tipo de Tela");
        }
        
            
    }
       
    public void resetUpdateConfiguration(ActionEvent e){
        clearData();
        updateConfiguration();
    }
    private void clearData(){
        tfId.setText(null);
        tfDescription.setText(null);
        tfPrice.setText(null);        
    }
    private void updateConfiguration(){
        title = "ALTERAÇÃO";
        tfId.setDisable(false);
        tfId.setEditable(true);
        btConfirm.setVisible(true);
        btConfirm.setDisable(true);
        tfDescription.setDisable(true);
        tfPrice.setDisable(true);
        btFind.setVisible(true);
        btConfirm.setOnAction(this::update);
        btCancel.setOnAction(this::resetUpdateConfiguration);
        tfId.requestFocus();
        
    }
    private void deleteConfiguration(){
        title = "DELETAR";
        tfId.setDisable(false);
        tfId.setEditable(true);
        btConfirm.setVisible(true);
        btConfirm.setDisable(true);
        tfDescription.setDisable(true);
        tfPrice.setDisable(true);
        btFind.setVisible(true);    
        btConfirm.setOnAction(this::delete);
        btCancel.setOnAction(this::resetUpdateConfiguration);
        tfId.requestFocus();
        
    }
    
    
    public void close (){
        stage.close();
        stage=null;       
    }    
}
