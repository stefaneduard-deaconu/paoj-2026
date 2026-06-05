package com.pao.laboratory14.exercise2.repository;

import com.pao.laboratory14.exercise1.TipBilet;
import com.pao.laboratory14.exercise2.model.Eveniment;
import com.pao.laboratory14.exercise2.util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EvenimentRepository implements Repository<Eveniment, Integer> {

    private final Connection connection;

    public EvenimentRepository() {
        try {
            this.connection = DatabaseConnection.getInstance().getConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        initSchema();
    }

    public void initSchema() {
        String dropTable = "DROP TABLE IF EXISTS evenimente;";
        String createTable = "CREATE TABLE IF NOT EXISTS evenimente (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nume TEXT NOT NULL, " +
                "data TEXT NOT NULL, " +
                "capacitate INTEGER, " +
                "tip TEXT" +
                ");";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(dropTable);
            stmt.execute(createTable);
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la initializarea schemei: " + e.getMessage());
        }
    }

    @Override
    public void save(Eveniment eveniment) {
        String sql = "INSERT INTO evenimente (nume, data, capacitate, tip) VALUES (?, ?, ?, ?);";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, eveniment.getNume());
            ps.setString(2, eveniment.getData());
            ps.setInt(3, eveniment.getCapacitate());
            ps.setString(4, eveniment.getTip().name());

            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    eveniment.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la salvarea evenimentului: " + e.getMessage());
        }
    }

    @Override
    public List<Eveniment> findAll() {
        List<Eveniment> evenimente = new ArrayList<>();
        String sql = "SELECT * FROM evenimente ORDER BY id;";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Eveniment ev = new Eveniment(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("data"),
                        rs.getInt("capacitate"),
                        TipBilet.valueOf(rs.getString("tip"))
                );
                evenimente.add(ev);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la preluarea evenimentelor: " + e.getMessage());
        }
        return evenimente;
    }

    public int deleteImpl(Integer id) {
        String sql = "DELETE FROM evenimente WHERE id = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la stergerea evenimentului: " + e.getMessage());
        }
    }

    public void delete(Integer id) {
        deleteImpl(id);
    }

    public int count() {
        String sql = "SELECT COUNT(*) FROM evenimente;";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la numărarea evenimentelor: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public Optional<Eveniment> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void update(Eveniment entity) {
    }
}