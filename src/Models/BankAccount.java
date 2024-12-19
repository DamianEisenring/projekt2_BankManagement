package Models;

import java.util.Scanner;

import static DbContext.DbContext.*;

public class BankAccount {
    public static void showUserDashboard(String email) {
        String option;
        int userId = getUserId(email);

        if (userId == -1) {
            System.out.println("Benutzer nicht gefunden.");
            return;
        }

        do {
            System.out.println("Willkommen in Ihrem Dashboard, " + email + "!");
            System.out.println("1. Zeige persönliche Informationen");
            System.out.println("2. Zeige Kontostand");
            System.out.println("3. Einzahlung");
            System.out.println("4. Abhebung");
            System.out.println("5. Zeige Transaktionsverlauf");
            System.out.println("6. Logout");

            Scanner sc = new Scanner(System.in);
            option = sc.nextLine();

            switch (option) {
                case "1":
                    displayUserInfo(email);
                    break;
                case "2":
                    System.out.println("Ihr aktueller Kontostand: " + getBalance(userId) + " EUR");
                    break;
                case "3":
                    System.out.println("Betrag zur Einzahlung eingeben:");
                    double depositAmount = sc.nextDouble();
                    if (updateBalance(userId, depositAmount, "Einzahlung")) {
                        System.out.println("Einzahlung erfolgreich.");
                    }
                    break;
                case "4":
                    System.out.println("Betrag zur Abhebung eingeben:");
                    double withdrawalAmount = sc.nextDouble();
                    if (updateBalance(userId, -withdrawalAmount, "Abhebung")) {
                        System.out.println("Abhebung erfolgreich.");
                    }
                    break;
                case "5":
                    System.out.println("Transaktionsverlauf:");
                    getTransactionHistory(userId);
                    break;
                case "6":
                    System.out.println("Sie wurden erfolgreich ausgeloggt.");
                    break;
                default:
                    System.out.println("Ungültige Auswahl.");
                    break;
            }
        } while (!option.equals("6"));
    }
}

