/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.interfaces;

import java.util.Date;

/**
 *
 * @author ilichh1
 */
public interface Registrable {
    
    void setRegisteredDate(Date registeredDate);
    Date getRegisteredDate();
    
    void setModifiedDate(Date modifiedDate);
    Date getModifiedDate();
    
}
