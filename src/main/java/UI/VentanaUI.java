/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

/**
 *
 * @author cacerola
 */
public class VentanaUI extends javax.swing.JFrame {
    
    Ventana ventana = new Ventana();
    
    public VentanaUI() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSeleccionarArchivo = new javax.swing.JButton();
        btnHTMLTokens = new javax.swing.JButton();
        btnHTMLErrores = new javax.swing.JButton();
        lblOpcion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSeleccionarArchivo.setText("Seleccionar Archivo");
        btnSeleccionarArchivo.setName("btnSeleccionarArchivo"); // NOI18N
        btnSeleccionarArchivo.addActionListener(this::btnSeleccionarArchivoActionPerformed);

        btnHTMLTokens.setText("HTML Tokens");
        btnHTMLTokens.setName(""); // NOI18N
        btnHTMLTokens.addActionListener(this::btnHTMLTokensActionPerformed);

        btnHTMLErrores.setText("HTML Errores");
        btnHTMLErrores.setActionCommand("HTML Errores");
        btnHTMLErrores.setName(""); // NOI18N
        btnHTMLErrores.addActionListener(this::btnHTMLErroresActionPerformed);

        lblOpcion.setText("Generar archivo HTML");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnHTMLTokens)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnHTMLErrores)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOpcion)
                    .addComponent(btnSeleccionarArchivo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnSeleccionarArchivo)
                .addGap(46, 46, 46)
                .addComponent(lblOpcion)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHTMLTokens)
                    .addComponent(btnHTMLErrores))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarArchivoActionPerformed
        ventana.procesarArchivo();
    }//GEN-LAST:event_btnSeleccionarArchivoActionPerformed

    private void btnHTMLTokensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHTMLTokensActionPerformed
        ventana.generarHTMLToken();
    }//GEN-LAST:event_btnHTMLTokensActionPerformed

    private void btnHTMLErroresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHTMLErroresActionPerformed
        ventana.generarHTMLError();
    }//GEN-LAST:event_btnHTMLErroresActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHTMLErrores;
    private javax.swing.JButton btnHTMLTokens;
    private javax.swing.JButton btnSeleccionarArchivo;
    private javax.swing.JLabel lblOpcion;
    // End of variables declaration//GEN-END:variables
}
