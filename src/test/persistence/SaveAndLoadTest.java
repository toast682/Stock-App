package persistence;

import model.StockList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SaveAndLoadTest {

    SaveAndLoad data;
    StockList stockList;

    @BeforeEach
    void setup() {
        stockList = new StockList();
    }


    @Test
    void saveTest() {
        data.saveData(stockList);

    }

    @Test
    void saveTestTwice() {

    }

    @Test
    void loadData() {

    }

    @Test
    void loadDataTwice() {

    }
}
