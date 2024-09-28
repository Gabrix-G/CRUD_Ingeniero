package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Ingeniero;
import vista.frmIngenieros;

public class ctrlIngeniero implements MouseListener {
    private frmIngenieros vista;
    private Ingeniero modelo;

    public ctrlIngeniero(frmIngenieros vista, Ingeniero modelo) {
        this.vista = vista;
        this.modelo = modelo;

        vista.btnGuardar.addMouseListener(this);
        vista.jtbIngenieros.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            modelo.setUUID_Ingeniero(java.util.UUID.randomUUID().toString());
            modelo.setNombre_Ingeniero(vista.txtNombre.getText());
            modelo.setEdad_Ingeniero(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso_Ingeniero(Double.parseDouble(vista.txtPeso.getText()));
            modelo.setCorreo_Ingeniero(vista.txtCorreo.getText());

            modelo.Guardar();
            modelo.Mostrar(vista.jtbIngenieros);
        }

        if (e.getSource() == vista.btnEliminar) {
            int row = vista.jtbIngenieros.getSelectedRow();
            if (row != -1) {
                String uuid = (String) vista.jtbIngenieros.getValueAt(row, 0);
                modelo.Eliminar(uuid);
                modelo.Mostrar(vista.jtbIngenieros);
            }
        }

        if (e.getSource() == vista.jtbIngenieros) {
            modelo.cargarDatosTabla(vista);
        }

        if (e.getSource() == vista.btnActualizar) {
            modelo.setNombre_Ingeniero(vista.txtNombre.getText());
            modelo.setEdad_Ingeniero(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso_Ingeniero(Double.parseDouble(vista.txtPeso.getText()));
            modelo.setCorreo_Ingeniero(vista.txtCorreo.getText());

            modelo.Actualizar();
            modelo.Mostrar(vista.jtbIngenieros);
        }

        if (e.getSource() == vista.btnLimpiar) {
            vista.txtNombre.setText("");
            vista.txtEdad.setText("");
            vista.txtPeso.setText("");
            vista.txtCorreo.setText("");
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}