package MINI_PROJECT;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
import java.io.*;

public class StudentTrackerApp extends JFrame implements ActionListener {
    private JTextField tfId, tfName, tfDept, tfMarks, tfAddress,
                       tfFatherName, tfFatherPhone, tfMotherName, tfMotherPhone,
                       tfStudentPhone, tfSection, tfBranch;
    private JTextArea taDisplay;
    private JButton btnAdd, btnView, btnUpdate, btnDelete, btnClear;

    private HashMap<Integer, Student> studentDB = new HashMap<>();

    public StudentTrackerApp() {
        setTitle("Student Academic Tracker");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load saved data
        loadDataFromText();

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(12, 2, 5, 5));
        formPanel.add(new JLabel("Student ID:")); tfId = new JTextField(); formPanel.add(tfId);
        formPanel.add(new JLabel("Name:")); tfName = new JTextField(); formPanel.add(tfName);
        formPanel.add(new JLabel("Department:")); tfDept = new JTextField(); formPanel.add(tfDept);
        formPanel.add(new JLabel("Marks:")); tfMarks = new JTextField(); formPanel.add(tfMarks);
        formPanel.add(new JLabel("Address:")); tfAddress = new JTextField(); formPanel.add(tfAddress);
        formPanel.add(new JLabel("Father Name:")); tfFatherName = new JTextField(); formPanel.add(tfFatherName);
        formPanel.add(new JLabel("Father Phone:")); tfFatherPhone = new JTextField(); formPanel.add(tfFatherPhone);
        formPanel.add(new JLabel("Mother Name:")); tfMotherName = new JTextField(); formPanel.add(tfMotherName);
        formPanel.add(new JLabel("Mother Phone:")); tfMotherPhone = new JTextField(); formPanel.add(tfMotherPhone);
        formPanel.add(new JLabel("Student Phone:")); tfStudentPhone = new JTextField(); formPanel.add(tfStudentPhone);
        formPanel.add(new JLabel("Section:")); tfSection = new JTextField(); formPanel.add(tfSection);
        formPanel.add(new JLabel("Branch:")); tfBranch = new JTextField(); formPanel.add(tfBranch);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnAdd = new JButton("Add");
        btnView = new JButton("View");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        buttonPanel.add(btnAdd); buttonPanel.add(btnView);
        buttonPanel.add(btnUpdate); buttonPanel.add(btnDelete); buttonPanel.add(btnClear);

        // Display area
        taDisplay = new JTextArea(10, 50);
        taDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taDisplay);

        // Layout
        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Listeners
        btnAdd.addActionListener(this);
        btnView.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnClear.addActionListener(this);

        // Save on exit
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                saveDataToText();
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        int id;
        try {
            id = Integer.parseInt(tfId.getText().trim());
        } catch (NumberFormatException ex) {
            showMessage("Invalid ID (must be an integer).");
            return;
        }

        if (e.getSource() == btnAdd) {
            double marks;
            try {
                marks = Double.parseDouble(tfMarks.getText().trim());
            } catch (NumberFormatException ex) {
                showMessage("Invalid Marks (must be a number).");
                return;
            }
            Student s = new Student(
                id, tfName.getText().trim(), tfDept.getText().trim(),
                marks, tfAddress.getText().trim(),
                tfFatherName.getText().trim(), tfFatherPhone.getText().trim(),
                tfMotherName.getText().trim(), tfMotherPhone.getText().trim(),
                tfStudentPhone.getText().trim(), tfSection.getText().trim(), tfBranch.getText().trim()
            );
            studentDB.put(id, s);
            showMessage("Student added.");
        } else if (e.getSource() == btnView) {
            Student s = studentDB.get(id);
            if (s != null) {
                tfName.setText(s.getName());
                tfDept.setText(s.getDepartment());
                tfMarks.setText(String.valueOf(s.getMarks()));
                tfAddress.setText(s.getAddress());
                tfFatherName.setText(s.getFatherName());
                tfFatherPhone.setText(s.getFatherPhone());
                tfMotherName.setText(s.getMotherName());
                tfMotherPhone.setText(s.getMotherPhone());
                tfStudentPhone.setText(s.getStudentPhone());
                tfSection.setText(s.getSection());
                tfBranch.setText(s.getBranch());
                showMessage("Student found.");
            } else {
                showMessage("Student not found.");
            }
        } else if (e.getSource() == btnUpdate) {
            Student s = studentDB.get(id);
            if (s != null) {
                double marks;
                try {
                    marks = Double.parseDouble(tfMarks.getText().trim());
                } catch (NumberFormatException ex) {
                    showMessage("Invalid Marks (must be a number).");
                    return;
                }
                s.setName(tfName.getText().trim());
                s.setDepartment(tfDept.getText().trim());
                s.setMarks(marks);
                s.setAddress(tfAddress.getText().trim());
                s.setFatherName(tfFatherName.getText().trim());
                s.setFatherPhone(tfFatherPhone.getText().trim());
                s.setMotherName(tfMotherName.getText().trim());
                s.setMotherPhone(tfMotherPhone.getText().trim());
                s.setStudentPhone(tfStudentPhone.getText().trim());
                s.setSection(tfSection.getText().trim());
                s.setBranch(tfBranch.getText().trim());
                showMessage("Student updated.");
            } else {
                showMessage("Student not found.");
            }
        } else if (e.getSource() == btnDelete) {
            if (studentDB.remove(id) != null) {
                showMessage("Student deleted.");
            } else {
                showMessage("Student not found.");
            }
        } else if (e.getSource() == btnClear) {
            clearFields();
            taDisplay.setText("");
            for (Student s : studentDB.values()) {
                taDisplay.append(s.toString() + "\n");
            }
        }
    }

    private void clearFields() {
        tfId.setText("");
        tfName.setText("");
        tfDept.setText("");
        tfMarks.setText("");
        tfAddress.setText("");
        tfFatherName.setText("");
        tfFatherPhone.setText("");
        tfMotherName.setText("");
        tfMotherPhone.setText("");
        tfStudentPhone.setText("");
        tfSection.setText("");
        tfBranch.setText("");
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    private void saveDataToText() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("students.txt"))) {
            for (Student s : studentDB.values()) {
                pw.println(s.toString());
            }
        } catch (Exception ex) {
            System.out.println("Error saving data: " + ex.getMessage());
        }
    }

    private void loadDataFromText() {
        studentDB.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 12) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String dept = parts[2];
                    double marks = Double.parseDouble(parts[3]);
                    String address = parts[4];
                    String fatherName = parts[5];
                    String fatherPhone = parts[6];
                    String motherName = parts[7];
                    String motherPhone = parts[8];
                    String studentPhone = parts[9];
                    String section = parts[10];
                    String branch = parts[11];

                    Student s = new Student(id, name, dept, marks, address,
                                            fatherName, fatherPhone,
                                            motherName, motherPhone,
                                            studentPhone, section, branch);
                    studentDB.put(id, s);
                }
            }
        } catch (Exception ex) {
            System.out.println("No saved data found, starting fresh.");
        }
    }

    public static void main(String[] args) {
        new StudentTrackerApp();
    }
}