package ui;

import exceptions.IncorrectTypeException;
import model.Stock;
import model.StockList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;

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
    private JButton submit;
    private JButton back;
    private GridLayout layout;


    public AddStock(StockList stocks) {
        stockPortfolio = stocks;
        initializeJPanel();
        initializeField();
    }


    private void initializeField() {
        stock = new Stock();
        setMinimumSize(getPreferredSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(TITLE);
        setVisible(true);

    }

    private void initializeJPanel() {
        panel = new JPanel();
        layout = new GridLayout(8, 2, 20, 20);
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

    private void addButtons() {
        submit = new JButton("Submit");
        submit.setActionCommand("Submit");
        submit.addActionListener(this);
        panel.add(submit);
        back = new JButton("Back");
        back.setActionCommand("Back");
        back.addActionListener(this);
        panel.add(back);
    }

    private void addMainLabel() {
        JLabel label = new JLabel("Please enter the following information:");
        label.setFont(new Font("Serif", Font.BOLD, 18));
        panel.add(label);
        panel.add(new JLabel());
    }


    private void nameField() {
        JLabel nameLabel = new JLabel("Please enter the name of the stock:");
        panel.add(nameLabel);
        name = new JTextField();
        panel.add(name);
    }

    private void symbolField() {
        JLabel symbolLabel = new JLabel("Please enter the Ticker Symbol of the stock:");
        panel.add(symbolLabel);
        symbol = new JTextField();
        panel.add(symbol);
    }

    private void amountField() {
        JLabel amountLabel = new JLabel("Please enter the amount of stocks that you purchased:");
        panel.add(amountLabel);
        amount = new JTextField();
        panel.add(amount);
    }

    private void purchaseDateField() {
        JLabel purchaseDateLabel = new JLabel("Please enter purchase date in the format \"yyyy-MM-dd\":");
        panel.add(purchaseDateLabel);
        purchaseDate = new JTextField();
        panel.add(purchaseDate);
    }

    private void purchasePriceField() {
        JLabel purchasePriceLabel = new JLabel("Please enter the price that you purchased the stock for:");
        panel.add(purchasePriceLabel);
        purchasePrice = new JTextField();
        panel.add(purchasePrice);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Submit".equals(e.getActionCommand())) {
            if (ensureAllFieldsFilled()) {
                try {
                    makeNewStock();
                    setVisible(false);
                    dispose();
                } catch (IncorrectTypeException e1) {
                    JOptionPane.showMessageDialog(null,
                            "Please correct the error and try again!");
                }

            }

        } else if ("Back".equals(e.getActionCommand())) {
            System.out.println("ldgjd");
        }

    }

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

    private void makeNewStock() throws IncorrectTypeException {
        stock.setName(name.getText());
        stock.setSymbol(symbol.getText());
        setAmount();
        setPurchaseDate();
        setPurchasePrice();
        addNewPurchaseHistory();
    }

    private void addNewPurchaseHistory() {
        String date = purchaseDate.getText();
        double price = Double.parseDouble(purchasePrice.getText());

        stock.addNewPriceHistory(date, price);
    }

    private void goBack() {
        stock = null;
    }

    private void setAmount() throws IncorrectTypeException {
        try {
            stock.setAmount(Integer.parseInt(amount.getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid integer for the amount!");
            throw new IncorrectTypeException();
        }
    }

    private void setPurchaseDate() throws IncorrectTypeException {
        try {
            stock.setPurchaseDate(purchaseDate.getText());
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Date in the format "
                    + "\"yyyy-MM-dd\" for the amount!");
            throw new IncorrectTypeException();
        }
    }

    private void setPurchasePrice() throws IncorrectTypeException {
        try {
            stock.setPurchasePrice(Double.parseDouble(purchasePrice.getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number for the amount!");
            throw new IncorrectTypeException();
        }
    }
}