package com.restaurante.java.view;

import com.restaurante.java.controller.ItemRegistration;
import com.restaurante.java.exception.DbException;
import com.restaurante.java.model.Item;
import com.restaurante.java.view.listeners.DataChangeListener;
import com.restaurante.java.view.util.Alerts;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ItemsScreenController implements Initializable, DataChangeListener {

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
    private TableView <Item>tvItems;

    @FXML
    private TableColumn<Item, Integer> tcID;

    @FXML
    private TableColumn<Item, String> tcDescription;

    @FXML
    private TextField tfDescription;

    @FXML
    private TableColumn<Item, Float> tcPrice;

    @FXML
    private TableColumn <Item, Item>tcAdd;
    
    private static Boolean setAddButtonsOn = false;

    private ItemRegistration itemReg;
    
    private static int tableNumber;

    public ItemsScreenController() {
        try {
            this.itemReg = new ItemRegistration();
        } catch (DbException ex) {
            Alerts.showAlert("ERRO!", null, ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTableData();
    }

    private void loadTableData() {
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
       
        tcAdd.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tcAdd.setCellFactory(param -> new TableCell<Item, Item>() {
            private final Button button = new Button("add");

            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(button);
                if(setAddButtonsOn!=true){
                    button.setVisible(false);
                }
                button.setOnAction(
                        event -> onAdd(item));
            }
        });        
        

        tvItems.setItems(tableData());

    }

    private ObservableList<Item> tableData() {

        try {
            ItemRegistration ItemReg = new ItemRegistration();
            //List <Item>lista = ItemReg.findAll();      
            List<Item> lista = ItemReg.smartFind(tfDescription.getText());
            ObservableList<Item> obsList = FXCollections.observableArrayList(lista);
            return obsList;
        } catch (DbException ex) {
            Alerts.showAlert("ERRO!", null, ex.getMessage(), Alert.AlertType.ERROR);
        }
        return null;

    }

    public void start(Stage owner) {
        try {
            
            stage = new Stage();
            Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("viewfxml/ItemsScreen.fxml"));
            scene = new Scene(fxmlPrincipal);
            stage.setScene(scene);
            stage.setTitle("Itens");
            stage.initOwner(owner);
            stage.show();
        } catch (IOException ex) {
            Alerts.showAlert("ERRO", null, "Erro ao abrir tela de Itens!\n" + ex.getMessage(), Alert.AlertType.ERROR);
            stage.close();
        }

    }
    
    public void start(Stage owner, int table){
        setAddButtonsOn = true;
        tableNumber = table;
        this.start(stage);
        
    }

    public void onRegistration() {
        ItemRegisterScreenController itemRegSc = new ItemRegisterScreenController();
        itemRegSc.subscribeDataChangeListener(this);
        itemRegSc.start();
    }

    public void onAlteration() {
        VerifyItemRegScreenController itemVeriRegSc = new VerifyItemRegScreenController();
        itemVeriRegSc.subscribeDataChangeListener(this);
        itemVeriRegSc.start(stage, null, VerifyItemRegScreenController.ScreenType.UPDATE);
    }

    public void onDelete() {
        VerifyItemRegScreenController itemVeriRegSc = new VerifyItemRegScreenController();
        itemVeriRegSc.subscribeDataChangeListener(this);
        itemVeriRegSc.start(stage, null, VerifyItemRegScreenController.ScreenType.DELETE);
    }

    public void close() {
        stage.close();
    }

    @Override
    public void onDataChanged() {
        loadTableData();
    }

    public void onDescriptionChanged() {
        loadTableData();
    }

    public void onAdd(Item item) {
        System.out.println(item.getDescription());
        OrderConfirmationScreenController orderConf = new OrderConfirmationScreenController();
        orderConf.start(stage, item,tableNumber);
    }

    
}
