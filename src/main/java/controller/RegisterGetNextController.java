package controller;

import domain.DoublyLinkedList;
import domain.ListException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class RegisterGetNextController
{
    @javafx.fxml.FXML
    private TextField tf_registrationId;
    @javafx.fxml.FXML
    private BorderPane bp;
    //defino la lista enlazada interna
    private DoublyLinkedList registerList;
    private Alert alert; //para el manejo de alertas

    @javafx.fxml.FXML
    public void initialize() {
        //cargamos la lista general
        this.registerList = util.Utility.getRegisterList();
        alert = util.FXUtility.alert("Register List", "Next Registration");
    }

    @javafx.fxml.FXML
    public void cleanOnAction(ActionEvent actionEvent) {
        this.tf_registrationId.setText("");
    }

    @javafx.fxml.FXML
    public void closeOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "register.fxml", bp);
    }

    @javafx.fxml.FXML
    public void getNextOnAction(ActionEvent actionEvent) throws ListException {
        if (isValid()){
            alert.setContentText("The next registration in the list is: \n" + registerList.getNext(this.tf_registrationId.getText()));
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.showAndWait();
        } else{
            alert.setContentText("The registration list don't exists or theres no next registration.");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    //verificar que la lista y el tf no esten vacios
    private boolean isValid(){
        return !(this.tf_registrationId.getText().isEmpty() && registerList.isEmpty()) && util.Utility.isInteger(this.tf_registrationId.getText());
    }

}