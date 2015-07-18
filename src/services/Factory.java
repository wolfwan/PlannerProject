package services;
    
public class Factory {
    public ITaskSvc getTaskSvc() {
        return new TaskSvcSioImpl();
    }
}
