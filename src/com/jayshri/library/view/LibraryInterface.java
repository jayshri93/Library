/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jayshri.library.view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class LibraryInterface extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private AddBookPanel abp;
    private JTabbedPane jtb;
    private String filter;
    private  BrowseLibraryPanel blp;
    
    public LibraryInterface(String title){
        super(title);
        
        jtb = new JTabbedPane();
        abp = new AddBookPanel();
        blp = new BrowseLibraryPanel();
        
        filter = "      ";
        jtb.addTab(filter+filter+"Add Book"+filter+filter,abp);
        jtb.addTab(filter+"Browse Library"+filter, blp);
        add(jtb);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 520);
        setResizable(false);
        
    }
    public AddBookPanel getAddBookPanel(){
        return abp;
    }
    
    public BrowseLibraryPanel getBrowseLibraryPanel(){
        return blp;
    }
    
    public JTabbedPane getTabbedPane(){
        return jtb;
    }
    
    public String getFilter(){
        return filter;
    }
    
}
