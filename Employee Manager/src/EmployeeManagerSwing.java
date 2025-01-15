import java.awt.*;
import javax.swing.*;

public class EmployeeManagerSwing extends JFrame {
    private EmployeeManager manager;
    private JTextArea textArea;

    public EmployeeManagerSwing() {
        // Initialize the employee manager
        manager = new EmployeeManager();

        // Configure the main window
        setTitle("Employee Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        JButton btnAdd = new JButton("Add");
        JButton btnRemove = new JButton("Remove");
        JButton btnList = new JButton("List");
        JButton btnFind = new JButton("Find");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRemove);
        buttonPanel.add(btnList);
        buttonPanel.add(btnFind);

        add(buttonPanel, BorderLayout.NORTH);

        // Text area to display results
        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Button events
        btnAdd.addActionListener(e -> showAddWindow());
        btnRemove.addActionListener(e -> showRemoveWindow());
        btnList.addActionListener(e -> listEmployees());
        btnFind.addActionListener(e -> showFindWindow());
    }

    private void showAddWindow() {
        // Create a new dialog to add employees
        JDialog dialog = new JDialog(this, "Add Employee", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(5, 2));

        dialog.add(new JLabel("ID:"));
        JTextField fieldID = new JTextField();
        dialog.add(fieldID);

        dialog.add(new JLabel("Name:"));
        JTextField fieldName = new JTextField();
        dialog.add(fieldName);

        dialog.add(new JLabel("Position:"));
        JTextField fieldPosition = new JTextField();
        dialog.add(fieldPosition);

        dialog.add(new JLabel("Salary:"));
        JTextField fieldSalary = new JTextField();
        dialog.add(fieldSalary);

        JButton btnSave = new JButton("Save");
        JButton btnCancel = new JButton("Cancel");
        dialog.add(btnSave);
        dialog.add(btnCancel);

        // Save employee event
        btnSave.addActionListener(e -> {
            try {
                int id = Integer.parseInt(fieldID.getText());
                String name = fieldName.getText();
                String position = fieldPosition.getText();
                double salary = Double.parseDouble(fieldSalary.getText());

                manager.addEmployee(new Employee(id, name, position, salary));
                textArea.setText("Employee added successfully.");
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid data entered.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Cancel event
        btnCancel.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void showRemoveWindow() {
        String input = JOptionPane.showInputDialog(this, "Enter the ID of the employee to remove:");
        if (input != null) {
            try {
                int id = Integer.parseInt(input);
                if (manager.removeEmployee(id)) {
                    textArea.setText("Employee removed successfully.");
                } else {
                    textArea.setText("Employee not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showFindWindow() {
        String input = JOptionPane.showInputDialog(this, "Enter the ID of the employee to find:");
        if (input != null) {
            try {
                int id = Integer.parseInt(input);
                Employee employee = manager.findEmployee(id);
                if (employee != null) {
                    textArea.setText("Employee found:\n" + employee);
                } else {
                    textArea.setText("Employee not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void listEmployees() {
        StringBuilder list = new StringBuilder("Employee list:\n");
        for (Employee employee : manager.listEmployees()) {
            list.append(employee).append("\n");
        }
        textArea.setText(list.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeManagerSwing().setVisible(true));
    }
}
