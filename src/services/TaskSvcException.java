package services;

public class TaskSvcException extends Exception {
    public TaskSvcException(String msg, Exception e){
        super(msg, e);
    }
}
