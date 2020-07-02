package Homework15.task1.pojo;

public class StudentPOJO {

    private String name;
    private int securityLevel;


    public StudentPOJO(String name, int securityLevel) {
        setAge(securityLevel);
        setName(name);
    }

    public void setSecurityLevel(int securityLevel) {
        this.securityLevel = securityLevel;
    }
    public void setAge(int securityLevel) {
        this.securityLevel = securityLevel;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getSecurityLevel() {
        return securityLevel;
    }

    public String getName() {
        return name;
    }
}
