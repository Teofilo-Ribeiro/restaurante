/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.view.util;

import javafx.scene.control.TextField;

/**
 *
 * @author teo
 */
public class Constraints {
    public static void setTextFieldInteger(TextField tf){
        tf.textProperty().addListener((obs, oldValue, newValue)->{
            if(newValue!=null && !newValue.matches("\\d*")){
                tf.setText(oldValue);
            }           
        });
    }
    public static void setTextFieldMaxLength (TextField tf, int maxLength){
        tf.textProperty().addListener((obs,oldValue,newValue)-> {
            if(newValue!=null && tf.getLength()>maxLength){
                tf.setText(oldValue);
            }
        });
    }
    public static void setTextFieldDouble(TextField tf){
        tf.textProperty().addListener((obs, oldValue, newValue)->{
            if(newValue!=null && !newValue.matches("\\d*([\\.]\\d*)?")){
                tf.setText(oldValue);
            }
        });
    }
    public static void setTextFieldCurrencyValue(TextField tf){}
    
    
}
