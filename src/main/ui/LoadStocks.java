package ui;

import model.StockList;
import persistence.SaveAndLoad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//Handles Gui associated with loading the stocks
public class LoadStocks extends JFrame implements ActionListener {

    private JPanel panel;
    private final StockApp app;

    //EFFECTS: creates new gui
    public LoadStocks(StockApp app) {
        this.app = app;
        initializeJPanel();
        initializeJFrame();
    }

    //MODIFIES: this
    //EFFECTS: creates panel for user interface things
    private void initializeJPanel() {
        panel = new JPanel();
        GridLayout layout = new GridLayout(3, 3, 20, 20);
        panel.setLayout(layout);
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        addLabel("");
        addLogo();
        addLabel("");
        addLabel("");
        addLabel("Would you like to load your existing portfolio?");
        addLabel("");
        addButton("Load", "Load");
        addLabel("");
        addButton("Do Not Load", "NoLoad");
        add(panel);
        validate();
    }


    //MODIFIES: this
    //EFFECTS: adds logo to gui
    private void addLogo() {
        ImageIcon logoImage = new ImageIcon("./data/logo.jpg");
        Image modifiedLogo = logoImage.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH);
        ImageIcon newLogo = new ImageIcon(modifiedLogo);
        JLabel logo = new JLabel(newLogo);
        panel.add(logo);
        validate();

    }

    //MODIFIES: this
    //EFFECTS: adds new button with given buttonName and sets Action Command to given actionCommand
    private void addButton(String buttonName, String actionCommand) {
        JButton doNotLoad = new JButton(buttonName);
        doNotLoad.setActionCommand(actionCommand);
        doNotLoad.addActionListener(this);
        panel.add(doNotLoad);
    }


    //MODIFIES: this
    //EFFECTS: adds label with given string to the panel
    private void addLabel(String label) {
        JLabel mainLabel = new JLabel(label);
        panel.add(mainLabel);
    }


    //MODIFIES: this
    //EFFECTS: creates frame to house panel
    private void initializeJFrame() {
        setMinimumSize(new Dimension(getPreferredSize().width + 100, getPreferredSize().height + 100));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Load your existing Portfolio!");
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: handles logic associated with button presses
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Load".equals(e.getActionCommand())) {
            try {
                app.setStockPortfolio(SaveAndLoad.loadData());
                this.setVisible(false);
                this.dispose();
            } catch (ClassNotFoundException | IOException ioException) {
                JOptionPane.showMessageDialog(null, "Error! Unable to load Data!");
            }
        } else if ("NoLoad".equals(e.getActionCommand())) {
            app.setStockPortfolio(new StockList());
            this.setVisible(false);
            this.dispose();
        }

    }
}


