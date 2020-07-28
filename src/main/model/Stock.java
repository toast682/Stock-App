//I used some help from this stack overflow thread
// https://stackoverflow.com/questions/42522744/taking-input-using-localdate

package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Represents a stock with a ticker symbol, a purchase price, a name for the company to which the stock belongs to
// a purchase date in the format yyyy-MM-dd, a price history consisting of StockPriceList, and the amount of stock
// owned
public class Stock {

    private String symbol;
    private double purchasePrice;
    private String name;
    private LocalDate purchaseDate;

    private StockPriceList priceHistory;
    private int amount;


    //EFFECTS: Make a new Stock with a defaut name that can be changed later
    public Stock() {
        LocalDate date = LocalDate.now();

        this.symbol = "DEF";
        this.name = "default";
        this.purchasePrice = 0;
        this.amount = 0;
        this.purchaseDate = date;
        this.priceHistory = new StockPriceList();
    }

    //REQUIRES: Date is in form "yyyy-MM-dd"
    //MODIFIES: this
    //EFFECTS: Updates the price of the stock at the current date
    public void addNewPriceHistory(String date, double currentPrice) {
        StockPrice stockPrice = new StockPrice();

        stockPrice.setDate(date);
        stockPrice.setPrice(currentPrice);
        priceHistory.add(stockPrice);
    }

    //MODIFIES: this
    //EFFECTS: Updates the symbol of the stock to given symbol
    public void setSymbol(String symbol) {
        this.symbol = symbol.toUpperCase().trim();
    }

    //MODIFIES: this
    //EFFECTS: Updates the name of the stock to given name
    public void setName(String name) {
        this.name = name.toLowerCase().trim();
    }

    //MODIFIES: this
    //EFFECTS: Updates the purchase price of the stock to the given price
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    //REQUIRES: Date be in yyyy-MM-dd format
    //MODIFIES: this
    //EFFECTS: Updates the purchase date of the stock to the given date
    public void setPurchaseDate(String purchaseDate) {
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.purchaseDate = LocalDate.parse(purchaseDate, formattedDate);
    }

    //MODIFIES: this
    //EFFECTS: Updates the purchase amount of the stock to the given amount
    public void setAmount(int amount) {
        this.amount = amount;
    }

    //EFFECTS: Returns stock symbol
    public String getSymbol() {
        return this.symbol;
    }

    //EFFECTS: Returns stock name
    public String getName() {
        return this.name;
    }

    //EFFECTS: Returns stock's current price
    public StockPriceList getPriceHistory() {
        return this.priceHistory;
    }

    //EFFECTS: Returns the amount of stock owned
    public int getAmount() {
        return this.amount;
    }

    //EFFECTS: Returns stock's purchase price
    public double getPurchasePrice() {
        return this.purchasePrice;
    }

    //EFFECTS: Returns stock's purchase date
    public LocalDate getPurchaseDate() {
        return this.purchaseDate;
    }

}
