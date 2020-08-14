package ui;

import model.StockList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Creates GUI to print out all stocks that the user owns
public class ShowStocks extends JFrame implements ActionListener {

    private static final String TITLE = "Look at all previous stocks";
    private static final int fontSize = 12;
    private static final int labelFontSize = 18;
    private static final int mainHorizontalGap = 20;
    private static final int mainVerticalGap = 20;

    private final StockList stockPortfolio;

    private JPanel mainPanel;

    //EFFECTS: Creates new showStock gui
    public ShowStocks(StockList stockList) {
        this.stockPortfolio = stockList;
        initializePanel();
        initializeFrame();
    }

    //MODIFIES: this
    //EFFECTS: creates new panel to hold components
    private void initializePanel() {
        mainPanel = new JPanel();
        GridLayout layout = new GridLayout(stockPortfolio.length() + 2, 1, mainHorizontalGap,
                mainVerticalGap);

        mainPanel.setLayout(layout);
        mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        initMainLabel();
        getAllStocks();
        initCloseButton();
        add(mainPanel);
    }

    //MODIFIES: this
    //EFFECTS: adds main label
    private void initMainLabel() {
        JLabel label = new JLabel("These are the stocks you currently have:");
        label.setFont(new Font("Serif", Font.BOLD, labelFontSize));

        mainPanel.add(label);
    }

    //MODIFIES: this
    //EFFECTS: creates new frame to hold panel
    private void initializeFrame() {
        setMinimumSize(new Dimension(getPreferredSize().width + 30, getPreferredSize().height));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(TITLE);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: Prints our all the information related to all the given stocks that are owned by the user
    private void getAllStocks() {
        GridLayout subLayout = new GridLayout(7, 2, 5, 5);
        for (int i = 0; i < stockPortfolio.length(); i++) {
            JPanel panel = new JPanel(subLayout);
            Font labelName = new Font("Serif", Font.BOLD, fontSize);
            Font infoFont = new Font("Serif", Font.PLAIN, fontSize);

            getAttribute(stockPortfolio.getIndex(i).getName(), panel, labelName, infoFont, "Name:");
            getAttribute(stockPortfolio.getIndex(i).getSymbol(), panel, labelName, infoFont, "Symbol:");
            getAttribute(Integer.toString(stockPortfolio.getIndex(i).getAmount()), panel, labelName, infoFont,
                    "Amount:");
            getAttribute(Double.toString(stockPortfolio.getIndex(i).getPurchasePrice()), panel, labelName, infoFont,
                    "Purchase Price:");
            getAttribute(stockPortfolio.getIndex(i).getPurchaseDate().toString(), panel, labelName, infoFont,
                    "Purchase Date:");
            getAttribute(Double.toString(stockPortfolio.getIndex(i).getPriceHistory().getStockPrice().getPrice()),
                    panel, labelName, infoFont, "Current Date:");
            getAttribute(stockPortfolio.getIndex(i).getPriceHistory().getStockPrice().getDate().toString(), panel,
                    labelName, infoFont, "As of date: ");
            mainPanel.add(panel);
        }
    }

    //MODIFIES: this
    //EFFECTS: Method that gets given attribute from stock and adds to the main panel.
    private void getAttribute(String attribute, JPanel panel, Font labelFont, Font infoFont, String label) {
        JLabel purchasePrice = new JLabel(label);
        purchasePrice.setFont(labelFont);
        panel.add(purchasePrice);
        JLabel stockPurchasePrice = new JLabel(attribute);
        stockPurchasePrice.setFont(infoFont);
        panel.add(stockPurchasePrice);
    }

    //MODIFIES: this
    //EFFECTS: creates close button
    private void initCloseButton() {
        JButton back = new JButton("Back");
        back.setActionCommand("Back");
        back.addActionListener(this);
        mainPanel.add(back);
    }

    //MODIFIES: this
    //EFFECTS: handles logic associated with button
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Back".equals(e.getActionCommand())) {
            setVisible(false);
            dispose();
        }

    }
}
