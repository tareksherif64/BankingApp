package guis;

import db_objs.User;
import javax.swing.*;

public class BankingAppDialog extends JDialog{
    private User user;
    private BankingAppGui bankingAppGui;

    public BankingAppDialog(BankingAppGui bankingAppGui, User user){

        setSize(400,400);
        setModal(true);
        setLocationRelativeTo(bankingAppGui);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        this.bankingAppGui = bankingAppGui;
        this.user = user;


    }


}
