package model;

import java.io.Serializable;
import java.util.LinkedList;

// Represents a list of Stock Prices
public class StockPriceList implements Serializable {

    LinkedList<StockPrice> priceList;

    public StockPriceList() {
        priceList = new LinkedList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a new Stock Price
    public void add(StockPrice price) {
        priceList.add(price);
    }

    //REQUIRES: Price List not be empty
    //EFFECTS: Returns last added Stock Price
    public StockPrice getStockPrice() {
        return priceList.getLast();
    }

    //EFFECTS: Returns the length Stock Price List
    public int length() {
        return priceList.size();
    }

    //EFFECTS: Returns true if List contains StockPrice, false otherwise
    public boolean contains(StockPrice stockPrice) {
        for (StockPrice price : priceList) {
            if (price.equals(stockPrice)) {
                return true;
            }
        }
        return false;
    }
}
