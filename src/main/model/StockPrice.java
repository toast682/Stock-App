package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Represents the date at which the given stock was at this price.
public class StockPrice {

    private double price;
    private LocalDate date;

    //EFFECTS: Produces new price with current date and price being 0
    public StockPrice() {
        this.price = 0;
        this.date = LocalDate.now();
    }

    //MODIFIES: this
    //EFFECTS: Changes to given price
    public void setPrice(double price) {
        this.price = price;
    }

    //REQUIRES: Date has to be in yyyy-MM-dd format; ERROR HANDLING
    //MODIFIES: this
    //EFFECTS: Changes to given date
    public void setDate(String date) {
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date, formattedDate);
    }

    //EFFECTS: Returns the stockPrices's price
    public double getPrice() {
        return this.price;
    }

    //EFFECTS: Returns the stockPrice's date;
    public LocalDate getDate() {
        return this.date;
    }
}
