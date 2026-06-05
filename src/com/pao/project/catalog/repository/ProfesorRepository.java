package com.pao.project.catalog.repository;

import com.pao.project.catalog.config.DatabaseConnection;
import com.pao.project.catalog.model.Profesor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfesorRepository implements Repository<Profesor, Integer> {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public void save(Profesor profesor) {
        String sql = "INSERT INTO profesori (nume, email, salariu, specializare) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, profesor.getNume());
            stmt.setString(2, profesor.getEmail());
            stmt.setDouble(3, profesor.getSalariu());
            stmt.setString(4, profesor.getSpecializare());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Profesor> findById(Integer id) {
        String sql = "SELECT * FROM profesori WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Profesor(
                            rs.getString("nume"),
                            rs.getString("email"),
                            rs.getDouble("salariu"),
                            rs.getString("specializare")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Profesor> findAll() {
        List<Profesor> profesori = new ArrayList<>();
        String sql = "SELECT * FROM profesori";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                profesori.add(new Profesor(
                        rs.getString("nume"),
                        rs.getString("email"),
                        rs.getDouble("salariu"),
                        rs.getString("specializare")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profesori;
    }

    @Override
    public void update(Profesor profesor) {
        String sql = "UPDATE profesori SET nume = ?, salariu = ?, specializare = ? WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, profesor.getNume());
            stmt.setDouble(2, profesor.getSalariu());
            stmt.setString(3, profesor.getSpecializare());
            stmt.setString(4, profesor.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM profesori WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}