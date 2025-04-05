
package controller;

import domain.ListException;
import domain.SinglyLinkedList;
import domain.Student;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class StudentContainsController
{

    @javafx.fxml.FXML
    private TextField tf_studentId;
    @javafx.fxml.FXML
    private BorderPane bp;
    private SinglyLinkedList studentList;
    private Alert alert; //para el manejo de alertas

    @javafx.fxml.FXML
    public void initialize() {
        //cargamos la lista general
        this.studentList = util.Utility.getStudentList();
        alert = util.FXUtility.alert("Student List", "Contains Student");
    }

    @javafx.fxml.FXML
    public void containsOnAction(ActionEvent actionEvent) throws ListException {
        if (isValid()){
            Student student = new Student(
                    this.tf_studentId.getText()
            );

            alert.setContentText(this.studentList.contains(student)
                    ?"The student exist in the list"
                    :"The student doesn't exist"
            );
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.showAndWait();

        } else{
            alert.setContentText("There was an error searching");
            alert.setAlertType(Alert.AlertType.ERROR);
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

    //verificar que la lista y el tf no esten vacios
    private boolean isValid(){
        return !(this.tf_studentId.getText().isEmpty() && studentList.isEmpty());
    }

}
