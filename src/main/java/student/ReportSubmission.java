/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package student;

import com.mycompany.javaprojectmanagementsystem.Student;
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
    private final String studentName;
    private final List<Submission> submissions;
    private final DefaultListModel<String> listModel;
    private final JList<String> submissionList;
    private PDDocument currentPdfDocument;
    private int currentPageIndex;

    /**
     * Creates new form ManageReport
     * @param name
     */
    public ReportSubmission(String name) {
        super();
        submissions = new ArrayList<>();
        listModel = new DefaultListModel<>();
        submissionList = new JList<>(listModel);

        setTitle("Report Submission");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        this.studentName = name;
        initComponents();
        profileField.setText(studentName);
        profileField.setEditable(false); 
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
        uploadBtn = new javax.swing.JButton();
        dateField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        assessmentTypeField = new javax.swing.JComboBox<>();
        prevBtn = new javax.swing.JButton();
        nextBtn = new javax.swing.JButton();
        pageNum = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        profileField = new javax.swing.JTextField();
        pdfDisplayLabel = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        uploadBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uploadBtn.setText("Upload File");
        uploadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadBtnActionPerformed(evt);
            }
        });
        getContentPane().add(uploadBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 640, 130, -1));

        dateField.setText("auto reads submission date");
        getContentPane().add(dateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 160, 20));

        jLabel2.setText("Date");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 40, 20));

        jLabel3.setText("Assesment Type");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, -1, 20));

        jLabel7.setText("Intake");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, -1, -1));

        jLabel4.setText("Course");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 50, -1));

        jTextField1.setText("get from txt file");
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 110, -1));

        jLabel8.setText("Project Name");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, -1, -1));

        assessmentTypeField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        assessmentTypeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assessmentTypeFieldActionPerformed(evt);
            }
        });
        getContentPane().add(assessmentTypeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 110, -1));

        prevBtn.setText("<");
        prevBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevBtnActionPerformed(evt);
            }
        });
        getContentPane().add(prevBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 640, -1, -1));

        nextBtn.setText(">");
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });
        getContentPane().add(nextBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 640, -1, -1));

        pageNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pageNum.setText(".");
        getContentPane().add(pageNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 640, 90, 30));

        jPanel2.setBackground(new java.awt.Color(255, 102, 102));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Submission Manager");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user-icon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(profileField, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(553, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(profileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, -1));

        pdfDisplayLabel.setText(".");
        pdfDisplayLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        getContentPane().add(pdfDisplayLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 440, 500));

        backBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        getContentPane().add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 730, -1, -1));

        jTextField2.setText("get from txt file");
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, -1, -1));

        jTextField3.setText("jTextField3");
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 100, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uploadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadBtnActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a PDF file");
        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File pdfFile = fileChooser.getSelectedFile();
            if (!pdfFile.getName().toLowerCase().endsWith(".pdf")) {
                JOptionPane.showMessageDialog(this, "Selected file is not a PDF", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirmation = JOptionPane.showConfirmDialog(this, "Do you want to upload this PDF?", "Confirm Upload", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                openPdf(pdfFile);
            }
        }
    }//GEN-LAST:event_uploadBtnActionPerformed

    private void assessmentTypeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assessmentTypeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_assessmentTypeFieldActionPerformed

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        if (currentPdfDocument != null && currentPageIndex < currentPdfDocument.getNumberOfPages() - 1) {
            currentPageIndex++;
            displayPdfPage(currentPageIndex);
        }
    }//GEN-LAST:event_nextBtnActionPerformed

    private void prevBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevBtnActionPerformed
        if (currentPdfDocument != null && currentPageIndex > 0) {
            currentPageIndex--;
            displayPdfPage(currentPageIndex);
        }
    }//GEN-LAST:event_prevBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        var sp = new Student(studentName);
        sp.setVisible(true);
        dispose();
    }//GEN-LAST:event_backBtnActionPerformed
    
    
    private void openPdf(File pdfFile) { 
        try {
            currentPdfDocument = Loader.loadPDF(pdfFile);
            currentPageIndex = 0;
            displayPdfPage(currentPageIndex);

            int confirmation = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to upload and save this file?",
                    "Confirm Upload",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmation == JOptionPane.YES_OPTION) {
                saveSubmission(pdfFile);
            } else {
                currentPdfDocument.close();
                currentPdfDocument = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void displayPdfPage(int pageIndex) {
        try {
            PDFRenderer renderer = new PDFRenderer(currentPdfDocument);
            BufferedImage image = renderer.renderImageWithDPI(pageIndex, 72);
            ImageIcon icon = new ImageIcon(getScaledImage(image, pdfDisplayLabel.getWidth(), pdfDisplayLabel.getHeight()));
            pdfDisplayLabel.setIcon(icon);
            pageNum.setText("Page " + (pageIndex + 1) + " of " + currentPdfDocument.getNumberOfPages());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error displaying PDF page: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Image getScaledImage(BufferedImage srcImg, int w, int h) {
        Image scaledImg = srcImg.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(scaledImg, 0, 0, null);
        g2d.dispose();

        return img;
    }
    
    private void saveSubmission(File pdfFile) {
        Submission submission = new Submission(
                new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                pdfFile.getName(),
                 studentName
        );
        submissions.add(submission);
        listModel.addElement(submission.toString());
        saveSubmissionToFile(submission);
    }
    
    private void saveSubmissionToFile(Submission submission) {
        try (FileWriter writer = new FileWriter("submissions.txt", true)) {
            writer.write(String.format("%s, %s, %s%n", submission.getDate(), submission.getFileName(), submission.getStudentName()));
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
                new ReportSubmission("Name").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> assessmentTypeField;
    private javax.swing.JButton backBtn;
    private javax.swing.JTextField dateField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton nextBtn;
    private org.apache.pdfbox.pdmodel.PDDocument pDDocument1;
    private javax.swing.JLabel pageNum;
    private javax.swing.JLabel pdfDisplayLabel;
    private javax.swing.JButton prevBtn;
    private javax.swing.JTextField profileField;
    private javax.swing.JButton uploadBtn;
    // End of variables declaration//GEN-END:variables
}
