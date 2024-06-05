/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author User
 */
public class EditSubmissionDialog extends JDialog {
    private JTextField[] fields;

    public EditSubmissionDialog(JFrame parent, String[] parts) {
        super(parent, "Edit Submission", true);
        JPanel panel = new JPanel(new GridLayout(parts.length, 2));

        fields = new JTextField[parts.length];
        for (int i = 0; i < parts.length; i++) {
            panel.add(new JLabel("Field " + i + ": "));
            fields[i] = new JTextField(parts[i]);
            panel.add(fields[i]);
        }

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(okButton);
        panel.add(cancelButton);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(parent);
    }

    public String[] getData() {
        String[] data = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            data[i] = fields[i].getText();
        }
        return data;
    }
}
