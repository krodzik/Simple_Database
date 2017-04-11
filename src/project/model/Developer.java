package project.model;


import java.io.Serializable;

public class Developer extends Employee implements Serializable {
    private String yearsOfExperience;

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
