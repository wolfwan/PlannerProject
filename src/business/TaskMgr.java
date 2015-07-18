package business;
import domain.*;
import java.util.Date;
import services.*;

public class TaskMgr extends MgrAbs {
    public int getSize() throws TaskSvcException{
        /*Factory fac = new Factory();
        ITaskSvc its = fac.getTaskSvc();
        return its.getSize();*/
        return factory.getTaskSvc().getSize();
    }
    
    public Task create(Task task) throws TaskSvcException{
        /*Factory fac = new Factory();
        ITaskSvc its = fac.getTaskSvc();
        return its.create(task);*/
        return factory.getTaskSvc().create(task);
    }
    
    public Task retrieve(int id) throws TaskSvcException{
        /*Factory fac = new Factory();
        ITaskSvc its = fac.getTaskSvc();
        return its.retrieve(id);*/
        return factory.getTaskSvc().retrieve(id);
    }
    
    public Task update(Task task) throws TaskSvcException{
        /*Factory fac = new Factory();
        ITaskSvc its = fac.getTaskSvc();
        return its.update(task);*/
        return factory.getTaskSvc().update(task);
    }
    
    public Task delete(Task task) throws TaskSvcException{
        /*Factory fac = new Factory();
        ITaskSvc its = fac.getTaskSvc();
        return its.delete(task);*/
        return factory.getTaskSvc().delete(task);
    }
    
    public int deleteRow(int selectedModelRow) throws TaskSvcException{
        return factory.getTaskSvc().deleteRow(selectedModelRow);
    }
    
    public int[] getIdTable() throws TaskSvcException{
        /*Factory fac = new Factory();
        ITaskSvc its = fac.getTaskSvc();
        return its.getIdTable();*/
        return factory.getTaskSvc().getIdTable();
    }
    
    public String[] getTaskName() throws TaskSvcException{
        /*Factory fac = new Factory();
        ITaskSvc its = fac.getTaskSvc();
        return its.getTaskName();*/
        return factory.getTaskSvc().getTaskName();
    }
    
    public Date[] getDueDate() throws TaskSvcException{
        /*Factory fac = new Factory();
        ITaskSvc its = fac.getTaskSvc();
        return its.getDueDate();*/
        return factory.getTaskSvc().getDueDate();
    }

    public int getIteration() throws TaskSvcException{
        return factory.getTaskSvc().getIteration();
    }
}
