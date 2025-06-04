package View;

import Controller.SubTasksController;
import Model.ModelSubTasks;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class SubTasksView extends JFrame {
    private SubTasksController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    private JTextField txtId, txtTitle;
    private JTextArea txtDescription;
    private JCheckBox chkIsDone;

    private JButton btnAdd, btnUpdate, btnDelete, btnRefresh;

    public SubTasksView() {
        controller = new SubTasksController();
        initComponents();
        loadTableData();
    }

    private void initComponents() {
        setTitle("SubTasks Manager");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Panel Input
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblId = new JLabel("ID:");
        txtId = new JTextField(5);
        txtId.setEnabled(false); // id auto, tidak bisa diedit

        JLabel lblTitle = new JLabel("Title:");
        txtTitle = new JTextField(20);

        JLabel lblDescription = new JLabel("Description:");
        txtDescription = new JTextArea(3, 20);
        JScrollPane scrollDesc = new JScrollPane(txtDescription);

        JLabel lblIsDone = new JLabel("Is Done:");
        chkIsDone = new JCheckBox();

        // Layout input panel
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0; inputPanel.add(lblId, gbc);
        gbc.gridx = 1; gbc.gridy = 0; inputPanel.add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1; inputPanel.add(lblTitle, gbc);
        gbc.gridx = 1; gbc.gridy = 1; inputPanel.add(txtTitle, gbc);

        gbc.gridx = 0; gbc.gridy = 2; inputPanel.add(lblDescription, gbc);
        gbc.gridx = 1; gbc.gridy = 2; inputPanel.add(scrollDesc, gbc);

        gbc.gridx = 0; gbc.gridy = 3; inputPanel.add(lblIsDone, gbc);
        gbc.gridx = 1; gbc.gridy = 3; inputPanel.add(chkIsDone, gbc);

        // Buttons
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnRefresh = new JButton("Refresh");

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnRefresh);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Title", "Description", "Is Done"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false; // agar tabel tidak bisa diedit langsung
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollTable = new JScrollPane(table);

        // Layout utama
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(scrollTable, BorderLayout.CENTER);
        contentPane.add(btnPanel, BorderLayout.SOUTH);

        // Event tombol
        btnAdd.addActionListener(e -> addSubTask());
        btnUpdate.addActionListener(e -> updateSubTask());
        btnDelete.addActionListener(e -> deleteSubTask());
        btnRefresh.addActionListener(e -> loadTableData());

        // Saat row tabel dipilih, tampilkan data ke form input
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int selectedRow = table.getSelectedRow();
                txtId.setText(tableModel.getValueAt(selectedRow, 0).toString());
                txtTitle.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtDescription.setText(tableModel.getValueAt(selectedRow, 2).toString());
                chkIsDone.setSelected((boolean) tableModel.getValueAt(selectedRow, 3));
            }
        });
    }

    private void loadTableData() {
        List<ModelSubTasks> list = controller.getAllSubTasks();
        tableModel.setRowCount(0); // clear tabel dulu
        for (ModelSubTasks subTask : list) {
            tableModel.addRow(new Object[]{
                subTask.getId(),
                subTask.getTitle(),
                subTask.getDescription(),
                subTask.getIsDone()
            });
        }
        clearForm();
    }

    private void clearForm() {
        txtId.setText("");
        txtTitle.setText("");
        txtDescription.setText("");
        chkIsDone.setSelected(false);
    }

    private void addSubTask() {
        String title = txtTitle.getText();
        String description = txtDescription.getText();
        boolean isDone = chkIsDone.isSelected();

        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        controller.addSubTask(title, description, isDone);
        loadTableData();
        JOptionPane.showMessageDialog(this, "SubTask berhasil ditambahkan.");
    }

    private void updateSubTask() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih SubTask yang ingin diupdate terlebih dahulu.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        String title = txtTitle.getText();
        String description = txtDescription.getText();
        boolean isDone = chkIsDone.isSelected();

        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        controller.updateSubTask(id, title, description, isDone);
        loadTableData();
        JOptionPane.showMessageDialog(this, "SubTask berhasil diupdate.");
    }

    private void deleteSubTask() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih SubTask yang ingin dihapus terlebih dahulu.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus SubTask ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(txtId.getText());
            controller.deleteSubTask(id);
            loadTableData();
            JOptionPane.showMessageDialog(this, "SubTask berhasil dihapus.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SubTasksView().setVisible(true);
        });
    }
}
