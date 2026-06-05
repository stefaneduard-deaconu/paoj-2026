package com.pao.project.catalog.repository;

import com.pao.project.catalog.config.DatabaseConnection;
import java.sql.*;

public class CatalogRepository {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    public void stergeStudentSiNote(String idStudent) throws SQLException {
        connection.setAutoCommit(false);

        String sqlNote = "DELETE FROM note WHERE id_student = ?";
        String sqlStudent = "DELETE FROM studenti WHERE id_student = ?";

        try (PreparedStatement stmtNote = connection.prepareStatement(sqlNote);
             PreparedStatement stmtStudent = connection.prepareStatement(sqlStudent)) {

            stmtNote.setString(1, idStudent);
            stmtNote.executeUpdate();

            stmtStudent.setString(1, idStudent);
            stmtStudent.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void afiseazaCatalogComplet() {
        String sql = "SELECT s.nume as NumeStudent, m.nume_materie, n.valoare " +
                "FROM studenti s " +
                "JOIN note n ON s.id_student = n.id_student " +
                "JOIN materii m ON n.id_materie = m.id_materie " +
                "ORDER BY s.nume";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("--- Catalog Complet ---");
            while (rs.next()) {
                System.out.printf("Student: %s | Materie: %s | Nota: %d%n",
                        rs.getString("NumeStudent"), rs.getString("nume_materie"), rs.getInt("valoare"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void afiseazaTopStudentiDupaMedie() {
        String sql = "SELECT s.nume, AVG(n.valoare) as Medie " +
                "FROM studenti s " +
                "JOIN note n ON s.id_student = n.id_student " +
                "GROUP BY s.id_student, s.nume " +
                "ORDER BY Medie DESC LIMIT 3";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("--- Top 3 Studenti ---");
            while (rs.next()) {
                System.out.printf("Student: %s | Medie Generala: %.2f%n",
                        rs.getString("nume"), rs.getDouble("Medie"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void detaliiNoteStudent(String idStudent) {
        String sql = "SELECT n.valoare, n.data_acordarii, m.nume_materie, m.nr_credite " +
                "FROM note n " +
                "JOIN materii m ON n.id_materie = m.id_materie " +
                "WHERE n.id_student = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idStudent);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("--- Detalii note student: " + idStudent + " ---");
                while (rs.next()) {
                    System.out.printf("Nota: %d (Data: %s) | Materie: %s (%d credite)%n",
                            rs.getInt("valoare"), rs.getString("data_acordarii"),
                            rs.getString("nume_materie"), rs.getInt("nr_credite"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}