/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package student;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.printing.PDFPageable;
/**
 *
 * @author User
 */
public class ReportSubmission extends javax.swing.JFrame {
    private final String studentName;
    private String fileName;
    private final List<Submission> submissions;
    private final DefaultListModel<String> listModel;
    private PDDocument currentPdfDocument;
    private int currentPageIndex;
    private File currentPdfFile;
    private final String filePath = "submitted_report.txt";



    public ReportSubmission(String name) {
        super();
        submissions = new ArrayList<>();
        listModel = new DefaultListModel<>();
        setTitle("Report Submission");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.studentName = name;
        initComponents();
        profileField.setText(studentName);
        profileField.setEditable(false);
        loadStudentData();
        loadAssignmentData();
    }
    
    private void loadStudentData() {
        try (BufferedReader br = new BufferedReader(new FileReader("student.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7 && parts[3].equals(studentName)) {
                    courseField.setText(parts[5]);
                    intakeField.setText(parts[6]);
                    courseField.setEditable(false);
                    intakeField.setEditable(false);
                    break;
                }
            }
        } catch (IOException e) {
            showError("Error loading student data: " + e.getMessage());
        }
    }
    
    private void loadAssignmentData() {
        try (BufferedReader br = new BufferedReader(new FileReader("assessment_details.txt"))) {
            String line;
            Map<String, String> assessmentProjectNameMap = new HashMap<>();
            Map<String, String> assessmentProjectIDMap = new HashMap<>();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length >= 12 && parts[5].equals(studentName)) {
                    String projectID = parts[0];
                    String projectName = parts[1];
                    String assessmentType = parts[2];

                    if (((DefaultComboBoxModel<String>) assessmentTypeField.getModel()).getIndexOf(assessmentType) == -1) {
                        assessmentTypeField.addItem(assessmentType);
                    }

                    assessmentProjectNameMap.put(assessmentType, projectName);
                    assessmentProjectIDMap.put(assessmentType, projectID);
                }
            }

            // Update the project field based on the selected assessment type
            assessmentTypeField.addActionListener(e -> {
                String selectedAssessmentType = (String) assessmentTypeField.getSelectedItem();
                if (selectedAssessmentType != null) {
                    if (assessmentProjectNameMap.containsKey(selectedAssessmentType)) {
                        projectField.setText(assessmentProjectNameMap.get(selectedAssessmentType));
                    }
                    if (assessmentProjectIDMap.containsKey(selectedAssessmentType)) {
                        projectIDField.setText(assessmentProjectIDMap.get(selectedAssessmentType));
                    }
                }
            });

        } catch (IOException e) {
            showError("Error loading assignment data: " + e.getMessage());
        }
    }
    
    private void writeModifiedLinesToFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("assessment_details.txt"))) {
            List<String> updatedLines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 12 && 
                    parts[2].equals(assessmentTypeField.getSelectedItem().toString()) && 
                    parts[5].equals(studentName) && 
                    parts[12].equals("Pending")) {
                    parts[12] = "Submitted";
                    line = String.join(", ", parts);
                }
                updatedLines.add(line);
            }
            Files.write(Paths.get("assessment_details.txt"), updatedLines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            showError("Error saving submission: " + e.getMessage());
        }
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
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
        courseField = new javax.swing.JTextField();
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
        intakeField = new javax.swing.JTextField();
        projectField = new javax.swing.JTextField();
        downloadBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        timeField = new javax.swing.JTextField();
        saveAllChanges = new javax.swing.JButton();
        printBtn = new javax.swing.JButton();
        projectIDField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        uploadBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uploadBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/status-icon.png"))); // NOI18N
        uploadBtn.setText("Upload File");
        uploadBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        uploadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadBtnActionPerformed(evt);
            }
        });
        getContentPane().add(uploadBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 140, 190, 60));
        getContentPane().add(dateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 400, 180, 30));

        jLabel2.setText("Submitted Date");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 100, 20));

        jLabel3.setText("Assesment Type");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, -1, 20));

        jLabel7.setText("Intake");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jLabel4.setText("Course");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 50, -1));
        getContentPane().add(courseField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 180, -1));

        jLabel8.setText("Project Name");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        assessmentTypeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assessmentTypeFieldActionPerformed(evt);
            }
        });
        getContentPane().add(assessmentTypeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 180, -1));

        prevBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        prevBtn.setText("<");
        prevBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevBtnActionPerformed(evt);
            }
        });
        getContentPane().add(prevBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 630, -1, -1));

        nextBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nextBtn.setText(">");
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });
        getContentPane().add(nextBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 630, -1, -1));

        pageNum.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pageNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pageNum.setText(".");
        getContentPane().add(pageNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 630, 90, 30));

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
                .addComponent(profileField, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(profileField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(37, 37, 37))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, -1));

        pdfDisplayLabel.setText(".");
        pdfDisplayLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        getContentPane().add(pdfDisplayLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, 420, 480));

        backBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-icon.png"))); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        getContentPane().add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 710, 190, 50));
        getContentPane().add(intakeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 180, -1));
        getContentPane().add(projectField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 180, -1));

        downloadBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/download-icon.png"))); // NOI18N
        downloadBtn.setText("Rename/Download");
        downloadBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        downloadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadBtnActionPerformed(evt);
            }
        });
        getContentPane().add(downloadBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 260, 190, 60));

        jLabel1.setText("Submitted Time");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, -1, -1));
        getContentPane().add(timeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 180, 30));

        saveAllChanges.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/file.png"))); // NOI18N
        saveAllChanges.setText("Save All Changes");
        saveAllChanges.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        saveAllChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAllChangesActionPerformed(evt);
            }
        });
        getContentPane().add(saveAllChanges, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 560, 190, 60));

        printBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print-icon.png"))); // NOI18N
        printBtn.setText("Print PDF");
        printBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        printBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtnActionPerformed(evt);
            }
        });
        getContentPane().add(printBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 410, 190, 60));
        getContentPane().add(projectIDField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 180, 30));

        jLabel9.setText("Project ID");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uploadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadBtnActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.getName().endsWith(".pdf")) {
                currentPdfFile = selectedFile;
                try {
                    currentPdfDocument = Loader.loadPDF(selectedFile);
                    currentPageIndex = 0;
                    displayPdfPage(currentPageIndex);
                    showSuccess("PDF file uploaded successfully.");

                    // Read project name from text file
                    String projectName = readProjectNameFromFile("assessment_details.txt");

                    // Define and populate submittedReportData
                    String[] submittedReportData = new String[]{studentName, selectedFile.getName()};

                    // Save the PDF to local with student name prefixed and project name
                    savePdfToLocal(selectedFile, studentName, projectName, submittedReportData);

                    // Write the filename with student name prefixed to the text file
                    writeFilenameToTextFile(studentName + "_" + selectedFile.getName(), "file.txt");

                } catch (IOException e) {
                    showError("Error loading PDF: " + e.getMessage());
                }
                openPdf(selectedFile);
            } else {
                showError("Please select a PDF file.");
            }
        }
    }//GEN-LAST:event_uploadBtnActionPerformed
    
    private void writeFilenameToTextFile(String filename, String filepath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true))) {
            writer.write(filename);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            showError("Error writing filename to text file: " + e.getMessage());
        }
    }

    
    private String readProjectNameFromFile(String filePath) {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            List<String> allLines = lines.collect(Collectors.toList());
            if (allLines.size() > 2) {
                return allLines.get(2).trim(); 
            } else {
                showError("Project name not found in the file.");
                return "";
            }
        } catch (IOException e) {
            showError("Error reading project name from file: " + e.getMessage());
            return "";
        }
    }
    
    private void savePdfToLocal(File selectedFile, String studentName, String projectName, String[] submittedReportData) {
        try {
            File databaseDirectory = new File("database");
            if (!databaseDirectory.exists()) {
                databaseDirectory.mkdirs();
            }

            // Create a subdirectory for the student if it doesn't already exist
            File studentDirectory = new File(databaseDirectory, studentName);
            if (!studentDirectory.exists()) {
                studentDirectory.mkdirs();
            }

            // Get the filename from the selected file
            String fileName = selectedFile.getName();

            // Construct the destination file name with the student's name prefixed
            String destinationFileName = studentName + "_" + fileName;

            // Construct the destination file path
            File destinationFile = new File(studentDirectory, destinationFileName);

            // Copy the selected file to the destination path
            Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            showSuccess("File saved successfully to database.");
        } catch (IOException e) {
            showError("Error saving file to database: " + e.getMessage());
        }
    }
    
    private void displayPdfPage(int pageIndex) {
        try {
            if (currentPdfDocument != null) {
                PDFRenderer pdfRenderer = new PDFRenderer(currentPdfDocument);
                BufferedImage pageImage = pdfRenderer.renderImageWithDPI(pageIndex, 300);
                ImageIcon imageIcon = new ImageIcon(getScaledImage(pageImage, pdfDisplayLabel.getWidth(), pdfDisplayLabel.getHeight()));
                pdfDisplayLabel.setIcon(imageIcon);
                pageNum.setText("Page " + (pageIndex + 1) + " of " + currentPdfDocument.getNumberOfPages());
            }
        } catch (IOException e) {
            showError("Error displaying PDF page: " + e.getMessage());
        }
    }
    
    private void loadAndDisplayPDF(File pdfFile) throws IOException {
        if (currentPdfDocument != null) {
            currentPdfDocument.close();
        }
        currentPdfDocument = Loader.loadPDF(pdfFile);
        currentPageIndex = 0;
        renderPage();
    }

    private void renderPage() throws IOException {
        PDFRenderer pdfRenderer = new PDFRenderer(currentPdfDocument);
        BufferedImage image = pdfRenderer.renderImage(currentPageIndex);
        ImageIcon imageIcon = new ImageIcon(image);
        pdfDisplayLabel.setIcon(imageIcon);
        pageNum.setText("Page " + (currentPageIndex + 1) + " of " + currentPdfDocument.getNumberOfPages());
    }
    
    private void assessmentTypeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assessmentTypeFieldActionPerformed

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

    private void downloadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadBtnActionPerformed
        if (currentPdfFile != null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File(currentPdfFile.getName()));
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File destinationFile = fileChooser.getSelectedFile();
                try (InputStream in = new FileInputStream(currentPdfFile);
                     OutputStream out = new FileOutputStream(destinationFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0) {
                        out.write(buffer, 0, length);
                    }
                    showSuccess("File downloaded successfully.");
                } catch (IOException e) {
                    showError("Error downloading file: " + e.getMessage());
                }
            }
        } else {
            showError("No file selected to download.");
        }
    }//GEN-LAST:event_downloadBtnActionPerformed

    private void saveAllChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAllChangesActionPerformed
        Date currentDateTime = new Date();

        // Format the current date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String formattedDate = dateFormat.format(currentDateTime);
        String formattedTime = timeFormat.format(currentDateTime);

        // Display the current date and time in their respective fields
        dateField.setText(formattedDate);
        timeField.setText(formattedTime);

        // Validate if both date and time fields are filled
        String date = formattedDate;  // Use the formatted current date
        String time = formattedTime;  // Use the formatted current time

        if (date.isEmpty() || time.isEmpty()) {
            showError("Please fill in both date and time fields.");
            return;
        }

        try {
            // Parse the date and time strings into Date objects
            Date submissionDate = dateFormat.parse(date);
            Date submissionTime = timeFormat.parse(time);

            // Combine date and time into a single Date object
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(submissionDate);

            // Use Calendar to get the hour and minute from submissionTime
            Calendar timeCalendar = Calendar.getInstance();
            timeCalendar.setTime(submissionTime);
            calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));

            Date submissionDateTime = calendar.getTime();

            // Assuming 'currentPdfFile' and 'studentName' are correctly initialized
            Submission newSubmission = new Submission(
                date,                // String date
                time,                // String time
                fileName,            // String fileName
                studentName,         // String studentName
                submissionDateTime,  // Date submissionDate
                currentPdfFile       // File currentPdfFile
            );
            submissions.add(newSubmission);

            // Call your method to write modified lines to a file (implement this method)
            writeModifiedLinesToFile();

            showSuccess("Submission saved successfully.");
        } catch (ParseException e) {
            showError("Error parsing date and time: " + e.getMessage());
        } catch (Exception e) {
            showError("Error saving submission: " + e.getMessage());
        }
    }//GEN-LAST:event_saveAllChangesActionPerformed


    private void printBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBtnActionPerformed
        if (currentPdfDocument != null && currentPdfFile != null) {
            try {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPageable(new PDFPageable(currentPdfDocument));
                if (job.printDialog()) {
                    job.print();
                    showSuccess("File printed successfully.");
                }
            } catch (PrinterException e) {
                showError("Error printing file: " + e.getMessage());
            }
        } else {
            showError("No file selected to print.");
        }
    }//GEN-LAST:event_printBtnActionPerformed
    
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
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        String studentName = profileField.getText(); // Assuming there's a field for the student's name

        Submission submission = new Submission(
                date,
                time,
                pdfFile.getName(),
                studentName, 
                new Date(), 
                pdfFile
        );

        submissions.add(submission);
        listModel.addElement(submission.toString());
        saveSubmissionToFile(submission); 
        writeModifiedLinesToFile(); 
        showSuccess("Submission saved successfully.");
    }
    
    
    private String[] readAssessmentDetails(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            return line.split(","); 
        }
    }
    
    private void saveSubmissionToFile(Submission submission) {
        try (FileWriter writer = new FileWriter(filePath, true)) {

            // Retrieve data from text fields
            String projectID = projectIDField.getText().trim();
            String project = projectField.getText().trim();
            String assessmentType = assessmentTypeField.getSelectedItem().toString().trim();
            String intake = intakeField.getText().trim();

            // Retrieve data from the Submission object
            String date = submission.getDate() != null ? submission.getDate().trim() : "";
            String time = submission.getTime() != null ? submission.getTime().trim() : "";
            String fileName = submission.getFileName() != null ? submission.getFileName().trim() : "";

            // Read assessment details from file
            String[] details = readAssessmentDetails("assessment_details.txt");

            String detail6 = details.length > 6 ? details[6].trim() : "";
            String detail13 = details.length > 13 ? details[13].trim() : "";

            writer.write(String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, -, -, -\n",
                    projectID, project, assessmentType, fileName, detail6, studentName.trim(), intake, time, date, detail13));

        } catch (IOException e) {
            showError("Error saving submission: " + e.getMessage());
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
    private javax.swing.JTextField courseField;
    private javax.swing.JTextField dateField;
    private javax.swing.JButton downloadBtn;
    private javax.swing.JTextField intakeField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton nextBtn;
    private org.apache.pdfbox.pdmodel.PDDocument pDDocument1;
    private javax.swing.JLabel pageNum;
    private javax.swing.JLabel pdfDisplayLabel;
    private javax.swing.JButton prevBtn;
    private javax.swing.JButton printBtn;
    private javax.swing.JTextField profileField;
    private javax.swing.JTextField projectField;
    private javax.swing.JTextField projectIDField;
    private javax.swing.JButton saveAllChanges;
    private javax.swing.JTextField timeField;
    private javax.swing.JButton uploadBtn;
    // End of variables declaration//GEN-END:variables
}
