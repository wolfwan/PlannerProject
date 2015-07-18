package business;

import domain.*;
import static org.junit.Assert.*;
import org.junit.Test;
import services.*;


public class TaskMgrTest {

    @Test
    public void testCreate() throws TaskSvcException {
        Task task = new Task();
        task.setTaskName("Cs 427");
        task.setDueDate(null);//do not know the test date format for right here, right 
        task.setId(1);
        TaskMgr tm = new TaskMgr();
        Task result = tm.create(task);
        assertNotNull (result);
        
    }

    @Test
    public void testRetrieve() throws TaskSvcException {
        Task task = new Task();
        task.setId(1);
        TaskMgr tm = new TaskMgr();
        Task result = tm.retrieve(1);
        assertNotNull(result);
        
    }

    @Test
    public void testUpdate() throws TaskSvcException {
        Task task = new Task();
        task.setTaskName("Cs 427");
        task.setDueDate(null);//do not know the test date format for right here, right 
        task.setId(1);
        TaskMgr tm = new TaskMgr();
        Task result = tm.update(task);
        assertNotNull (result);
        
    }

    @Test
    public void testDelete() throws TaskSvcException {
        Task task = new Task();
        task.setTaskName("Cs 427");
        task.setDueDate(null);//do not know the test date format for right here, right 
        task.setId(1);
        TaskMgr tm = new TaskMgr();
        Task result = tm.delete(task);
        assertNotNull (result);
        
    }
        
}