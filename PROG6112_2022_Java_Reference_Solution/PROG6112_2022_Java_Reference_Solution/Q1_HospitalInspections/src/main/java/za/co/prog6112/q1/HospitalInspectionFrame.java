package za.co.prog6112.q1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/** GUI for capturing, printing and clearing up to five inspection records. */
public class HospitalInspectionFrame extends JFrame {
    private final JTextField nameField = new JTextField(16);
    private final JTextField locationField = new JTextField(16);
    private final JComboBox<Integer> yearsBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
    private final DefaultListModel<HospitalInspection> listModel = new DefaultListModel<>();
    private final JList<HospitalInspection> inspectionList = new JList<>(listModel);
    private final List<HospitalInspection> inspections = new ArrayList<>();

    public HospitalInspectionFrame() {
        super("Hospital Inspection Capture");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 400);
        setLocationRelativeTo(null);

        JPanel fields = new JPanel(new GridLayout(3, 2, 8, 8));
        fields.add(new JLabel("Hospital name:")); fields.add(nameField);
        fields.add(new JLabel("Location:")); fields.add(locationField);
        fields.add(new JLabel("Years since last inspection:")); fields.add(yearsBox);

        JButton save = new JButton("Save inspection");
        JButton print = new JButton("Print inspections");
        JButton clear = new JButton("Clear controls");
        JPanel buttons = new JPanel();
        buttons.add(save); buttons.add(print); buttons.add(clear);

        add(fields, BorderLayout.NORTH);
        add(new JScrollPane(inspectionList), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
        setJMenuBar(createMenuBar());

        save.addActionListener(e -> saveInspection());
        print.addActionListener(e -> printInspections());
        clear.addActionListener(e -> clearControls());
    }

    private JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> dispose());
        file.add(exit);

        JMenu tools = new JMenu("Tools");
        JMenuItem save = new JMenuItem("Save inspections");
        JMenuItem print = new JMenuItem("Print inspections");
        JMenuItem clear = new JMenuItem("Clear controls");
        save.addActionListener(e -> saveInspection());
        print.addActionListener(e -> printInspections());
        clear.addActionListener(e -> clearControls());
        tools.add(save); tools.add(print); tools.add(clear);
        bar.add(file); bar.add(tools);
        return bar;
    }

    private void saveInspection() {
        String name = nameField.getText().trim();
        String location = locationField.getText().trim();
        if (name.isEmpty() || location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter both the hospital name and location.");
            return;
        }
        if (inspections.size() >= 5) {
            JOptionPane.showMessageDialog(this, "A maximum of five hospital inspections can be captured.",
                    "Limit reached", JOptionPane.ERROR_MESSAGE);
            return;
        }
        inspections.add(new HospitalInspection(name, location, (Integer) yearsBox.getSelectedItem()));
        JOptionPane.showMessageDialog(this, "Inspection saved.");
        nameField.setText("");
        locationField.setText("");
        yearsBox.setSelectedIndex(0);
    }

    private void printInspections() {
        listModel.clear();
        for (HospitalInspection inspection : inspections) listModel.addElement(inspection);
    }

    private void clearControls() {
        nameField.setText("");
        locationField.setText("");
        yearsBox.setSelectedIndex(0);
        inspectionList.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HospitalInspectionFrame().setVisible(true));
    }
}
