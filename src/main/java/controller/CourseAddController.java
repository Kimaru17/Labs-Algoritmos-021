
package controller;

import domain.Course;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.Student;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class CourseAddController
{
    @javafx.fxml.FXML
    private TextField tf_courseId;
    @javafx.fxml.FXML
    private TextField tf_name;
    @javafx.fxml.FXML
    private TextField tf_credits;
    @javafx.fxml.FXML
    private BorderPane bp;
    //defino la lista enlazada interna
    private DoublyLinkedList courseList;
    private Alert alert; //para el manejo de alertas

    @javafx.fxml.FXML
    public void initialize() {
        //cargamos la lista general
        this.courseList = util.Utility.getCourseList();
        alert = util.FXUtility.alert("Course List", "Add Course");
    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) throws ListException {
        if (CourseIsValid()){
            String courseId = this.tf_courseId.getText();
            boolean idExists = false;
            if (!courseList.isEmpty()) {
                for (int i = 1; i <= courseList.size(); i++) {
                    Course existingCourse = (Course) courseList.getNode(i).data;
                    if (existingCourse.getId().equals(courseId)) {
                        idExists = true;
                        break;
                    }
                }
            }
            if (idExists) {
                alert.setContentText("The course Id is already registered ");
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.showAndWait();
                return;
            }
            Course course = new Course(
                    this.tf_courseId.getText(),
                    this.tf_name.getText(),
                    Integer.parseInt(this.tf_credits.getText())
            );
            this.courseList.add(course);
            util.Utility.setCourseList(this.courseList);
            alert.setContentText("The course was added successfully");
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.showAndWait();
        } else{
            alert.setContentText("The course can't be added successfully");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
            }
    }

    @javafx.fxml.FXML
    public void cleanOnAction(ActionEvent actionEvent) {
        this.tf_courseId.setText("");
        this.tf_name.setText("");
        this.tf_credits.setText("");
    }

    @javafx.fxml.FXML
    public void closeOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "course.fxml", bp);
    }

    //verificar que no falte ningun dato en los tf
    private boolean CourseIsValid() throws ListException {
        return !(this.tf_courseId.getText().isEmpty()) && !(this.tf_name.getText().isEmpty())
                && !(this.tf_credits.getText().isEmpty()) && util.Utility.isInteger(this.tf_credits.getText());
    }

}
