package controller;

import domain.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.time.LocalDateTime;

public class RegisterController
{
    @javafx.fxml.FXML
    private TableColumn <Register, Integer> idTableColumn;
    @javafx.fxml.FXML
    private TableColumn <Register, String> studentIdTableColumn;
    @javafx.fxml.FXML
    private TableColumn <Register, Integer> creditsTableColumn;
    @javafx.fxml.FXML
    private TableColumn <Register, String> studentNameTableColumn;
    @javafx.fxml.FXML
    private TableColumn <Register, String> courseIdTableColumn;
    @javafx.fxml.FXML
    private TableColumn <Register, LocalDateTime> dateTableColumn;
    @javafx.fxml.FXML
    private TableView <Register> courseRegistrationTableview;
    @javafx.fxml.FXML
    private TableColumn <Register, String> courseNameTableColumn;
    @javafx.fxml.FXML
    private BorderPane bp;
    //defino la lista enlazada interna
    private DoublyLinkedList registerList;
    private Alert alert; //para el manejo de alertas

    @javafx.fxml.FXML
    public void initialize() {
        //cargamos la lista general
        this.registerList = util.Utility.getRegisterList();
        alert = util.FXUtility.alert("Register List", "Display Register");
        alert.setAlertType(Alert.AlertType.ERROR);
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        studentIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("Student Id"));
        creditsTableColumn.setCellValueFactory(new PropertyValueFactory<>("Credits"));
        studentNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Student Name"));
        courseIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("Course Id"));
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        courseNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Course Name"));
        try{
            if(registerList!=null && !registerList.isEmpty()){
                for(int i=1; i<=registerList.size(); i++) {
                    courseRegistrationTableview.getItems().add((Register) registerList.getNode(i).data);
                }
            }
            //this.courseRegistrationTableView.setItems(observableList);
        }catch(ListException ex){
            alert.setContentText("Course list is empty");
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
        this.registerList.clear();
        util.Utility.setRegisterList(this.registerList); //actualizo la lista general
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
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "registerRemove.fxml", bp);
    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "registerAdd.fxml", bp);
    }

    @javafx.fxml.FXML
    public void removeFirstOnAction(ActionEvent actionEvent) throws ListException {
        if (tableViewIsNotEmpty() ){
            if (registerList.size()==1){
                registerList.clear();
                courseRegistrationTableview.getItems().clear();
                initialize();
            }
            else {
                registerList.removeFirst();
                courseRegistrationTableview.getItems().clear();
                initialize();
            }
        } else {
            alert.setContentText("The registration list is empty");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void containsOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "registerContains.fxml", bp);
    }

    @javafx.fxml.FXML
    public void sizeOnAction(ActionEvent actionEvent) throws ListException {
        if(tableViewIsNotEmpty()) {
            alert.setContentText("The size of the registration list is: \n" + registerList.size());
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.showAndWait();
        }
        else {
            alert.setContentText("The registration list is empty");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void sortByStudentOnAction(ActionEvent actionEvent) throws ListException {
        if(tableViewIsNotEmpty()) {

            this.registerList.sort();
            util.Utility.setRegisterList(this.registerList);
            alert.setContentText("The registration list was sorted by the student name");
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.showAndWait();
        }
        else {
            alert.setContentText("The registration list is empty");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void getPrevOnAction(ActionEvent actionEvent) {
        if(tableViewIsNotEmpty()) {
            alert.setContentText("The last student in the list is: \n" + registerList.getPrev());
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.showAndWait();
        }
        else {
            alert.setContentText("The student list is empty");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void sortByIdOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void getNextOnAction(ActionEvent actionEvent) {
    }

    // verificacion de contenido en el TableView
    private boolean tableViewIsNotEmpty(){
        return !(this.courseRegistrationTableview.getItems().isEmpty());
    }

    private void updateTableView() throws ListException {
        this.courseRegistrationTableview.getItems().clear(); //clear table
        this.registerList = util.Utility.getCourseList(); //cargo la lista
        if(registerList!=null && !registerList.isEmpty()){
            for(int i=1; i<=registerList.size(); i++) {
                this.courseRegistrationTableview.getItems().add((Register)registerList.getNode(i).data);
            }
        }
    }

}