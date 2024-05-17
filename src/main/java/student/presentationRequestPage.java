/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package student;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
//import com.mycompany.javaprojectmanagementsystem;

/**
 *
 * @author User
 */
public class presentationRequestPage extends javax.swing.JFrame {
    private class presentationRequest {
        private final String name;
        private final String id;
        private final String intake;
        private final String email;
        private final String topic;
        private final String startTime;
        private final String endTime;
        private final String supervisee;
        private final String status;
        private final String reason;
        private final LocalDateTime presentationDate;   
    
    
    public presentationRequest(String name, String id, String intake, String email, String topic, String startTime, String endTime, String supervisee, String status, String reason, LocalDateTime presentationDate) {
        this.name = name;
        this.id = id;
        this.intake = intake;
        this.email = email;
        this.topic = topic;
        this.startTime = startTime;
        this.endTime = endTime;
        this.supervisee = supervisee;
        this.status = status;
        this.reason = reason;
        this.presentationDate = presentationDate;
    }
    
    @Override
    public String toString() {
        return  id + "," + name + "," + email + "," + intake + "," + topic + "," + presentationDate + "," + startTime + "," + endTime + "," + reason + "," + supervisee + "," + status;
    }
}
    /**
     * Creates new form requestPresentation
     */
    public presentationRequestPage() {
        initComponents();
        setSize(950, 700);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;

        setLocation(x, y);
        
        // Set Pending Status to get approval
        statusField.setText("Pending"); 
        
        // Make the Status field read-only
        statusField.setEditable(false);
    }
    
    private void sendRequest() {
        String name = nameField.getText();
        String id = idField.getText();
        String intake = intakeField.getText();
        String email = emailField.getText();
        String topic = topicField.getText();
        String startTime = (String) jComboBox2.getSelectedItem();
        String endTime = (String) jComboBox3.getSelectedItem();
        String reason = reasonField.getText();
        String selectSupervisee = (String) jComboBox1.getSelectedItem();
        
        java.util.Date selectedDate = jDateChooser1.getDate();

        // To Check if fields are empty
        if (name.isEmpty() || id.isEmpty() || intake.isEmpty() ||email.isEmpty() || topic.isEmpty() || reason.isEmpty() || selectedDate == null) {
            JOptionPane.showMessageDialog(this, "Please fill in all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Check if start time is less than or equal to end time
        if (startTime.compareTo(endTime) >= 0) {
            JOptionPane.showMessageDialog(this, "Start time should be before end time.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        LocalDateTime presentationDate = LocalDateTime.ofInstant(selectedDate.toInstant(), java.time.ZoneId.systemDefault());

        //Request Presentation
        presentationRequest pr = new presentationRequest(name, id, intake, email, topic, startTime, endTime, selectSupervisee, statusField.getText(), reason, presentationDate);
        
        //Saves the request and validate the request
        if (savePresentationRequest(pr)) {
            JOptionPane.showMessageDialog(this, "Presentation request sent successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to send presentation request. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean savePresentationRequest(presentationRequest pr) {
        String file = "presentation_request.txt";

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(pr.toString() + "\n");
            System.out.println("Presentation request saved to " + file);
            return true;
        } catch (IOException e) {
            System.err.println("Error occurred while saving presentation request: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        topicField = new javax.swing.JTextField();
        reasonField = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        sendRequestBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        statusField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        idField = new javax.swing.JTextField();
        intakeField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Presentation Request Form");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user-icon.png"))); // NOI18N

        jLabel15.setText("GET TEH NAME");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel1)
                    .addComponent(jLabel14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 80));

        jLabel2.setText("Name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 60, -1));

        jLabel4.setText("Appointment Date");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, -1, 20));

        jLabel5.setText("Start-Time");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, 60, -1));

        jLabel6.setText("Reason");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, -1, -1));

        jLabel7.setText("Email");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        jLabel8.setText("End-Time");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 200, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 130, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/calendar2-icon.png"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 50, -1));

        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });
        getContentPane().add(nameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 130, -1));
        getContentPane().add(emailField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 130, -1));
        getContentPane().add(topicField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 130, -1));

        reasonField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reasonFieldActionPerformed(evt);
            }
        });
        getContentPane().add(reasonField, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 320, 360, 70));
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, 160, 30));

        jLabel10.setText("Topic");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, -1, -1));

        jLabel11.setText("Select Supervisor");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09:00am", "10:00am", "11:00am", "12:00pm", "13:00pm", "14:00pm", "15:00pm", "16:00pm", "17:00pm", "18:00pm", " " }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, -1, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09:00am", "10:00am", "11:00am", "12:00pm", "13:00pm", "14:00pm", "15:00pm", "16:00pm", "17:00pm", "18:00pm" }));
        getContentPane().add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, 80, -1));

        sendRequestBtn.setText("Send Request");
        sendRequestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendRequestBtnActionPerformed(evt);
            }
        });
        getContentPane().add(sendRequestBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 450, -1, -1));

        jLabel3.setText("Status");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 200, 40, -1));
        getContentPane().add(statusField, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 130, -1));

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 490, -1, -1));

        idField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idFieldActionPerformed(evt);
            }
        });
        getContentPane().add(idField, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 130, -1));
        getContentPane().add(intakeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, 130, -1));

        jLabel12.setText("Student ID");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 70, -1));

        jLabel13.setText("Student Intake");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 80, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void sendRequestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendRequestBtnActionPerformed
        // TODO add your handling code here:
        sendRequest();
    }//GEN-LAST:event_sendRequestBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
//        back to STUDENT DASHBOARD
    }//GEN-LAST:event_jButton1ActionPerformed

    private void reasonFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reasonFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reasonFieldActionPerformed

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    private void idFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idFieldActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(presentationRequestPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(presentationRequestPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(presentationRequestPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(presentationRequestPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new presentationRequestPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField idField;
    private javax.swing.JTextField intakeField;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField reasonField;
    private javax.swing.JButton sendRequestBtn;
    private javax.swing.JTextField statusField;
    private javax.swing.JTextField topicField;
    // End of variables declaration//GEN-END:variables
}
