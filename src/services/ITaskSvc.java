package services;
import domain.*;
import java.util.Date;

    /* getSize()       <int>
     * create()        <Task>
     * retrieve()      <Task>
     * update()        <Task>
     * delete()        <Task>
     * getId()         <int>
     * getIdTable()    <int[]>
     * getTaskName()   <String[]>
     * getDueDate()    <Date[]>
     */

public interface ITaskSvc {
    public int getSize () throws TaskSvcException;
    public Task create (Task task) throws TaskSvcException;
    public Task retrieve (int id) throws TaskSvcException;
    public Task update (Task task) throws TaskSvcException;
    public Task delete (Task task) throws TaskSvcException;
    public int deleteRow (int selectedModelRow) throws TaskSvcException;
    public int[] getIdTable () throws TaskSvcException;
    public String[] getTaskName () throws TaskSvcException;
    public Date[] getDueDate () throws TaskSvcException;
    public int getIteration () throws TaskSvcException;
}
