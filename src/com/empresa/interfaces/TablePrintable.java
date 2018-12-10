/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.interfaces;

import com.empresa.util.ConsoleUtils;

/**
 *
 * @author ilichh1
 */
public interface TablePrintable {
    
    String[] toStringArray();
    
    default public String[] generateRow(int[] widths) {
        String[] columnsWithSpace = this.toStringArray();
        
        // Starting with 1 because the first element is always the ID
        for (int i = 0; i < columnsWithSpace.length; i++) {
            String column = columnsWithSpace[i];
            columnsWithSpace[i] += ConsoleUtils.fillRemainingSpace(column.length(), widths[i+1], " ");
        }
        
        return columnsWithSpace;
    }
    
    default public int[] columnsLenghts(int[] oldLengths) {
        String[] columns = this.toStringArray();
        // Starting on the index 1 because the index 0 is always the INDEX
        for (int i = 0; i < columns.length; i++) {
            int lengthsIndex = i+1;
            oldLengths[lengthsIndex] = columns[i].length() > oldLengths[lengthsIndex] ?
                            columns[i].length() : oldLengths[lengthsIndex];
        }
        
//        System.out.println("Evaluating: " + Arrays.toString(this.toStringArray()));
//        System.out.println("Results: " + Arrays.toString(oldLengths) + "\n");
        return oldLengths;
    }
}
