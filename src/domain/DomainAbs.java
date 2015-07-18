package domain;

public abstract class DomainAbs implements java.io.Serializable {
    
    protected int id = 0;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public boolean validate () {
        if (getId() <= 0){
            return false;
        }     
        return true;
    }
}
