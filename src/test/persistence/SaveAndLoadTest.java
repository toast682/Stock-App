package persistence;

import exceptions.MassiveStockFindError;
import model.Stock;
import model.StockList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Test class for the SaveAndLoad Class
public class SaveAndLoadTest {


    private static final String SAVE_FILE_NAME = "./data/stockportfolio.ser";

    SaveAndLoad data;
    StockList stockList;
    StockList testList;

    @BeforeEach
    void setup() {
        stockList = new StockList();
        Stock google = new Stock();

        google.addNewPriceHistory("2020-07-31", 20.12);
        google.setAmount(100);
        google.setSymbol("GOOG");
        stockList.buyStock(google);
    }


    @Test
    void saveAndLoadOnceTest() {
        try {
            data.saveData(stockList);
        } catch (Exception e) {
            fail("Should not have thrown error");
        }

        try {
            testList = data.loadData();
        } catch (IOException e) {
            fail("Should not have thrown error");
        } catch (ClassNotFoundException e) {
            fail("Should not have thrown error");
        }

        try {
            assertTrue(testList.contains("GOOG", 100));
            assertFalse(testList.contains("AMZ", 12));
            assertEquals(stockList.length(), testList.length());
            assertEquals(stockList.findStock("GOOG", 100).getPriceHistory().getStockPrice().getDate(),
                    testList.findStock("GOOG", 100).getPriceHistory().getStockPrice().getDate());
            assertEquals(stockList.findStock("GOOG", 100).getPriceHistory().getStockPrice().getPrice(),
                    testList.findStock("GOOG", 100).getPriceHistory().getStockPrice().getPrice());
        } catch (MassiveStockFindError e) {
            fail("Should not have occured");
        }
    }

    @Test
    void saveTestTwice() {
        Stock amazon;

        try {
            data.saveData(stockList);
        } catch (Exception e) {
            fail("Should not have thrown error");
        }

        try {
            testList = data.loadData();
        } catch (IOException e) {
            fail("Should not have thrown error");
        } catch (ClassNotFoundException e) {
            fail("Should not have thrown error");
        }

        try {
            assertTrue(testList.contains("GOOG", 100));
            assertFalse(testList.contains("AMZ", 12));
            assertEquals(stockList.length(), testList.length());
            assertEquals(stockList.findStock("GOOG", 100).getPriceHistory().getStockPrice().getDate(),
                    testList.findStock("GOOG", 100).getPriceHistory().getStockPrice().getDate());
            assertEquals(stockList.findStock("GOOG", 100).getPriceHistory().getStockPrice().getPrice(),
                    testList.findStock("GOOG", 100).getPriceHistory().getStockPrice().getPrice());
        } catch (MassiveStockFindError e) {
            fail("Should not have occurred");
        }
        amazon = new Stock();
        amazon.setAmount(121);
        assertEquals(121, amazon.getAmount());
        amazon.setSymbol("AMZN");
        assertEquals("AMZN", amazon.getSymbol());
        amazon.addNewPriceHistory("2020-04-03", 1234);
        assertEquals(1234, amazon.getPriceHistory().getStockPrice().getPrice());
        stockList.buyStock(amazon);

        try {
            data.saveData(stockList);
        } catch (IOException e) {
            fail("Should not have thrown error");
        }

        try {
            testList = data.loadData();
        } catch (IOException e) {
            fail("Should not have thrown error");
        } catch (ClassNotFoundException e) {
            fail("Should not have thrown error");
        }

        try {
            assertTrue(testList.contains("GOOG", 100));
            assertFalse(testList.contains("AMZ", 12));
            assertEquals(stockList.length(), testList.length());
            assertEquals(stockList.findStock("GOOG", 100).getPriceHistory().getStockPrice().getDate(),
                    testList.findStock("GOOG", 100).getPriceHistory().getStockPrice().getDate());
            assertEquals(stockList.findStock("GOOG", 100).getPriceHistory().getStockPrice().getPrice(),
                    testList.findStock("GOOG", 100).getPriceHistory().getStockPrice().getPrice());

            assertTrue(testList.contains("GOOG", 100));
            assertTrue(testList.contains("AMZN", 121));
            assertEquals(stockList.length(), testList.length());
            assertEquals(stockList.findStock("GOOG", 100).getPriceHistory().getStockPrice().getDate(),
                    testList.findStock("GOOG", 100).getPriceHistory().getStockPrice().getDate());
            assertEquals(stockList.findStock("GOOG", 100).getPriceHistory().getStockPrice().getPrice(),
                    testList.findStock("GOOG", 100).getPriceHistory().getStockPrice().getPrice());
            assertEquals(stockList.findStock("AMZN", 121).getPriceHistory().getStockPrice().getDate(),
                    testList.findStock("AMZN", 121).getPriceHistory().getStockPrice().getDate());
            assertEquals(stockList.findStock("AMZN", 121).getPriceHistory().getStockPrice().getPrice(),
                    testList.findStock("AMZN", 121).getPriceHistory().getStockPrice().getPrice());
        } catch (MassiveStockFindError e) {
            fail("Should not have occurred");
        }
    }
}
