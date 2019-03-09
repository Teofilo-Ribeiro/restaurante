/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.view;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author teo
 */
public class NewFXMain extends Application {
    private static Stage stage;
    private static Scene principal;
    
    @Override
    public void start(Stage primaryStage) {
        
        /*stage=primaryStage;
        Parent fxmlPrincipal; 
        try {
            fxmlPrincipal = FXMLLoader.load(getClass().getResource("viewfxml/TelaCadastroPratos.fxml"));
            principal = new Scene(fxmlPrincipal);
        } catch (IOException ex) {
            System.out.println("AQUI!");
                    
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
       
        primaryStage.setScene(principal);
        primaryStage.show();*/
        MainScreenController tp = new MainScreenController();
        //ItemsScreenController tp = new ItemsScreenController();
        tp.start();
        
    }

    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
