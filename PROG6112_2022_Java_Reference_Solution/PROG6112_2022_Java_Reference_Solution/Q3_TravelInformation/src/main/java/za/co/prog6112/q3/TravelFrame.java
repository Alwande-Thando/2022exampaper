package za.co.prog6112.q3;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

/** Captures travel information and persists it in the sequential text file travel.txt. */
public class TravelFrame extends JFrame {
    private static final String FILE_NAME = "travel.txt";
    private final JComboBox<String> startBox =
            new JComboBox<>(new String[]{"Cape Town", "Durban", "Port Elizabeth"});
    private final JComboBox<String> endBox =
            new JComboBox<>(new String[]{"Cape Town", "Durban", "Port Elizabeth"});
    private final JComboBox<String> travelBox =
            new JComboBox<>(new String[]{"Airplane", "Train"});
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> travelList = new JList<>(listModel);

    public TravelFrame() {
        super("Travel Information");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 350);
        setLocationRelativeTo(null);

        JPanel input = new JPanel(new GridLayout(3, 2, 8, 8));
        input.add(new JLabel("Start location:")); input.add(startBox);
        input.add(new JLabel("End location:")); input.add(endBox);
        input.add(new JLabel("Travel by:")); input.add(travelBox);

        JButton submit = new JButton("Submit");
        submit.addActionListener(e -> submitTravel());
        JPanel south = new JPanel();
        south.add(submit);

        add(input, BorderLayout.NORTH);
        add(new JScrollPane(travelList), BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);

        // Load previously saved travel records when the application opens.
        loadTravelRecords();
    }

    private void submitTravel() {
        String start = (String) startBox.getSelectedItem();
        String end = (String) endBox.getSelectedItem();
        String travelBy = (String) travelBox.getSelectedItem();

        if (start.equals(end)) {
            JOptionPane.showMessageDialog(this,
                    "The start and end locations cannot be the same.",
                    "Invalid journey", JOptionPane.ERROR_MESSAGE);
            return;
        }

        TravelRecord record = new TravelRecord(start, end, travelBy);
        // Append one record per line, creating travel.txt if it does not exist.
        try (BufferedWriter writer = Files.newBufferedWriter(
                Path.of(FILE_NAME), StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(record.toString());
            writer.newLine();
            listModel.addElement(record.toString());
            JOptionPane.showMessageDialog(this, "Travel information saved.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not save travel.txt: " + ex.getMessage(),
                    "File error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTravelRecords() {
        Path path = Path.of(FILE_NAME);
        if (!Files.exists(path)) return;
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            listModel.clear();
            for (String line : lines) {
                if (!line.isBlank()) listModel.addElement(line);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not load travel.txt: " + ex.getMessage(),
                    "File error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TravelFrame().setVisible(true));
    }
}
