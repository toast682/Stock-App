// Received Help from this website: https://www.tutorialspoint.com/java/java_serialization.htm

package persistence;

import model.StockList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// Class that handles object serialization and deserialization of stock portfolio
public class SaveAndLoad {

    //MODIFIES: "./data/account.ser
    //EFFECTS: Serializes and saves give stock list and all of its components. Handles exception by killing program.
    public static void saveData(StockList stockList) {
        try {
            FileOutputStream outputFile = new FileOutputStream("./data/stockportfolio.ser");
            ObjectOutputStream output = new ObjectOutputStream(outputFile);
            output.writeObject(stockList);
            output.close();
            outputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred");
        }
    }

    //MODIFIES:
    public static StockList loadData() {
        StockList stockList;
        try {
            FileInputStream inputFile = new FileInputStream("./data/stockportfolio.ser");
            ObjectInputStream input = new ObjectInputStream(inputFile);
            stockList = (StockList) input.readObject();
            System.out.println(stockList.length());
            return stockList;
        } catch (Exception e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
        return new StockList();
    }
}
