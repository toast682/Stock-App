package model;

import model.StockPrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StockPriceTest {

    StockPrice price;

    @BeforeEach
    void setup() {
        price = new StockPrice();
    }

    @Test
    void setPriceOnceTest() {
        assertEquals(0, price.getPrice());
        setPriceToGiven(1949.26);
    }

    @Test
    void setPriceTwiceTest() {
        assertEquals(0, price.getPrice());
        setPriceToGiven(7987.123);
        setPriceToGiven(45645.46);
    }

    @Test
    void setDateOnceTest() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate newDate = LocalDate.parse("2002-12-30", format);

        assertEquals(LocalDate.now(), price.getDate());
        price.setDate("2002-12-30");
        assertEquals(newDate, price.getDate());
    }

    @Test
    void setDateTwiceTest() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate firstDate = LocalDate.parse("2002-12-30", format);
        LocalDate secondDate = LocalDate.parse("2020-02-20");

        assertEquals(LocalDate.now(), price.getDate());
        price.setDate("2002-12-30");
        assertEquals(firstDate, price.getDate());
        price.setDate("2020-02-20");
        assertEquals(secondDate, price.getDate());
    }

    private void setPriceToGiven(double v) {
        price.setPrice(v);
        assertEquals(v, price.getPrice());
    }
}
