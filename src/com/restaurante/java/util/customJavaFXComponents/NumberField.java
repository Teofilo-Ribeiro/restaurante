/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.java.util.customJavaFXComponents;

import javafx.scene.control.TextField;

/**
 *
 * @author teo
 */
public class NumberField extends TextField{
    private Integer fieldSize;
    
    public NumberField() {
        super();        
        fieldSize = null;
    }

    public NumberField(int fieldSize) {
        super();        
        if(fieldSize<=0)
            throw new IllegalStateException("ERRO: O tamanho do campo deve ser maior que 0");
        this.fieldSize = fieldSize;
    }
    @Override 
    public void replaceText(int start, int end, String text) {
        if (!text.matches("[a-z]")) {
            super.replaceText(start, end, text);
        }
        if (fieldSize!= null){
            verifyFieldSize();
        }
    }
 
    @Override 
    public void replaceSelection(String text) {
        if (!text.matches("[a-z]")) {
            super.replaceSelection(text);
        }
        if (fieldSize!= null){
            verifyFieldSize();
        }
    }
    
    private void verifyFieldSize() {
    if (getText().length() > fieldSize) {
        setText(getText().substring(0, fieldSize));
    }

}
    
}
