/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rizki
 */
public class ModelTableSubTaks {
    public static DefaultTableModel getModel(List<ModelSubTasks> subTasks) {
        // Nama kolom tabel
        String[] columnNames = {"ID", "ToDo ID", "Title", "Description", "Status"};

        // Buat DefaultTableModel dengan nama kolom, 0 baris awal
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            // Membuat kolom Status (isDone) bertipe Boolean supaya tampil checkbox
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 4) { // kolom Status
                    return Boolean.class;
                }
                return String.class;
            }

            // Membuat tabel tidak bisa diedit langsung (kecuali kolom tertentu jika mau)
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Isi baris tabel dengan data dari subTasks
        for (ModelSubTasks task : subTasks) {
            Object[] rowData = {
                task.getId(),
                task.getToDoId(),
                task.getTitle(),
                task.getDescription(),
                task.getIsDone()  // boolean, akan ditampilkan checkbox
            };
            model.addRow(rowData);
        }

        return model;
    }
    
}
