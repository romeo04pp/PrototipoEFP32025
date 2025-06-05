/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.seguridad;

import Controlador.seguridad.Bodega; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import Modelo.Conexion;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author visitante
 */
public class BodegaDAO {

    private static final String SQL_SELECT = "SELECT id, idTipobodega, nombre, direccion, estado FROM bodega";
    private static final String SQL_INSERT = "INSERT INTO bodega(id, idTipobodega, nombre, direccion, estado) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE bodega SET idTipobodega=?, nombre=?, direccion=?, estado=? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM bodega WHERE id=?";
    private static final String SQL_QUERY = "SELECT id, idTipobodega, nombre, direccion, estado FROM bodega WHERE id = ?";

    public List<Bodega> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Bodega perfil = null;
        List<Bodega> perfiles = new ArrayList<Bodega>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String idTipo = rs.getString("idTipobodega");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String estado = rs.getString("estado");
                
                perfil = new Bodega();
                perfil.setId(id);
                perfil.setIdTipobodega(idTipo);
                perfil.setNombre(nombre);
                perfil.setDireccion(direccion);
                perfil.setEstado(estado);
                perfiles.add(perfil);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return perfiles;
    }

    public int insert(Bodega perfil) { 
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, perfil.getId());
            stmt.setString(2, perfil.getIdTipobodega());
            stmt.setString(3, perfil.getNombre());
            stmt.setString(4, perfil.getDireccion());
            stmt.setString(5, perfil.getEstado());

            System.out.println("ejecutando query: " + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(Bodega perfil) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, perfil.getIdTipobodega());
            stmt.setString(2, perfil.getNombre());
            stmt.setString(3, perfil.getDireccion());
            stmt.setString(4, perfil.getEstado());
            stmt.setString(5, perfil.getId());
            
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado: " + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(Bodega perfil) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, perfil.getId());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public Bodega query(Bodega perfil) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Bodega> perfiles = new ArrayList<Bodega>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, perfil.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                String id = rs.getString("id");
                String idTipo = rs.getString("idTipobodega");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String estado = rs.getString("estado");
                
                perfil = new Bodega();
                perfil.setId(id);
                perfil.setIdTipobodega(idTipo);
                perfil.setNombre(nombre);
                perfil.setDireccion(direccion);
                perfil.setEstado(estado);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return perfil; 
    }
        public void imprimirReporte() {
       //Conexion con la base de datos
        Connection conn = null;
        // Mapa de parámetros que se puede enviar al reporte (aquí está vacío)
        Map p = new HashMap();
        // Objeto que representa el reporte compilado
        JasperReport report;
        // Objeto que representa el reporte ya lleno con los datos
        JasperPrint print;

        try {
            //Conexion con la base de datos
            conn = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/reportes_compras_cxp/"+ "ReporteMetodoDePago.jrxml");
            //se llena el reporte con los datos obtenidos
            print = JasperFillManager.fillReport(report, p, conn);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Vendedores");
            view.setVisible(true);

        } catch (Exception e) {
            // Si ocurre cualquier error, se imprime la traza del error en consola
            e.printStackTrace();
        }
    }    
        
}
