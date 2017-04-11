package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import project.model.Developer;
import project.model.Director;
import project.model.Employee;
import project.model.EmployeeList;

import java.io.IOException;
import java.util.ArrayList;

public class ShowDirectorController {
    @FXML
    private Label employeePositionField;
    @FXML
    private Label employeeNameField;
    @FXML
    private Label employeeSurnameField;
    @FXML
    private Label employeeSalaryField;
    @FXML
    private Label directorManagedCompaniesField;
    @FXML
    private Button buttonBackToMainFromList;

    private ArrayList<Employee> employeeList = EmployeeList.getInstance().getEmployeeList();
    private int positionInArray;

    @FXML
    private void initialize() {
        positionInArray = EmployeeList.getInstance().getIterator();
        Director director = (Director) employeeList.get(positionInArray);
        employeePositionField.setText(director.getPosition());
        employeeNameField.setText(director.getName());
        employeeSurnameField.setText(director.getSurname());
        employeeSalaryField.setText(director.getSalary());
        directorManagedCompaniesField.setText(director.getManagedCompanies());
    }

    public void onNextEmployeeInListClick(ActionEvent actionEvent) throws IOException {
        if (employeeList.size() <= ++positionInArray) {
            // List is empty or ended
            Stage stage = (Stage) buttonBackToMainFromList.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/mainMenu.fxml"));
            stage.setScene(new Scene(root, 400, 500));
            return;
        }
        EmployeeList.getInstance().setIterator(positionInArray);
        if (employeeList.get(positionInArray) instanceof Developer) {
            Stage stage = (Stage) buttonBackToMainFromList.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/employeeDeveloper.fxml"));
            stage.setScene(new Scene(root, 400, 500));
        } else {
            Stage stage = (Stage) buttonBackToMainFromList.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/employeeDirector.fxml"));
            stage.setScene(new Scene(root, 400, 500));
        }
    }

    public void onBackToMainMenuClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonBackToMainFromList.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/mainMenu.fxml"));
        stage.setScene(new Scene(root, 400, 500));
    }
}
