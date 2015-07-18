package domain;
import java.text.*;
import java.util.*;

public class Task extends DomainAbs {
    private Date dueDate;
    private String taskName = null;
    private List<Task> tasks = new ArrayList<Task>();

//set and get dueDate    
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

//set and get TaskName
    public String getTaskName() {
        return taskName;
        
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }  
    
//set and get Tasks
    public void setTasks(Task task) {
        tasks.add(task);
    }
    public List<Task> getTasks() {
        return tasks;
    }
    
    //@Override
    public boolean validate () {
        if (!(super.validate())){
            return false;
        }
        if (getDueDate() == null){
            return false;
        }  
        if (getTaskName() == null){
            return false;
        }
        /*if(tasks.isEmpty()){
            return false;
        } */
        return true;
    }
    
    @Override
    public boolean equals (Object obj){
        if (this==obj){
            return true;
        }
        if(!(obj instanceof Task)){
            return false;
        }
        Task task = (Task)obj;
        if(task.id != id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        return taskName + " " + dueDate;
    }


}
