import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GradeManagerGUI extends JFrame {
    private ArrayList<Student> students = new ArrayList<>();

    private JTextField nameField, idField, gradeField;
    private JTextArea displayArea;

    public GradeManagerGUI() {
        setTitle("Student Grade Manager");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI components
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField(20);

        JLabel gradeLabel = new JLabel("Grade:");
        gradeField = new JTextField(20);

        JButton addButton = new JButton("Add Student");
        JButton viewButton = new JButton("View Students");

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Layout setup
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(gradeLabel);
        inputPanel.add(gradeField);
        inputPanel.add(addButton);
        inputPanel.add(viewButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Button actions
        addButton.addActionListener(e -> addStudent());
        viewButton.addActionListener(e -> viewStudents());
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String idText = idField.getText().trim();
        String gradeText = gradeField.getText().trim();

        if (name.isEmpty() || idText.isEmpty() || gradeText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            double grade = Double.parseDouble(gradeText);

            students.add(new Student(name, id, grade));
            JOptionPane.showMessageDialog(this, "Student added successfully!");

            // Clear input fields
            nameField.setText("");
            idField.setText("");
            gradeField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID must be an integer and Grade must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewStudents() {
        if (students.isEmpty()) {
            displayArea.setText("No students found.");
            return;
        }

        StringBuilder sb = new StringBuilder("Student List:\n");
        for (Student s : students) {
            sb.append(s.toString()).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GradeManagerGUI gui = new GradeManagerGUI();
            gui.setVisible(true);
        });
    }
}
