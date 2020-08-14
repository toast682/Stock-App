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
        addBlank();
        addLogo();
        addBlank();
        addBlank();
        addMainLabel();
        addBlank();
        addLoad();
        addBlank();
        addDoNotLoad();
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
    //EFFECTS: adds do not load button to gui
    private void addDoNotLoad() {
        JButton doNotLoad = new JButton("Do Not Load");
        doNotLoad.setActionCommand("NoLoad");
        doNotLoad.addActionListener(this);
        panel.add(doNotLoad);
    }

    //MODIFIES: this
    //EFFECTS: adds a load button to panel
    private void addLoad() {
        JButton load = new JButton("Load");
        load.setActionCommand("Load");
        load.addActionListener(this);
        panel.add(load);
    }

    //MODIFIES: this
    //EFFECTS: adds main label to gui
    private void addMainLabel() {
        JLabel mainLabel = new JLabel("Would you like to load your existing portfolio?");
        panel.add(mainLabel);
    }

    //MODIFIES: this
    //EFFECTS: adds blank label to gui
    private void addBlank() {
        JLabel blank = new JLabel();
        panel.add(blank);
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


