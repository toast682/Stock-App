package ui;

import model.StockList;
import persistence.SaveAndLoad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoadExistingStockGUI extends JFrame implements ActionListener {

    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;
    public static final String TITLE = "Amogh's Super Stock App";

    private final GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JButton yes;
    private JButton no;
    private StockApp stockApp;
    private StockList stockList;
    private SaveAndLoad data;

    public LoadExistingStockGUI() {
        initializeJFrame();
        initializeJPanel();

    }

    private void initializeJFrame() {
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(TITLE);
        add(initializeJPanel());
    }

    private JPanel initializeJPanel() {
        JPanel panel = new JPanel(layout);
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel loadExistingLabel = initializeJLabel("Do you want to load your existing Stock Portfolio?");
        setGbcXYPosition(1, 0);
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(loadExistingLabel, gbc);

        yes = initializeJButton("Yes");
        yes.addActionListener(this);
        gbc.gridwidth = 1;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        setGbcXYPosition(0, 1);
        panel.add(yes, gbc);
        yes.setActionCommand("Yes");


        no = initializeJButton("No");
        setGbcXYPosition(3, 1);
        gbc.gridwidth = 1;
        panel.add(no, gbc);
        no.addActionListener(this);
        no.setActionCommand("No");
        return panel;
    }

    private void setGbcXYPosition(int i, int i2) {
        gbc.gridx = i;
        gbc.gridy = i2;
    }

    private JButton initializeJButton(String label) {
        return new JButton(label);
    }

    private JLabel initializeJLabel(String label) {
        JLabel newLabel = new JLabel(label);
        newLabel.setFont(new Font("Serif", Font.BOLD, 22));
        return newLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Yes".equals(e.getActionCommand())) {
            loadSavedData();
            this.setVisible(false);
            this.dispose();
        } else if ("No".equals(e.getActionCommand())) {
            stockList = new StockList();
            this.setVisible(false);
            this.dispose();
        }
    }

    private void loadSavedData() {
        try {
            stockList = data.loadData();
        } catch (IOException ioException) {
            System.out.println("There was an error reading the given data. Exiting the program");
            System.exit(-1);
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("That Class does not exist.");
            classNotFoundException.printStackTrace();
        }
    }
}

