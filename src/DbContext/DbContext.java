package DbContext;

import java.sql.*;

public class DbContext {
    private static final String URL = "jdbc:sqlite:test.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Verbindung zur SQLite-Datenbank erfolgreich hergestellt.");
        } catch (SQLException e) {
            System.out.println("Verbindung fehlgeschlagen: " + e.getMessage());
        }
        return conn;
    }

    public static void createNewTable() {
        String userTable = "CREATE TABLE IF NOT EXISTS users (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " name TEXT NOT NULL,\n"
                + " email TEXT NOT NULL,\n"
                + " password TEXT NOT NULL,\n"
                + " balance REAL DEFAULT 0\n"
                + ");";

        String transactionTable = "CREATE TABLE IF NOT EXISTS transactions (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " user_id INTEGER NOT NULL,\n"
                + " amount REAL NOT NULL,\n"
                + " type TEXT NOT NULL,\n"
                + " timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,\n"
                + " FOREIGN KEY (user_id) REFERENCES users (id)\n"
                + ");";


        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(userTable);
            stmt.execute(transactionTable);

            // Füge die Spalte "balance" hinzu, falls sie noch nicht existiert
            String addBalanceColumn = "ALTER TABLE users ADD COLUMN balance REAL DEFAULT 0";
            stmt.execute(addBalanceColumn);

            System.out.println("Tabellen erfolgreich erstellt oder aktualisiert.");
        } catch (SQLException e) {
            if (!e.getMessage().contains("duplicate column name: balance")) {
                System.out.println("Fehler beim Erstellen oder Aktualisieren der Tabellen: " + e.getMessage());
            }
        }
    }


    public static boolean updateBalance(int userId, double amount, String type) {
        String updateBalanceSQL = "UPDATE users SET balance = balance + ? WHERE id = ?";
        String insertTransactionSQL = "INSERT INTO transactions(user_id, amount, type) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement updateStmt = conn.prepareStatement(updateBalanceSQL);
             PreparedStatement insertStmt = conn.prepareStatement(insertTransactionSQL)) {

            conn.setAutoCommit(false); // Transaktion starten

            updateStmt.setDouble(1, amount);
            updateStmt.setInt(2, userId);
            updateStmt.executeUpdate();

            insertStmt.setInt(1, userId);
            insertStmt.setDouble(2, amount);
            insertStmt.setString(3, type);
            insertStmt.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("Fehler beim Aktualisieren des Kontos: " + e.getMessage());
            return false;
        }
    }

    public static double getBalance(int userId) {
        String sql = "SELECT balance FROM users WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Abrufen des Kontostands: " + e.getMessage());
        }
        return 0.0;
    }

    public static void getTransactionHistory(int userId) {
        String sql = "SELECT amount, type, timestamp FROM transactions WHERE user_id = ? ORDER BY timestamp DESC";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Betrag: " + rs.getDouble("amount") +
                        ", Typ: " + rs.getString("type") +
                        ", Zeit: " + rs.getString("timestamp"));
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Abrufen des Transaktionsverlaufs: " + e.getMessage());
        }
    }

    public static int getUserId(String email) {
        String sql = "SELECT id FROM users WHERE email = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Abrufen der Benutzer-ID: " + e.getMessage());
        }
        return -1;
    }

    public static void displayUserInfo(String email) {
        String sql = "SELECT name, email, balance FROM users WHERE email = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("E-Mail: " + rs.getString("email"));
                System.out.println("Kontostand: " + rs.getDouble("balance") + " EUR");
            } else {
                System.out.println("Benutzerinformationen konnten nicht gefunden werden.");
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Abrufen der Informationen: " + e.getMessage());
        }
    }

    public static boolean loginUser(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login erfolgreich. Willkommen, " + rs.getString("name") + "!");
                return true; // Benutzer gefunden und authentifiziert
            } else {
                System.out.println("Login fehlgeschlagen. E-Mail oder Passwort ist falsch.");
                return false; // Keine Übereinstimmung gefunden
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Login: " + e.getMessage());
            return false;
        }
    }

    public static void insertUser(String name, String email, String password) {
        String sql = "INSERT INTO users(name, email, password) VALUES(?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
            System.out.println("Benutzer erfolgreich hinzugefügt: " + name);
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen: " + e.getMessage());
        }
    }
}
