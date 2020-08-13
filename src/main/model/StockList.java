package model;

import exceptions.MassiveStockFindError;

import java.io.Serializable;
import java.util.LinkedList;

// Represents a list of stocks
public class StockList implements Serializable {

    LinkedList<Stock> list;

    public StockList() {
        list = new LinkedList<>();
    }

    //MODIFIES: this
    //EFFECTS: Adds stock to the stock list
    public void buyStock(Stock newStock) {
        list.add(newStock);
    }


    //MODIFIES: this
    //EFFECTS: Sells stock if contained within list, otherwise do nothing
    public void sellStock(String symbol, int amount) {
        Stock stock;
        try {
            stock = findStock(symbol, amount);
            list.remove(stock);
        } catch (MassiveStockFindError e) {
            System.out.println("There was an error trying to find the stock. Cannot find stock");
        }
    }


    //REQUIRES: Stock has already been added to StockList
    //EFFECTS: Finds all the information about the given stock. However, this method is always called on a list
    //         containing the stock, thus it will always return a stock. In teh even that it does not, it will throw an
    //         error.
    public Stock findStock(String symbol, int amount) throws MassiveStockFindError {
        symbol = symbol.toUpperCase().trim();
        for (Stock stock : list) {
            if (stock.getSymbol().equals(symbol) && stock.getAmount() == amount) {
                return stock;
            }
        }
        throw new MassiveStockFindError();
    }


    //EFFECTS: Returns the length of the StockList
    public int length() {
        return list.size();
    }

    public boolean contains(String symbol, int amount) {
        symbol = symbol.toUpperCase().trim();
        for (Stock stock : list) {
            if (stock.getSymbol().equals(symbol) && stock.getAmount() == amount) {
                return true;
            }
        }
        return false;
    }

    //REQUIRES: i be a specified index of list
    //EFFECTS: Returns the ith element of the StockList
    public Stock getIndex(int i) {
        return list.get(i);
    }
}
