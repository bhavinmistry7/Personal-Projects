package prereqchecker;

import java.util.*;

public class Course {
    private String id;
    private boolean flag;
    private boolean requirement;
    private ArrayList<String> prereqs = new ArrayList<String>();

    public Course(String id) {
        this.id = id;
        this.flag = false;
        this.requirement = false;
    }

    public void add(String id) {
        prereqs.add(id);
    }

    public String getID(){
        return id;
    }

    public void getOutput() {
        StdOut.print(id + " ");
        for (int i = 0; i < prereqs.size(); ++i) {
            StdOut.print(prereqs.get(i) + " ");
        }
        StdOut.println();
    }

    public ArrayList<String> getConnections(){
            return prereqs;
    }

    public void setFlag(boolean b){
        flag = b;
    }

    public boolean getFlag(){
        return flag;
    }

    public void setRequirement(boolean b){
        this.requirement = b;
    }

    public boolean getRequirement(){
        return requirement;
    }

}
