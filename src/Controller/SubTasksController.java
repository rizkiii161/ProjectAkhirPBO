package Controller;

import Model.ModelSubTasks;
import View.SubTaksView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Config.Connector;
import Dao.SubTasksDao;

import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubTasksController {

    private final SubTaksView view;
    private final SubTasksDao dao;   // DAO instance
    private List<ModelSubTasks> subTasksList;
    private final DefaultTableModel tableModel;
    private final int todoId;

    public SubTasksController(SubTaksView view, int todoId) {
        this.todoId = todoId;
        this.view = view;
        this.dao = new SubTasksDao();  // Inisialisasi DAO

        // Setup table model dengan kolom
        tableModel = new DefaultTableModel(new String[]{ "Title", "Description", "Status"}, 0);
        view.getTasksTable().setModel(tableModel);

        // Ambil data dari DB saat awal
        subTasksList = dao.getByToDoId(todoId);  // ambil data dari DB
        refreshTable();

        // Listener tombol Add, Update, Delete
        view.getAddButton().addActionListener(e -> addSubTask());
        view.getUpdateButton().addActionListener(e -> updateSubTask());
        view.getDeleteButton().addActionListener(e -> deleteSubTask());

        // Listener klik pada baris tabel
        view.getTasksTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = view.getTasksTable().getSelectedRow();
                if (selectedRow >= 0 && selectedRow < subTasksList.size()) {
                    ModelSubTasks task = subTasksList.get(selectedRow);
                    view.setTitleText(task.getTitle());
                    view.setDescriptionText(task.getDescription());
                    view.setDoneSelected(task.getIsDone());
                }
            }
        });
    }

    private void addSubTask() {
        String title = view.getTitleText();
        String desc = view.getDescriptionText();
        boolean done = view.isDoneSelected();

        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Title tidak boleh kosong.");
            return;
        }

        ModelSubTasks task = new ModelSubTasks();
        // ID biasanya auto increment di DB, jangan set manual
        task.setToDoId(todoId);  // contoh ToDoId, sesuaikan jika ada input
        task.setTitle(title);
        task.setDescription(desc);
        task.setIsDone(done);

        dao.insert(task);  // simpan ke DB

        // reload data dari DB untuk update list & tabel
        subTasksList = dao.getByToDoId(todoId);
        refreshTable();
        clearForm();
    }

    private void updateSubTask() {
        int selectedRow = view.getTasksTable().getSelectedRow();
        if (selectedRow >= 0 && selectedRow < subTasksList.size()) {
            ModelSubTasks task = subTasksList.get(selectedRow);

            task.setTitle(view.getTitleText());
            task.setDescription(view.getDescriptionText());
            task.setIsDone(view.isDoneSelected());

            dao.update(task);  // update di DB

            subTasksList = dao.getByToDoId(todoId);
            refreshTable();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(view, "Pilih baris yang ingin di-update.");
        }
    }

    private void deleteSubTask() {
        int selectedRow = view.getTasksTable().getSelectedRow();
        if (selectedRow >= 0 && selectedRow < subTasksList.size()) {
            ModelSubTasks task = subTasksList.get(selectedRow);
            dao.delete(task.getId());  // hapus di DB

            subTasksList = dao.getByToDoId(todoId);
            refreshTable();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(view, "Pilih baris yang ingin dihapus.");
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (ModelSubTasks task : subTasksList) {
            tableModel.addRow(new Object[] {
                    //                    task.getId(),
                    //                    task.getToDoId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getIsDone() ? "Finished" : "Not Yet"
            });
        }
    }
    
    private void clearForm() {
        view.setTitleText("");
        view.setDescriptionText("");
        view.setDoneSelected(false);
    }
}
