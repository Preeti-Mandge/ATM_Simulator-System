package bank.management.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class BalanceEnquiry extends JFrame implements ActionListener {

    JButton back, show;
    String pinnumber;
    JLabel image;

    BalanceEnquiry(String pinnumber)  {

        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm3.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        image = new JLabel(i3);
        image.setBounds(0, 0, 900, 750);
        add(image);

        back = new JButton("BACK");
        back.setBounds(360, 380, 150, 30);
        back.addActionListener(this);
        image.add(back);

       int balance = 0;
        try{
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                   
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                     
                }
            }
        }catch(Exception e){}
        

       
        JLabel text = new JLabel("Your Current Account Balance is Rs " + balance);
     
        text.setBounds(180, 150, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true); //to hide that java logo at upper side
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }

    }

    public static void main(String args[])  {
        new BalanceEnquiry("");
    }

}
