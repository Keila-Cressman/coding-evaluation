package com.aa.act.interview.org;

import java.util.HashMap;
import java.util.Optional;

public abstract class Organization {

    private Position root;
    private Position empInfo;
    private int empID = 0;
    HashMap<String, String> empMap = new HashMap<String, String>();

    public Organization() {
        root = createOrganization();
    }
    
    protected abstract Position createOrganization();
    
    /**
     * hire the given person as an employee in the position that has that title
     * 
     * @param person
     * @param title
     * @return the newly filled position or empty if no position has that title
     */
    public Optional<Position> hire(Name person, String title) {
        //keep track of incoming employees
        empID++;
        //now we can create an Employee
        Employee emp = new Employee(empID, person);
        //store info in Position to set Employee
        empInfo = new Position(title, emp);
        //use map to search title
        empMap.put(title, (person.toString()));

        //returns position or empty
        return Optional.ofNullable(root);
    }

    @Override
    public String toString() {
        return printOrganization(root, empMap, "");
    }
    
    private String printOrganization(Position pos, HashMap<String, String> nm, String prefix) {
        StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() +": " + nm.get(pos.toString()) +"\n");
        for(Position p : pos.getDirectReports()) {
            sb.append(printOrganization(p,nm, prefix + "  "));   
        }
        return sb.toString();
    }
}
