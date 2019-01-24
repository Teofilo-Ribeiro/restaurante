/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.view;


import com.restaurante.java.controller.CadastroMesas;
import com.restaurante.java.model.Mesa;
import com.restaurante.java.model.enums.EstadoMesa;
import com.restaurante.java.util.ButtonMesa;
import static com.restaurante.java.util.ButtonMesa.parseId;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TelaPrincipal {

    @FXML
    private GridPane grMesas;
       
    private static Stage stage;
    private static Scene scene;
    
    //private Mesa mesas [];
    private ButtonMesa[] botoesMesas;
    private List<Mesa> mesas;
    
    @FXML
    void initialize (){
        CadastroMesas cadMesas = new CadastroMesas();
        mesas= cadMesas.listarTodos();
        botoesMesas= new ButtonMesa[mesas.size()];
        int i = 0;
        int gridH = 0, gridV =0;
       
        for(Mesa m : mesas ){
            botoesMesas[i] = new ButtonMesa(Integer.toString(m.getId()),m.getEstado(),this::abrirMesa, this::fecharMesa, this::reservarMesa, this::status,this::novoPedido);
            grMesas.add(botoesMesas[i],gridV,gridH);
            GridPane.setHalignment(botoesMesas[i], HPos.CENTER);
            GridPane.setValignment(botoesMesas[i], VPos.CENTER);
            if(gridV>12){
                gridV = 0;
            }
            else{
                gridV++;
            }
            i++;
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
        CadastroMesas p = new CadastroMesas();   
        botoesMesas[parseId(e)-1].setEstado(EstadoMesa.OCUPADA); // -1 pois o id da mesa começa em 1;        
        Mesa mesa = mesas.get(parseId(e)-1);
        mesa.setEstado(EstadoMesa.OCUPADA);
        p.atualizarEstado(mesa);
    }
    public void fecharMesa(ActionEvent e){
        //fachar a comanda e abrir tela de pagamento
        CadastroMesas p = new CadastroMesas();
        botoesMesas[parseId(e)-1].setEstado(EstadoMesa.LIVRE); 
        Mesa mesa = mesas.get(parseId(e)-1);
        mesa.setEstado(EstadoMesa.LIVRE);
        p.atualizarEstado(mesa);
    }
    public void reservarMesa(ActionEvent e){
        CadastroMesas p = new CadastroMesas();
                
        botoesMesas[parseId(e)-1].setEstado(EstadoMesa.RESERVADA);
                
        Mesa mesa = mesas.get(parseId(e)-1);
        mesa.setEstado(EstadoMesa.RESERVADA);
        p.atualizarEstado(mesa);
    }
    public void novoPedido(ActionEvent e){
        
    }
    public void status(ActionEvent e){
    
    }
    
    public void cadPratos(){
        TelaCadastroItem tCadPratos = new TelaCadastroItem();
        tCadPratos.start();
    
    }
}
