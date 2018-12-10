/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.utilerias.menu;

/**
 *
 * @author ilichh1
 */
public class MenuEntry {
    
    public String menuLabel;
    public char menuOption;
    public String actionToCall;
    
    public MenuEntry(String label, char option, String action) {
        this.menuLabel = label;
        this.menuOption = option;
        this.actionToCall = action;
    }
    
}