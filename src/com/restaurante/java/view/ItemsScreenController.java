package com.restaurante.java.view;

import com.restaurante.java.controller.ItemRegistration;
import com.restaurante.java.exception.DbException;
import com.restaurante.java.model.Item;
import com.restaurante.java.view.listeners.DataChangeListener;
import com.restaurante.java.view.util.Alerts;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ItemsScreenController implements Initializable, DataChangeListener{

    private Stage stage;
    private Scene scene;
    
    @FXML
    private Button btRegister;

    @FXML
    private Button btAlter;

    @FXML
    private Button btDelete;

    @FXML
    private Button btExit;
    
    @FXML
    private TableView  tvItems;

    @FXML
    private TableColumn <Item, Integer> tcID;

    @FXML
    private  TableColumn <Item, String>tcDescription;

    @FXML
    private TableColumn <Item, Float> tcPrice;
    
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTableData();     
    }
    private void loadTableData(){
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<>("price"));       
        tvItems.setItems(tableData());
        
    }
    
    private ObservableList<Item> tableData(){
               
        try {
            ItemRegistration ItemReg = new ItemRegistration();
            List <Item>lista;            
            lista = ItemReg.findAll();
            ObservableList <Item> obsList = FXCollections.observableArrayList(lista);
            return obsList;
        } catch (DbException ex) {
            Alerts.showAlert("ERRO!", null, ex.getMessage(), Alert.AlertType.ERROR);
        }
        return null;
        
    }

    public void start(Stage owner){
        try {
            stage= new Stage();
            Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("viewfxml/ItemsScreen.fxml"));
            scene = new Scene(fxmlPrincipal);           
            stage.setScene(scene);
            stage.setTitle("Itens");
            stage.initOwner(owner);
            stage.show();
        } catch (IOException ex) {
            Alerts.showAlert("ERRO", null, "Erro ao abrir tela de Itens!\n"+ex.getMessage(), Alert.AlertType.ERROR);
            stage.close();
        }
        
    }
    public void onRegistration(){
        ItemRegisterScreenController itemRegSc = new ItemRegisterScreenController();
        itemRegSc.subscribeDataChangeListener(this);
        itemRegSc.start();
    }
    public void onAlteration(){
        VerifyItemRegScreenController itemVeriRegSc = new VerifyItemRegScreenController();
        itemVeriRegSc.subscribeDataChangeListener(this);
        itemVeriRegSc.start(stage,null, VerifyItemRegScreenController.ScreenType.UPDATE);
    }
    public void onDelete(){
        VerifyItemRegScreenController itemVeriRegSc = new VerifyItemRegScreenController();
        itemVeriRegSc.subscribeDataChangeListener(this);
        itemVeriRegSc.start(stage,null, VerifyItemRegScreenController.ScreenType.DELETE);
    }
    public void close(){
        stage.close();        
    }

    @Override
    public void onDataChanged() {
        loadTableData();
    }

    
}
