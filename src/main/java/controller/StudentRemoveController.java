package controller;

import domain.ListException;
import domain.SinglyLinkedList;
import domain.Student;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class StudentRemoveController
{

    @javafx.fxml.FXML
    private TextField tf_studentId;
    @javafx.fxml.FXML
    private BorderPane bp;
    //lista enlazada interna
    private SinglyLinkedList studentList;
    private Alert alert; //para el manejo de alertas

    @javafx.fxml.FXML
    public void initialize() {
        //cargamos la lista general
        this.studentList = util.Utility.getStudentList();
        alert = util.FXUtility.alert("Student List", "Remove Student");
    }

    @javafx.fxml.FXML
    public void removeOnAction(ActionEvent actionEvent) throws ListException {
        if (isValid()) {
            Student student = new Student(this.tf_studentId.getText());

            if (studentList.contains(student)) {
                studentList.remove(student);
                util.Utility.setStudentList(studentList);

                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("The student was removed successfully");
                alert.showAndWait();

                // Si está vacía, volver a la página principal
                if (studentList.isEmpty()) {
                    util.FXUtility.loadPage("ucr.lab.HelloApplication", "student.fxml", bp);
                }
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("The student is already deleted or doesn't exist");
                alert.showAndWait();
            }
        } else {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("Invalid student ID");
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void cleanOnAction(ActionEvent actionEvent) {
        this.tf_studentId.setText("");
    }

    @javafx.fxml.FXML
    public void closeOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "student.fxml", bp);
    }

    //verificador si la lista no este vacia y el tf tenga un valor
    private boolean isValid() throws ListException {
        return !(this.tf_studentId.getText().isEmpty() && studentList.isEmpty()) ;
    }
}