import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static DbContext.DbContext.*;
import static Models.BankAccount.*;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String selection;
        connect();
        createNewTable();

        while (true){
            System.out.println("What is your goal?");
            System.out.println("Add Account (A)");
            System.out.println("Login (L)");
            System.out.println("Exit (E)");
            selection = sc.nextLine();
            switch (selection){
                case "A" :
                    System.out.println("Please enter your name");
                    String name = sc.nextLine();

                    System.out.println("Please enter your E-Mail");
                    String email = sc.nextLine();

                    System.out.println("Please enter your Password");
                    String password = sc.nextLine();

                    insertUser(name, email, password );
                    break;
                case "L":
                    System.out.println("Please enter your E-Mail:");
                    email = sc.nextLine();

                    System.out.println("Please enter your Password:");
                    password = sc.nextLine();

                    if (loginUser(email, password)) {
                        showUserDashboard(email);
                    }
                    break;
                case "E":
                    System.out.println("Goodbye");
                    System.exit(0);
            }
        }
    }
}

