// Sound bite received from https://www.youtube.com/channel/UCi-xN4ZB6e-0JcXzvBEomlw

package ui;


import exceptions.IncorrectTypeException;
import model.Stock;
import model.StockList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

//GUI representing the buy interaction with the user
public class AddStock extends JFrame implements ActionListener {

    private static final String TITLE = "Adding A Stock";

    private final StockList stockPortfolio;
    private Stock stock;

    private JPanel panel;
    private JTextField name;
    private JTextField symbol;
    private JTextField amount;
    private JTextField purchaseDate;
    private JTextField purchasePrice;


    //EFFECTS: Creates new AddStock Gui to buy stock
    public AddStock(StockList stocks) {
        stockPortfolio = stocks;
        initializeJPanel();
        initializeFrame();
    }


    //MODIFIES: this
    //EFFECTS: Creates a frame for the user information to be inside
    private void initializeFrame() {
        stock = new Stock();
        setMinimumSize(getPreferredSize());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(TITLE);
        setVisible(true);

    }

    //MODIFIES: This
    //EFFECTS: creates all user interaction stuff and places it where it needs to be
    private void initializeJPanel() {
        panel = new JPanel();
        GridLayout layout = new GridLayout(8, 2, 20, 20);
        panel.setLayout(layout);
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        addMainLabel();
        nameField();
        symbolField();
        amountField();
        purchaseDateField();
        purchasePriceField();
        addButtons();

        add(panel);

    }

    //EFFECTS: performs tasks associated with the two buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Submit".equals(e.getActionCommand())) {
            if (ensureAllFieldsFilled()) {
                try {
                    makeNewStock();
                    stockPortfolio.buyStock(stock);
                    playCashSound();
                    setVisible(false);
                    dispose();
                } catch (IncorrectTypeException e1) {
                    JOptionPane.showMessageDialog(null,
                            "Please correct the error and try again!");
                }

            }

        } else if ("Back".equals(e.getActionCommand())) {
            goBack();
        }

    }

    //EFFECTS: Plays cash sound whenever you buy a stock
    private void playCashSound() {
        try {
            String soundFileLink = "./data/cashSound.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(soundFileLink).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (Exception e0) {
            JOptionPane.showMessageDialog(null, "Error!");
            e0.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: Creates a new stock ang gets all user information from forms
    private void makeNewStock() throws IncorrectTypeException {
        stock.setName(name.getText());
        stock.setSymbol(symbol.getText());
        setAmount();
        setPurchaseDate();
        setPurchasePrice();
        addNewPurchaseHistory();
    }

    //MODIFIES: this
    //EFFECTS: Exits gui and goes back to the commandline menu
    private void goBack() {
        setVisible(false);
        dispose();
    }


    //MODIFIES: this
    //EFFECTS: adds buttons to panel
    private void addButtons() {
        JButton submit = new JButton("Submit");
        submit.setActionCommand("Submit");
        submit.addActionListener(this);
        panel.add(submit);
        JButton back = new JButton("Back");
        back.setActionCommand("Back");
        back.addActionListener(this);
        panel.add(back);
    }

    //MODIFIES: this
    //EFFECTS: adds main label to panel
    private void addMainLabel() {
        JLabel label = new JLabel("Please enter the following information:");
        label.setFont(new Font("Serif", Font.BOLD, 18));
        panel.add(label);
        panel.add(new JLabel());
    }


    //MODIFIES: this
    //EFFECTS: adds name label and input field to panel
    private void nameField() {
        JLabel nameLabel = new JLabel("Please enter the name of the stock:");
        panel.add(nameLabel);
        name = new JTextField();
        panel.add(name);
    }

    //MODIFIES: this
    //EFFECTS: adds symbol label and input field to panel
    private void symbolField() {
        JLabel symbolLabel = new JLabel("Please enter the Ticker Symbol of the stock:");
        panel.add(symbolLabel);
        symbol = new JTextField();
        panel.add(symbol);
    }

    //MODIFIES: this
    //EFFECTS: adds amount label and input field to panel
    private void amountField() {
        JLabel amountLabel = new JLabel("Please enter the amount of stocks that you purchased:");
        panel.add(amountLabel);
        amount = new JTextField();
        panel.add(amount);
    }

    //MODIFIES: this
    //EFFECTS: adds purchaseDate label and input field to panel
    private void purchaseDateField() {
        JLabel purchaseDateLabel = new JLabel("Please enter purchase date in the format \"yyyy-MM-dd\":");
        panel.add(purchaseDateLabel);
        purchaseDate = new JTextField();
        panel.add(purchaseDate);
    }

    //MODIFIES: this
    //EFFECTS: adds purchasePrice label and input field to panel
    private void purchasePriceField() {
        JLabel purchasePriceLabel = new JLabel("Please enter the price that you purchased the stock for:");
        panel.add(purchasePriceLabel);
        purchasePrice = new JTextField();
        panel.add(purchasePrice);
    }

    //EFFECTS: Ensures all fields are filled before submitting
    private boolean ensureAllFieldsFilled() {
        if (name.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter a name");
            return false;
        } else if (symbol.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter a symbol!");
            return false;
        } else if (amount.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter a amount!");
            return false;
        } else if (purchaseDate.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter a Date!");
            return false;
        } else if (purchasePrice.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter a Price!");
            return false;
        } else {
            return true;
        }
    }


    //MODIFIES: Stock
    //EFFECTS: states stock purchase history
    private void addNewPurchaseHistory() {
        String date = purchaseDate.getText();
        double price = Double.parseDouble(purchasePrice.getText());

        stock.addNewPriceHistory(date, price);
    }

    //MODIFIES: Stock
    //EFFECTS: sets stocks amount
    private void setAmount() throws IncorrectTypeException {
        try {
            stock.setAmount(amount.getText());
        } catch (IncorrectTypeException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number for the amount!");
            throw new IncorrectTypeException();
        }
    }

    //MODIFIES: Stock
    //EFFECTS: sets purchase date amount
    private void setPurchaseDate() throws IncorrectTypeException {
        try {
            stock.setPurchaseDate(purchaseDate.getText());
        } catch (IncorrectTypeException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Date in the format "
                    + "\"yyyy-MM-dd\" for the amount!");
            throw new IncorrectTypeException();
        }
    }

    //MODIFIES: Stock
    //EFFECTS: sets purchase price amount
    private void setPurchasePrice() throws IncorrectTypeException {
        try {
            stock.setPurchasePrice(purchasePrice.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number for the amount!");
            throw new IncorrectTypeException();
        }
    }
}