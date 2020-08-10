// Received Help from this website: https://www.tutorialspoint.com/java/java_serialization.htm

package persistence;

import model.StockList;

import java.io.*;

// Class that handles object serialization and deserialization of stock portfolio
public class SaveAndLoad {

    private static final String SAVE_FILE_NAME = "./data/stockportfolio.ser";


    //MODIFIES: ./data/account.ser
    //EFFECTS: Serializes and saves give stock list and all of its components. Handles exception by killing program.
    public static void saveData(StockList stockList) throws IOException {
        FileOutputStream outputFile = new FileOutputStream(SAVE_FILE_NAME);
        ObjectOutputStream output = new ObjectOutputStream(outputFile);
        output.writeObject(stockList);
        output.close();
        outputFile.close();
    }

    //REQUIRES: system already have a "./data/stockportfolio.ser" file on the computer at the correct directory
    //EFFECTS: Returns the previously saved portfolio
    public static StockList loadData() throws IOException, ClassNotFoundException {
        StockList stockList;

        FileInputStream inputFile = new FileInputStream(SAVE_FILE_NAME);
        ObjectInputStream input = new ObjectInputStream(inputFile);
        stockList = (StockList) input.readObject();
        input.close();
        inputFile.close();
        return stockList;
    }
}
