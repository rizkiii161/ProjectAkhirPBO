package Controller;

import Dao.CategoriesDAO;
import Dao.TodosDAO;
import Model.ModelCategories;
import Model.ModelTodos;
import Model.ModelTableTodos;
import View.TodosView;

import javax.swing.*;
import java.util.List;

public class TodosController {

    private TodosView view;
    private TodosDAO todosDAO;
    private CategoriesDAO categoriesDAO;
    private List<ModelTodos> todosList;

    public TodosController(TodosView view) {
        this.view = view;
        this.todosDAO = new TodosDAO();
        this.categoriesDAO = new CategoriesDAO();

        loadCategoriesToComboBox();
        showAllTodos();
        addEventHandlers();
        clearForm();
    }

    public void showAllTodos() {
        todosList = todosDAO.getAll();
        ModelTableTodos modelTable = new ModelTableTodos(todosList);
        view.getTodosTable().setModel(modelTable);
        view.getTodosTable().setDefaultEditor(Object.class, null);
    }

    public void insertTodo(ModelTodos todo) {
        try {
            if (todo.getTitle().isEmpty()) {
                throw new Exception("Judul tidak boleh kosong");
            }
            if (todo.getCategoryId() == null) {
                throw new Exception("Kategori harus dipilih");
            }
//            if (todo.getDueAt().compareTo(todo.getCreatedAt()) < 0) {
//                throw new Exception("Due date tidak boleh lebih awal dari created date");
//            }

            todosDAO.insert(todo);
            showAllTodos();
            clearForm();
            JOptionPane.showMessageDialog(view, "Data berhasil ditambahkan!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    public void updateTodo(int rowIndex, ModelTodos updatedTodo) {
        try {
            if (rowIndex < 0 || rowIndex >= todosList.size()) {
                throw new Exception("Pilih baris yang valid!");
            }
            if (updatedTodo.getTitle().isEmpty()) {
                throw new Exception("Judul tidak boleh kosong");
            }
//            if (updatedTodo.getDueAt().compareTo(updatedTodo.getCreatedAt()) < 0) {
//                throw new Exception("Due date tidak boleh lebih awal dari created date");
//            }

            updatedTodo.setId(todosList.get(rowIndex).getId());
            todosDAO.update(updatedTodo);
            showAllTodos();
            clearForm();
            JOptionPane.showMessageDialog(view, "Data berhasil diperbarui!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    public void deleteTodo(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= todosList.size()) {
            JOptionPane.showMessageDialog(view, "Pilih baris yang akan dihapus!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(
            view,
            "Yakin ingin menghapus data ini?",
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION
        );

        if (konfirmasi == JOptionPane.YES_OPTION) {
            todosDAO.delete(todosList.get(rowIndex).getId());
            showAllTodos();
            clearForm();
            JOptionPane.showMessageDialog(view, "Data berhasil dihapus!");
        }
    }

    private void loadCategoriesToComboBox() {
        List<ModelCategories> categories = categoriesDAO.getAll();
        JComboBox<ModelCategories> comboBox = view.getCategoryComboBox();
        comboBox.removeAllItems();

        for (ModelCategories cat : categories) {
            comboBox.addItem(cat);
        }
        comboBox.setSelectedIndex(-1);
    }

    private void addEventHandlers() {
        view.getInsertButton().addActionListener(e -> insertTodo(view.getFormData()));
        view.getUpdateButton().addActionListener(e -> {
            int row = view.getTodosTable().getSelectedRow();
            updateTodo(row, view.getFormData());
        });
        view.getDeleteButton().addActionListener(e -> {
            int row = view.getTodosTable().getSelectedRow();
            deleteTodo(row);
        });
        view.getClearButton().addActionListener(e -> clearForm());
        view.getDetailButton().addActionListener(e -> showDetail());

        view.getTodosTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = view.getTodosTable().getSelectedRow();
                if (selectedRow >= 0 && selectedRow < todosList.size()) {
                    view.setFormData(todosList.get(selectedRow));
                    view.getCreatedAtTextField().setEnabled(false);
                }
            }
        });
    }

    private void showDetail() {
        int selectedRow = view.getTodosTable().getSelectedRow();
        if (selectedRow < 0 || selectedRow >= todosList.size()) {
            JOptionPane.showMessageDialog(view, "Pilih data yang ingin ditampilkan detailnya!");
            return;
        }

        ModelTodos todo = todosList.get(selectedRow);
        String detail = "Title: " + todo.getTitle() + "\n" +
                        "Description: " + todo.getDescription() + "\n" +
                        "Created At: " + todo.getCreatedAt() + "\n" +
                        "Due At: " + todo.getDueAt() + "\n" +
                        "Category: " + todo.getCategoryName() + "\n" +
                        "Status: " + (todo.isDone() ? "Done" : "Pending");

        JOptionPane.showMessageDialog(view, detail, "Detail To-Do", JOptionPane.INFORMATION_MESSAGE);
    }

    public void clearForm() {
        view.clearForm();
        view.getCreatedAtTextField().setEnabled(true);
        view.getTodosTable().clearSelection();
    }
}
