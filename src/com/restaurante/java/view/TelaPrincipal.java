/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.view;


import com.restaurante.java.model.Mesa;
import com.restaurante.java.model.Prato;
import com.restaurante.java.util.ButtonMesa;
import static com.restaurante.java.util.ButtonMesa.parseId;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class TelaPrincipal {

    @FXML
    private GridPane grMesas;
       
    private static Stage stage;
    private static Scene scene;
    
    private Mesa mesas [];
    private ButtonMesa[] botoesMesas;
    
    @FXML
    void initialize (){
        //ler o as mesas
        //retorno do banco
        mesas = new Mesa[3];
        mesas[0]= new Mesa();
        mesas[0].setCod_mesa(001);
        mesas[0].setEstado('L');
        mesas[1]= new Mesa();
        mesas[1].setCod_mesa(002); 
        mesas[1].setEstado('L');
        mesas[2]= new Mesa();
        mesas[2].setCod_mesa(003); 
        mesas[2].setEstado('L');
        
        botoesMesas= new ButtonMesa[3];
        
        
        int qtdMesas = 3; // tamanho do vetor
        int gridH = 0, gridV =0;
        
        for(int i=0; i<qtdMesas; i++){
            botoesMesas[i] = new ButtonMesa(Integer.toString(mesas[i].getCod_mesa()),this::abrirMesa, this::fecharMesa, this::reservarMesa,this::abrirMesa, this::abrirMesa);
            grMesas.add(botoesMesas[i],gridV,gridH);
            GridPane.setHalignment(botoesMesas[i], HPos.CENTER);
            GridPane.setValignment(botoesMesas[i], VPos.CENTER);
            if(gridV>12){
                gridV = 0;
            }
            else{
                gridV++;
            }           
        }
    }
    
    public void start (){
        
       stage= new Stage();
        Parent fxmlPrincipal; 
        try {
            fxmlPrincipal = FXMLLoader.load(getClass().getResource("viewfxml/TelaPrincipal.fxml"));
            scene = new Scene(fxmlPrincipal);
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }
    
    public void execute(ActionEvent e){
        System.out.println(e.getSource().toString()/*.substring(10, 11)*/); //ALTERA 11 PARA 13 QUANDO ASSOCIAR COM O BNACO
        System.out.println(e.getClass());
    }
    
    public void abrirMesa(ActionEvent e){
        botoesMesas[parseId(e)-1].setEstado("abrir"); // -1 pois o id da mesa começa em 1;
           
    }
    public void fecharMesa(ActionEvent e){
        //fachar a comanda e abrir tela de pagamento
        botoesMesas[parseId(e)-1].setEstado("livre");    
    }
    public void reservarMesa(ActionEvent e){
        botoesMesas[parseId(e)-1].setEstado("reservada");
    
    
    }
    
}
