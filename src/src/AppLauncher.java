import guis.BankingAppGui;
import guis.LoginGui;
import guis.RegisterGui;

import javax.swing.*;
import java.math.BigDecimal;
import db_objs.*;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginGui().setVisible(true);
//                new RegisterGui().setVisible(true);
//                new BankingAppGui(new User(1, "username", "password", new BigDecimal("20.00"))).setVisible(true);
            }
        });
    }
}
