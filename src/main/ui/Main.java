package ui;

import model.Stock;
import model.StockList;


//Main Class, with Main method.
public class Main {
    public static void main(String[] args) {
        StockList stocks = new StockList();
        Stock stock = new Stock();
        stocks.buyStock(stock);
        new ShowStocks(stocks);
//        new LoadExistingStockGUI();
//        new StockApp();

    }
}


