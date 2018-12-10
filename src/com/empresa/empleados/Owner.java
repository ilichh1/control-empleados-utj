/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.empleados;

import com.empresa.abstracts.Empleado;
import java.time.LocalDate;

/**
 *
 * @author ilichh1
 */
public class Owner extends Empleado {
    
    public Owner() throws Exception {
        super(
                "Ilich",
                "Arredondo",
                20,
                //new Date(new Long("1542742977964")),
                LocalDate.ofEpochDay(17855821),
                Empleado.OWNER
        );
    }
    
}
