//I used some Help from the given Teller App in order to finish this class. I incorporated a similar while loop

package ui;

import exceptions.MassiveStockFindError;
import model.Stock;
import model.StockList;

import java.util.InputMismatchException;
import java.util.Scanner;

// Represents the UI interface through which the user will interact with
public class StockApp {

    private Scanner mainInput;
    private StockList stockPortfolio;

    //EFFECTS: Constructor calls other method and starts ui
    public StockApp() {
        startStockApp();
    }

    //MODIFIES: this, Stock, StockList
    //EFFECTS: Starts the startup sequence for the app, ends when user exits
    public void startStockApp() {
        initStockApp();
        while (true) {
            showOptions();
            int input;
            try {
                input = mainInput.nextInt();
                mainInput.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer!");
                mainInput.nextLine();
                input = mainInput.nextInt();
            }
            if (input == 6) {
                quitSequence();
                System.out.println("Thanks for using Amogh's Super Stock App! Hope to see you soon!");
                break;
            } else {
                processSelection(input);
            }
        }
    }

    private void initStockApp() {
        mainInput = new Scanner(System.in);
        new LoadStocks(this);
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
                showAllStock();
                break;
            case 5:
                changeCurrentStockPrice();
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
        if (stockPortfolio.contains(symbol, amount)) {
            try {
                stock = stockPortfolio.findStock(symbol, amount);
                stockInfo(stock);
            } catch (MassiveStockFindError e) {
                System.out.println("There has been a massive error.");
            }
        } else {
            System.out.println("Sorry this stock has not been added. Make sure all the information given is correct");
        }
    }

    //EFFECTS: Removes a stock from the Stocklist if it exists, otherwise does nothing
    private void processSale(String symbol, int amount) {
        Stock stock;
        try {
            if (stockPortfolio.contains(symbol, amount)) {
                System.out.println("Here is the information related to your stock");
                stock = stockPortfolio.findStock(symbol, amount);
                stockInfo(stock);

                deleteStock(symbol, amount);
            } else {
                System.out.println("Sorry this stock does not exist. Make sure all the given information is correct");
            }
        } catch (MassiveStockFindError e) {
            System.out.println("There was a massive Error");
        }
    }

    private void deleteStock(String symbol, int amount) {
        String deleteInput;
        System.out.println("Are you sure you want to delete the stock?");
        System.out.println("Type \"y\" for yes or \"n\" for no");
        deleteInput = mainInput.next();
        if (deleteInput.equals("y")) {
            System.out.println("Deleting the Stock");
            stockPortfolio.sellStock(symbol, amount);
        } else if (deleteInput.equals("n")) {
            System.out.println("Cancelling");
        } else {
            System.out.println("Invalid Input");
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
        new AddStock(stockPortfolio);
    }

    //EFFECTS: Shows all the stocks in the stock list
    private void showAllStock() {
        new ShowStocks(stockPortfolio);
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
        try {
            showAllStock();
            System.out.println("Please input the symbol of which stock you want to change the price of:");
            symbol = mainInput.next().toUpperCase().trim();
            System.out.println("Please enter the amount of stocks which you own of the stock which you are changing "
                    + "the current price");
            amount = mainInput.nextInt();
            stock = stockPortfolio.findStock(symbol, amount);
            System.out.println("Please enter the current price of the Stock:");
            price = mainInput.nextDouble();
            System.out.println("Please enter the date of the price is correct in the yyyy-MM-dd format with "
                    + "the dashes");
            date = mainInput.next();
            addNewPriceHistory(stock, price, date);
        } catch (MassiveStockFindError e) {
            System.out.println("There has been a massive error");
        }
    }

    //MODIFIES: "./data/account/ser"
    //EFFECTS: If user wants to save portfolio then saves portfolio, otherwise does not save portfolio
    private void quitSequence() {
        new SaveStock(this.stockPortfolio);
    }


    //MODIFIES: Stock, StockPriceList, StockPrice
    //EFFECTS: Changes the stocks current price
    private void addNewPriceHistory(Stock newStock, double purchasePrice, String purchaseDate) {
        System.out.println("The purchase price has been set up to be the current price. If you would like to update "
                + "the price of the stock later on, you can through the main menu!");
        newStock.addNewPriceHistory(purchaseDate, purchasePrice);
    }

    //EFFECTS: Shows all the possible inputs available for the user when program first starts
    private void showOptions() {
        System.out.println("1. Add a new Stock");
        System.out.println("2. Remove a stock from your portfolio");
        System.out.println("3. Find a stock from your portfolio");
        System.out.println("4. Look at all of your existing stocks");
        System.out.println("5. Change a stocks current price");
        System.out.println("6. Exit the app");
        System.out.println("Please input a number associated with the options above \n");
    }

    public void setStockPortfolio(StockList stockList) {
        this.stockPortfolio = stockList;
    }
}
