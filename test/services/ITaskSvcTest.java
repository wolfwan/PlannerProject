package services;

import domain.Task;
import static org.junit.Assert.*;
import org.junit.Test;

public class ITaskSvcTest {
    /* Reason for testing:
     * 1) Instantiate the target
     * 2) Instantiate supporting objects
     * 3) Invoke the target
     * 4) Assert Expectation
     */
    
    
    @Test
    public void testCreate() throws TaskSvcException {
        Factory fac = new Factory();
        ITaskSvc its = fac.getTaskSvc();
        Task task = new Task();
        task.setTaskName("Cs 427");
        task.setDueDate(null);//do not know the test date format for right here, right 
        task.setId(1);
        Task result = its.create(task);
        assertNotNull(result);
    }

    @Test
    public void testRetrieve() throws TaskSvcException {
        Factory fac = new Factory();
        ITaskSvc its = fac.getTaskSvc();
        Task task = new Task();
        task.setId(1);
        Task result = its.retrieve(1);
        assertNotNull(result);
    }

    @Test
    public void testUpdate() throws TaskSvcException {
        Factory fac = new Factory();
        ITaskSvc its = fac.getTaskSvc();
        Task task = new Task();
        task.setTaskName("Cs 427");
        task.setDueDate(null);//do not know the test date format for right here, right 
        task.setId(1);
        Task result = its.update(task);
        assertNotNull(result);
    }

    @Test
    public void testDelete() throws TaskSvcException {
        //not null
        Factory fac = new Factory();
        ITaskSvc its = fac.getTaskSvc();
        Task task = new Task();
        task.setTaskName("Cs 427");
        task.setDueDate(null);//do not know the test date format for right here, right 
        task.setId(1);
        int size = its.getSize();
        Task result = its.create(task);
        assertNotNull(result);
        int newsize = its.getSize();
        Task result2 = its.delete(task);
        assertNotNull(result2);
    }
        
}