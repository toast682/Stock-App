package ui;

import model.StockList;
import persistence.SaveAndLoad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveStock extends JFrame implements ActionListener {

    private JLabel mainLabel;
    private JButton save;
    private JButton doNotSave;
    private JPanel panel;
    private GridLayout layout;
    private SaveAndLoad data;
    private StockList stockList;

    public SaveStock(StockList stockList) {
        this.stockList = stockList;
        initializeJPanel();
        initializeJFrame();

    }

    private void initializeJPanel() {
        panel = new JPanel();
        layout = new GridLayout(2, 3, 20, 20);
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

    private void addDoNotSave() {
        doNotSave = new JButton("Do not save");
        doNotSave.setActionCommand("NoSave");
        doNotSave.addActionListener(this);
        panel.add(doNotSave);
    }

    private void addSave() {
        save = new JButton("Save");
        save.setActionCommand("Save");
        save.addActionListener(this);
        panel.add(save);
    }

    private void addMainLabel() {
        mainLabel = new JLabel("Would you like to save your portfolio?");
        panel.add(mainLabel);
    }

    private void addBlank() {
        JLabel blank = new JLabel();
        panel.add(blank);
    }

    private void initializeJFrame() {
        setMinimumSize(new Dimension(getPreferredSize().width + 100, getPreferredSize().height + 100));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Save your Portfolio");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Save".equals(e.getActionCommand())) {
            try {
                data.saveData(stockList);
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
