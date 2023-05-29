package bank.management.system;

import java.awt.Color;
import java.sql.ResultSet;
import javax.swing.*;

public class MiniStatement extends JFrame {

    MiniStatement(String pinnumber) {

        setTitle("Mini Statement");
        setLayout(null);  //we make it null otherwise the setbounds will not work

        JLabel mini = new JLabel();
        mini.setBounds(20, 140, 400, 200);
        add(mini);

        JLabel bank = new JLabel("HDFC Bank");
        bank.setBounds(150, 20, 100, 20);
        add(bank);

        JLabel card = new JLabel();
        card.setBounds(20, 80, 300, 20);
        add(card);

        JLabel bal = new JLabel();
        bal.setBounds(20, 400, 300, 20);
        add(bal);

        JLabel msg = new JLabel("Thank you for visiting HDFC Bank!!");
         msg.setBounds(80, 470, 300, 20);
        add(msg);
        
        try {
            Conn conn = new Conn();

            ResultSet rs = conn.s.executeQuery("select * from login where pin= '" + pinnumber + "'");
            while (rs.next()) {

                card.setText("Card Number: " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            int balance = 0;
            Conn conn = new Conn();

            ResultSet rs = conn.s.executeQuery("select * from bank where pin= '" + pinnumber + "' ");

            while (rs.next()) {
                mini.setText(mini.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                        + rs.getString("amount") + "<br><br><br>");

                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            bal.setText("Your total Balance is Rs " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(400, 600);
        setLocation(20, 20);
        getContentPane().setBackground(Color.white);
        setVisible(true);
    }

    public static void main(String args[]) {
        new MiniStatement("");
    }
}
