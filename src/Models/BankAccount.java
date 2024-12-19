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
            System.out.println("1. Zeige persönliche Informationen");
            System.out.println("2. Zeige Kontostand");
            System.out.println("3. Einzahlung");
            System.out.println("4. Abhebung");
            System.out.println("5. Zeige Transaktionsverlauf");
            System.out.println("6. Logout");
            System.out.println("7. Kontoinformationen aktualisieren");
            System.out.println("8. Konto löschen");


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
                    if (!sc.hasNextDouble()) {
                        System.out.println("Ungültiger Betrag.");
                        sc.next(); // Eingabe verwerfen
                        break;
                    }
                    double depositAmount = sc.nextDouble();
                    if (depositAmount <= 0) {
                        System.out.println("Betrag muss positiv sein.");
                        break;
                    }
                    if (updateBalance(userId, depositAmount, "Einzahlung")) {
                        System.out.println("Einzahlung erfolgreich.");
                    }
                    break;

                case "4":
                    System.out.println("Betrag zur Abhebung eingeben:");
                    if (!sc.hasNextDouble()) {
                        System.out.println("Ungültiger Betrag.");
                        sc.next();
                        break;
                    }
                    double withdrawalAmount = sc.nextDouble();
                    if (withdrawalAmount <= 0) {
                        System.out.println("Betrag muss positiv sein.");
                        break;
                    }
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
                case "7":
                    System.out.println("Was möchten Sie aktualisieren? (name/password)");
                    String field = sc.nextLine();
                    if (!field.equals("name") && !field.equals("password")) {
                        System.out.println("Ungültige Option.");
                        break;
                    }
                    System.out.println("Geben Sie den neuen Wert ein:");
                    String newValue = sc.nextLine();
                    if (updateUserInfo(userId, field, newValue)) {
                        System.out.println(field + " erfolgreich aktualisiert.");
                    }
                    break;
                case "8":
                    System.out.println("Sind Sie sicher, dass Sie Ihr Konto löschen möchten? (ja/nein)");
                    String confirm = sc.nextLine();
                    if (confirm.equalsIgnoreCase("ja")) {
                        if (deleteUserAccount(userId)) {
                            System.out.println("Konto gelöscht. Sie werden ausgeloggt.");
                            option = "6"; // Automatisch ausloggen
                        }
                    } else {
                        System.out.println("Löschung abgebrochen.");
                    }
                    break;
            }
        } while (!option.equals("6"));
    }
}

