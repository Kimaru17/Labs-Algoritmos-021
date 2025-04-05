package controller;

import domain.Course;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.Student;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class CourseContainsController
{
    @javafx.fxml.FXML
    private TextField tf_courseId;
    @javafx.fxml.FXML
    private BorderPane bp;
    //defino la lista enlazada interna
    private DoublyLinkedList courseList;
    private Alert alert; //para el manejo de alertas

    @javafx.fxml.FXML
    public void initialize() {
        //cargamos la lista general
        this.courseList = util.Utility.getCourseList();
        alert = util.FXUtility.alert("Course List", "Contains Course");
    }

    @javafx.fxml.FXML
    public void containsOnAction(ActionEvent actionEvent) throws ListException {
        if (isValid()){
            Course course = new Course(
                    this.tf_courseId.getText()
            );

            alert.setContentText(this.courseList.contains(course)
                    ?"The course exist in the list"
                    :"The course doesn't exist"
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
        this.tf_courseId.setText("");
    }

    @javafx.fxml.FXML
    public void closeOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "course.fxml", bp);
    }

    //verificar que la lista y el tf no esten vacios
    private boolean isValid(){
        return !(this.tf_courseId.getText().isEmpty() && courseList.isEmpty());
    }

}