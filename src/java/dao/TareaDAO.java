/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import config.ConexionDB;
import modelo.Tarea;

/**
 *
 * @author Sebastian
 */
public class TareaDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public boolean registrar(Tarea tarea) {
        String sql = "INSERT INTO tarea(titulo, descripcion, estado, fecha, idUsuario) VALUES (?, ?, ?, ?, ?)";
        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, tarea.getTitulo());
            ps.setString(2, tarea.getDescripcion());
            ps.setString(3, tarea.getEstado());
            ps.setDate(4, new java.sql.Date(tarea.getFecha().getTime())); // Convertimos java.util.Date a java.sql.Date
            ps.setInt(5, tarea.getIdUsuario());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public List<Tarea> listar() {
        List<Tarea> lista = new ArrayList<>();
        String sql = "SELECT * FROM tarea";
        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Tarea tarea = new Tarea();
                tarea.setIdTarea(rs.getInt("idTarea"));
                tarea.setTitulo(rs.getString("titulo"));
                tarea.setDescripcion(rs.getString("descripcion"));
                tarea.setEstado(rs.getString("estado"));
                tarea.setFecha(rs.getDate("fecha"));
                tarea.setIdUsuario(rs.getInt("idUsuario"));
                lista.add(tarea);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    
    public boolean eliminar(int id) {
        String sql = "DELETE FROM tarea WHERE idTarea = ?";
        try {
            con = ConexionDB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
