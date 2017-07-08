/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jayshri.library.view;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddBookPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Box mainBox, hBox1, hBox2, hBox3, hBox4, hBox5, hBox6, hBox7, hBox8;

    private JLabel jlIsbn, jlTitle, jlAuthor, jlPrice, jlFile, jlLogDog;
    private JTextField jtIsbn, jtTitle, jtAuthor, jtPrice, jtFile;
    private JButton bBrowse, bAddFile, bAddBook, bSaveAndQuit, bSave,bListAllBooks;
    private JTextArea jtaLog;
    private JScrollPane scrollPane;
    
    public AddBookPanel(){
        super(new FlowLayout());
        initWidgets();
        addWidgets();
        setBackground(new Color(194, 230, 248));
    }

    private void initWidgets() {
        mainBox = Box.createVerticalBox();
        
        hBox1 = Box.createHorizontalBox();
        hBox2 = Box.createHorizontalBox();
        hBox2 = Box.createHorizontalBox();
        hBox3 = Box.createHorizontalBox();
        hBox4 = Box.createHorizontalBox();
        hBox5 = Box.createHorizontalBox();
        hBox6 = Box.createHorizontalBox();
        hBox7 = Box.createHorizontalBox();
        hBox8 = Box.createHorizontalBox();
        
        jlIsbn =    new JLabel("Enter ISBN:      ");
        jlTitle =   new JLabel("Enter Title:      ");
        jlAuthor =  new JLabel("Enter Author:  ");
        jlPrice =   new JLabel("Enter Price:     ");
        jlFile =    new JLabel("Add new File:  ");        
        jlLogDog =  new JLabel("LogDog:");
        
        jtIsbn = new JTextField(19);
        jtTitle = new JTextField(19);
        jtAuthor = new JTextField(19);
        jtPrice = new JTextField(19);
        jtFile = new JTextField(19);
        
        jtFile.setText("Optional");
        
        bBrowse = new JButton("Browse");
        bAddFile = new JButton("Add File To Book");
        bAddBook = new JButton("Add Book to Library");
        bSave = new JButton("Save");
        bSaveAndQuit = new JButton("Save and Quit");
        bListAllBooks = new JButton("List All Books");
        
        jtaLog = new JTextArea(10,24);
        jtaLog.setEditable(false);
        
        scrollPane = new JScrollPane(jtaLog);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
    }

    private void addWidgets() {
        hBox1.add(jlIsbn);
        hBox1.add(jtIsbn);
        
        hBox2.add(jlTitle);
        hBox2.add(jtTitle);
        
        hBox3.add(jlAuthor);
        hBox3.add(jtAuthor);
        
        hBox4.add(jlPrice);
        hBox4.add(jtPrice);
        
        hBox5.add(jlFile);
        hBox5.add(jtFile);
        
        hBox6.add(Box.createHorizontalStrut(85));
        hBox6.add(bBrowse);
        hBox6.add(Box.createHorizontalStrut(5));
        hBox6.add(bAddFile);
        
        hBox7.add(jlLogDog);
        hBox7.add(Box.createHorizontalStrut(110));
        hBox7.add(bAddBook);
        hBox8.add(bListAllBooks);
        hBox8.add(Box.createHorizontalStrut(10));
        hBox8.add(bSave);
        hBox8.add(Box.createHorizontalStrut(5));
        hBox8.add(bSaveAndQuit);
        
        mainBox.add(hBox1);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(hBox2);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(hBox3);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(hBox4);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(hBox5);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(hBox6);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(hBox7);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(scrollPane);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(hBox8);
        
        add(mainBox);
    }

    public void addActionListener(ActionListener a ) {
        bBrowse.addActionListener(a);
        bAddFile.addActionListener(a);
        bAddBook.addActionListener(a);
        bSave.addActionListener(a);
        bSaveAndQuit.addActionListener(a);
        bListAllBooks.addActionListener(a);
    }
    public JButton getListAllBooksButton(){
        return bListAllBooks;
    }
    
    public JButton getBrowseButton(){
        return bBrowse;
    }
    
    public JButton getAddFileButton(){
        return bAddFile;
    }
    
    public JButton getAddBookButton(){
        return bAddBook;
    }
    
    public JButton getSaveButton(){
        return bSave;
    }
    
    public JButton getSaveAndQuitButton(){
        return bSaveAndQuit;
    }

    public JTextField getTextFieldIsbn(){
        return jtIsbn;
    }
    
    public JTextField getTextFieldTitle(){
        return jtTitle;
    }
    
    public JTextField getTextFieldAuthor(){
        return jtAuthor;
    }
    
    public JTextField getTextFieldPrice(){
        return jtPrice;
    }
  
    public JTextField getTextFieldFile(){
        return jtFile;
    }
    
    public JTextArea getTextAreaLog(){
        return jtaLog;
    }
  
}
