/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.interfaces;

/**
 *
 * @author ilichh1
 */
public interface ConsoleAskable {
    
    public default void promptInConsole() {
        String[] fieldNames = this.getAskableFieldNames();
        for (String fieldName : fieldNames) {
            this.askForFieldName(fieldName);
        }
    }
    
    public String[] getAskableFieldNames();
    
    void askForFieldName(String fieldName);
}
