package Dao;

import Model.ModelTodos;
import Config.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodosDAO {

    // INSERT
    public void insert(ModelTodos todo) {
        try {
            String query = "INSERT INTO todos (title, description, is_done, category_id, created_at, due_at) VALUES (?, ?, ?, ?, ?, ?)";
            
            PreparedStatement stmt;
            stmt = Connector.connect().prepareStatement(query);
            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getDescription());
            stmt.setBoolean(3, todo.isDone());
            stmt.setInt(4, todo.getCategoryId());
            stmt.setTimestamp(5, Timestamp.valueOf(todo.getCreatedAt()));
            stmt.setTimestamp(6, Timestamp.valueOf(todo.getDueAt()));
            
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Insert Failed: " + e.getLocalizedMessage());
        }
    }

    // GET ALL
    public List<ModelTodos> getAll() {
        List<ModelTodos> listTodos = new ArrayList<>();
        try {
            Statement stmt;
            stmt = Connector.connect().createStatement();
            String query = "SELECT t.*, c.name_category FROM todos t JOIN categories c ON t.category_id = c.id";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                ModelTodos todo = new ModelTodos();
                todo.setId(rs.getInt("id"));
                todo.setTitle(rs.getString("title"));
                todo.setDescription(rs.getString("description"));
                todo.setDone(rs.getBoolean("is_done"));
                todo.setCategoryId(rs.getInt("category_id"));
                todo.setCategoryName(rs.getString("name_category")); 
                todo.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                todo.setDueAt(rs.getTimestamp("due_at").toLocalDateTime());
                listTodos.add(todo);
            }

            stmt.close();
        } catch (SQLException e) {
            System.out.println("GetAll Failed: " + e.getLocalizedMessage());
        }
        return listTodos;
    }
    
    // GET BY ID (ngaa tau kepake ngga, nanti diubah aja kalo mau getBy yg lain)
    public ModelTodos getById(int id) {
        ModelTodos todo = null;
        try {
            String query = "SELECT t.*, c.name_category FROM todos t JOIN categories c ON t.category_id = c.id WHERE id = ?";
            
            PreparedStatement stmt;
            stmt = Connector.connect().prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                todo = new ModelTodos();
                todo.setId(rs.getInt("id"));
                todo.setTitle(rs.getString("title"));
                todo.setDescription(rs.getString("description"));
                todo.setDone(rs.getBoolean("is_done"));
                todo.setCategoryId(rs.getInt("category_id"));
                todo.setCategoryName(rs.getString("name_category"));
                todo.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                todo.setDueAt(rs.getTimestamp("due_at").toLocalDateTime());
            }
            
            stmt.close();
        } catch (SQLException e) {
            System.out.println("GetById Failed: " + e.getLocalizedMessage());
        }
        return todo;
    }

    // UPDATE
    public void update(ModelTodos todo) {
        try {
            String query = "UPDATE todos SET title = ?, description = ?, is_done = ?, category_id = ?, due_at = ? WHERE id = ?";
            
            PreparedStatement stmt;
            stmt = Connector.connect().prepareStatement(query);
            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getDescription());
            stmt.setBoolean(3, todo.isDone());
            stmt.setInt(4, todo.getCategoryId()); 
            stmt.setTimestamp(6, Timestamp.valueOf(todo.getDueAt()));
            stmt.setInt(6, todo.getId());
            
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Update Failed: " + e.getLocalizedMessage());
        }
    }

    // DELETE
    public void delete(int id) {
        try {
            String query = "DELETE FROM todos WHERE id = ?";
            
            PreparedStatement stmt;
            stmt = Connector.connect().prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete Failed: " + e.getLocalizedMessage());
        }
    }
}
