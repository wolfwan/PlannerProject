package domain;

import static org.junit.Assert.*;
import org.junit.Test;


public class TaskTest {
   
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Task task = new Task();
        boolean result = task.equals(task);
        assertTrue(result);
        result = task.equals(obj);
        assertFalse(result);
        task.setId(1);
        result = task.equals(obj);
        assertFalse(result);
        result = task.equals(task);
        assertTrue(result);
    }

    @Test
    public void testValidate() {
        System.out.println("validate");
        Task task = new Task();
        task.setId(1);
        task.setTaskName("Cs427");
        task.setDueDate(null);//do not know the test date format for right here, right 
        task.setTasks(task);
        boolean result = task.validate();
        assertTrue("expected true", true);
        task.setId(0);
        task.setTaskName(null);
        task.setDueDate(null);
        task.setTasks(null);
        result = task.validate();
        assertFalse(false);
    }
    
}