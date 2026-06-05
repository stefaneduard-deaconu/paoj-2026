package com.pao.project.catalog.repository;

import com.pao.project.catalog.config.DatabaseConnection;
import com.pao.project.catalog.model.IdentificatorScolar;
import com.pao.project.catalog.model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository implements Repository<Student, String> {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public void save(Student student) {
        String sql = "INSERT INTO studenti (id_student, nume, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, student.getId().getCod());
            stmt.setString(2, student.getNume());
            stmt.setString(3, student.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Student> findById(String id) {
        String sql = "SELECT * FROM studenti WHERE id_student = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Student(
                            rs.getString("nume"),
                            rs.getString("email"),
                            new IdentificatorScolar(rs.getString("id_student"))
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        List<Student> studenti = new ArrayList<>();
        String sql = "SELECT * FROM studenti";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                studenti.add(new Student(
                        rs.getString("nume"),
                        rs.getString("email"),
                        new IdentificatorScolar(rs.getString("id_student"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studenti;
    }

    @Override
    public void update(Student student) {
        String sql = "UPDATE studenti SET nume = ?, email = ? WHERE id_student = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, student.getNume());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getId().getCod());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM studenti WHERE id_student = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}