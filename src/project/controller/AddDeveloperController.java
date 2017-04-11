package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.model.Developer;
import project.model.Employee;
import project.model.EmployeeList;

import java.io.IOException;
import java.util.ArrayList;

public class AddDeveloperController {
    @FXML
    private TextField employeeNameTextField;
    @FXML
    private TextField employeeSurnameTextField;
    @FXML
    private TextField employeeSalaryTextField;
    @FXML
    private TextField developerExperienceTextField;

    @FXML
    private Button buttonBackToMainFromAdd;

    private ArrayList<Employee> employeeList = EmployeeList.getInstance().getEmployeeList();

    public void onAddEmployeeInListClick(ActionEvent actionEvent) throws IOException {
        // Add employee to list
        Developer developer = new Developer();
        developer.setPosition("Developer");
        developer.setName(employeeNameTextField.getText());
        developer.setSurname(employeeSurnameTextField.getText());
        developer.setSalary(employeeSalaryTextField.getText());
        developer.setYearsOfExperience(developerExperienceTextField.getText());
        employeeList.add(developer);
        // Go back to project.view menu
        Stage stage = (Stage) buttonBackToMainFromAdd.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/mainMenu.fxml"));
        stage.setScene(new Scene(root, 400, 500));
    }

    public void onBackToMainMenuFromAddClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonBackToMainFromAdd.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/mainMenu.fxml"));
        stage.setScene(new Scene(root, 400, 500));
    }
}
