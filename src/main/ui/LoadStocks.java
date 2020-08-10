package ui;

import model.StockList;
import persistence.SaveAndLoad;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LoadStocks extends JFrame implements ActionListener {


    private JLabel mainLabel;
    private JButton load;
    private JButton doNotLoad;
    private JPanel panel;
    private GridLayout layout;
    private SaveAndLoad data;
    private StockList stockList;
    private StockApp app;

    public LoadStocks(StockApp app) {
        this.app = app;
        initializeJPanel();
        initializeJFrame();
    }

    private void initializeJPanel() {
        panel = new JPanel();
        layout = new GridLayout(3, 3, 20, 20);
        panel.setLayout(layout);
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        addBlank();
//        addLogo();

        addBlank();
        addMainLabel();
        addBlank();
        addLoad();
        addBlank();
        addDoNotLoad();
        add(panel);
    }

//    private void addLogo() {
//        JPanel panel = new JPanel();
//
//    }

    private void addDoNotLoad() {
        doNotLoad = new JButton("Do Not Load");
        doNotLoad.setActionCommand("NoLoad");
        doNotLoad.addActionListener(this);
        panel.add(doNotLoad);
    }

    private void addLoad() {
        load = new JButton("Load");
        load.setActionCommand("Load");
        load.addActionListener(this);
        panel.add(load);
    }

    private void addMainLabel() {
        mainLabel = new JLabel("Would you like to load your existing portfolio?");
        panel.add(mainLabel);
    }

    private void addBlank() {
        JLabel blank = new JLabel();
        panel.add(blank);
    }

    private void initializeJFrame() {
        setMinimumSize(new Dimension(getPreferredSize().width + 100, getPreferredSize().height + 100));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Load your existing Portfolio!");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Load".equals(e.getActionCommand())) {
            try {
                app.setStockPortfolio(data.loadData());
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

    public StockList getStockList() {
        return this.stockList;
    }
}


