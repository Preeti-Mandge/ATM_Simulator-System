package bank.management.system;

import java.sql.*;

public class Conn {

    Statement s;
    Connection c;

    public Conn() {
        try {

            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bankmanagementsystem", "root", "12345");
            s = c.createStatement();
            System.out.println("Connection done");

        } catch (Exception e) {
            System.out.println("Connection not  done" + e);
        }

    }

}
