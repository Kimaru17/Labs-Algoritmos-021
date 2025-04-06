package controller;

import domain.Course;
import domain.DoublyLinkedList;
import domain.ListException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class CourseAddSortedController
{
    @FXML
    private TextField tf_courseId;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_credits;
    @FXML
    private BorderPane bp;
    //defino la lista enlazada interna
    private DoublyLinkedList courseList;
    private Alert alert; //para el manejo de alertas

    @FXML
    public void initialize() {
        //cargamos la lista general
        this.courseList = util.Utility.getCourseList();
        alert = util.FXUtility.alert("Course List", "Add Sorted Course");
    }

    @FXML
    public void cleanOnAction(ActionEvent actionEvent) {
        this.tf_courseId.setText("");
        this.tf_name.setText("");
        this.tf_credits.setText("");
    }

    @FXML
    public void closeOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "course.fxml", bp);
    }

    @FXML
    public void addSortedOnAction(ActionEvent actionEvent) throws ListException {
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
                    Integer.parseInt(this.tf_credits.getText()));

            if (courseList.isEmpty()){
                this.courseList.add(course);
                alert.setContentText("The course was added successfully");
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.showAndWait();
            }else {
                this.courseList.sort();
                this.courseList.addInSortedList(course);
                util.Utility.setCourseList(this.courseList);
                alert.setContentText("The course was added successfully");
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.showAndWait();
            }

        } else{
            alert.setContentText("The course can't be added successfully");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    //verificar que no falte ningun dato en los tf
    private boolean CourseIsValid() throws ListException {
        return !(this.tf_courseId.getText().isEmpty()) && !(this.tf_name.getText().isEmpty())
                && !(this.tf_credits.getText().isEmpty()) && util.Utility.isInteger(this.tf_credits.getText());
    }

}