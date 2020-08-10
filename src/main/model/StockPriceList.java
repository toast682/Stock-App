package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;

// Represents a list of Stock Prices
public class StockPriceList implements Serializable {

    public LinkedList<StockPrice> priceList;

    public StockPriceList() {
        priceList = new LinkedList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a new Stock Price
    public void add(StockPrice price) {
        priceList.add(price);
        sort();
    }

    public void sort() {
        priceList.sort(new Comparator<StockPrice>() {
            @Override
            public int compare(StockPrice o1, StockPrice o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
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
