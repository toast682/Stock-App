package ui;

import model.Stock;
import model.StockList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ShowStocks extends JFrame implements ActionListener {

    private static final String TITLE = "Look at all previous stocks";

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
        layout = new GridLayout(stockPortfolio.length() + 1, 1, 20, 20);
        mainPanel.setLayout(layout);
        mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JLabel label = new JLabel("These are the stocks you currently have:");
        label.setFont(new Font("Serif", Font.BOLD, 18));
        mainPanel.add(label);

        getAllStocks();

        initCloseButton();

        add(mainPanel);
    }


    private void initializeFrame() {
        setMinimumSize(getPreferredSize());
        System.out.println(getPreferredSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(TITLE);
        setVisible(true);
    }

    private void getAllStocks() {
        GridLayout subLayout = new GridLayout(7, 2, 5, 5);
        for (int i = 0; i < stockPortfolio.length(); i++) {
            JPanel panel = new JPanel(subLayout);
            Font labelName = new Font("Serif", Font.BOLD, 12);
            Font infoFont = new Font("Serif", Font.PLAIN, 12);

            JLabel name = new JLabel("Name:");
            name.setFont(labelName);
            panel.add(name);
            JLabel stockName = new JLabel(stockPortfolio.getIndex(i).getName());
            stockName.setFont(infoFont);
            panel.add(stockName);

            JLabel symbol = new JLabel("Symbol:");
            symbol.setFont(labelName);
            panel.add(symbol);
            JLabel stockSymbol = new JLabel(stockPortfolio.getIndex(i).getSymbol());
            stockSymbol.setFont(infoFont);
            panel.add(stockSymbol);

            JLabel amount = new JLabel("Amount:");
            amount.setFont(labelName);
            panel.add(amount);
            JLabel stockAmount = new JLabel(Integer.toString(stockPortfolio.getIndex(i).getAmount()));
            stockAmount.setFont(infoFont);
            panel.add(stockAmount);


            JLabel purchasePrice = new JLabel("Purchase Price:");
            purchasePrice.setFont(labelName);
            panel.add(purchasePrice);
            JLabel stockPurchasePrice = new JLabel(Double.toString(stockPortfolio.getIndex(i).getPurchasePrice()));
            stockPurchasePrice.setFont(infoFont);
            panel.add(stockPurchasePrice);

            JLabel purchaseDate = new JLabel("Purchase Date:");
            purchaseDate.setFont(labelName);
            panel.add(purchasePrice);
            JLabel stockPurchaseDate = new JLabel(stockPortfolio.getIndex(i).getPurchaseDate().toString());
            stockPurchasePrice.setFont(infoFont);
            panel.add(stockPurchaseDate);


            mainPanel.add(panel);
        }
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
            System.out.println("Hello!");
        }

    }
}

//    private void showAllStock(String s) {
//        System.out.println(s);
//        System.out.println("These are the stocks you currently have: \n");
//        for (int i = 0; i <= stockPortfolio.length() - 1; i++) {
//            Stock stockI = stockPortfolio.getIndex(i);
//            stockInfo(stockI);
//        }
//    }


//    private void stockInfo(Stock stock) {
//        System.out.println("Symbol: " + stock.getSymbol());
//        System.out.println("name: " + stock.getName());
//        System.out.println("Purchase Price: " + stock.getPurchasePrice());
//        System.out.println("Amount: " + stock.getAmount());
//        System.out.println("Purchase Date: " + stock.getPurchaseDate());
//        System.out.println("Current Price: " + stock.getPriceHistory().getStockPrice().getPrice());
//        System.out.println("As of date: " + stock.getPriceHistory().getStockPrice().getDate() + "\n");
//    }
