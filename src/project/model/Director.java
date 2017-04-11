package project.model;


import java.io.Serializable;

public class Director extends Employee implements Serializable {
    private String managedCompanies;

    public String getManagedCompanies() {
        return managedCompanies;
    }

    public void setManagedCompanies(String managedCompanies) {
        this.managedCompanies = managedCompanies;
    }
}
