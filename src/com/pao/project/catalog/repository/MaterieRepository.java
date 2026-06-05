package com.pao.project.catalog.repository;

import com.pao.project.catalog.config.DatabaseConnection;
import com.pao.project.catalog.model.Materie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MaterieRepository implements Repository<Materie, Integer> {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public void save(Materie materie) {
        String sql = "INSERT INTO materii (nume_materie, nr_credite) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, materie.getNumeMaterie());
            stmt.setInt(2, materie.getNrCredite());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Materie> findById(Integer id) {
        String sql = "SELECT * FROM materii WHERE id_materie = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Materie(
                            rs.getString("nume_materie"),
                            rs.getInt("nr_credite")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Materie> findAll() {
        List<Materie> materii = new ArrayList<>();
        String sql = "SELECT * FROM materii";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                materii.add(new Materie(
                        rs.getString("nume_materie"),
                        rs.getInt("nr_credite")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materii;
    }

    @Override
    public void update(Materie materie) {
        String sql = "UPDATE materii SET nr_credite = ? WHERE nume_materie = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, materie.getNrCredite());
            stmt.setString(2, materie.getNumeMaterie());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM materii WHERE id_materie = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}