package Model;

public class ModelTodos {
    private Integer id;
    private Integer category_id; // Foreign Key
    private String name_category; // Data table categories
    private String title;
    private String description;
    private boolean isDone;
    private String createdAt;  // diubah jadi String
    private String dueAt;      // diubah jadi String
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getCategoryId() {
        return category_id;
    }
    
    public void setCategoryId(Integer categoryId) {
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
    
    public String getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getDueAt() {
        return dueAt;
    }
    
    public void setDueAt(String dueAt) {
        this.dueAt = dueAt;
    }
}
