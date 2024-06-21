package guis;

import db_objs.MyJDBC;
import db_objs.Transaction;
import db_objs.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class BankingAppDialog extends JDialog implements ActionListener {
    private User user;
    private BankingAppGui bankingAppGui;
    private JLabel balanceLabel, enterAmountLabel, enterUserLabel;
    private JTextField enterAmountField, enterUserField;
    private JButton actionButton;
    private JPanel pastTransactionPanel;
    private ArrayList<Transaction> pastTransactions;

    public BankingAppDialog(BankingAppGui bankingAppGui, User user) {
        setSize(400, 400);
        setModal(true);
        setLocationRelativeTo(bankingAppGui);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        this.bankingAppGui = bankingAppGui;
        this.user = user;
    }

    public void addCurrentBalanceAndAmount() {
        balanceLabel = new JLabel("Balance: $" + user.getCurrentBalance());
        balanceLabel.setBounds(0, 10, getWidth() - 20, 20);
        balanceLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(balanceLabel);

        enterAmountLabel = new JLabel("Enter Amount:");
        enterAmountLabel.setBounds(0, 50, getWidth() - 20, 20);
        enterAmountLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        enterAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterAmountLabel);

        enterAmountField = new JTextField();
        enterAmountField.setBounds(15, 80, getWidth() - 50, 40);
        enterAmountField.setFont(new Font("Dialog", Font.BOLD, 20));
        enterAmountField.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterAmountField);
    }

    public void addActionButton(String actionButtonType) {
        actionButton = new JButton(actionButtonType);
        actionButton.setBounds(15, 300, getWidth() - 50, 40);
        actionButton.setFont(new Font("Dialog", Font.BOLD, 20));
        actionButton.addActionListener(this);
        add(actionButton);
    }

    public void addUserField() {
        enterUserLabel = new JLabel("Enter User:");
        enterUserLabel.setBounds(0, 160, getWidth() - 20, 20);
        enterUserLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        enterUserLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterUserLabel);

        enterUserField = new JTextField();
        enterUserField.setBounds(15, 190, getWidth() - 50, 40);
        enterUserField.setFont(new Font("Dialog", Font.BOLD, 20));
        enterUserField.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterUserField);
    }

    public void addPastTransactionComponents(){
        //container to store each transaction
        pastTransactionPanel = new JPanel();

        //1x1 layout
        pastTransactionPanel.setLayout(new BoxLayout(pastTransactionPanel, BoxLayout.Y_AXIS));

        //make container scrollable
        JScrollPane scrollPane = new JScrollPane(pastTransactionPanel);

        //displays the vertical scroll only when needed
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 20, getWidth() - 15, getHeight() - 15);

        //call jdbc method to get all the transactions from a user
        pastTransactions = MyJDBC.getPastTransaction(user);

        //iterate through the list and add it to the gui
        for(int i = 0; i < pastTransactions.size(); i++){
            //store current transaction
            Transaction pastTransaction = pastTransactions.get(i);

            //create a container to store a singular component
            JPanel pastTransactionContainer = new JPanel();
            pastTransactionContainer.setLayout(new BorderLayout());

            //transaction type label
            JLabel pastTransactionTypeLabel = new JLabel(pastTransaction.getTransactionType());
            pastTransactionTypeLabel.setFont(new Font("Dialog", Font.BOLD, 20));

            //transaction amount label
            JLabel pastTransactionAmountLabel = new JLabel(String.valueOf(pastTransaction.getTransactionAmount()));
            pastTransactionAmountLabel.setFont(new Font("Dialog", Font.BOLD, 20));

            //transaction date label
            JLabel pastTransactionDateLabel = new JLabel(String.valueOf(pastTransaction.getTransactionDate()));
            pastTransactionDateLabel.setFont(new Font("Dialog", Font.BOLD, 20));

            //add them to the container
            pastTransactionContainer.add(pastTransactionTypeLabel, BorderLayout.WEST);
            pastTransactionContainer.add(pastTransactionAmountLabel, BorderLayout.EAST);
            pastTransactionContainer.add(pastTransactionDateLabel, BorderLayout.SOUTH);

            //white background color to each container
            pastTransactionContainer.setBackground(Color.white);

            //add borders
            pastTransactionContainer.setBorder(BorderFactory.createLineBorder(Color.black));

            //add transaction component to the transaction panel
            pastTransactionPanel.add(pastTransactionContainer);
        }

        //add to the dialog
        add(scrollPane);
    }

    private void handleTransaction(String transactionType, BigDecimal amountVal) {
        if (amountVal.compareTo(BigDecimal.ZERO) <= 0) {
            JOptionPane.showMessageDialog(this, "Error: Amount must be greater than zero.");
            return;
        }

        Transaction transaction;
        if (transactionType.equalsIgnoreCase("Deposit")) {
            user.setCurrentBalance(user.getCurrentBalance().add(amountVal));
            transaction = new Transaction(user.getId(), transactionType, amountVal, null);
        } else {
            user.setCurrentBalance(user.getCurrentBalance().subtract(amountVal));
            transaction = new Transaction(user.getId(), transactionType, amountVal.negate(), null);
        }

        if (MyJDBC.addTransactionToDatabase(transaction) && MyJDBC.updateCurrentBalance(user)) {
            JOptionPane.showMessageDialog(this, transactionType + " Successful!");
            resetFieldsAndUpdateCurrentBalance();
        } else {
            JOptionPane.showMessageDialog(this, transactionType + " Failed...");
        }
    }

    private void handleTransfer(User user, String transferredUser, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            JOptionPane.showMessageDialog(this, "Error: Amount must be greater than zero.");
            return;
        }

        int result = user.getCurrentBalance().compareTo(amount);
        if (result < 0) {
            JOptionPane.showMessageDialog(this, "Error: Insufficient funds.");
            return;
        }

        if (MyJDBC.transfer(user, transferredUser, amount)) {
            JOptionPane.showMessageDialog(this, "Transfer Successful!");
            resetFieldsAndUpdateCurrentBalance();
        } else {
            JOptionPane.showMessageDialog(this, "Transfer Failed...");
        }
    }

    private void resetFieldsAndUpdateCurrentBalance() {
        enterAmountField.setText("");
        if (enterUserField != null) {
            enterUserField.setText("");
        }

        // Update the current balance on the dialog page and the main GUI
        balanceLabel.setText("Balance: $" + user.getCurrentBalance());
        bankingAppGui.getCurrentBalanceField().setText("$" + user.getCurrentBalance());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();
        BigDecimal amountVal;

        try {
            amountVal = new BigDecimal(enterAmountField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Please enter a valid number.");
            return;
        }

        // Deposit
        if (buttonPressed.equalsIgnoreCase("Deposit")) {
            handleTransaction(buttonPressed, amountVal);
        } else {
            // Withdraw or transfer
            int result = user.getCurrentBalance().compareTo(amountVal);
            if (result < 0) {
                JOptionPane.showMessageDialog(this, "Error: Input value is more than the current balance.");
                return;
            }

            if (buttonPressed.equalsIgnoreCase("Withdraw")) {
                handleTransaction(buttonPressed, amountVal);
            } else {
                String transferredUser = enterUserField.getText();
                handleTransfer(user, transferredUser, amountVal);
            }
        }
    }
}
