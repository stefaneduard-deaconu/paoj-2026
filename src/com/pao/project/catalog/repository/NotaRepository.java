package com.pao.project.catalog.repository;

import com.pao.project.catalog.config.DatabaseConnection;
import com.pao.project.catalog.model.Nota;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NotaRepository implements Repository<Nota, Integer> {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    public void saveNotaComplet(Nota nota, String idStudent, int idMaterie) {
        String sql = "INSERT INTO note (valoare, data_acordarii, id_student, id_materie) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, nota.getValoare());
            stmt.setString(2, nota.getData());
            stmt.setString(3, idStudent);
            stmt.setInt(4, idMaterie);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Nota entity) {
        throw new UnsupportedOperationException("Foloseste saveNotaComplet pentru a asocia studentul si materia!");
    }

    @Override
    public Optional<Nota> findById(Integer id) {
        return Optional.empty();
    }

    public List<Nota> getNoteByStudentId(String studentId) {
        List<Nota> note = new ArrayList<>();
        String sql = "SELECT valoare, data_acordarii FROM note WHERE id_student = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    note.add(new Nota(rs.getInt("valoare"), rs.getString("data_acordarii")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return note;
    }

    @Override
    public List<Nota> findAll() {
        return new ArrayList<>();
    }

    @Override
    public void update(Nota nota) {}

    @Override
    public void delete(Integer id) {}
}