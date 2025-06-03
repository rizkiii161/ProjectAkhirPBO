/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;


/**
 *
 * @author Rizki
 */
public class ModelTodos {
    private Integer id;
    private String title, description;
    private boolean isDone;
    private LocalDate createdAt, dueAt;
    

    public void setcreatedAt(LocalDate createdAt){
        this.createdAt = createdAt;
    }
    public LocalDate getcreatedteAt(){
        return this.createdAt ;
    }
    public void setDueAt(LocalDate dueAt){
        this.dueAt = dueAt;
    }
    public LocalDate getDueAt(){
        return this.dueAt ; 
    }
    
    public void setDone(boolean isDone){
    this.isDone = isDone;
    }
    
    public boolean getIsDone(){
        return this.isDone ; 
    }
    public void setTitle(String title){
    this.title = title;
    }
    
    public String getTitle(){
        return this.title ; 
    }
    public void setDescription(String description){
    this.description = description;
    }
    
    public String getDescription(){
        return this.description ; 
    }
    public void setId(Integer id){
    this.id = id;
    }
    
    public Integer getId(){
        return this.id ; 
    }
    
    
    
}
