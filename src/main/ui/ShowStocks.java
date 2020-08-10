package ui;

import model.StockList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowStocks extends JFrame implements ActionListener {

    private static final String TITLE = "Look at all previous stocks";
    private final int fontSize = 12;
    private final int labelFontSize = 18;
    private final int mainHorizontalGap = 20;
    private final int mainVerticalGap = 20;

    private StockList stockPortfolio;

    private JPanel mainPanel;
    private GridLayout layout;

    public ShowStocks(StockList stockList) {
        this.stockPortfolio = stockList;
        initializePanel();
        initializeFrame();
    }

    private void initializePanel() {
        mainPanel = new JPanel();
        layout = new GridLayout(stockPortfolio.length() + 2, 1, mainHorizontalGap, mainVerticalGap);
        mainPanel.setLayout(layout);
        mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        initMainLabel();

        getAllStocks();
        initCloseButton();
        add(mainPanel);
    }

    private void initMainLabel() {
        JLabel label = new JLabel("These are the stocks you currently have:");
        label.setFont(new Font("Serif", Font.BOLD, labelFontSize));
        mainPanel.add(label);
    }

    private void initializeFrame() {
        setMinimumSize(new Dimension(getPreferredSize().width + 30, getPreferredSize().height));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(TITLE);
        setVisible(true);
    }

    private void getAllStocks() {
        GridLayout subLayout = new GridLayout(7, 2, 5, 5);
        for (int i = 0; i < stockPortfolio.length(); i++) {
            JPanel panel = new JPanel(subLayout);
            Font labelName = new Font("Serif", Font.BOLD, fontSize);
            Font infoFont = new Font("Serif", Font.PLAIN, fontSize);

            getName(i, panel, labelName, infoFont);
            getSymbol(i, panel, labelName, infoFont);
            getAmount(i, panel, labelName, infoFont);
            getPurchasePrice(i, panel, labelName, infoFont);
            getPurchaseDate(i, panel, labelName, infoFont);
            getCurrentPrice(i, panel, labelName, infoFont);
            getCurrentDate(i, panel, labelName, infoFont);


            mainPanel.add(panel);
        }
    }

    private void getName(int i, JPanel panel, Font labelName, Font infoFont) {
        JLabel name = new JLabel("Name:");
        name.setFont(labelName);
        panel.add(name);
        JLabel stockName = new JLabel(stockPortfolio.getIndex(i).getName());
        stockName.setFont(infoFont);
        panel.add(stockName);
    }

    private void getSymbol(int i, JPanel panel, Font labelName, Font infoFont) {
        JLabel symbol = new JLabel("Symbol:");
        symbol.setFont(labelName);
        panel.add(symbol);
        JLabel stockSymbol = new JLabel(stockPortfolio.getIndex(i).getSymbol());
        stockSymbol.setFont(infoFont);
        panel.add(stockSymbol);
    }

    private void getAmount(int i, JPanel panel, Font labelName, Font infoFont) {
        JLabel amount = new JLabel("Amount:");
        amount.setFont(labelName);
        panel.add(amount);
        JLabel stockAmount = new JLabel(Integer.toString(stockPortfolio.getIndex(i).getAmount()));
        stockAmount.setFont(infoFont);
        panel.add(stockAmount);
    }

    private void getPurchasePrice(int i, JPanel panel, Font labelName, Font infoFont) {
        JLabel purchasePrice = new JLabel("Purchase Price:");
        purchasePrice.setFont(labelName);
        panel.add(purchasePrice);
        JLabel stockPurchasePrice = new JLabel(Double.toString(stockPortfolio.getIndex(i).getPurchasePrice()));
        stockPurchasePrice.setFont(infoFont);
        panel.add(stockPurchasePrice);
    }

    private void getPurchaseDate(int i, JPanel panel, Font labelName, Font infoFont) {
        JLabel purchaseDate = new JLabel("Purchase Date:");
        purchaseDate.setFont(labelName);
        panel.add(purchaseDate);
        JLabel stockPurchaseDate = new JLabel(stockPortfolio.getIndex(i).getPurchaseDate().toString());
        stockPurchaseDate.setFont(infoFont);
        panel.add(stockPurchaseDate);
    }

    private void getCurrentPrice(int i, JPanel panel, Font labelName, Font infoFont) {
        JLabel currentPrice = new JLabel("Current Price:");
        currentPrice.setFont(labelName);
        panel.add(currentPrice);
        JLabel stockCurrentPrice = new JLabel(
                Double.toString(stockPortfolio.getIndex(i).getPriceHistory().getStockPrice().getPrice()));
        stockCurrentPrice.setFont(infoFont);
        panel.add(stockCurrentPrice);
    }

    private void getCurrentDate(int i, JPanel panel, Font labelName, Font infoFont) {
        JLabel currentDate = new JLabel("As of Date:");
        currentDate.setFont(labelName);
        panel.add(currentDate);
        JLabel stockCurrentDate = new JLabel(
                stockPortfolio.getIndex(i).getPriceHistory().getStockPrice().getDate().toString());
        stockCurrentDate.setFont(infoFont);
        panel.add(stockCurrentDate);
    }

    private void initCloseButton() {
        JButton back = new JButton("Back");
        back.setActionCommand("Close");
        back.addActionListener(this);
        mainPanel.add(back);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Close".equals(e.getActionCommand())) {
            setVisible(false);
            dispose();
        }

    }
}