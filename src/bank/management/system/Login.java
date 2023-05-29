package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.JFrame;

public class Login extends JFrame implements ActionListener {

    JButton login, signup, clear;   // golbally define
    JTextField cardTextField;
    JPasswordField pinTextField;

    //default constructor
    Login() {

        setTitle("AUTOMATED TELLER MACHINE");

        setLayout(null); //we make it null otherwise the setbounds will not work

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bank-icon.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        //add some text/label
        JLabel text = new JLabel("Welcome to HDFC ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200, 40, 500, 40);
        add(text);

        JLabel cardno = new JLabel("Card No:");
        cardno.setFont(new Font("Raleway", Font.BOLD, 28));
        cardno.setBounds(120, 150, 150, 30);
        add(cardno);

        //adding text field
        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220, 250, 30);
        add(pin);

        //adding text field
        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);

        //add buttons
        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.black);
        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.black);
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        add(clear);

        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.black);
        signup.setForeground(Color.white);
        signup.addActionListener(this);
        add(signup);

        //set frame colour
        getContentPane().setBackground(Color.WHITE);

        setSize(800, 480);   //frame size
        setVisible(true);    // to make frame visible
        setLocation(350, 200);   // to open frame in middle

    }

    //perform action when buttons get clicked
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            //when clear button is clicked it will clear feild
            cardTextField.setText(""); // to clear field
            pinTextField.setText("");
        } else if (ae.getSource() == login) {
             
            Conn conn = new Conn();
            
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();
            
            //match if cardnumber and pin enetered in login form matches with any of the data from login table
            String query = "select * from login where cardnumber = '"+cardnumber+"' and pin = '"+pinnumber+"' ";
            try{
                
                ResultSet rs = conn.s.executeQuery(query);
                if(rs.next()){   //if we get data from DB then close the login frame
                    
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                    
                } else{
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
                        
                    
            }catch (Exception e){
                System.out.println(e);
            }
            
        } else if (ae.getSource() == signup) {
            //when signup button get clicked open signup page
                     setVisible(false);
                     new SignupOne().setVisible(true);
        }
    }

    public static void main(String args[]) {
        new Login();
    }
}
