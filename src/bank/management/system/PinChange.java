package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PinChange extends JFrame implements ActionListener {

    String pinnumber;
    JPasswordField pin, repin;
    JButton change, back;

    PinChange(String pinnumber) {

        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm3.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 750);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setBounds(250, 150, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        JLabel pintext = new JLabel("New  PIN:");
        pintext.setBounds(170, 190, 500, 35);
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.BOLD, 16));
        image.add(pintext);

        pin = new JPasswordField();
        pin.setFont(new Font("System", Font.BOLD, 16));
        pin.setBounds(340, 195, 150, 25);
        image.add(pin);

        JLabel repintext = new JLabel("Re-Enter New  PIN:");
        repintext.setBounds(170, 250, 500, 35);
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.BOLD, 16));
        image.add(repintext);

        repin = new JPasswordField();
        repin.setFont(new Font("System", Font.BOLD, 16));
        repin.setBounds(340, 250, 150, 25);
        image.add(repin);

        change = new JButton("CHANGE");
        change.setBounds(360, 340, 150, 30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("BACK");
        back.setBounds(360, 380, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true); //to hide that java logo at upper side
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == change) {
            try {

                String npin = pin.getText();
                String rpin = repin.getText();

                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }
                
                if (npin.equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter new PIN");
                    return;
                }
                
                if (rpin.equals("")){
                    JOptionPane.showMessageDialog(null, "Please re-enter new PIN");
                    return;
                }
                
                Conn conn = new Conn();
                //there are pin in 3tables so need to update in all tables
                
                String query1 = " update bank set pin = '"+rpin+"' where pin='"+pinnumber+"'";
                String query2 = " update login set pin = '"+rpin+"' where pin='"+pinnumber+"'";
                String query3 = " update signupthree set pinnumber = '"+rpin+"' where pinnumber='"+pinnumber+"'";
                
                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);
                conn.s.executeUpdate(query3);
                
                  JOptionPane.showMessageDialog(null, "PIN changed successfully");
                  setVisible(false);
            new Transactions(rpin).  setVisible(true);

            } catch (Exception e) {
                System.out.println(e);
            }
        }else{
            setVisible(false);
            new Transactions(pinnumber).  setVisible(true);
        }
    }

    public static void main(String args[]) {
        new PinChange("").setVisible(true);
    }

}
