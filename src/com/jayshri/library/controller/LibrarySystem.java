/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jayshri.library.controller;

import com.jayshri.library.model.Book;
import com.jayshri.library.model.Library;
import com.jayshri.library.model.VIM;
import com.jayshri.library.view.AddBookPanel;
import com.jayshri.library.view.BrowseLibraryPanel;
import com.jayshri.library.view.LibraryInterface;
import com.jayshri.library.view.LoadScreen;
import com.jayshri.library.view.MyTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LibrarySystem implements ChangeListener, ActionListener {

    private LibraryInterface screen;
    private AddBookPanel abp;
    private LoadScreen ls;
    private BrowseLibraryPanel blp;
    private String fileName;
    private Library lib;
    private JFileChooser chooser;
    private FileFilter filter, filter2;
    private int resultCode;
    private File vimFile, saveFile, libFile;
    private String[] validFileTypes = {".jpg", ".wav", ".mp3", ".avi", ".mp4", ".png", ".jpeg"};
    private List<VIM> vimCache;
    private FileOutputStream fos;
    private FileInputStream fis;
    private String validFileTypeReminder;
    private ObjectOutputStream out;
    private boolean exit;
    private ObjectInputStream in;
    private String[][] dataBook, dataFile;

    public LibrarySystem() {
        initEventAttributes();
        screen = new LibraryInterface("Library System");
        abp = screen.getAddBookPanel();
        blp = screen.getBrowseLibraryPanel();
        screen.getTabbedPane().addChangeListener(this);
        abp.addActionListener(this);
        blp.addActionListenr(this);

        ls = new LoadScreen("Welcome");
        ls.addActionListener(this);
        ls.setVisible(true);

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (screen.getTabbedPane().getSelectedIndex() == 1) {
            screen.getTabbedPane().setTitleAt(1, screen.getFilter() + "  Browse Library  " + screen.getFilter());
            //screen.setSize(400,460);
            screen.setSize(430, 490);

        } else {
            screen.getTabbedPane().setTitleAt(0, screen.getFilter() + screen.getFilter() + "Add Book" + screen.getFilter() + screen.getFilter());
            screen.setSize(400, 520);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == abp.getBrowseButton()) {
            openChooserAndSetTheVIMFile();
        } else if (event.getSource() == abp.getAddFileButton()) {
            addVimFileToCache();
        } else if (event.getSource() == abp.getAddBookButton()) {
            addVimFilesInVimCacheToBookAndAddBookToLibrary();
            reloadDataBook();
            reloadDataFile();
        } else if (event.getSource() == abp.getListAllBooksButton()) {
            listAllBooksInLibrary();
        } else if (event.getSource() == abp.getSaveButton()) {
            save();
        } else if (event.getSource() == abp.getSaveAndQuitButton()) {
            saveAndQuit();
        } else if (event.getSource() == blp.getButtonOpenBook()) {
            openBook();
        } else if (event.getSource() == blp.getButtonDeleteBook()) {
            deleteBook();
        } else if (event.getSource() == blp.getButtonOpenFile()) {
            openFile();
        } else if (event.getSource() == blp.getButtonDeleteFile()) {
            deleteFile();
        } else if (event.getSource() == blp.getButtonSave()) {
            save();
        } else if (event.getSource() == blp.getButtonSaveAndQuit()) {
            saveAndQuit();
        } else if (event.getSource() == ls.getButtonLoad()) {
            //clear the data inside the table if any
            ((MyTableModel) blp.getBookTable().getModel()).getDataVector().clear();
            ((MyTableModel) blp.getFileTable().getModel()).getDataVector().clear();
            reloadDataBook();
            reloadDataFile();
            loadLibrary();
            chooser.setFileFilter(filter);

        } else if (event.getSource() == ls.getButtonNew()) {
            ls.dispose();
            screen.setVisible(true);
        } else if (event.getSource() == ls.getButtonExit()) {
            //Exit button from LoadScreen
            System.exit(0);
        }

    }

    private void initEventAttributes() {

        chooser = new JFileChooser();

        filter = new FileNameExtensionFilter("Video/Image/Music Files", "jpg", "wav", "mp3", "avi", "mp4", "png", "jpeg");
        filter2 = new FileNameExtensionFilter("Library Files", "ser");

        chooser.addChoosableFileFilter(filter);
        chooser.addChoosableFileFilter(filter2);

        // chooser.setFileFilter(filter);
        lib = new Library();
        vimFile = null;
        saveFile = null;
        exit = false;
        vimCache = new ArrayList<VIM>();
        validFileTypeReminder = "Valid File Types Are End With .jpg, .avi, .jpeg, .mp3, .mp4, .wav, .png";
        fileName = "Library";
    }

    private void openChooserAndSetTheVIMFile() {
        resultCode = chooser.showOpenDialog(screen);

        if (resultCode == JFileChooser.APPROVE_OPTION) {
            vimFile = chooser.getSelectedFile();
            abp.getTextFieldFile().setText(vimFile.getName());
        }
    }

    private void addVimFileToCache() {
        if (vimFile != null) {
            for (int i = 0; i < validFileTypes.length; i++) {
                if (abp.getTextFieldFile().getText().trim().endsWith(validFileTypes[i])) {
                    byte[] data = new byte[(int) vimFile.length()];
                    try {
                        fis = new FileInputStream(vimFile);
                        fis.read(data);
                        fis.close();
                    } catch (FileNotFoundException ex) {
                        //"File Not zfound"
                        JOptionPane.showMessageDialog(screen, "File Not Found");
                    } catch (IOException ex) {
                        // "Error reading file"
                        JOptionPane.showMessageDialog(screen, "Error reading file");
                    }

                    VIM v = new VIM(abp.getTextFieldFile().getText().trim(), data);
                    vimCache.add(v);
                    vimFile = null;
                    abp.getTextAreaLog().append("\n" + abp.getTextFieldFile().getText().trim()
                            + " is ready to be added to book.");
                    abp.getTextFieldFile().setText("Optional");
                    return;
                }

            }
            JOptionPane.showMessageDialog(screen,
                    "Something went wrong!\n Please click browse again"
                    + "and choose file");

        } else {

            JOptionPane.showMessageDialog(screen, "\n" + abp.getTextFieldFile().getText().trim()
                    + " is not a valid file type" + "\n" + validFileTypeReminder);
        }
    }

    private void addVimFilesInVimCacheToBookAndAddBookToLibrary() {
        boolean ISBNAlreadyExist = false;

        boolean allFieldsAreFilled = false;

        int isbn = 0;
        double price = 0.0;
        Book b = null;
        if (!abp.getTextFieldPrice().getText().trim().contentEquals("")
                && !abp.getTextFieldTitle().getText().trim().contentEquals("")
                && !abp.getTextFieldAuthor().getText().trim().contentEquals("")
                && !abp.getTextFieldIsbn().getText().trim().contentEquals("")) {
            allFieldsAreFilled = true;
        }
        if (allFieldsAreFilled) {
            try {
                isbn = Integer.parseInt(abp.getTextFieldIsbn().getText().trim());
                price = Double.parseDouble(abp.getTextFieldPrice().getText().trim());
                ISBNAlreadyExist = lib.doesISBNAlreadyExist(isbn);
                if (ISBNAlreadyExist) {
                    JOptionPane.showMessageDialog(screen, isbn
                            + " already exist!\n Please use another ISBN");
                } else {
                    b = new Book(isbn, abp.getTextFieldTitle().getText().trim(),
                            abp.getTextFieldAuthor().getText().trim(), price);
                    for (int i = 0; i < vimCache.size(); i++) {
                        b.addVIM(vimCache.get(i));
                    }
                    lib.addBook(b);
                    abp.getTextFieldPrice().setText("");
                    abp.getTextFieldTitle().setText("");
                    abp.getTextFieldAuthor().setText("");
                    abp.getTextFieldIsbn().setText("");
                    abp.getTextAreaLog().append("\n" + b.getTitle()
                            + " has been added to library");
                    vimCache = new ArrayList<VIM>(); 

                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(screen, "ISBN or Price is not a Number ");
            }

        } else {
            JOptionPane.showMessageDialog(screen, "Please fill up all non-ooptional fields");
        }
    }

    private void listAllBooksInLibrary() {
        abp.getTextAreaLog().setText("> Listing all books in Library");
        abp.getTextAreaLog().append(" " + lib.toString());

    }

    private void save() {

        fileName = JOptionPane.showInputDialog(screen, "Enter File name to save as..",
                "save", JOptionPane.INFORMATION_MESSAGE);
        if (fileName != null) {
            if (!fileName.trim().contentEquals("")) {
                FileOutputStream fos = null;
                ObjectOutputStream out = null;
                try {
                    saveFile = new File(fileName + ".ser");
                    if (!saveFile.exists()) {
                        fos = new FileOutputStream(saveFile);
                        out = new ObjectOutputStream(fos);
                        out.writeObject(lib);
                        fos.close();
                        out.close();
                        exit = true;
                    } else {
                        int resultCode = JOptionPane.showConfirmDialog(screen,
                                "File with this name is already exists,\n Overwrite it?",
                                "Warning", JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                        if (resultCode == JOptionPane.YES_OPTION) {
                            fos = new FileOutputStream(saveFile);
                            out = new ObjectOutputStream(fos);
                            out.writeObject(lib);
                            fos.close();
                            out.close();
                            exit = true;
                        } else {
                            abp.getTextAreaLog().append("\n> save cancelled");
                            exit = false;
                        }
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(LibrarySystem.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                abp.getTextAreaLog().append("\n> save cancelled");
                exit = false;
            }
        } else {
            abp.getTextAreaLog().append("\n> save cancelled");
            exit = false;
        }
    }

    private void saveAndQuit() {
        save();
        if (exit) {
            System.exit(0);
        }
    }

    private void loadLibrary() {
        chooser.setFileFilter(filter2);
        resultCode = chooser.showOpenDialog(screen);
        if (resultCode == JFileChooser.APPROVE_OPTION) {
            libFile = chooser.getSelectedFile();
            try {
                fis = new FileInputStream(libFile);
                in = new ObjectInputStream(fis);
                lib = (Library) in.readObject();
                in.close();
                screen.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ls.dispose();

            //Load Book Table
            reloadDataBook();
            screen.setVisible(true);
        }
    }

    private void reloadDataBook() {

        while (((MyTableModel) blp.getBookTable().getModel()).getRowCount() > 0) {
            ((MyTableModel) blp.getBookTable().getModel()).removeRow(0);
        }

        dataBook = lib.toStringVector();
        for (int i = 0; i < dataBook.length; i++) {
            ((MyTableModel) blp.getBookTable().getModel()).addRow(dataBook[i]);

        }

    }

    private void reloadDataFile() {
        while (((MyTableModel) blp.getFileTable().getModel()).getRowCount() > 0) {
            ((MyTableModel) blp.getFileTable().getModel()).removeRow(0);
        }
        if (dataFile != null) {
            for (int i = 0; i < dataFile.length; i++) {
                ((MyTableModel) blp.getFileTable().getModel()).insertRow(i, dataFile[i]);

            }
        }
    }

    private void openBook() {
        int row,column;
        String isbn;
        Book book;
        row = ((JTable) blp.getBookTable()).getSelectedRow();
        column = 3;
        isbn = ((JTable) blp.getBookTable()).getValueAt(row,column).toString();
        book = lib.getBookByISBN(isbn);
        
        dataFile = book.toStringVectorFiles();
        reloadDataFile();
    }

    private void deleteBook() {

    }

    private void openFile() {

    }

    private void deleteFile() {

    }

}
