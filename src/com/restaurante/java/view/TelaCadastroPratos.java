/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.view;

import com.restaurante.java.model.Prato;
import com.restaurante.java.controller.CadastroPratos;
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
public class TelaCadastroPratos  {
   /*
    //@FXML
    private TextField tfNomedoPrato;
    @FXML
    private TextField tfPreco;
    @FXML
    private Button btConfirmar;
*/
    
    private static Scene popup;
    @FXML
    private Label lbTitulo;

    @FXML
    private Label lbNomePrato;

    @FXML
    private Label lbPreco;

    @FXML
    private Label lbCifrao;

    @FXML
    private TextField tfNomePrato;
    
    @FXML
    private TextField tfPreco;

    @FXML
    private Button btConfirmar;
    @FXML
    public void Cadastrar (){
        Prato p = new Prato ();
        VerificacaoCadPratos tVerificacao = new VerificacaoCadPratos();
        String titulo="Peixe";
        Prato verifica;
        CadastroPratos pCad= new CadastroPratos();
        boolean validado;

        validado = true;
        p.setDescricao(tfNomePrato.getText());

        if(tfNomePrato.getText().length() == 0){
            System.out.println("Digite uma descrição Valida");
            validado = false;
        }
        try{
            p.setPreco(Double.parseDouble(tfPreco.getText()));
        }catch(NumberFormatException ex){
            System.out.println("Digite um valor preço valido!");
            validado = false;
        }
        if(validado){
            verifica = pCad.CadastrarPrato(p);
            if(verifica.getDescricao()==null){
                //Cadastrado com sucesso id do produto =
                titulo = "Prato cadastrado com sucesso!";
                
                
            }
            else{
                titulo = "Prato já existente! Deseja salvar alteração?";
                //produto ja cadastrado com id e preco tal, deseja alterar o preço?
            }     
            p =pCad.Buscar(p.getDescricao());
            tVerificacao.start(titulo,p);
        }   
       
        
    
        
        
        
        
        
        //tVerificao.texto(titulo);
        
        
      
    }

    
    
}
