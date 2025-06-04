/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;

public class ModelTodos {
    private Integer id;
    private Integer category_id; // Foreign Key
    private String name_category; // Data table categories
    private String title;
    private String description;
    private boolean isDone;
    private LocalDateTime createdAt;
    private LocalDateTime dueAt;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getCategoryId() { // Foreign Key
        return category_id;
    }
    
    public void setCategoryId(int categoryId) { // Foreign Key
        this.category_id = categoryId;
    }
    
    public String getCategoryName() {
        return name_category;
    }

    public void setCategoryName(String name_category) {
        this.name_category = name_category;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isDone() {
        return isDone;
    }
    
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getDueAt() {
        return dueAt;
    }
    
    public void setDueAt(LocalDateTime dueAt) {
        this.dueAt = dueAt;
    }
}
