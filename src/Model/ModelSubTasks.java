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
public class ModelSubTasks {
    private Integer id;
    private String title, description;
    private boolean isDone;

 
    
    public void setIsDone(boolean isDone){
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
