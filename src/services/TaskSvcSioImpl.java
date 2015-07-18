package services;
import domain.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

    /* String filename = "id"
     * getInputStream()
     * getOutputStream()
     * getSize()
     * create()
     * retrieve()
     * update()
     * delete()
     * getId()
     * getIdTable()
     * getTaskName()
     * getDueDate()
     */

public class TaskSvcSioImpl implements ITaskSvc {
    
    private final String filename = "somefilename";
    private ObjectInputStream getInputStream() throws Exception{
        return new ObjectInputStream(new FileInputStream (filename));
    }
    private ObjectOutputStream getOutputStream() throws Exception{
        return new ObjectOutputStream(new FileOutputStream (filename));
    }
    
    @Override
    public int getSize() throws TaskSvcException {
        int size = 0;
        try {
            ObjectInputStream ois = getInputStream();
            List<Task> tasks = (List<Task>)ois.readObject();
            size = tasks.size();
            ois.close();
            return size;
        } catch (Exception e){
            throw new TaskSvcException("Error in Services: TaskSvcSioImpl: getSize", e);
        }
        //System.out.println(size);
        //return 0;
    }
    
    @Override
    public Task create (Task task) throws TaskSvcException {
        try{
            List<Task> tasks = new ArrayList<Task>();
            try {
                ObjectInputStream ois = getInputStream();
                tasks = (List<Task>)ois.readObject();
                ois.close();
            } catch (Exception e){ 
                System.out.println("File Created Successfully");
            //If Exception is caught, it means that this file is completely new and was able to be created.
            }
            
            if (tasks == null) {
                tasks = new ArrayList<Task>();
            }
            
            tasks.add(task);
            ObjectOutputStream oos = getOutputStream();
            oos.writeObject(tasks);
            oos.flush();
            oos.close();
        } catch (Exception e){
            throw new TaskSvcException("Error in Services: TaskSvcSioImpl: create: Cannot Create File", e);
        }
        return task;
    }
       
    @Override
    public Task retrieve(int id) throws TaskSvcException {
        //Task task = null;
        try {
            ObjectInputStream ois = getInputStream ();
            List<Task> tasks = (List<Task>)ois.readObject();
            ois.close();
            Iterator<Task> iterator = tasks.iterator();
            while (iterator.hasNext()){
                Task task = iterator.next();
                if (task.getId() == id){
                    return task;
                }
            }
        } catch (Exception e){
            throw new TaskSvcException("Error in Services: TaskSvcSioImpl: retrieve: File Does Not Exist", e);
        }
        return null;
    }
 
    @Override
    public Task update (Task task) throws TaskSvcException {
        /* 1) Find the file
         * 2) If found, update
         * 3) Else, throw exception
         */
        try {
            ObjectInputStream ois = getInputStream ();
            List<Task> tasks = (List<Task>)ois.readObject();
            ois.close();
            int id = task.getId();
            Iterator<Task> iterator = tasks.iterator();
            while (iterator.hasNext()){
                Task t = iterator.next();
                if (task.getId() == id){
                    tasks.add(task);
                    tasks.remove(t);
                    break;
                }
            }
            ObjectOutputStream oos = getOutputStream();
            oos.writeObject(tasks);
            oos.flush();
            oos.close();
        } catch (Exception e){
            throw new TaskSvcException("Error in Services: TaskSvcSioImpl: update: File Does Not Exist", e);
        }
        return task;
    }
   
    @Override
    public Task delete (Task task) throws TaskSvcException {
        //int numberOfTask = getSize();
        try {
            ObjectInputStream ois = getInputStream ();
            List<Task> tasks = (List<Task>)ois.readObject();
            ois.close();
            //int size = tasks.size();
            tasks.remove(task);
            //int updatesize = tasks.size();
            ObjectOutputStream oos = getOutputStream();
            oos.writeObject(tasks);
            oos.flush();
            oos.close();
        } catch (Exception e){
            throw new TaskSvcException("Error in Services: TaskSvcSioImpl: delete: File Does Not Exist", e);
        }
        return task;
    }
    
    @Override
    public int deleteRow (int selectedModelRow) throws TaskSvcException {
        //int numberOfTask = getSize();
        int id = 0;
        Task task = new Task();
        try {
            ObjectInputStream ois = getInputStream ();
            List<Task> tasks = (List<Task>)ois.readObject();
            ois.close();
            Iterator<Task> iterator = tasks.iterator();
            while (iterator.hasNext()){
                task = iterator.next();
                if (task.getId() == id){
                    tasks.remove(task);
                }
            }
            ObjectOutputStream oos = getOutputStream();
            oos.writeObject(tasks);
            oos.flush();
            oos.close();
        } catch (Exception e){
            throw new TaskSvcException("Error in Services: TaskSvcSioImpl: delete: File Does Not Exist", e);
        }
        return selectedModelRow;
    }
    
    @Override
    public int[] getIdTable() throws TaskSvcException{
        //Task task = new Task();
        int[] idNumber;
        int i = 0;
        try{
            ObjectInputStream ois = getInputStream();
            List<Task> tasks = (List<Task>) ois.readObject();
            ois.close();
            int result = tasks.size();
            idNumber = new int [result];
            Iterator<Task> iterator = tasks.iterator();
            while(iterator.hasNext()){
                Task task = iterator.next();
                idNumber[i] = task.getId();
                i++;
            }
        } catch(Exception e){
            throw new TaskSvcException("Error in Services: TaskSvcSioImpl: getIdTable: Cannot find Id Number", e);
        }
        return idNumber;
    }
    
    @Override
    public String[] getTaskName() throws TaskSvcException {
        String[] taskName;
        int i = 0;
        try{
            ObjectInputStream ois = getInputStream();
            List<Task> tasks = (List<Task>) ois.readObject();
            ois.close();
            int result = tasks.size();
            taskName = new String[result];
            Iterator<Task> iterator = tasks.iterator();
            while(iterator.hasNext()){
                Task task = iterator.next();
                taskName[i] = task.getTaskName();
                i++;
            }
        } catch(Exception e){
            throw new TaskSvcException("Error in Services: TaskSvcSioImpl: getTaskName: Cannot find Task Name", e);
        }
        return taskName;    
    }

    @Override
    public Date[] getDueDate() throws TaskSvcException {
        Date[] dueDate;
        int i = 0;
        try{
            ObjectInputStream ois = getInputStream();
            List<Task> tasks = (List<Task>) ois.readObject();
            ois.close();
            int result = tasks.size();
            dueDate = new Date[result];
            Iterator<Task> iterator = tasks.iterator();
            while(iterator.hasNext()){
                Task task = iterator.next();
                dueDate[i] = task.getDueDate();
                i++;
            }
        } catch(Exception e){
            throw new TaskSvcException("Error in Services: TaskSvcSioImpl: getDueDate: Cannot find Date", e);
        }
        return dueDate;    
    }
    
    @Override
    public int getIteration() throws TaskSvcException {
        int iter = 0;
        try {
            ObjectInputStream ois = getInputStream();
            List<Task> tasks = (List<Task>)ois.readObject();
            iter = tasks.size();
            ois.close();
            int iteration = 0;
            iteration = (++iter);
            return iteration;
        } catch (Exception e){
            throw new TaskSvcException("Error in Services: TaskSvcSioImpl: getSize", e);
        }
    }
}
