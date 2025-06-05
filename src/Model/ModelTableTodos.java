package Model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ModelTableTodos extends AbstractTableModel {
    private final List<ModelTodos> todos;
    private final String[] columnNames = {
        "Title", "Description", "Created At", "Due At", "Category", "Status"
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
            case 0: return todo.getTitle();
            case 1: return todo.getDescription();
            case 2: return todo.getCreatedAt();
            case 3: return todo.getDueAt();
            case 4: return todo.getCategoryName();
            case 5: return todo.isDone() ? "Done" : "Pending";
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
