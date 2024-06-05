/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Projectmanager;

import com.toedter.calendar.JDateChooser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Date;
import lecturer.LecturerDashboard;

/**
 *
 * @author Darren
 */
public class AssignMarker extends javax.swing.JFrame {
    private final String lecturerName;
    /**
     * Creates new form AllotStudents
     * @param name
     */
    public AssignMarker(String name) {
        this.lecturerName = name;
        initComponents();
        table();
        populateFirstMarkerComboBox();
        populateSecondMarkerComboBox();
        populatesupervisorFComboBox();
        }
    
    
private void table() {
    DefaultTableModel tableModel = new DefaultTableModel();
    tableModel.addColumn("Project Name");
    tableModel.addColumn("Project Type");
    tableModel.addColumn("Supervisor");
    tableModel.addColumn("First Marker");
    tableModel.addColumn("Second Marker");
    tableModel.addColumn("Start Date");
    tableModel.addColumn("Due Date");

    Set<String> addedProjectIds = new HashSet<>();

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Assessment_details.txt"))) {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(", "); // Adjust delimiter based on your text file
            if (data.length >= 13) {
                String projectId = data[0]; // Project ID
                if (addedProjectIds.contains(projectId)) {
                    continue; // Skip duplicate project ID
                }
                addedProjectIds.add(projectId);

                String projname = data[1]; // Project Name
                String projType = data[2]; // Project Type
                String supervisor = data[8]; // Supervisor
                String firstMarker = data[3]; // First Marker
                String secondMarker = data[4]; // Second Marker 
                String startDate = data[9]; // Start Date
                String dueDate = data[10]; // Due Date

                String[] rowData = {projname, projType, supervisor, firstMarker, secondMarker, startDate, dueDate};
                tableModel.addRow(rowData);
            } else {
                System.out.println("Skipping line due to insufficient data: " + line);
                continue; // Skip to the next iteration
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    jTable1.setModel(tableModel);
}

    private void updateDataInTableAndFile(int selectedRowIndex, String projectName, String projectType, String supervisor, String firstMarker, String secondMarker, String startDate, String dueDate) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        // Update the row in the JTable
        model.setValueAt(projectName, selectedRowIndex, 0); // Project Name
        model.setValueAt(projectType, selectedRowIndex, 1); // Project Type
        model.setValueAt(supervisor, selectedRowIndex, 2); // Supervisor
        model.setValueAt(firstMarker, selectedRowIndex, 3); // First Marker
        model.setValueAt(secondMarker, selectedRowIndex, 4); // Second Marker
        model.setValueAt(startDate, selectedRowIndex, 5); // Start Date
        model.setValueAt(dueDate, selectedRowIndex, 6); // Due Date

        // Update the data in the text file
        updateDataInFile(selectedRowIndex, projectName, projectType, supervisor, firstMarker, secondMarker, startDate, dueDate);

        JOptionPane.showMessageDialog(this, "Data updated successfully!");
    }


private void updateDataInFile(int selectedRowIndex, String projectName, String projectType, String supervisor, String firstMarker, String secondMarker, String startDate, String dueDate) {
    String filePath = "Assessment_details.txt";
    List<String> fileLines = new ArrayList<>();
    String projectId = null;

    // Read the file and determine the project ID for the selected row
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            fileLines.add(line);
        }
        // Get the project ID from the selected row
        String selectedLine = fileLines.get(selectedRowIndex);
        String[] selectedData = selectedLine.split(", ");
        projectId = selectedData[0]; // Assuming the project ID is in the first column
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error reading the file.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Update all lines with the same project ID
    for (int i = 0; i < fileLines.size(); i++) {
        String line = fileLines.get(i);
        String[] data = line.split(", ");
        if (data[0].equals(projectId)) {
            data[1] = projectName;   // Project Name
            data[2] = projectType;   // Project Type
            data[3] = firstMarker;   // First Marker
            data[4] = secondMarker;  // Second Marker
            data[8] = supervisor;    // Supervisor
            data[9] = startDate;     // Start Date
            data[10] = dueDate;       // Due Date

            // Reconstruct the line with updated data
            String updatedLine = String.join(", ", data);
            fileLines.set(i, updatedLine);
        }
    }

    // Write the updated lines back to the file
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
        for (String updatedLine : fileLines) {
            bw.write(updatedLine);
            bw.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error writing to the file.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
}




        private void populatesupervisorFComboBox() {
            jComboBoxsuper.removeAllItems(); // Clear existing items
            jComboBoxsuper.addItem("None"); // Add "None" item

            try (BufferedReader br = new BufferedReader(new FileReader("lecturer.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 3) {
                        String supervisorName = parts[2].trim();
                        jComboBoxsuper.addItem(supervisorName);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        private void populateFirstMarkerComboBox() {
            jComboBox1Marker.removeAllItems(); // Clear existing items
            jComboBox1Marker.addItem("None"); // Add "None" item

            try (BufferedReader br = new BufferedReader(new FileReader("lecturer.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 3) {
                        String supervisorName = parts[2].trim();
                        jComboBox1Marker.addItem(supervisorName);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    
    
        private void populateSecondMarkerComboBox() {
        jComboBox2Marker.removeAllItems(); // Clear existing items
        jComboBox2Marker.removeAllItems(); // Clear existing items (assuming you meant to clear this one too)

        jComboBox2Marker.addItem("None"); // Add "None" item to the second combo box

        try (BufferedReader br = new BufferedReader(new FileReader("lecturer.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String supervisorName = parts[2].trim();
                    jComboBox2Marker.addItem(supervisorName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
               private void saveTableDataToFile() {
                DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
                try (BufferedWriter bw = new BufferedWriter(new FileWriter("Assessment_details.txt"))) {
                    for (int row = 0; row < tableModel.getRowCount(); row++) {
                        StringBuilder rowData = new StringBuilder();
                        rowData.append(row + 1).append(", ");
                        for (int col = 0; col < tableModel.getColumnCount(); col++) {
                            rowData.append(tableModel.getValueAt(row, col));
                            if (col < tableModel.getColumnCount() - 1) {
                                rowData.append(", ");
                            }
                        }
                        // Append placeholders for missing columns to maintain the file structure
                        rowData.append(", -, -, -, -, Pending, Pending");
                        bw.write(rowData.toString());
                        bw.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        private String getSelectedDate(JDateChooser dateChooser) {
            Date date = dateChooser.getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            return date != null ? dateFormat.format(date) : "-";
        }
        
        private Date parseDate(String dateStr) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try {
                return dateFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
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

        jScrollBar1 = new javax.swing.JScrollBar();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldAssesment = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBox2Marker = new javax.swing.JComboBox<>();
        jComboBoxProjType = new javax.swing.JComboBox<>();
        jComboBox1Marker = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxsuper = new javax.swing.JComboBox<>();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Project Type");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Start Date");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Supervisor");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Second Marker");

        jButton2.setBackground(new java.awt.Color(255, 102, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Delete");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 153, 153));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Update");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox2Marker.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2Marker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2MarkerActionPerformed(evt);
            }
        });

        jComboBoxProjType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Internship Report", "Investigation Report", "RMCP", "Capstone project – P1", "Course Project", "Final Year Project" }));
        jComboBoxProjType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxProjTypeActionPerformed(evt);
            }
        });

        jComboBox1Marker.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1Marker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1MarkerActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("First Marker");

        jComboBoxsuper.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Assessment Type");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("End Date");

        backBtn.setBackground(new java.awt.Color(204, 204, 0));
        backBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        backBtn.setForeground(new java.awt.Color(255, 255, 255));
        backBtn.setText("Back");
        backBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jComboBox2Marker, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxProjType, javax.swing.GroupLayout.Alignment.LEADING, 0, 218, Short.MAX_VALUE)
                    .addComponent(jComboBox1Marker, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldAssesment, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxsuper, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(299, 299, 299))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jTextFieldAssesment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxsuper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1Marker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2Marker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxProjType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel3.setBackground(new java.awt.Color(0, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/file.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("New Project");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(44, 44, 44))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
    int selectedRow = jTable1.getSelectedRow();
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

    if (selectedRow >= 0) {
        // Ensure we are accessing the correct columns
        if (model.getColumnCount() >= 5) {
            // Set Assessment text field
            String assessment = model.getValueAt(selectedRow, 0).toString().trim();
            jTextFieldAssesment.setText(assessment);

            // Set Project Type in JComboBox
            String selectedProjType = model.getValueAt(selectedRow, 1).toString().trim();
            jComboBoxProjType.setSelectedItem(selectedProjType);

            String selectedSupervisor = model.getValueAt(selectedRow, 2).toString().trim();
            jComboBoxsuper.setSelectedItem(selectedSupervisor);
            
            // Set First Marker in JComboBox
            String selectedFirstMarker = model.getValueAt(selectedRow, 3).toString().trim();
            jComboBox1Marker.setSelectedItem(selectedFirstMarker);

            // Set Second Marker in JComboBox
            String selectedSecmarker = model.getValueAt(selectedRow, 4).toString().trim();
            jComboBox2Marker.setSelectedItem(selectedSecmarker);
            
                        // Parse and set Start Date in JDateChooser
            String startDateStr = model.getValueAt(selectedRow, 5).toString().trim();
            Date startDate = parseDate(startDateStr);
            jDateChooser1.setDate(startDate);

            // Parse and set Due Date in JDateChooser
            String dueDateStr = model.getValueAt(selectedRow, 6).toString().trim();
            Date dueDate = parseDate(dueDateStr);
            jDateChooser2.setDate(dueDate);
        }
    }


    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
                    int selectedRow = jTable1.getSelectedRow();
    
            if (selectedRow >= 0) {
                DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();

                // Remove the selected row from the table model
                tblModel.removeRow(selectedRow);
                
                // Clear the text fields
                jTextFieldAssesment.setText("");
                
                // Save the updated table data to the text file
                saveTableDataToFile();

                JOptionPane.showMessageDialog(this, "Row deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        new CreateProject(lecturerName).setVisible(true);
            dispose();
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    int selectedRow = jTable1.getSelectedRow();
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

    if (selectedRow >= 0) {
        String projectName = jTextFieldAssesment.getText(); // Get updated project name from JTextField
        String projectType = jComboBoxProjType.getSelectedItem().toString(); // Get updated project type from JComboBox
        String firstMarker = jComboBox1Marker.getSelectedItem().toString(); // Get updated first marker from JComboBox
        String secondMarker = jComboBox2Marker.getSelectedItem().toString(); // Get updated second marker from JComboBox
        String supervisor = jComboBoxsuper.getSelectedItem().toString(); // Get updated supervisor from JComboBox
        String startDate = getSelectedDate(jDateChooser1); // Get updated start date from JDateChooser
        String dueDate = getSelectedDate(jDateChooser2); // Get updated due date from JDateChooser
        // Validate input fields
        if (projectName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a project name.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop execution if project name is empty
        }

        if (projectType.equals("Select") || supervisor.equals("Select") || firstMarker.equals("Select") || secondMarker.equals("Select") || startDate.isEmpty() || dueDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop execution if any required field is not selected
        }
        
        // Validate lecturer selection
        if (!supervisor.equals("None") && (supervisor.equals(firstMarker) || supervisor.equals(secondMarker) || firstMarker.equals(secondMarker))) {
            JOptionPane.showMessageDialog(this, "Please select different lecturers for each combo box.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop execution if same lecturer selected in different combo boxes
        }

        // Call the method to update data in table and file
        updateDataInTableAndFile(selectedRow, projectName, projectType, supervisor, firstMarker, secondMarker, startDate, dueDate);
    

        // Clear input fields or reset JComboBox selection
        jTextFieldAssesment.setText(""); // Clear JTextField
        jComboBoxProjType.setSelectedIndex(0); // Reset JComboBox selection
        jComboBox1Marker.setSelectedIndex(0); // Reset JComboBox selection
        jComboBox2Marker.setSelectedIndex(0); // Reset JComboBox selection
        jDateChooser1.setDate(null); // Clear the first JDateChooser
        jDateChooser2.setDate(null); // Clear the second JDateChooser

        
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }



    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox2MarkerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2MarkerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2MarkerActionPerformed

    private void jComboBox1MarkerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1MarkerActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBox1MarkerActionPerformed

    private void jComboBoxProjTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxProjTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxProjTypeActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        var sp = new LecturerDashboard(lecturerName);
        sp.setVisible(true);
        dispose();
    }//GEN-LAST:event_backBtnActionPerformed

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
            java.util.logging.Logger.getLogger(AssignMarker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AssignMarker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AssignMarker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AssignMarker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AssignMarker("Name").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1Marker;
    private javax.swing.JComboBox<String> jComboBox2Marker;
    private javax.swing.JComboBox<String> jComboBoxProjType;
    private javax.swing.JComboBox<String> jComboBoxsuper;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldAssesment;
    // End of variables declaration//GEN-END:variables
}
