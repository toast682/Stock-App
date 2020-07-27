package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockListTest {

    StockList stockList;

    @BeforeEach
    public void setup() {
        stockList = new StockList();
    }

    @Test
    public void buyOneStockTest() {
        Stock boom = new Stock();

        stockList.buyStock(boom);
        assertEquals(1, stockList.length());
        assertTrue(stockList.contains("DEF", 0));
    }

    @Test
    public void buyTwoStocksTest() {
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
    public void buyLargeAmountOfStocksTest() {
        for (int i = 0; i <= 5000; i++) {
            Stock boom = new Stock();
            boom.setSymbol(Integer.toString(i));
            boom.setAmount(i);

            stockList.buyStock(boom);
            assertEquals(i + 1, stockList.length());
        }
    }

    @Test
    public void sellOneStockTest() {
        Stock Boom = new Stock();

        stockList.buyStock(Boom);
        assertEquals(1, stockList.length());
        assertTrue(stockList.contains("DEF", 0));
        stockList.sellStock("DEF", 0);
        assertEquals(0, stockList.length());
        assertFalse(stockList.contains("DEF", 0));
    }

    @Test
    public void sellTwoStocksTest() {
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
    public void sellLargeAmountOfStocksTest() {
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
    public void checkNoneContainsFalseTest() {
        assertFalse(stockList.contains("None", 12));
    }

    @Test
    public void checkOneContainsFalseTest() {
        Stock boom = new Stock();

        stockList.buyStock(boom);
        assertFalse(stockList.contains("None", 12));
    }

    @Test
    public void checkOneContainsTrueTest() {
        Stock boom = new Stock();

        stockList.buyStock(boom);
        assertTrue(stockList.contains("DEF", 0));
    }

    @Test
    public void checkOneContainsTrueWrongTest() {
        Stock boom = new Stock();

        stockList.buyStock(boom);
        assertFalse(stockList.contains("HOOD", 10));
    }

    @Test
    public void checkLargeContainsTrueAndFalseTest() {

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
    public void checkOneFindTest() {
        Stock boom = new Stock();

        stockList.buyStock(boom);
        assertTrue(stockList.contains("DEF", 0));
        assertEquals(boom, stockList.findStock("DEF", 0));
    }

    @Test
    public void checkTwoFindTest() {
        Stock boom = new Stock();
        Stock look = new Stock();

        look.setAmount(100);
        look.setSymbol("GERF");

        stockList.buyStock(boom);
        stockList.buyStock(look);
        assertFalse(stockList.contains("DEF", 100));
        assertEquals(look, stockList.findStock("GERF", 100));
        assertEquals(boom, stockList.findStock("DEF", 0));
    }

    @Test
    public void checkLargerFindTest() {
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

    }


    @Test
    public void checkLengthWithOneTest() {
        Stock boom = new Stock();

        stockList.buyStock(boom);
        assertEquals(1, stockList.length());
    }

    @Test
    public void checkLengthWithTwoTest() {
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
    public void checkLengthWithLargeAmountTest() {
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


}
