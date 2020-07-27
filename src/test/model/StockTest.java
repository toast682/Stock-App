package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    Stock stock;

    @BeforeEach
    public void setup() {
        stock = new Stock();
    }

    @Test
    public void setStockAmountTest() {
        SetAmountOnce(1000);
    }

    @Test
    public void setStockAmountTwiceTest() {
        SetAmountOnce(1000);
        SetAmountOnce(212);
    }

    @Test
    public void setSymbolOnceTest() {
        assertEquals("DEF", stock.getSymbol());
        setSymbolOnce("WEET");
    }

    private void setSymbolOnce(String weet) {
        stock.setSymbol(weet);
        assertEquals(weet, stock.getSymbol());
    }

    @Test
    public void setStockThriceTest() {
        assertEquals("DEF", stock.getSymbol());
        setSymbolOnce("GOOG");
        setSymbolOnce("AAPL");
        setSymbolOnce("FB");
    }

    @Test
    public void addNewPriceHistoryTest() {

    }


    @Test
    public void getStockSymbolTest() {
        assertEquals("DEF", stock.getSymbol());
    }

    @Test
    public void getStockNameTest() {
        assertEquals("default", stock.getName());
    }

    @Test
    public void getStockPurchaseDateTest() {
        assertEquals(LocalDate.now(), stock.getPurchaseDate());
    }

    @Test
    public void getStockPurchasePriceTest() {
        assertEquals(0, stock.getPurchasePrice());
    }

    @Test
    public void getStockAmountTest() {
        assertEquals(0, stock.getAmount());
    }

    private void SetAmountOnce(int i) {
        stock.setAmount(i);
        assertEquals(i, stock.getAmount());
    }
}
