/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ModelTableTodos extends AbstractTableModel {
    private final List<ModelTodos> todos;
    private final String[] columnNames = {
        "ID", "Title", "Description", "Status", "Category ID", "Created At", "Due At"
    };

    public ModelTableTodos(List<ModelTodos> todos) {
        this.todos = todos;
    }

    @Override
    public int getRowCount() {
        return todos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ModelTodos todo = todos.get(rowIndex);
        switch (columnIndex) {
            case 0: return todo.getId();
            case 1: return todo.getTitle();
            case 2: return todo.getDescription();
            case 3: return todo.isDone() ? "Done" : "Pending";
            case 4: return todo.getCategoryName(); // tampilkan nama kategori
            case 5: return todo.getCreatedAt();
            case 6: return todo.getDueAt();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
