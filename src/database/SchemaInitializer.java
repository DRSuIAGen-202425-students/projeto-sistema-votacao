package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaInitializer {
    public static void initialize() {
        String sqlEleitor = """
            CREATE TABLE IF NOT EXISTS Eleitor (
              id INTEGER PRIMARY KEY AUTOINCREMENT,
              nome TEXT NOT NULL,
              numero_eleitor TEXT UNIQUE NOT NULL,
              username TEXT UNIQUE NOT NULL,
              password_hash TEXT NOT NULL,
              votou INTEGER NOT NULL DEFAULT 0
            );
            """;

        String sqlCandidato = """
            CREATE TABLE IF NOT EXISTS Candidato (
              id INTEGER PRIMARY KEY AUTOINCREMENT,
              nome TEXT NOT NULL,
              partido TEXT NOT NULL,
              numero TEXT UNIQUE NOT NULL
            );
            """;

        String sqlVoto = """
            CREATE TABLE IF NOT EXISTS Voto (
              id INTEGER PRIMARY KEY AUTOINCREMENT,
              id_candidato INTEGER NOT NULL,
              timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
              FOREIGN KEY(id_candidato) REFERENCES Candidato(id)
            );
            """;

        try (Connection conn = ConnectionManager.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlEleitor);
            stmt.execute(sqlCandidato);
            stmt.execute(sqlVoto);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inicializar esquema", e);
        }
    }
}
