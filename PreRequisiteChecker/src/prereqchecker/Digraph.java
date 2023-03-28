package prereqchecker;

import java.util.*;

public class Digraph {
    private int V;
    private int E;
    private HashMap<String, Integer> key = new HashMap<String, Integer>();
    private boolean isVal = false;
    private ArrayList<Course> connections = new ArrayList<Course>();

    // reads in input file and creates digraph
    public Digraph(String inputFile) {
        StdIn.setFile(inputFile);
        this.V = Integer.parseInt(StdIn.readString());

        for (int i = 0; i < V; i++) {
            String word = StdIn.readString();
            key.put(word, i);

            Course course = new Course(word);
            connections.add(course);
        }

        this.E = Integer.parseInt(StdIn.readString());

        for (int i = 0; i < E; i++) {
            String word = StdIn.readString();
            String prereq = StdIn.readString();
            int k = key.get(word);

            connections.get(k).add(prereq);
        }
    }

    public void getOutput(String outputFile) {
        StdOut.setFile(outputFile);

        for (int i = 0; i < V; i++) {
            connections.get(i).getOutput();
        }
    }

// 2 prereq

    public boolean readPrereqFile(String pFile) {
        StdIn.setFile(pFile);
        String course1 = StdIn.readString();
        String course2 = StdIn.readString();

        return !isValidPrereq(course2, course1);
    }

    public boolean isValidPrereq(String course, String prereq) {
        Course c = connections.get(key.get(course));
        ArrayList<String> arr = c.getConnections();
        if (arr.isEmpty())
            return false;
        for (int i = 0; i < arr.size(); ++i) {
            String curr = arr.get(i);
            if (curr.equals(prereq))
                return true;
            if (isValidPrereq(arr.get(i), prereq))
            return true;       
        }
        return false;
    }

    // for eligible #3
    public void eligibleOut(String eFile, String oFile){
        ArrayList<String> taken = setEligibleArray(eFile);
        flagMultiple(taken);
        StdOut.setFile(oFile);

        for (Course c: connections){
            if (checkEligibility(c))
            StdOut.println(c.getID());
        }
    }

    public ArrayList<String> setEligibleArray(String eFile) {
        ArrayList<String> arr = new ArrayList<String>();
        StdIn.setFile(eFile);

        int n = StdIn.readInt(); 
        for (int i = 0; i<n; i++) {
            int k = key.get(StdIn.readString());
            arr.add(connections.get(k).getID());
        }
        return arr;
    }

    public void flagMultiple(ArrayList<String> arr) {
        for (String s: arr) {
            flag(s);
        }
    }

    public void flag(String course)
    {
        Course c = connections.get(key.get(course));
        c.setFlag(true);
        ArrayList<String> arr = c.getConnections();
        if (arr.isEmpty())
            return;
            for (int i = 0; i < arr.size(); ++i) {
                flag(arr.get(i));     
            }
        return;
    }

    public boolean checkEligibility(Course c){
        ArrayList<String> arr = c.getConnections();
        if (c.getFlag()==true)
        return false;
        for (String s: arr){
            if (connections.get(key.get(s)).getFlag()==false)
            return false;
        }
        return true;
    }
    // for need to take class
    
    public void needToTakeOutput(ArrayList<String> taken, String target, String outputFile){
        flagMultiple(taken);
        flagRequirements(target);
        connections.get(key.get(target)).setRequirement(false);

        StdOut.setFile(outputFile);

        for (Course c: connections){
            if (c.getRequirement()){
                StdOut.println(c.getID());
            }
        }
    }

    public void flagRequirements(String target){
        Course c = connections.get(key.get(target));
        if(c.getFlag()==false)
        c.setRequirement(true);
        ArrayList<String> arr = c.getConnections();
        if (arr.isEmpty())
            return;
            for (int i = 0; i < arr.size(); ++i) {
                flagRequirements(arr.get(i));     
            }
        return;
    }

}

