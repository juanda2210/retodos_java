package com.apex.dao;

import com.apex.database.ConexionDB;
import com.apex.models.Participante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipanteDAO {

    public void crear(Participante participante) throws SQLException {

        String sql = """
        INSERT INTO participantes (nombre, correo, empresa)
        VALUES (?, ?, ?)
        """;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, participante.getNombre());
            ps.setString(2, participante.getCorreo());
            ps.setString(3, participante.getEmpresa());

            ps.executeUpdate();
        }
    }

    public Participante buscarPorId(int id) throws  SQLException {

        String sql = """
        SELECT id_participante, nombre, correo, empresa
        FROM participantes
        WHERE id_participante = ?
        """;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Participante participante = new Participante();

                    participante.setId_participante(rs.getInt("id_participante"));
                    participante.setNombre(rs.getString("nombre"));
                    participante.setCorreo(rs.getString("correo"));
                    participante.setEmpresa(rs.getString("empresa"));
                    return participante;
                }
            }
        }

        return null;
    }

    public void actualizar(Participante participante) throws SQLException {

        String sql = """
        UPDATE participantes
        SET nombre = ?, correo = ?, empresa = ?
        WHERE id_participante = ?
        """;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, participante.getNombre());
            ps.setString(2, participante.getCorreo());
            ps.setString(3, participante.getEmpresa());
            ps.setInt(4, participante.getId_participante());

            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {

        String sql = """
        DELETE FROM participantes
        WHERE id_participante = ?
        """;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }

    public List<Participante> listarTodos() throws SQLException {

        String sql = """
        SELECT id_participante, nombre, correo, empresa
        FROM participantes
        """;

        List<Participante> participantes = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Participante participante = new Participante();

                participante.setId_participante(rs.getInt("id_participante"));
                participante.setNombre(rs.getString("nombre"));
                participante.setCorreo(rs.getString("correo"));
                participante.setEmpresa(rs.getString("empresa"));

                participantes.add(participante);
            }
        }

        return participantes;
    }

    public List<Participante> buscarPorEmpresa(String empresa) throws SQLException {

        String sql = """
            SELECT id_participante, nombre, correo, empresa
            FROM participantes
            WHERE empresa = ?
            """;

        List<Participante> participantes = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, empresa);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Participante participante = new Participante();

                    participante.setId_participante(rs.getInt("id_participante"));
                    participante.setNombre(rs.getString("nombre"));
                    participante.setCorreo(rs.getString("correo"));
                    participante.setEmpresa(rs.getString("empresa"));

                    participantes.add(participante);
                }
            }
        }

        return participantes;
    }

    public int contarParticipantes() throws SQLException {

        String sql = """
            SELECT COUNT(*) AS total
            FROM participantes
            """;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            }
        }

        return 0;
    }
}
