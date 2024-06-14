package guis;

import javax.swing.*;
import java.awt.*;

public class RegisterGui extends BaseFrame{
    public RegisterGui(){
        super("Banking App Register");
    }


    @Override
    protected void addGuiComponents() {
        //main label
        JLabel bankingAppLabel = new JLabel("Banking Application");
        bankingAppLabel.setBounds(0, 20, super.getWidth(), 40);
        bankingAppLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        bankingAppLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(bankingAppLabel);

        //username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 120, getWidth() - 30, 24);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(usernameLabel);

        //username field
        JTextField usernameField = new JTextField();
        usernameField.setBounds(20,160,getWidth() - 50, 40);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 28));
        add(usernameField);

        //password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 220, getWidth() - 50, 24);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(passwordLabel);

        //password field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20,260,getWidth() - 50, 40);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 28));
        add(passwordField);

        //re-enter password
        JLabel rePasswordLabel = new JLabel("Re-type password:");
        rePasswordLabel.setBounds(20, 320, getWidth() - 50, 24);
        rePasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(rePasswordLabel );

        //re-enter password field
        JPasswordField rePasswordField = new JPasswordField();
        rePasswordField.setBounds(20,360,getWidth() - 50, 40);
        rePasswordField.setFont(new Font("Dialog", Font.PLAIN, 28));
        add(rePasswordField);

        //Register Button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(20,460,getWidth() - 50, 40);
        registerButton.setFont(new Font("Dialog", Font.BOLD, 20));
        add(registerButton);

        //Login label
        JLabel loginLabel = new JLabel("<html><a href =\"#\">Already have an account? Login Here</a></html>");
        loginLabel.setBounds(0,510,getWidth() - 10, 30);
        loginLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loginLabel);
    }
}
