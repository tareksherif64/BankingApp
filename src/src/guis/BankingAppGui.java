package guis;

import db_objs.User;

import javax.swing.*;
import java.awt.*;


public class BankingAppGui extends BaseFrame{

    private JTextField currentBalanceField;
    public JTextField getCurrentBalanceField(){return currentBalanceField;}

    public BankingAppGui(User user){
        super("Banking App", user);
    }
    @Override
    protected void addGuiComponents() {
        // welcome message
        String welcomeMessage = "<html>"
                + "<body style = 'text-align:center'>" +
                "<b>Hello " + user.getUsername() + "</b><br>"
                + "What would you like to do today?</body></html>";
        JLabel WelcomeMessageLabel = new JLabel(welcomeMessage);
        WelcomeMessageLabel.setBounds(0, 20, getWidth() - 10, 40);
        WelcomeMessageLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        WelcomeMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(WelcomeMessageLabel);

        // current balance label
        JLabel currentBalanceLabel = new JLabel("Current Balance:");
        currentBalanceLabel.setBounds(0, 80, getWidth() - 10, 30);
        currentBalanceLabel.setFont(new Font("Dialog", Font.BOLD, 22));
        currentBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(currentBalanceLabel);

        // current balance field
        currentBalanceField = new JTextField(("$" + user.getCurrentBalance()));
        currentBalanceField.setBounds(15, 120, getWidth() - 50, 40);
        currentBalanceField.setFont(new Font("Dialog", Font.BOLD, 28));
        currentBalanceField.setHorizontalAlignment(SwingConstants.RIGHT);
        currentBalanceField.setEditable(false);
        add(currentBalanceField);

        //deposit button
        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(15,180,getWidth() - 50, 50);
        depositButton.setFont(new Font("Dialog", Font.BOLD, 22));
        add(depositButton);

        //withdraw button
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(15,250,getWidth() - 50, 50);
        withdrawButton.setFont(new Font("Dialog", Font.BOLD, 22));
        add(withdrawButton);

        //past transaction button
        JButton pastTransactionButton = new JButton("Past Transaction");
        pastTransactionButton.setBounds(15,320,getWidth() - 50, 50);
        pastTransactionButton.setFont(new Font("Dialog", Font.BOLD, 22));
        add(pastTransactionButton);

        //transfer button
        JButton transferButton = new JButton("Transfer");
        transferButton.setBounds(15,390,getWidth() - 50, 50);
        transferButton.setFont(new Font("Dialog", Font.BOLD, 22));
        add(transferButton);

        //logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(15,500,getWidth() - 50, 50);
        logoutButton.setFont(new Font("Dialog", Font.BOLD, 22));
        add(logoutButton);
    }
}
