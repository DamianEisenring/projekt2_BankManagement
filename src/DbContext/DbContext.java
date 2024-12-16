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
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " email text NOT NULL,\n"
                + " password text NOT NULL\n"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabelle erfolgreich erstellt.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Erstellen der Tabelle: " + e.getMessage());
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

    public static void selectAllUsers() {
        String sql = "SELECT id, name, email, password FROM users";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("email") + "\t" +
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Abrufen: " + e.getMessage());
        }
    }
    public static void displayUserInfo(String email) {
        String sql = "SELECT name, email FROM users WHERE email = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("E-Mail: " + rs.getString("email"));
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
}
