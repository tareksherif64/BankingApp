package guis;

import javax.swing.*;
import java.awt.*;

public class LoginGui extends BaseFrame{
    public LoginGui(){
        super("Banking App Login");
    }
    @Override
    protected void addGuiComponents() {
        JLabel bankingAppLabel = new JLabel("Banking Application");
        bankingAppLabel.setBounds(0, 20, super.getWidth(), 40);
        bankingAppLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        bankingAppLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(bankingAppLabel);
    }
}
