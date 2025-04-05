package controller;

import domain.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class CourseController
{
    @javafx.fxml.FXML
    private TableColumn <Course, String> idTableColumn;
    @javafx.fxml.FXML
    private TableColumn <Course, String> nameTableColumn;
    @javafx.fxml.FXML
    private TableColumn <Course, Integer> creditsTableColumn;
    @javafx.fxml.FXML
    private TableView <Course> courseTableview;
    @javafx.fxml.FXML
    private BorderPane bp;
    //defino la lista enlazada interna
    private DoublyLinkedList courseList;
    private Alert alert; //para el manejo de alertas

    @javafx.fxml.FXML
    public void initialize() {
        //cargamos la lista general
        this.courseList = util.Utility.getCourseList();
        alert = util.FXUtility.alert("Course List", "Display Course");
        alert.setAlertType(Alert.AlertType.ERROR);
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        creditsTableColumn.setCellValueFactory(new PropertyValueFactory<>("Credits"));
        try{
            if(courseList!=null && !courseList.isEmpty()){
                for(int i=1; i<=courseList.size(); i++) {
                    courseTableview.getItems().add((Course) courseList.getNode(i).data);
                }
            }
            //this.courseTableView.setItems(observableList);
        }catch(ListException ex){
            alert.setContentText("Course list is empty");
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void addFirstOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "courseAddFirst.fxml", bp);
    }

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
        this.courseList.clear();
        util.Utility.setCourseList(this.courseList); //actualizo la lista general
        this.alert.setContentText("The list was deleted");
        this.alert.setAlertType(Alert.AlertType.INFORMATION);
        this.alert.showAndWait();
        try {
            updateTableView(); //actualiza el contenido del tableview
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void removeOnAction(ActionEvent actionEvent) {
        if (tableViewIsNotEmpty()){
            util.FXUtility.loadPage("ucr.lab.HelloApplication", "courseRemove.fxml", bp);
        }else {
            alert.setContentText("The course list is empty");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "courseAdd.fxml", bp);
    }

    @javafx.fxml.FXML
    public void addSortedOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "courseAddSorted.fxml", bp);
    }

    @javafx.fxml.FXML
    public void getFirstOnAction(ActionEvent actionEvent) throws ListException {
        if(tableViewIsNotEmpty()) {
            alert.setContentText("The first course of the list is: \n" + courseList.getFirst());
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.showAndWait();
        }
        else {
            alert.setContentText("The course list is empty");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void removeFirstOnAction(ActionEvent actionEvent) throws ListException {
        if (tableViewIsNotEmpty() ){
            if (courseList.size()==1){
                courseList.clear();
                courseTableview.getItems().clear();
                initialize();
            }
            else {
                courseList.removeFirst();
                courseTableview.getItems().clear();
                initialize();
            }
        } else {
            alert.setContentText("The course list is empty");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void getLastOnAction(ActionEvent actionEvent) throws ListException {
        if(tableViewIsNotEmpty()) {
            alert.setContentText("The last course of the list is: \n" + courseList.getLast());
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.showAndWait();
        }
        else {
            alert.setContentText("The course list is empty");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void containsOnAction(ActionEvent actionEvent) {
        if (tableViewIsNotEmpty()){
            util.FXUtility.loadPage("ucr.lab.HelloApplication", "courseContains.fxml", bp);
        }else {
            alert.setContentText("The student list is empty");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void sizeOnAction(ActionEvent actionEvent) throws ListException {
        if(tableViewIsNotEmpty()) {
            alert.setContentText("The size of the courses list is: \n" + courseList.size());
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.showAndWait();
        }
        else {
            alert.setContentText("The course list is empty");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    private void updateTableView() throws ListException {
        this.courseTableview.getItems().clear(); //clear table
        this.courseList = util.Utility.getCourseList(); //cargo la lista
        if(courseList!=null && !courseList.isEmpty()){
            for(int i=1; i<=courseList.size(); i++) {
                this.courseTableview.getItems().add((Course)courseList.getNode(i).data);
            }
        }
    }

    // verificacion de contenido en el TableView
    private boolean tableViewIsNotEmpty(){
        return !(this.courseTableview.getItems().isEmpty());
    }

}