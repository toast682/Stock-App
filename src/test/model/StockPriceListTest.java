package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StockPriceListTest {

    StockPriceList priceList;

    @BeforeEach
    void setup() {
        priceList = new StockPriceList();
    }

    @Test
    void addOneNewPriceTest() {
        StockPrice firstStockPrice = new StockPrice();

        addNewStockPrice(firstStockPrice, 1);
    }

    @Test
    void checkLastAddTest() {
        StockPrice first = new StockPrice();

        priceList.add(first);
        assertEquals(first, priceList.getStockPrice());
    }

    @Test
    void checkTwoAddedLastTest() {
        StockPrice first = new StockPrice();
        StockPrice second = new StockPrice();

        priceList.add(first);
        assertEquals(first, priceList.getStockPrice());
        priceList.add(second);
        assertEquals(second, priceList.getStockPrice());
    }

    @Test
    void checkManyAddedLastTest() {
        StockPrice first = new StockPrice();
        StockPrice second = new StockPrice();
        StockPrice third = new StockPrice();
        StockPrice fourth = new StockPrice();
        StockPrice fifth = new StockPrice();

        priceList.add(first);
        assertEquals(first, priceList.getStockPrice());
        priceList.add(second);
        assertEquals(second, priceList.getStockPrice());
        priceList.add(third);
        priceList.add(fourth);
        priceList.add(fifth);
        assertEquals(fifth, priceList.getStockPrice());
    }

    @Test
    void addThreeNewPriceTest() {
        StockPrice firstStockPrice = new StockPrice();
        StockPrice secondStockPrice = new StockPrice();
        StockPrice thirdStockPrice = new StockPrice();

        addNewStockPrice(firstStockPrice, 1);
        addNewStockPrice(secondStockPrice, 2);
        addNewStockPrice(thirdStockPrice, 3);
    }

    @Test
    void checkZeroLengthTest() {
        assertEquals(0, priceList.length());
    }

    @Test
    void checkOneLengthTest() {
        StockPrice firstStockPrice = new StockPrice();

        priceList.add(firstStockPrice);
        assertEquals(1, priceList.length());
    }

    @Test
    void checkFourLengthTest() {
        StockPrice firstStockPrice = new StockPrice();
        StockPrice secondStockPrice = new StockPrice();
        StockPrice thirdStockPrice = new StockPrice();
        StockPrice fourthStockPrice = new StockPrice();

        priceList.add(firstStockPrice);
        priceList.add(secondStockPrice);
        priceList.add(thirdStockPrice);
        priceList.add(fourthStockPrice);
        assertEquals(4, priceList.length());
    }

    @Test
    void checkEmptyContainsTest() {
        StockPrice testStockPrice = new StockPrice();

        assertFalse(priceList.contains(testStockPrice));
    }

    @Test
    void checkContainsFalseTest() {
        StockPrice testStockPrice = new StockPrice();
        StockPrice falsePrice = new StockPrice();

        priceList.add(testStockPrice);
        assertFalse(priceList.contains(falsePrice));
    }

    @Test
    void checkContainsTest() {
        StockPrice firstStockPrice = new StockPrice();

        priceList.add(firstStockPrice);
        assertTrue(priceList.contains(firstStockPrice));
    }

    @Test
    void checkFiveContainsTest() {
        StockPrice firstStockPrice = new StockPrice();
        StockPrice secondStockPrice = new StockPrice();
        StockPrice thirdStockPrice = new StockPrice();
        StockPrice fourthStockPrice = new StockPrice();
        StockPrice fifthStockPrice = new StockPrice();
        StockPrice sixthStockPrice = new StockPrice();

        priceList.add(firstStockPrice);
        priceList.add(secondStockPrice);
        priceList.add(thirdStockPrice);
        priceList.add(fourthStockPrice);
        priceList.add(fifthStockPrice);
        assertTrue(priceList.contains(firstStockPrice));
        assertTrue(priceList.contains(secondStockPrice));
        assertTrue(priceList.contains(thirdStockPrice));
        assertTrue(priceList.contains(fourthStockPrice));
        assertTrue(priceList.contains(fifthStockPrice));
        assertFalse(priceList.contains(sixthStockPrice));

    }

    @Test
    void checkLengthForOneTest() {
        StockPrice price = new StockPrice();

        priceList.add(price);
        assertEquals(1, priceList.length());
    }

    @Test
    void checkLengthForTwoTest() {
        StockPrice price1 = new StockPrice();
        StockPrice price2 = new StockPrice();

        priceList.add(price1);
        assertEquals(1, priceList.length());
        priceList.add(price2);
        assertEquals(2, priceList.length());
    }

    @Test
    void checkSortFunction() {
        StockPrice price1 = new StockPrice();
        StockPrice price2 = new StockPrice();

        price2.setDate("5000-02-01");
        priceList.add(price1);
        priceList.add(price2);
        priceList.sort();

        assertEquals(price2, priceList.priceList.getLast());
        assertEquals(price1, priceList.priceList.getFirst());
    }

    private void addNewStockPrice(StockPrice stockPrice, int length) {
        priceList.add(stockPrice);
        assertEquals(length, priceList.length());
        assertTrue(priceList.contains(stockPrice));
    }

}
