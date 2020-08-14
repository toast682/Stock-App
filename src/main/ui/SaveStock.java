package ui;

import model.StockList;
import persistence.SaveAndLoad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//Handles GUI associated with saving stock
public class SaveStock extends JFrame implements ActionListener {

    private JPanel panel;
    private final StockList stockList;

    //EFFECTS: Creates gui to handle saving stock
    public SaveStock(StockList stockList) {
        this.stockList = stockList;
        initializeJPanel();
        initializeJFrame();

    }

    //MODIFIES: this
    //EFFECTS: Creates panel to house all user interaction components
    private void initializeJPanel() {
        panel = new JPanel();
        GridLayout layout = new GridLayout(2, 3, 20, 20);
        panel.setLayout(layout);
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        addBlank();
        addMainLabel();
        addBlank();
        addSave();
        addBlank();
        addDoNotSave();
        add(panel);
    }

    //MODIFIES: this
    //EFFECTS: adds do not save button
    private void addDoNotSave() {
        JButton doNotSave = new JButton("Do not save");
        doNotSave.setActionCommand("NoSave");
        doNotSave.addActionListener(this);
        panel.add(doNotSave);
    }

    //MODIFIES: this
    //EFFECTS: adds save button
    private void addSave() {
        JButton save = new JButton("Save");
        save.setActionCommand("Save");
        save.addActionListener(this);
        panel.add(save);
    }

    //MODIFIES: this
    //EFFECTS: adds main label button
    private void addMainLabel() {
        JLabel mainLabel = new JLabel("Would you like to save your portfolio?");
        panel.add(mainLabel);
    }

    //MODIFIES: this
    //EFFECTS: adds blank label
    private void addBlank() {
        JLabel blank = new JLabel();
        panel.add(blank);
    }

    //MODIFIES: this
    //EFFECTS: Creates frame to hold panel
    private void initializeJFrame() {
        setMinimumSize(new Dimension(getPreferredSize().width + 100, getPreferredSize().height + 100));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Save your Portfolio");
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: handles logic associated with button presses
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Save".equals(e.getActionCommand())) {
            try {
                SaveAndLoad.saveData(stockList);
                this.setVisible(false);
                this.dispose();
                System.exit(0);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "Error! Unable to save Data!");
            }
        } else if ("NoSave".equals(e.getActionCommand())) {
            this.setVisible(false);
            this.dispose();
            System.exit(0);
        }

    }
}
