package model;

import exceptions.MassiveStockFindError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StockListTest {

    StockList stockList;

    @BeforeEach
    void setup() {
        stockList = new StockList();
    }

    @Test
    void buyOneStockTest() {
        Stock boom = new Stock();

        stockList.buyStock(boom);
        assertEquals(1, stockList.length());
        assertTrue(stockList.contains("DEF", 0));
    }

    @Test
    void buyTwoStocksTest() {
        Stock boom = new Stock();
        Stock doom = new Stock();
        doom.setSymbol("GOG");
        doom.setAmount(12);

        stockList.buyStock(boom);
        assertEquals(1, stockList.length());
        assertTrue(stockList.contains("DEF", 0));
        stockList.buyStock(doom);
        assertEquals(2, stockList.length());
        assertTrue(stockList.contains("GOG", 12));
    }

    @Test
    void buyLargeAmountOfStocksTest() {
        for (int i = 0; i <= 5000; i++) {
            Stock boom = new Stock();
            boom.setSymbol(Integer.toString(i));
            boom.setAmount(i);

            stockList.buyStock(boom);
            assertEquals(i + 1, stockList.length());
        }
    }

    @Test
    void sellOneStockTest() {
        Stock Boom = new Stock();

        stockList.buyStock(Boom);
        assertEquals(1, stockList.length());
        assertTrue(stockList.contains("DEF", 0));
        stockList.sellStock("DEF", 0);
        assertEquals(0, stockList.length());
        assertFalse(stockList.contains("DEF", 0));
    }

    @Test
    void sellTwoStocksTest() {
        Stock boom = new Stock();
        Stock doom = new Stock();
        doom.setSymbol("GOG");
        doom.setAmount(12);

        stockList.buyStock(boom);
        assertEquals(1, stockList.length());
        assertTrue(stockList.contains("DEF", 0));
        stockList.buyStock(doom);
        assertEquals(2, stockList.length());
        assertTrue(stockList.contains("GOG", 12));
        stockList.sellStock("DEF", 0);
        assertEquals(1, stockList.length());
        assertFalse(stockList.contains("DEF", 0));
        stockList.sellStock("GOG", 12);
        assertEquals(0, stockList.length());
        assertFalse(stockList.contains("GOG", 12));
    }

    @Test
    void sellLargeAmountOfStocksTest() {
        for (int i = 0; i <= 5000; i++) {
            Stock boom = new Stock();
            boom.setAmount(i);
            boom.setSymbol(Integer.toString(i));

            stockList.buyStock(boom);
            assertEquals(i + 1, stockList.length());
        }
        for (int i = 0; i <= 5000; i++) {
            stockList.sellStock(Integer.toString(i), i);
            assertEquals(5000 - i, stockList.length());
        }
    }

    @Test
    void checkNoneContainsFalseTest() {
        assertFalse(stockList.contains("None", 12));
    }

    @Test
    void checkOneContainsFalseTest() {
        Stock boom = new Stock();

        stockList.buyStock(boom);
        assertFalse(stockList.contains("None", 12));
    }

    @Test
    void checkOneContainsTrueTest() {
        Stock boom = new Stock();

        stockList.buyStock(boom);
        assertTrue(stockList.contains("DEF", 0));
    }

    @Test
    void checkOneContainsTrueWrongTest() {
        Stock boom = new Stock();

        stockList.buyStock(boom);
        assertFalse(stockList.contains("HOOD", 10));
    }

    @Test
    void checkLargeContainsTrueAndFalseTest() {

        for (int i = 0; i <= 5000; i++) {
            Stock boom = new Stock();
            boom.setAmount(i);
            boom.setSymbol(Integer.toString(i));

            stockList.buyStock(boom);
            assertEquals(i + 1, stockList.length());
            assertTrue(stockList.contains(Integer.toString(i), i));
        }
        for (int i = 0; i <= 5000; i++) {
            stockList.sellStock(Integer.toString(i), i);
            assertFalse(stockList.contains(Integer.toString(i), i));
        }
    }

    @Test
    void checkOneFindTest() {
        Stock boom = new Stock();

        try {
            stockList.buyStock(boom);
            assertTrue(stockList.contains("DEF", 0));
            assertEquals(boom, stockList.findStock("DEF", 0));
        } catch (MassiveStockFindError e) {
            fail("Should not have occurred");
        }
    }

    @Test
    void checkTwoFindTest() {
        Stock boom = new Stock();
        Stock look = new Stock();

        look.setAmount(100);
        look.setSymbol("GERF");

        try {
            stockList.buyStock(boom);
            stockList.buyStock(look);
            assertFalse(stockList.contains("DEF", 100));
            assertEquals(look, stockList.findStock("GERF", 100));
            assertEquals(boom, stockList.findStock("DEF", 0));
        } catch (MassiveStockFindError e) {
            fail("Should not have occurred");
        }
    }

    @Test
    void checkLargerFindTest() {
        Stock boom = new Stock();
        Stock look = new Stock();
        Stock fdg = new Stock();
        Stock gogs = new Stock();
        Stock hoid = new Stock();

        look.setAmount(100);
        look.setSymbol("GERF");
        fdg.setAmount(1000);
        fdg.setSymbol("fgd");
        gogs.setAmount(100);
        gogs.setSymbol("gogs");
        hoid.setAmount(100);
        hoid.setSymbol("hoid");

        try {
            stockList.buyStock(boom);
            stockList.buyStock(look);
            stockList.buyStock(fdg);
            stockList.buyStock(gogs);
            stockList.buyStock(hoid);
            assertFalse(stockList.contains("DEF", 100));
            assertEquals(boom, stockList.findStock("def", 0));
            assertEquals(look, stockList.findStock("GERF", 100));
            assertEquals(fdg, stockList.findStock("fgd", 1000));
            assertEquals(gogs, stockList.findStock("gogs", 100));
            assertEquals(hoid, stockList.findStock("HOID", 100));
        } catch (MassiveStockFindError e) {
            fail("Should not have occurred");
        }
    }


    @Test
    void checkLengthWithOneTest() {
        Stock boom = new Stock();

        stockList.buyStock(boom);
        assertEquals(1, stockList.length());
    }

    @Test
    void checkLengthWithTwoTest() {
        Stock boom = new Stock();
        Stock look = new Stock();

        stockList.buyStock(boom);
        assertEquals(1, stockList.length());
        stockList.buyStock(look);
        assertEquals(2, stockList.length());
        stockList.sellStock("DEF", 0);
        assertEquals(1, stockList.length());
        stockList.sellStock("DEF", 0);
        assertEquals(0, stockList.length());
    }

    @Test
    void checkLengthWithLargeAmountTest() {
        for (int i = 0; i <= 5000; i++) {
            Stock boom = new Stock();

            stockList.buyStock(boom);
            assertEquals(i + 1, stockList.length());
        }
        for (int i = 0; i <= 5000; i++) {
            stockList.sellStock("DEF", 0);
            assertEquals(5000 - i, stockList.length());
        }
    }

    @Test
    void exceptionTest() {
        try{
            stockList.findStock("HH", 212 );
            fail("Should not have happened");
        } catch (MassiveStockFindError e){

        }
    }

    @Test
    void getFirstIndexTest() {
        Stock stock = new Stock();

        stockList.buyStock(stock);
        assertEquals(stock, stockList.getIndex(0));
    }

    @Test
    void getSecondIndexTest() {
        Stock stock1 = new Stock();
        Stock stock2 = new Stock();

        stockList.buyStock(stock1);
        stockList.buyStock(stock2);
        assertEquals(stock1, stockList.getIndex(0));
        assertEquals(stock2, stockList.getIndex(1));
    }

    @Test
    void sellStockWhenNoStockExistsTest() {
        stockList.sellStock("HH", 200);
    }
}
