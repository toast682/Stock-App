//I used some Help from the given Teller App in order to finish this class. I incorporated a similar while loop

package ui;

import model.Stock;
import model.StockList;

import java.util.InputMismatchException;
import java.util.Scanner;

// Represents the UI interface through which the user will interact with
public class StockApp {

    private Scanner mainInput;
    private StockList yourStocks;

    //EFFECTS: Constructor calls other method and starts ui
    public StockApp() {
        startStockApp();
    }

    //MODIFIES: this, Stock, StockList
    //EFFECTS: Starts the startup sequence for the app, ends when user exits
    public void startStockApp() {
        mainInput = new Scanner(System.in);
        yourStocks = new StockList();

        System.out.println("Hey, welcome to the Stock Traders Delight!");
        System.out.println("Before we get started, what is you name?");
        String name = mainInput.next();
        System.out.println("Welcome to the app " + name + "!");
        System.out.println("What would you like to do?");

        while (true) {
            showOptions();
            int input = mainInput.nextInt();
            if (input == 7) {
                System.out.println("Thanks for using Amogh's Super Stock App! Hope to see you soon!");
                break;
            } else {
                processSelection(input);
            }
        }
    }

    //MODIFIES: StockList, Stock, StockPrice, StockPriceList
    //EFFECTS: User selects from a given number of options then option is executed
    private void processSelection(int input) {
        switch (input) {
            case 1:
                buyStock();
                break;

            case 2:
                sellStock();
                break;
            case 3:
                findAStock();
                break;
            case 4:
                showAllStock("You have selected that you want to look at you existing stocks");
                break;
            case 5:
                changeCurrentStockPrice();
                break;
            case 6:
                System.out.println("This feature is not yet supported, so stay tuned for an update!");
                break;
            default:
                System.out.println("Invalid selection. Please choose one of the given options");
                break;
        }
    }

    // MODIFIES: StockList
    // EFFECTS: Removes a stock form StockList
    private void sellStock() {
        String symbol;
        int amount;

        System.out.println("You have selected that you want to remove a stock in your portfolio");
        System.out.println("Please input the symbol of the stock");
        symbol = mainInput.next();
        System.out.println("Please input the amount of stocks that were inputted (if you have inputted a stock "
                + "multiple times, then input the amount of stocks related to the instance of the stock you want to "
                + "find");
        amount = mainInput.nextInt();
        processSale(symbol, amount);

    }

    //EFFECTS: Finds a stock from the given stock list
    private void findAStock() {
        String symbol;
        int amount;
        Stock stock;

        System.out.println("You have selected that you want to find a stock in your portfolio");
        System.out.println("Please input the symbol of the stock");
        symbol = mainInput.next();
        System.out.println("Please input the amount of stocks that were inputted (if you have inputted a stock "
                + "multiple times, then input the amount of stocks related to the instance of the stock you want to "
                + "find");
        amount = mainInput.nextInt();
        if (yourStocks.contains(symbol, amount)) {
            stock = yourStocks.findStock(symbol, amount);
            stockInfo(stock);
        } else {
            System.out.println("Sorry this stock has not been added. Make sure all the information given is correct");
        }
    }

    //EFFECTS: Removes a stock from the Stocklist if it exists, otherwise does nothing
    private void processSale(String symbol, int amount) {
        Stock stock;

        if (yourStocks.contains(symbol, amount)) {
            String deleteInput;

            System.out.println("Here is the information related to your stock");
            stock = yourStocks.findStock(symbol, amount);
            stockInfo(stock);
            System.out.println("Are you sure you want to delete the stock?");
            System.out.println("Type \"Yes\" or \"No\"");
            deleteInput = mainInput.next();
            if (deleteInput.equals("Yes")) {
                System.out.println("Deleting the Stock");
                yourStocks.sellStock(symbol, amount);
            } else if (deleteInput.equals("No")) {
                System.out.println("Cancelling");
            } else {
                System.out.println("Invalid Input");
            }
        } else {
            System.out.println("Sorry this stock does not exist. Make sure all the given information is correct");
        }
    }

    //EFFECTS: Prints out all information related to a specific stock
    private void stockInfo(Stock stock) {
        System.out.println("Symbol: " + stock.getSymbol());
        System.out.println("name: " + stock.getName());
        System.out.println("Purchase Price: " + stock.getPurchasePrice());
        System.out.println("Amount: " + stock.getAmount());
        System.out.println("Purchase Date: " + stock.getPurchaseDate());
        System.out.println("Current Price: " + stock.getPriceHistory().getStockPrice().getPrice());
        System.out.println("As of date: " + stock.getPriceHistory().getStockPrice().getDate() + "\n");
    }

    //MODIFIES: Stock, StockList
    //EFFECTS: Adds a new stock to the stock list with given user input
    private void buyStock() {
        Stock newStock = new Stock();
        double purchasePrice;
        String purchaseDate;

        System.out.println("You have selected that you want to add a stock \n");
        setStockSymbol(newStock);
        setStockName(newStock);
        setStockAmount(newStock);
        System.out.println("Please enter a valid Number");
        setStockAmount(newStock);
        purchasePrice = setStockPurchasePrice(newStock);
        purchaseDate = setStockPurchaseDate(newStock);
        addNewPriceHistory(newStock, purchasePrice, purchaseDate);
        yourStocks.buyStock(newStock);
    }

    //EFFECTS: Shows all the stocks in the stock list
    private void showAllStock(String s) {
        System.out.println(s);
        System.out.println("These are the stocks you currently have: \n");
        for (int i = 0; i <= yourStocks.length() - 1; i++) {
            Stock stocki = yourStocks.getIndex(i);
            stockInfo(stocki);
        }

    }

    //MODIFIES: Stock, StockPriceList, StockPrice
    //EFFECTS: changes the Stocks current price
    private void changeCurrentStockPrice() {
        System.out.println("You have selected that you want to change the stock price of one of your current stocks");
        System.out.println("Keep in mind, the app will still keep track of your old price in order to generate a "
                + "stock graph");
        System.out.println("What stock will you like to change the price of?");
        chooseStock();
    }

    //MODIFIES: Stock
    //EFFECT: Changes a stocks current price
    private void chooseStock() {
        String symbol;
        int amount;
        Stock stock;
        double price;
        String date;

        showAllStock("These are the current Stocks that you have");
        System.out.println("Please input the symbol of which stock you want to change the price of:");
        symbol = mainInput.next().toUpperCase().trim();
        System.out.println("Please enter the amount of stocks which you own of the stock which you are changing the "
                + "current price");
        amount = mainInput.nextInt();
        stock = yourStocks.findStock(symbol, amount);
        System.out.println("Please enter the current price of the Stock:");
        price = mainInput.nextDouble();
        System.out.println("Please enter the date of the price is correct in the yyyy-MM-dd format with the dashes");
        date = mainInput.next();
        addNewPriceHistory(stock, price, date);
    }


    //MODIFIES: Stock, StockPriceList, StockPrice
    //EFFECTS: Changes the stocks current price
    private void addNewPriceHistory(Stock newStock, double purchasePrice, String purchaseDate) {
        System.out.println("The purchase price has been set up to be the current price. If you would like to update "
                + "the price of the stock later on, you can through the main menu!");
        newStock.addNewPriceHistory(purchaseDate, purchasePrice);
    }

    //MODIFIES: Stock, StockPriceList, StockPrice
    //EFFECTS: Changes a Stocks purchase date and returns the new purchase date
    private String setStockPurchaseDate(Stock newStock) {
        String purchaseDate;
        System.out.println("When did you buy the stock? Please enter the date in the format yyyy-MM-dd with the "
                + "dashes");
        purchaseDate = mainInput.next();
        newStock.setPurchaseDate(purchaseDate);
        return purchaseDate;
    }

    //MODIFIES: Stock, StockPriceList, StockPrice
    //EFFECTS: Changes a Stocks purchase price and returns the new purchase price
    private double setStockPurchasePrice(Stock newStock) {
        double purchasePrice;
        System.out.println("What was the purchase price for the stock?");
        purchasePrice = mainInput.nextDouble();
        newStock.setPurchasePrice(purchasePrice);
        return purchasePrice;
    }

    //MODIFIES: Stock
    //EFFECTS: Changes the amount of stocks
    private void setStockAmount(Stock newStock) {
        int amount;
        System.out.println("How many stocks did you buy?");
        amount = mainInput.nextInt();
        newStock.setAmount(amount);
    }

    //MODIFIES: Stock
    //EFFECTS: Changes the name of the stock
    private void setStockName(Stock newStock) {
        String name;
        System.out.println("What is the name of the stock?");
        name = mainInput.next();
        newStock.setName(name);
    }

    //MODIFIES: Stock
    //EFFECTS: Changes the symbol of the stock
    private void setStockSymbol(Stock newStock) {
        String symbol;
        System.out.println("I will just need some information about the stock to get started!");
        System.out.println("What is the ticker symbol (stock symbol) for the stock you want to add?");
        symbol = mainInput.next();
        newStock.setSymbol(symbol);
    }

    //EFFECTS: Shows all the possible inputs available for the user when program first starts
    private void showOptions() {
        System.out.println("1. Add a new Stock");
        System.out.println("2. Remove a stock from your portfolio");
        System.out.println("3. Find a stock from your portfolio");
        System.out.println("4. Look at all of your existing stocks");
        System.out.println("5. Change a stocks current price");
        System.out.println("6. Look at a stock's performance (NOT SUPPORTED AS OF RIGHT NOW)");
        System.out.println("7. Exit the app");
        System.out.println("Please input a number associated with the options above \n");
    }
}
