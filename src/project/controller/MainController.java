package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import project.model.Developer;
import project.model.Director;
import project.model.Employee;
import project.model.EmployeeList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainController {

    @FXML
    private Button buttonShowList;

    private ArrayList<Employee> employeeList = EmployeeList.getInstance().getEmployeeList();

    public void onShowListClick(ActionEvent actionEvent) throws IOException {
        if (employeeList.isEmpty()) {
            // List is empty
            return;
        }
        EmployeeList.getInstance().setIterator(0);
        if (employeeList.get(0) instanceof Developer) {
            Stage stage = (Stage) buttonShowList.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/employeeDeveloper.fxml"));
            stage.setScene(new Scene(root, 400, 500));
        } else {
            Stage stage = (Stage) buttonShowList.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/employeeDirector.fxml"));
            stage.setScene(new Scene(root, 400, 500));
        }
    }

    public void onAddClick(ActionEvent actionEvent) throws IOException {
        List<String> choices = new ArrayList<>();
        choices.add("Developer");
        choices.add("Director");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Developer", choices);
        dialog.setTitle("Add employee");
        dialog.setHeaderText("What type of employee you want to add?");
        dialog.setContentText("Choose position:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println("Your choice: " + result.get());
            if (result.get().equals("Developer")) {
                Stage stage = (Stage) buttonShowList.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("../view/addDeveloperForm.fxml"));
                stage.setScene(new Scene(root, 400, 500));
            } else {
                Stage stage = (Stage) buttonShowList.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("../view/addDirectorForm.fxml"));
                stage.setScene(new Scene(root, 400, 500));
            }
        }

        // Lambda
        /*result.ifPresent((s) -> {
            try {
                onAccept(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
    }

    /*void onAccept(String result) throws IOException {
        System.out.println("Your choice: " + result);
        if (result.equals("Developer")) {
            Stage stage = (Stage) buttonShowList.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/addDeveloperForm.fxml"));
            stage.setScene(new Scene(root, 400, 500));
        } else {
            Stage stage = (Stage) buttonShowList.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/addDirectorForm.fxml"));
            stage.setScene(new Scene(root, 400, 500));
        }
    };*/

    public void onSaveClick(ActionEvent actionEvent) throws IOException {
        TextInputDialog dialog = new TextInputDialog("backup");
        dialog.setHeaderText("Save employee database to file");
        dialog.setContentText("Please enter file name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println("File name: " + result.get());
            FileOutputStream fileOut = new FileOutputStream(result.get());
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            for (Employee employee : employeeList) {
                out.writeObject(employee);
                System.out.println("Saving employee: " + employee.getSurname());
            }

            out.close();
            fileOut.close();
        }
    }

    public void onLoadClick(ActionEvent actionEvent) throws IOException {
        TextInputDialog dialog = new TextInputDialog("backup");
        dialog.setHeaderText("Load employee database from file");
        dialog.setContentText("Please enter file name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println("File name: " + result.get());
            try (FileInputStream fileIn = new FileInputStream(result.get());
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {
                while (true) {
                    Employee employee = (Employee) in.readObject();

                    if (employee instanceof Developer) {
                        Developer developer = (Developer) employee;
                        employeeList.add(developer);
                    } else {
                        Director director = (Director) employee;
                        employeeList.add(director);
                    }
                    System.out.println("Loading employee: " + employee.getSurname());
                }
            } catch (EOFException ignored) {
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void onExitClick(ActionEvent actionEvent) {
        System.exit(0);
    }
}
