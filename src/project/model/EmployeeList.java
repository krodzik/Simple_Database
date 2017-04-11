package project.model;


import java.util.ArrayList;

public class EmployeeList {
    private ArrayList<Employee> employeeList = new ArrayList<>();

    public int getIterator() {
        return iterator;
    }

    public void setIterator(int iterator) {
        this.iterator = iterator;
    }

    private int iterator = 0;

    private static EmployeeList instance = new EmployeeList();

    public static EmployeeList getInstance() {
        if(instance == null) {
            instance = new EmployeeList();
        }
        return instance;
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    private EmployeeList() {
    }
}
