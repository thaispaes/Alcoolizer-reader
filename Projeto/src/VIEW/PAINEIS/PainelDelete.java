/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW.PAINEIS;

import DAO.Func;
import javax.swing.JOptionPane;

/**
 *
 * @author Danilo
 */
public class PainelDelete extends javax.swing.JPanel {

    /**
     * Creates new form PainelDelete
     */
    public PainelDelete() {
        initComponents();
        DAO.Func.AddArquivos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        combo = new javax.swing.JComboBox<>();
        jButton_delete = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 152, 0));
        setMaximumSize(new java.awt.Dimension(525, 67));
        setMinimumSize(new java.awt.Dimension(525, 67));
        setPreferredSize(new java.awt.Dimension(525, 67));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Name of file where data will be deleted:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 339, 19));

        combo.setBackground(new java.awt.Color(255, 224, 178));
        combo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });
        add(combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, 430, 31));

        jButton_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/remove.png"))); // NOI18N
        jButton_delete.setBorderPainted(false);
        jButton_delete.setContentAreaFilled(false);
        jButton_delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteActionPerformed(evt);
            }
        });
        add(jButton_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 70, 60));
    }// </editor-fold>//GEN-END:initComponents

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboActionPerformed

    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed
        
        if (combo.getSelectedIndex()==-1) {
            JOptionPane.showMessageDialog(null, "Select at least one file", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Func.DeleteArquivos(combo.getSelectedItem().toString());
        }
    }//GEN-LAST:event_jButton_deleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox<String> combo;
    public static javax.swing.JButton jButton_delete;
    public static javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

}