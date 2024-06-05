/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Projectmanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import lecturer.LecturerDashboard;

/**
 *
 * @author Darren
 */
public class AssignAssesment extends javax.swing.JFrame {
    private String lecturerName;


    /**
     * Creates new form AssignAssesment
     */
    public AssignAssesment(String name) {
        this.lecturerName = name;
        initComponents();
        table();
        populateIntakeComboBox();

        // Set the initial selected item if available
        if (IntakeComboBox2.getItemCount() > 0) {
            IntakeComboBox2.setSelectedIndex(0); // Select the first item by default
            String selectedIntakeCode = (String) IntakeComboBox2.getSelectedItem();
            populateCourseComboBox(selectedIntakeCode);
        }
    }
    
    private void table() {
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
            tableModel.addColumn("Project Name");
            tableModel.addColumn("Project Type");
            tableModel.addColumn("First Marker");
            tableModel.addColumn("Second Marker");
            tableModel.addColumn("Intake");
            tableModel.addColumn("Course");
            tableModel.addColumn("Status");
 
        Set<String> projectIds = new HashSet<>();
 
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Assessment_details.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(", ");
                if (data.length >= 13) { // Adjust the condition based on your file structure
                    String projId = data[0]; // Project ID
                    if (projectIds.contains(projId)) {
                        continue; // Skip this line if project ID is already encountered
                    }
                    projectIds.add(projId); // Add project ID to set
                    String projname = data[1]; // Project Name
                    String projType = data[2]; // Project Type
                    String firstMarker = data[3]; // First Marker
                    String Intake = data[7]; // Second Marker
                    String Course = data[13]; // Second Marker
                    String secondMarker = data[4]; // Second Marker
 
                    String Pending = data[11]; // Pending
                    // Change color of "Pending" cells to red if the value is "Assigned"
                   if ("Assigned".equals(Pending)) {
                       Pending = "<html><font color='red'>" + Pending + "</font></html>";
                   } else {
                       Pending = "<html><font color='green'>" + Pending + "</font></html>";
                   }
                    String[] rowData = {projname, projType, firstMarker, secondMarker, Intake, Course, Pending};
                    tableModel.addRow(rowData);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
 
            jTable1.setModel(tableModel);
        }

    
    
    private void populateIntakeComboBox() {
        IntakeComboBox2.removeAllItems(); // Clear existing items
        Set<String> intakeCodes = new HashSet<>(); // Use a set to store unique intake codes
        try (BufferedReader br = new BufferedReader(new FileReader("student.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) { // Ensure array length is at least 7 to avoid IndexOutOfBoundsException
                    String intakeCode = parts[6].trim(); // Assuming Intake Code is at index 6
                    if (!intakeCodes.contains(intakeCode)) { // Check if intake code is not already added
                        intakeCodes.add(intakeCode);
                        IntakeComboBox2.addItem(intakeCode);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void populateCourseComboBox(String selectedIntakeCode) {
        CourseComboBox3.removeAllItems(); // Clear existing items
        Set<String> addedCourses = new HashSet<>(); // To track added courses

        try (BufferedReader br = new BufferedReader(new FileReader("student.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) { // Ensure array length is at least 7 to avoid IndexOutOfBoundsException
                    String intakeCode = parts[6].trim(); // Assuming Intake Code is at index 6
                    String course = parts[5].trim(); // Assuming Course is at index 5

                    if (intakeCode.equals(selectedIntakeCode) && !addedCourses.contains(course)) {
                        CourseComboBox3.addItem(course);
                        addedCourses.add(course); // Track added course
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private String findProjectID(String projectName) {
    String selectedProject = null;
    try (BufferedReader br = new BufferedReader(new FileReader("Assessment_details.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(", ");
            if (data.length >= 12 && data[1].equals(projectName)) { // Assuming project name is at index 2
                selectedProject = data[0]; // Assuming project ID is at index 0
                break;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return selectedProject;
}
    
private void assignStudentToAssessment() {
    String selectedIntakeCode = (String) IntakeComboBox2.getSelectedItem();
    String selectedCourse = (String) CourseComboBox3.getSelectedItem();

    if (selectedIntakeCode == null || selectedCourse == null) {
        JOptionPane.showMessageDialog(this, "Please select both intake code and course.");
        return;
    }

    // Read student information
    List<String[]> students = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("student.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 7) {
                String intakeCode = parts[6].trim();
                String course = parts[5].trim();
                if (intakeCode.equals(selectedIntakeCode) && course.equals(selectedCourse)) {
                    students.add(parts);
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error reading student.txt");
        return;
    }

    if (students.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No students found for the selected intake and course.");
        return;
    }

    // Debug print: show all students found for the selected intake and course
    System.out.println("Students found:");
    for (String[] student : students) {
        System.out.println(Arrays.toString(student));
    }

    // Get the project ID based on the project name entered in the JTextField
    String projectName = jTextField2.getText(); // Assuming jTextField2 is the JTextField for project name
    String selectedProject = findProjectID(projectName);

    // Debug print: show the project ID obtained from findProjectID
    System.out.println("Selected Project ID (from findProjectID): " + selectedProject);

    // Read assessment details and update lines
    List<String> updatedLines = new ArrayList<>();
    boolean assignmentMade = false;

    try (BufferedReader br = new BufferedReader(new FileReader("Assessment_details.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(", ");
        if (data.length >= 13 && data[11].equals("Pending")) {
            boolean lineUpdated = false;
            if (data[0].equals(selectedProject)) { // Check if the project ID matches the selected project
                for (String[] student : students) {
                    if (data[5].equals("-") && data[6].equals("-")) {
                        data[5] = student[3]; // Student ID
                        data[6] = student[2]; // Student email
                        if (data.length <= 13) {
                            // Ensure data array has at least 14 elements before accessing index 13
                            if (data.length < 14) {
                                data = Arrays.copyOf(data, 14); // Increase array length to 14
                            }
                        }
                        if (data[13].equals("-")) { // Check if course is "-"
                            data[13] = selectedCourse; // Update course if it's "-"
                        }
                        if (data[7].equals("-")) { // Replace next "-" with intake code
                            data[7] = selectedIntakeCode;
                        }
                        assignmentMade = true;
                        updatedLines.add(String.join(", ", data)); // Add the updated line
                        lineUpdated = true;
                    } else {
                        // Create new lines for each additional student found
                        String[] newData = Arrays.copyOf(data, 14); // Ensure newData array has 14 elements
                        newData[5] = student[3]; // Student ID
                        newData[6] = student[2]; // Student email
                        newData[7] = selectedIntakeCode; // Intake code
                        newData[13] = selectedCourse; // Course
                        updatedLines.add(String.join(", ", newData)); // Add the new line
                    }
                }
            }
            // If the line is not updated, add it as is
            if (!lineUpdated) {
                updatedLines.add(line);
            }
        } else {
            updatedLines.add(line); // Add non-pending lines without modification
        }
    }
} catch (IOException e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error reading Assessment_details.txt");
    return;
}


    if (!assignmentMade) {
        JOptionPane.showMessageDialog(this, "No pending assessments found or all slots are filled.");
        return;
    }

    // Debug print: show all updated lines to be written back to the file
    System.out.println("Updated lines:");
    for (String updatedLine : updatedLines) {
        System.out.println(updatedLine);
    }

    // Sort the updated lines based on project ID in ascending order
    Collections.sort(updatedLines, new Comparator<String>() {
        @Override
        public int compare(String line1, String line2) {
            String[] data1 = line1.split(", ");
            String[] data2 = line2.split(", ");
            String projectId1 = data1[0];
            String projectId2 = data2[0];
            return projectId1.compareTo(projectId2);
        }
    });

    boolean projectFound = false;

    // Iterate through the updated lines
    for (int i = 0; i < updatedLines.size(); i++) {
        String line = updatedLines.get(i);
        String[] data = line.split(", ");
        if (data[0].equals(selectedProject)) {
            // Change the 11th column (index 10) from "Pending" to "Assigned"
            if (data.length >= 11 && data[11].equals("Pending")) {
                data[11] = "Assigned";
                updatedLines.set(i, String.join(", ", data));
                projectFound = true;
            }
        }
    }

    // If the project ID was not found in any line, show an error message
    if (!projectFound) {
        JOptionPane.showMessageDialog(this, "No pending assessments found for the selected project ID.");
        return;
    }

    // Write updated data back to the original file
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Assessment_details.txt"))) {
        for (String updatedLine : updatedLines) {
            bw.write(updatedLine);
            bw.newLine();
        }
    } catch (IOException e) {
        Logger.getLogger(AssignAssesment.class.getName()).log(Level.SEVERE, null, e);
        JOptionPane.showMessageDialog(this, "Error writing to Assessment_details.txt");
        return;
    }

    // Refresh the table
    table();

    JOptionPane.showMessageDialog(this, "Students assigned successfully.");
}



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        IntakeComboBox2 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        CourseComboBox3 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Assign");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        IntakeComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        IntakeComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        IntakeComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IntakeComboBox2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Project Name:");

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Course:");

        CourseComboBox3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CourseComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CourseComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CourseComboBox3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Intake:");

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setText("Assign Assessment");

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Back");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 913, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(CourseComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(IntakeComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(357, 357, 357)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(387, 387, 387)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(IntakeComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(CourseComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        assignStudentToAssessment();

        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    int selectedRow = jTable1.getSelectedRow();
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    
    if (selectedRow >= 0) {
       jTextField2.setText(model.getValueAt(selectedRow, 0).toString()); // Project Name
       jTextField2.setText(model.getValueAt(selectedRow, 0).toString()); // Project Name
       jTextField2.setEditable(false); // Make the text field non-editable

    }
    }//GEN-LAST:event_jTable1MouseClicked

    private void CourseComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CourseComboBox3ActionPerformed
        // TODO add your handling code here:

        
    }//GEN-LAST:event_CourseComboBox3ActionPerformed

    private void IntakeComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IntakeComboBox2ActionPerformed
        // TODO add your handling code here:
           String selectedIntakeCode = (String) IntakeComboBox2.getSelectedItem();
            populateCourseComboBox(selectedIntakeCode);
        
    }//GEN-LAST:event_IntakeComboBox2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        var sp = new LecturerDashboard(lecturerName);
        sp.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(AssignAssesment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AssignAssesment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AssignAssesment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AssignAssesment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AssignAssesment("Name").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CourseComboBox3;
    private javax.swing.JComboBox<String> IntakeComboBox2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
