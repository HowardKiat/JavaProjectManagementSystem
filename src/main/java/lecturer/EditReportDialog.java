package lecturer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditReportDialog extends JDialog {
    private final JTextField[] textFields;
    private final JButton updateButton;
    private final int rowIndex;
    private final DefaultTableModel model;

    public EditReportDialog(JFrame parent, DefaultTableModel model, int rowIndex) {
        super(parent, "Edit Report", true);
        this.model = model;
        this.rowIndex = rowIndex;
        
        textFields = new JTextField[model.getColumnCount()];
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (int i = 0; i < model.getColumnCount(); i++) {
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
            JLabel label = new JLabel(model.getColumnName(i) + ": ");
            textFields[i] = new JTextField(model.getValueAt(rowIndex, i).toString(), 20);
            rowPanel.add(label);
            rowPanel.add(textFields[i]);
            panel.add(rowPanel);
        }

        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onUpdate();
            }
        });
        panel.add(updateButton);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(parent);
    }

    private void onUpdate() {
        for (int i = 0; i < textFields.length; i++) {
            model.setValueAt(textFields[i].getText(), rowIndex, i);
        }
        dispose();
    }
}
