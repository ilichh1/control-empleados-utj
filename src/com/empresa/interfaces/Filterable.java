/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.interfaces;

import java.util.Arrays;

/**
 *
 * @author ilichh1
 */
public interface Filterable {
    
    public String[] toStringArray();
    
    public default boolean contains(String searchValue) {
        return Arrays.toString(this.toStringArray()).contains(searchValue);
    }
    
}
