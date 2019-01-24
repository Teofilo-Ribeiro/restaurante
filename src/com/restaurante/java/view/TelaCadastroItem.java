/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.view;

import com.restaurante.java.model.Item;
import com.restaurante.java.controller.CadastroItem;
import com.restaurante.java.dao.ItemDaoJDBC;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author teo
 */
public class TelaCadastroItem  {
   /*
    //@FXML
    private TextField tfNomedoPrato;
    @FXML
    private TextField tfPreco;
    @FXML
    private Button btConfirmar;
*/
    
    
    @FXML
    private Label lbTitulo;

    @FXML
    private Label lbDescricaoItem;

    @FXML
    private Label lbPreco;

    @FXML
    private Label lbCifrao;

    @FXML
    private TextField tfDescricaoItem;
    
    @FXML
    private TextField tfPreco;

    @FXML
    private Button btConfirmar;
    
    private static Scene scene;
    private static Stage stage;
    
    
    @FXML
    public void cadastrar (){
       Item item, itemVerificacao;
       CadastroItem cadItem = new CadastroItem();
       TelaVerificacaoCadItem verificaCadItem = new TelaVerificacaoCadItem();
       try {
           item = getFormData();
           itemVerificacao = cadItem.buscar(item.getDescricao());
           if(itemVerificacao.getDescricao() == null){
               //item foi cadastrador mostar id
               item.setId(cadItem.cadastrar(item));
               verificaCadItem.start("Item cadastrado com sucesso!", item, false);               
           }
           else{
               verificaCadItem.start("Item já cadastrado, deseja salvar alterações?", itemVerificacao, true);
           }
           
       }
       catch(IllegalStateException e){
          System.out.println("ERROR: " + e.getMessage());
       }
    }
    
    
    public Item getFormData(){
        Item item = new Item();
        if(tfDescricaoItem.getText() == null || tfDescricaoItem.getText().trim().equals("")){
            throw new IllegalStateException("Todos os campos deve está devidamente preenchidos!");
        }
        item.setDescricao(tfDescricaoItem.getText());
        
        try{
            item.setPreco(Double.parseDouble(tfPreco.getText()));
        }catch(NumberFormatException ex){
            throw new IllegalStateException ("O preço digitado é invalido!");
        }
        
        
        return item;
    }
    
    
    public void start(){
    
        
        try {
            stage= new Stage();
        //Parent fxmlPrincipal; 
            Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("viewfxml/TelaCadastroItem.fxml"));
            scene = new Scene(fxmlPrincipal);
        } catch (IOException ex) {
            System.out.println("AQUI!");

            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }


        stage.setScene(scene);
        stage.setTitle("Cadastro de Itens"); 
        stage.show();
       // texto("Oi");    
   }
    public void close (){
        stage.close();
        stage=null;
       
   }
    
    
}
