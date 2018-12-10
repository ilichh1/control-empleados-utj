/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.utilerias.menu;

import com.empresa.interfaces.ControladorMenu;
import com.empresa.util.ConsoleUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ilichh1
 */
public class Menu {
    
    private final List<MenuEntry> menuOptions;
    private final String menuName;
    private int longestMenuLabel = 0;
    private HashMap<Character, String> menuMap;
    
    public Menu(String name, MenuEntry[] initialOptions) throws Exception {
        this.menuName = name;
        this.menuOptions = new ArrayList<>(Arrays.asList(initialOptions));
        this.determineLongestMenuLabel();
    }
    
    private void determineLongestMenuLabel() throws Exception {
        this.menuMap = new HashMap<>();
        
        for (MenuEntry menuOption : menuOptions) {
            
            if (this.menuMap.getOrDefault(menuOption.menuOption, "NO_OPTION").equals("NO_OPTION")) {
                this.menuMap.put(menuOption.menuOption, menuOption.actionToCall);
            } else {
                throw new Exception("No pueden existir dos opciones de menú con la misma letra.");
            }
            
            if (menuOption.menuLabel.length() > this.longestMenuLabel)
                this.longestMenuLabel = menuOption.menuLabel.length();
        }
    }
    
    private boolean validateAndCallAction(char selectedOption, ControladorMenu controladorMenu) {
        selectedOption = Character.toUpperCase(selectedOption);
        if (this.menuMap.getOrDefault(selectedOption, "NO_OPTION").equals("NO_OPTION")) {
            ConsoleUtils.printErrorMessage("Esa opción no existe.");
            return false;
        }
        controladorMenu.doSpecificAction(this.menuMap.getOrDefault(selectedOption, "NO_OPTION"));
        return true;
    }
    
    private boolean promptUser(ControladorMenu controladorMenu) {
        for(;;) {
            System.out.print("Su eleccion: ");
            if (this.validateAndCallAction(ConsoleUtils.pedirCaracter(), controladorMenu))
                return false;
        }
    }
    
    public void addMenuOption(MenuEntry newOption) {
        this.menuOptions.add(newOption);
        if(newOption.menuLabel.length() > this.longestMenuLabel)
            this.longestMenuLabel = newOption.menuLabel.length();
    }
    
    public boolean printMenu(ControladorMenu controladorMenu) {
        int suposedFinalLength = this.longestMenuLabel + 8;
        
        String topDividerLine = "======== " + this.menuName + " ========";
        String bottomDividerLine = "";
        
        for (int i = 0; i < topDividerLine.length(); i++)
            bottomDividerLine += "=";
        
        
        System.out.print("\n" + topDividerLine + "\n");
        for (MenuEntry menuOption : this.menuOptions) {
            String lineToPrint = " *  " + menuOption.menuLabel + " ";
            String remainingDots = "";
            for (int i = 0; i < suposedFinalLength - lineToPrint.length(); i++)
                remainingDots += ".";
            System.out.println(lineToPrint + remainingDots + " " + menuOption.menuOption + ")");
        }
        System.out.println(bottomDividerLine);
        
        return this.promptUser(controladorMenu);
    }
    
    public void printMenuWithoutPrompt() {
        int suposedFinalLength = this.longestMenuLabel + 8;
        
        String topDividerLine = "======== " + this.menuName + " ========";
        String bottomDividerLine = "";
        
        for (int i = 0; i < topDividerLine.length(); i++)
            bottomDividerLine += "=";
        
        
        System.out.print("\n" + topDividerLine + "\n");
        for (MenuEntry menuOption : this.menuOptions) {
            String lineToPrint = " *  " + menuOption.menuLabel + " ";
            String remainingDots = "";
            for (int i = 0; i < suposedFinalLength - lineToPrint.length(); i++)
                remainingDots += ".";
            System.out.println(lineToPrint + remainingDots + " " + menuOption.menuOption + ")");
        }
        System.out.println(bottomDividerLine);
    }
}
