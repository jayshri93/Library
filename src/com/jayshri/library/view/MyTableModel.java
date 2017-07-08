/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jayshri.library.view;

import javax.swing.table.DefaultTableModel;


public class MyTableModel extends DefaultTableModel {
    
    public MyTableModel(String[][] data,String[] columns){
        super(data,columns);
        
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
    
    
}
