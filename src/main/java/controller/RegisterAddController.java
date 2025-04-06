
package controller;

import domain.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class RegisterAddController
{
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TextField tf_registerId;
    private DoublyLinkedList registerList;
    private Alert alert; //para el manejo de alertas
    @javafx.fxml.FXML
    private DatePicker date;
    @javafx.fxml.FXML
    private ChoiceBox courseCB;
    @javafx.fxml.FXML
    private ChoiceBox studentCB;

    @javafx.fxml.FXML
    public void initialize() {
        //cargamos la lista general
        this.registerList = util.Utility.getRegisterList();
        alert = util.FXUtility.alert("Register List", "Add Register");
        loadStudentsToChoiceBox();
        loadCoursesToChoiceBox();
    }

    private void loadStudentsToChoiceBox() {
        studentCB.getItems().clear();
        studentCB.getItems().clear();

        try {
            SinglyLinkedList studentList = util.Utility.getStudentList();

            for (int i = 1; i <= studentList.size(); i++) {
                Student s = (Student) studentList.getNode(i).data;
                studentCB.getItems().add(s);
            }
        } catch (ListException e) {
            System.out.println("Error al cargar estudiantes: " + e.getMessage());
        }
    }

    private void loadCoursesToChoiceBox() {
        courseCB.getItems().clear();

        try {
            DoublyLinkedList courseList = util.Utility.getCourseList();

            for (int i = 1; i <= courseList.size(); i++) {
                Course c = (Course) courseList.getNode(i).data;
                courseCB.getItems().add(c);
            }
        } catch (ListException e) {
            System.out.println("Error al cargar cursos: " + e.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) throws ListException {
        if (RegisterIsValid()){
            Student selectedStudent = (Student) studentCB.getValue();
            Course selectedCourse = (Course) courseCB.getValue();
            int registerId = Integer.parseInt(this.tf_registerId.getText());
            boolean idExists  = false;
            if (!registerList.isEmpty()){
                for (int i = 1; i <= registerList.size(); i++) {
                    Register existingRegister = (Register) registerList.getNode(i).data;
                    if (existingRegister.getId() == registerId) {
                        idExists = true;
                        break;
                    }
                }
            }
            if (idExists) {
                alert.setContentText("The register Id is already registered ");
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.showAndWait();
                return;
            }
            Register register = new Register(
                    Integer.parseInt(tf_registerId.getText()),
                    LocalDateTime.of(date.getValue(), LocalTime.MIDNIGHT),
                    selectedStudent.getId(),
                    selectedStudent.getName(),
                    selectedCourse.getId(),
                    selectedCourse.getName(),
                    selectedCourse.getCredits()
            );

            this.registerList.add(register);
            util.Utility.setRegisterList(this.registerList);
            alert.setContentText("The registration was added successfully");
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.showAndWait();
        } else{
            alert.setContentText("The registration can't be added successfully");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void cleanOnAction(ActionEvent actionEvent) {
        this.tf_registerId.setText("");
        date.setValue(null);
        studentCB.getSelectionModel().clearSelection();
        courseCB.getSelectionModel().clearSelection();
    }

    @javafx.fxml.FXML
    public void closeOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "register.fxml", bp);
    }

    //verificar que no falte ningun dato en los tf
    private boolean RegisterIsValid() throws ListException {
        return !this.tf_registerId.getText().isEmpty()
                && util.Utility.isInteger(this.tf_registerId.getText())
                && this.date.getValue() != null
                && this.studentCB.getValue() != null
                && this.courseCB.getValue() != null;
    }

}
