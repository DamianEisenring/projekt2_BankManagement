package Models;

import java.io.DataOutput;
import java.util.Scanner;

import static DbContext.DbContext.*;

public class BankAccount {
    public static void showUserDashboard(String email) {
        String option;


        do
        {
            System.out.println("Willkommen in Ihrem Dashboard, " + email + "!");
            System.out.println("Zeige persönliche Informationen (I)");
            System.out.println("Zeige Transaktionsverlauf (T)");
            System.out.println("Logout (L)");

            Scanner sc = new Scanner(System.in);
            option = sc.nextLine();

            switch (option) {
                case "I":
                    displayUserInfo(email);
                    break;
                case "T":
                    System.out.println("Transaktionsverlauf LOL");
                    break;
                case "L":
                    System.out.println("Sie wurden erfolgreich ausgeloggt.");
                    break;
                default:
                    System.out.println("Ungültige Auswahl.");
                    break;
            }
        } while (!option.equalsIgnoreCase("L"));
    }


}
