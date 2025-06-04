/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.ModelCategories;
import Config.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDAO {
    
    // INSERT
    public void insert(ModelCategories category) {
        try {
            String query = "INSERT INTO categories (name_category) VALUES (?)";
            
            PreparedStatement stmt;
            stmt = Connector.connect().prepareStatement(query);
            stmt.setString(1, category.getNameCategory());
            
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Insert Failed: " + e.getLocalizedMessage());
        }
    }
    
     public void update(ModelCategories category) {
        try {
            String sql = "UPDATE categories SET name_category = ? WHERE id = ?";
            PreparedStatement stmt = Connector.connect().prepareStatement(sql);
            stmt.setString(1, category.getNameCategory());
            stmt.setInt(2, category.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update Category Failed: " + e.getLocalizedMessage());
        }
    }

    public void delete(int id) {
        try {
            String sql = "DELETE FROM categories WHERE id = ?";
            PreparedStatement stmt = Connector.connect().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete Category Failed: " + e.getLocalizedMessage());
        }
    }
    
    // GET ALL 
    public List<ModelCategories> getAll() {
        List<ModelCategories> listCategories = null;
        
        try {
            listCategories = new ArrayList<>();
            Statement stmt;
            stmt = Connector.connect().createStatement();
            
            String query = "SELECT * FROM categories";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ModelCategories category = new ModelCategories();
                category.setId(rs.getInt("id"));
                category.setNameCategory(rs.getString("name_category"));
                listCategories.add(category);
            }
            
            stmt.close();
        } catch (SQLException e) {
            System.out.println("GetAll Failed: " + e.getLocalizedMessage());
        }
        return listCategories;
    }
    
    // GET BY ID (Belum tau kepake ngga)
    public ModelCategories getById(int id) {
        ModelCategories category = null;
        try {
            String sql = "SELECT * FROM categories WHERE id = ?";
            PreparedStatement stmt = Connector.connect().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                category = new ModelCategories();
                category.setId(rs.getInt("id"));
                category.setNameCategory(rs.getString("name_category"));
            }

        } catch (SQLException e) {
            System.out.println("Get Category by ID Failed: " + e.getLocalizedMessage());
        }
        return category;
    }
}
