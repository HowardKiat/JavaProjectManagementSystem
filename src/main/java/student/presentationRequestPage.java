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
        private final String email;
        private final String topic;
        private final String startTime;
        private final String endTime;
        private final String supervisee;
        private final String status;
        private final String reason;
        private final LocalDateTime presentationDate;   
    
    
    public presentationRequest(String name, String email, String topic, String startTime, String endTime, String supervisee, String status, String reason, LocalDateTime presentationDate) {
        this.name = name;
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
        return "Name: " + name + ", Email: " + email + ", Topic: " + topic + ", Start Time: " + startTime + ", End Time: " + endTime + ", Supervisee: " + supervisee + ", Presentation Date: " + presentationDate + ", Status: " + status + ", Reason: " + reason;
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
        String email = emailField.getText();
        String topic = topicField.getText();
        String startTime = (String) jComboBox2.getSelectedItem();
        String endTime = (String) jComboBox3.getSelectedItem();
        String reason = reasonField.getText();
        String selectSupervisee = (String) jComboBox1.getSelectedItem();
        
        java.util.Date selectedDate = jDateChooser1.getDate();

        // To Check if fields are empty
        if (name.isEmpty() || email.isEmpty() || topic.isEmpty() || reason.isEmpty() || selectedDate == null) {
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
        presentationRequest pr = new presentationRequest(name, email, topic, startTime, endTime, selectSupervisee, statusField.getText(), reason, presentationDate);
        
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
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Presentation Request Form");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(192, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 660, 80));

        jLabel2.setText("Name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 60, -1));

        jLabel4.setText("Appointment Date");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 200, -1, -1));

        jLabel5.setText("Start-Time");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 290, 60, -1));

        jLabel6.setText("Reason");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, -1, -1));

        jLabel7.setText("Email");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, -1, -1));

        jLabel8.setText("End-Time");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 330, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 430, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/calendar2-icon.png"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 180, 50, 50));

        nameField.setText("jTextField1");
        getContentPane().add(nameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, -1, -1));

        emailField.setText("jTextField4");
        getContentPane().add(emailField, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, -1, -1));

        topicField.setText("jTextField5");
        getContentPane().add(topicField, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 380, -1, -1));

        reasonField.setText("jTextField6");
        getContentPane().add(reasonField, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, 300, 70));
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, 160, 30));

        jLabel10.setText("Topic");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 380, -1, -1));

        jLabel11.setText("Supervisee");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 430, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09:00am", "10:00am", "11:00am", "12:00pm", "13:00pm", "14:00pm", "15:00pm", "16:00pm", "17:00pm", "18:00pm", " " }));
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 290, -1, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09:00am", "10:00am", "11:00am", "12:00pm", "13:00pm", "14:00pm", "15:00pm", "16:00pm", "17:00pm", "18:00pm" }));
        getContentPane().add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 330, 80, -1));

        sendRequestBtn.setText("Send Request");
        sendRequestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendRequestBtnActionPerformed(evt);
            }
        });
        getContentPane().add(sendRequestBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 540, -1, -1));

        jLabel3.setText("Status");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 480, 40, -1));

        statusField.setText("jTextField1");
        getContentPane().add(statusField, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 480, -1, -1));

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 600, -1, -1));

        jTextField1.setText("jTextField1");
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 100, -1));

        jTextField2.setText("jTextField2");
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, -1, -1));

        jLabel12.setText("Student ID");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 70, -1));

        jLabel13.setText("Student Intake");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 80, -1));

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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField reasonField;
    private javax.swing.JButton sendRequestBtn;
    private javax.swing.JTextField statusField;
    private javax.swing.JTextField topicField;
    // End of variables declaration//GEN-END:variables
}
