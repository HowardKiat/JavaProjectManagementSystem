/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package student;

import com.mycompany.javaprojectmanagementsystem.student;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
//import com.mycompany.javaprojectmanagementsystem;

/**
 *
 * @author User
 */
public class presentationRequestPage extends javax.swing.JFrame {
    private class PresentationRequest {
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
        private final LocalDate presentationDate;  
    
    
    public PresentationRequest(String name, String id, String intake, String email, String topic, String startTime, String endTime, String supervisee, String status, String reason, LocalDate presentationDate) {
        super();
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return id + "," + name + "," + email + "," + intake + "," + topic + "," + dtf.format(presentationDate) + "," + startTime + "," + endTime + "," + reason + "," + supervisee + "," + status;
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
        statusField.setText("Pending");
        statusField.setEditable(false);
        appendLecturers();
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
        Date selectedDate = jDateChooser1.getDate();

        // To Check if fields are empty
        if (name.isEmpty() || id.isEmpty() || intake.isEmpty() || email.isEmpty() || topic.isEmpty() || reason.isEmpty() || selectedDate == null) {
            JOptionPane.showMessageDialog(this, "Please fill in all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if start time is less than or equal to end time
        if (startTime.compareTo(endTime) >= 0) {
            JOptionPane.showMessageDialog(this, "Start time should be before end time.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDate presentationDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        PresentationRequest pr = new PresentationRequest(name, id, intake, email, topic, startTime, endTime, selectSupervisee, statusField.getText(), reason, presentationDate);
        List<PresentationRequest> existingRequests = readExistingRequests();

        if (!existingRequests.isEmpty() && isConflict(pr, existingRequests)) {
            int response = JOptionPane.showConfirmDialog(this, "The selected supervisor is already occupied at the chosen time. Would you like to view the supervisor's schedule?", "Conflict", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                displaySupervisorSchedule(selectSupervisee);
            }
            return;
        }
        
        //Saves the request and validate the request
        if (savePresentationRequest(pr)) {
            JOptionPane.showMessageDialog(this, "Presentation request sent successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to send presentation request. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }    
    
    private void displaySupervisorSchedule(String supervisor) {
    // Implement this method to show the supervisor's schedule
    // This could be a new window or a dialog displaying the schedule
        JOptionPane.showMessageDialog(this, "Supervisor's schedule functionality is not yet implemented.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean savePresentationRequest(PresentationRequest pr) {
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
    
    private List<PresentationRequest> readExistingRequests() {
        List<PresentationRequest> requests = new ArrayList<>();
        String file = "presentation_request.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 11) {
                    String id = parts[0];
                    String name = parts[1];
                    String email = parts[2];
                    String intake = parts[3];
                    String topic = parts[4];
                    LocalDate presentationDate = LocalDate.parse(parts[5], dtf);
                    String startTime = parts[6];
                    String endTime = parts[7];
                    String reason = parts[8];
                    String supervisee = parts[9];
                    String status = parts[10];
                    requests.add(new PresentationRequest(name, id, intake, email, topic, startTime, endTime, supervisee, status, reason, presentationDate));
                }
            }
        } catch (IOException e) {
            System.err.println("Error occurred while reading presentation requests: " + e.getMessage());
            e.printStackTrace();
        }
        return requests;
    }
    
    private boolean isConflict(PresentationRequest newRequest, List<PresentationRequest> existingRequests) {
        for (PresentationRequest existingRequest : existingRequests) {
            if (existingRequest.supervisee.equals(newRequest.supervisee) &&
                existingRequest.presentationDate.equals(newRequest.presentationDate) &&
                existingRequest.startTime.equals(newRequest.startTime) &&
                existingRequest.endTime.equals(newRequest.endTime)) {
                return true;
            }
        }
        return false;
    }
    
    private void appendLecturers() {
        try (BufferedReader br = new BufferedReader(new FileReader("lecturer.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    jComboBox1.addItem(parts[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(27, Short.MAX_VALUE))
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

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 130, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/calendar2-icon.png"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 50, -1));
        getContentPane().add(nameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 130, -1));
        getContentPane().add(emailField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 130, -1));
        getContentPane().add(topicField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 130, -1));
        getContentPane().add(reasonField, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 320, 360, 70));
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, 160, 30));

        jLabel10.setText("Topic");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, -1, -1));

        jLabel11.setText("Select Supervisor");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09:00am", "10:00am", "11:00am", "12:00pm", "13:00pm", "14:00pm", "15:00pm", "16:00pm", "17:00pm", "18:00pm", " " }));
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
        getContentPane().add(idField, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 130, -1));
        getContentPane().add(intakeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, 130, -1));

        jLabel12.setText("Student ID");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 70, -1));

        jLabel13.setText("Student Intake");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 80, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
//        try {
//            // Create a BufferedReader to read the file
//            BufferedReader br = new BufferedReader(new FileReader("lecturer.txt"));
//            String line;
//            // Clear the existing items in the JComboBox
//            jComboBox1.removeAllItems();
//            // Read each line from the file
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(",");
//                if (parts.length >= 6) {
//                    jComboBox1.addItem(parts[2]);
//                }
//            }
//            br.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void sendRequestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendRequestBtnActionPerformed
        // TODO add your handling code here:
        sendRequest();
    }//GEN-LAST:event_sendRequestBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new student().setVisible(true);
        this.dispose();
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
