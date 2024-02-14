/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itson.bdavanzadas.clubnautico;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.itson.bdavanzadas.clubnauticodominio.Socio;
import org.itson.bdavanzadas.clubnauticopersitencia.daos.ISociosDAO;
import org.itson.bdavanzadas.clubnauticopersitencia.dtos.SocioActualizadoDTO;
import org.itson.bdavanzadas.clubnauticopersitencia.dtos.SocioNuevoDTO;
import org.itson.bdavanzadas.clubnauticopersitencia.excepciones.PersistenciaException;
import org.itson.bdavanzadas.clubnauticopersitencia.excepciones.ValidacionDTOException;

/**
 *
 * @author Abe
 */
public class SociosForm extends javax.swing.JFrame {

    private final ISociosDAO sociosDAO;
    DefaultTableModel modelo;
    long idSeleccionado;

    /**
     * Creates new form SocioForm
     */
    public SociosForm(ISociosDAO sociosDAO) {
        initComponents();

        this.sociosDAO = sociosDAO;
        consultar();
    }

    public void guardar() {
        //OBTENER DATOS DEL FORMULARIO
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        //ENCAPSULARLOS
        SocioNuevoDTO socioNuevo = new SocioNuevoDTO();
        socioNuevo.setNombre(nombre);
        socioNuevo.setTelefono(telefono);
        socioNuevo.setCorreo(correo);
        //VALIDARLOS

        try {
            if (socioNuevo.esValido()) {
                Socio socio = this.sociosDAO.agregar(socioNuevo);
                limpiarTabla();
                JOptionPane.showMessageDialog(this, "Se registró el socio", "Notificación", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ValidacionDTOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validadción", JOptionPane.ERROR_MESSAGE);
        } catch (PersistenciaException ex) {
            JOptionPane.showMessageDialog(this, "No fue posible agregar al socio", "Error de almacenamiento",
                    JOptionPane.ERROR_MESSAGE);

        }
        limpiarTabla();
        //MANDARLOS A LA DAO
        //SEGUN RESULTADO LE NOTIFICAMOS AL USUARIO
    }

    private void actualizar() {
        String id = txtid.getText();
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        //ENCAPSULARLOS
        SocioActualizadoDTO socioActualizar = new SocioActualizadoDTO();
        socioActualizar.setId(Integer.parseInt(id));
        socioActualizar.setNombre(nombre);
        socioActualizar.setTelefono(telefono);
        socioActualizar.setCorreo(correo);

        try {

            Socio socioActualizado = this.sociosDAO.actualizar(socioActualizar);
            limpiarTabla();
            JOptionPane.showMessageDialog(this, "Se registró el socio", "Notificación", JOptionPane.INFORMATION_MESSAGE);

        } catch (PersistenciaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validadción", JOptionPane.ERROR_MESSAGE);

            limpiarTabla();
        }
    }
    
    void eliminar(){
        int fila = tblSocios.getSelectedRow();
        try {
            if(fila<0){
                JOptionPane.showMessageDialog(null, "Socio no seleccionado");
                limpiarTabla();
            }
            else{
                Socio socioEliminado = this.sociosDAO.eliminar((int) idSeleccionado);
            }
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, "No se ha eliminado el socio", "No elimnado", JOptionPane.ERROR_MESSAGE);
        }
        limpiarTabla();
    }
    

    private void consultar() {

        try {
            List<Socio> listaSocios = sociosDAO.consultar();
            Object[] socioFila = new Object[4];
            modelo = (DefaultTableModel) tblSocios.getModel();
            for (Socio listaSocio : listaSocios) {
                socioFila[0] = listaSocio.getId();
                socioFila[1] = listaSocio.getNombre();
                socioFila[2] = listaSocio.getTelefono();
                socioFila[3] = listaSocio.getCorreo();

                modelo.addRow(socioFila);
            }

            tblSocios.setModel(modelo);
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, "No se puede acceder a los socios", "Error de consulta", 
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    
    
    void limpiarTabla() {
        for (int i = 0; i < tblSocios.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void limpiarDatos() {
        limpiarTabla();
        txtid.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblId = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSocios = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnCancelar2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblId.setText("id");

        lblNombre.setText("Nombre");

        lblTelefono.setText("Telefono");

        lblCorreo.setText("Correo");

        txtid.setEditable(false);
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });

        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        tblSocios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Telefono", "Correo"
            }
        ));
        tblSocios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSociosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSocios);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnCancelar2.setText("Actualizar");
        btnCancelar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(6, 6, 6)
                        .addComponent(btnCancelar2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                            .addComponent(lblCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblId)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTelefono)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCorreo)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnCancelar)
                            .addComponent(btnEliminar)
                            .addComponent(btnCancelar2))))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
        consultar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarDatos();
        consultar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
        consultar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar2ActionPerformed
        actualizar();
        consultar();
        
    }//GEN-LAST:event_btnCancelar2ActionPerformed

    private void tblSociosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSociosMouseClicked
        int fila = tblSocios.getSelectedRow();
        idSeleccionado = -1;
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "No hay fila seleccionada");
        } else {
            idSeleccionado = Long.parseLong((String) tblSocios.getValueAt(fila, 0).toString());
            String nombre = (String) tblSocios.getValueAt(fila, 1);
            String telefono = (String) tblSocios.getValueAt(fila, 2);
            String correo = (String) tblSocios.getValueAt(fila, 3);

            txtid.setText("" + idSeleccionado);
            txtNombre.setText(nombre);
            txtTelefono.setText(telefono);
            txtCorreo.setText(correo);
        }

    }//GEN-LAST:event_tblSociosMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelar2;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JTable tblSocios;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
