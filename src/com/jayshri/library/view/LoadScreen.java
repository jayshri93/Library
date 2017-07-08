/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jayshri.library.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author jayu
 */
public class LoadScreen extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JLabel jlMessage;
    private JButton jbnew,jbload,jbexit;
    
    public LoadScreen(String title){
        super(title);
        initWidgets();
        addWidgets();
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(380,125);
        setResizable(false);
    }

    private void initWidgets() {
        panel = new JPanel(new FlowLayout());
        jlMessage = new JLabel("Would you ike to start a new library? or load up one?");
        jbnew = new JButton("Start new library");
        jbload = new JButton("Load saved library");
        jbexit = new JButton("Exit");
    }

    private void addWidgets() {
        panel.add(jlMessage);
        panel.add(jbload);
        panel.add(jbnew);
        panel.add(jbexit);
        
        panel.setBackground(new Color(194,230,248));
        setContentPane(panel);
    }
    
    public void addActionListener(ActionListener l){
        jbnew.addActionListener(l);
        jbload.addActionListener(l);
        jbexit.addActionListener(l);
    }
    
    public JButton getButtonLoad(){
        return jbload;
    }
    public JButton getButtonNew(){
        return jbnew;
    }
    
    public JButton getButtonExit(){
        return jbexit;
    }
}
