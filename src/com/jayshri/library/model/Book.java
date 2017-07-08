/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jayshri.library.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Book implements Serializable{

    private int isbn;
    private String title, author;
    private double price;

    private List<VIM> vims;

    public Book(int isbn, String title, String author, double price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
        vims = new ArrayList<VIM>();
    }

    public void addVIM(VIM v) {
        vims.add(v);
    }

    public String getTitle() {
        return title;
    }
    
    public int getIsbn(){
        return isbn;
    }
    public String getAuthor(){
        return author;
    }
    
    public String getPrice(){
        return  Double.toString(price);
    }

    public VIM getVIMByName(String name) {
        VIM v = null;
        Iterator<VIM> i = vims.iterator();
        while (i.hasNext()) {
            v = i.next();
            if (v.getName().toLowerCase().contentEquals(name.toLowerCase())) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String vimNames = "";
        String vimAmount = "(" + String.valueOf(vims.size()) + ")";

        Iterator<VIM> i = vims.iterator();
        while (i.hasNext()) {
            vimNames += i.next().getName() + ", ";
        }
        return "\nTitle: " + title + "\nAuthor:" + author + "\nPrice: " + price + "\nISBN: " + isbn + "\nVIM Files" + vimAmount + ": " + vimNames + "\n";
    }

    public String getSerialNumber() {
        return Integer.toString(isbn);
        
    }

    public String[][] toStringVectorFiles() {
        String[][] total = new String[vims.size()][3];
        VIM v;
        for(int i=0;i<vims.size();i++){
            v = vims.get(i);
            if(v.getName().endsWith("wav") || v.getName().endsWith("mp3")){
                total[i][0] = v.getName();
                
            }else if(v.getName().endsWith("png") || v.getName().endsWith("jpeg") 
                    || v.getName().endsWith("jpg")){
                total[i][1] = v.getName();
            }else{
                total[i][2] = v.getName();
            }
        }
        
        return total;
    }

}

