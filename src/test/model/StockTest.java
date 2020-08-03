package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    Stock stock;

    @BeforeEach
    void setup() {
        stock = new Stock();
    }

    @Test
    public void setStockAmountTest() {
        SetAmountOnce(1000);
    }

    @Test
    void setStockAmountTwiceTest() {
        SetAmountOnce(1000);
        SetAmountOnce(212);
    }

    @Test
    void setSymbolOnceTest() {
        assertEquals("DEF", stock.getSymbol());
        setSymbolOnce("WEET");
    }


    @Test
    void setStockThriceTest() {
        assertEquals("DEF", stock.getSymbol());
        setSymbolOnce("GOOG");
        setSymbolOnce("AAPL");
        setSymbolOnce("FB");
    }

    @Test
    void addNewPriceHistoryTest() {
        stock.addNewPriceHistory("2020-02-01", 20);
        assertEquals(stock.getPriceHistory().getStockPrice(), stock.getPriceHistory().getStockPrice());
    }

    @Test
    void addTwoNewPriceHistoriesTest() {
        stock.addNewPriceHistory("2020-02-01", 20);
        assertEquals(stock.getPriceHistory().getStockPrice(), stock.getPriceHistory().getStockPrice());
        stock.addNewPriceHistory("2013-02-06", 2020);
        assertEquals(stock.getPriceHistory().getStockPrice(), stock.getPriceHistory().getStockPrice());
    }

    @Test
    void setNameTest() {
        stock.setName("Booga");
        assertEquals("booga", stock.getName());
    }

    @Test
    void setNameTwiceTest() {
        stock.setName("Booga");
        assertEquals("booga", stock.getName());
        stock.setName("Googa");
        assertEquals("googa", stock.getName());
    }

    @Test
    void setPurchasePriceTest() {
        stock.setPurchasePrice(20);
        assertEquals(20, stock.getPurchasePrice());
    }

    @Test
    void setPurchasePriceTwiceTest() {
        stock.setPurchasePrice(20);
        assertEquals(20, stock.getPurchasePrice());
        stock.setPurchasePrice(152);
        assertEquals(152, stock.getPurchasePrice());
    }

    @Test
    void setPurchaseDateTest() {
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2020-02-01", formattedDate);

        stock.setPurchaseDate("2020-02-01");
        assertEquals(date, stock.getPurchaseDate());
    }

    @Test
    void setPurchaseDateTwiceTest() {
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate firstDate = LocalDate.parse("2020-02-01", formattedDate);
        LocalDate secondDate = LocalDate.parse("2021-02-06", formattedDate);

        stock.setPurchaseDate("2020-02-01");
        assertEquals(firstDate, stock.getPurchaseDate());
        stock.setPurchaseDate("2021-02-06");
        assertEquals(secondDate, stock.getPurchaseDate());
    }

    @Test
    void getPriceHistory() {
        StockPriceList stockPriceList = stock.getPriceHistory();
        assertEquals(stockPriceList, stock.getPriceHistory());
    }

    @Test
    void getStockSymbolTest() {
        assertEquals("DEF", stock.getSymbol());
    }

    @Test
    void getStockNameTest() {
        assertEquals("default", stock.getName());
    }

    @Test
    void getStockPurchaseDateTest() {
        assertEquals(LocalDate.now(), stock.getPurchaseDate());
    }

    @Test
    void getStockPurchasePriceTest() {
        assertEquals(0, stock.getPurchasePrice());
    }

    @Test
    void getStockAmountTest() {
        assertEquals(0, stock.getAmount());
    }

    private void setSymbolOnce(String weet) {
        stock.setSymbol(weet);
        assertEquals(weet, stock.getSymbol());
    }

    private void SetAmountOnce(int i) {
        stock.setAmount(i);
        assertEquals(i, stock.getAmount());
    }
}
