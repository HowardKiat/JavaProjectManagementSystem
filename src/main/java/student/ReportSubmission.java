/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package student;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.pdfbox.Loader;
/**
 *
 * @author User
 */
public class ReportSubmission extends javax.swing.JFrame {
    
    private final List<Submission> submissions;
    private final DefaultListModel<String> listModel;
    private final JList<String> submissionList;
    /**
     * Creates new form ManageReport
     */
    public ReportSubmission() {
        submissions = new ArrayList<>();
        listModel = new DefaultListModel<>();
        submissionList = new JList<>(listModel);

        setTitle("Report Submission");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pDDocument1 = new org.apache.pdfbox.pdmodel.PDDocument();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        uploadBtn = new javax.swing.JButton();
        dateField = new javax.swing.JTextField();
        assessmentTypeField = new javax.swing.JTextField();
        moodleLinkField = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        statusBtn = new javax.swing.JButton();
        pdfDisplayLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setText("Upcoming Submissions");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 200, 230));

        uploadBtn.setText("Upload File");
        uploadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadBtnActionPerformed(evt);
            }
        });
        getContentPane().add(uploadBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 570, -1, -1));
        getContentPane().add(dateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 110, 20));
        getContentPane().add(assessmentTypeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 110, 20));
        getContentPane().add(moodleLinkField, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 110, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, -1, -1));

        jLabel2.setText("Date");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 40, 10));

        jLabel3.setText("Assesment Type");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, -1, 10));

        jLabel4.setText("Moodle Link");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, -1, -1));

        jLabel5.setText("THE NAME");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, -1, -1));

        jLabel6.setText("PROFILE ICON");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, -1, -1));

        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        getContentPane().add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 570, -1, -1));

        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        getContentPane().add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 570, -1, -1));

        statusBtn.setText("Status");
        statusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusBtnActionPerformed(evt);
            }
        });
        getContentPane().add(statusBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 570, -1, -1));

        pdfDisplayLabel.setBackground(new java.awt.Color(204, 204, 255));
        pdfDisplayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pdfDisplayLabel.setText("PDF display");
        getContentPane().add(pdfDisplayLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 580, 410));

        jLabel7.setText("Courses(if we have)");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uploadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadBtnActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a PDF file");
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File pdfFile = fileChooser.getSelectedFile();
            if (!pdfFile.getName().toLowerCase().endsWith(".pdf")) {
                JOptionPane.showMessageDialog(this, "Selected file is not a PDF", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirmation = JOptionPane.showConfirmDialog(this, "Do you want to upload this PDF?", "Confirm Upload", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                checkPdf(pdfFile);
            }
        }
    }//GEN-LAST:event_uploadBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        int selectedIndex = submissionList.getSelectedIndex();
        if (selectedIndex != -1) {
            Submission submission = submissions.get(selectedIndex);
            submission.setDate(dateField.getText());
            submission.setAssessmentType(assessmentTypeField.getText());
            submission.setMoodleLink(moodleLinkField.getText());
            listModel.set(selectedIndex, submission.toString());
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        int selectedIndex = submissionList.getSelectedIndex();
        if (selectedIndex != -1) {
            submissions.remove(selectedIndex);
            listModel.remove(selectedIndex);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void statusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusBtnActionPerformed
        int selectedIndex = submissionList.getSelectedIndex();
        if (selectedIndex != -1) {
            Submission submission = submissions.get(selectedIndex);
            JOptionPane.showMessageDialog(this, """
                                                Submission Details:
                                                Date: """ + submission.getDate() + "\n" +
                    "Assessment Type: " + submission.getAssessmentType() + "\n" +
                    "Moodle Link: " + submission.getMoodleLink() + "\n" +
                    "File Name: " + submission.getFileName());
        }
    }//GEN-LAST:event_statusBtnActionPerformed
    
    
    private void checkPdf(File pdfFile) {
        try (PDDocument pdfDocument = Loader.loadPDF(pdfFile)) {
            PDFRenderer pdfRenderer = new PDFRenderer(pdfDocument);

            BufferedImage image = pdfRenderer.renderImageWithDPI(0, 300);
            Image scaledImage = image.getScaledInstance(pdfDisplayLabel.getWidth(), pdfDisplayLabel.getHeight(), Image.SCALE_SMOOTH);
            pdfDisplayLabel.setIcon(new ImageIcon(scaledImage));

            Submission submission = new Submission(
                    dateField.getText().isEmpty() ? new SimpleDateFormat("yyyy-MM-dd").format(new Date()) : dateField.getText(),
                    assessmentTypeField.getText().isEmpty() ? "Unknown" : assessmentTypeField.getText(),
                    moodleLinkField.getText().isEmpty() ? "N/A" : moodleLinkField.getText(),
                    pdfFile.getName()
            );
            submissions.add(submission);
            listModel.addElement(submission.toString());

            saveSubmissionToFile(submission);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void saveSubmissionToFile(Submission submission) {
        try (FileWriter writer = new FileWriter("submissions.txt", true)) {
            writer.write(submission.toString() + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving submission: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
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
            java.util.logging.Logger.getLogger(ReportSubmission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportSubmission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportSubmission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportSubmission.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportSubmission().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField assessmentTypeField;
    private javax.swing.JTextField dateField;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField moodleLinkField;
    private org.apache.pdfbox.pdmodel.PDDocument pDDocument1;
    private javax.swing.JLabel pdfDisplayLabel;
    private javax.swing.JButton statusBtn;
    private javax.swing.JButton uploadBtn;
    // End of variables declaration//GEN-END:variables
}
