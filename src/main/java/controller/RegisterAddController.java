package controller;

import domain.DoublyLinkedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

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
        //to do, cargar los datos de la lista
    }

    private void loadCoursesToChoiceBox() {
        courseCB.getItems().clear();
        //to do, cargar los datos de la lista
    }
    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
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
}