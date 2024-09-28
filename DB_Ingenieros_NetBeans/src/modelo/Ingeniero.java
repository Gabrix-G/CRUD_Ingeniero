package modelo;

import vista.frmIngenieros;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Ingeniero {
    private String UUID_Ingeniero;
    private String Nombre_Ingeniero;
    private int Edad_Ingeniero;
    private double Peso_Ingeniero;
    private String Correo_Ingeniero;

    // Getters y Setters
    public String getUUID_Ingeniero() { return UUID_Ingeniero; }
    public void setUUID_Ingeniero(String UUID_Ingeniero) { this.UUID_Ingeniero = UUID_Ingeniero; }
    public String getNombre_Ingeniero() { return Nombre_Ingeniero; }
    public void setNombre_Ingeniero(String nombre_Ingeniero) { Nombre_Ingeniero = nombre_Ingeniero; }
    public int getEdad_Ingeniero() { return Edad_Ingeniero; }
    public void setEdad_Ingeniero(int edad_Ingeniero) { Edad_Ingeniero = edad_Ingeniero; }
    public double getPeso_Ingeniero() { return Peso_Ingeniero; }
    public void setPeso_Ingeniero(double peso_Ingeniero) { Peso_Ingeniero = peso_Ingeniero; }
    public String getCorreo_Ingeniero() { return Correo_Ingeniero; }
    public void setCorreo_Ingeniero(String correo_Ingeniero) { Correo_Ingeniero = correo_Ingeniero; }

    // Métodos CRUD
    public void Guardar() {
        String query = "INSERT INTO tb_Ingeniero (UUID_Ingeniero, Nombre_Ingeniero, Edad_Ingeniero, Peso_Ingeniero, Correo_Ingeniero) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, UUID_Ingeniero);
            stmt.setString(2, Nombre_Ingeniero);
            stmt.setInt(3, Edad_Ingeniero);
            stmt.setDouble(4, Peso_Ingeniero);
            stmt.setString(5, Correo_Ingeniero);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Actualizar() {
        String query = "UPDATE tb_Ingeniero SET Nombre_Ingeniero=?, Edad_Ingeniero=?, Peso_Ingeniero=?, Correo_Ingeniero=? WHERE UUID_Ingeniero=?";
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, Nombre_Ingeniero);
            stmt.setInt(2, Edad_Ingeniero);
            stmt.setDouble(3, Peso_Ingeniero);
            stmt.setString(4, Correo_Ingeniero);
            stmt.setString(5, UUID_Ingeniero);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Eliminar(String uuid) {
        String query = "DELETE FROM tb_Ingeniero WHERE UUID_Ingeniero=?";
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, uuid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Mostrar(JTable table) {
        String query = "SELECT * FROM tb_Ingeniero";
        try (Connection conn = Conexion.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            DefaultTableModel model = new DefaultTableModel(new String[]{"UUID", "Nombre", "Edad", "Peso", "Correo"}, 0);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("UUID_Ingeniero"),
                    rs.getString("Nombre_Ingeniero"),
                    rs.getInt("Edad_Ingeniero"),
                    rs.getDouble("Peso_Ingeniero"),
                    rs.getString("Correo_Ingeniero")
                });
            }
            table.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    

    public void cargarDatosTabla(frmIngenieros vista) {
        int row = vista.jtbIngenieros.getSelectedRow();
        if (row != -1) {
            UUID_Ingeniero = (String) vista.jtbIngenieros.getValueAt(row, 0);
            Nombre_Ingeniero = (String) vista.jtbIngenieros.getValueAt(row, 1);
            Edad_Ingeniero = (int) vista.jtbIngenieros.getValueAt(row, 2);
            Peso_Ingeniero = (double) vista.jtbIngenieros.getValueAt(row, 3);
            Correo_Ingeniero = (String) vista.jtbIngenieros.getValueAt(row, 4);

            vista.txtNombre.setText(Nombre_Ingeniero);
            vista.txtEdad.setText(String.valueOf(Edad_Ingeniero));
            vista.txtPeso.setText(String.valueOf(Peso_Ingeniero));
            vista.txtCorreo.setText(Correo_Ingeniero);
        }
    }
}