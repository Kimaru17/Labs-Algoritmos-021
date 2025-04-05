
package controller;

import domain.DoublyLinkedList;
import domain.ListException;
import domain.Register;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class RegisterContainsController
{
    @javafx.fxml.FXML
    private TextField tf_registrationId;
    @javafx.fxml.FXML
    private BorderPane bp;
    private DoublyLinkedList registerList;
    private Alert alert; //para el manejo de alertas

    @javafx.fxml.FXML
    public void initialize() {
        //cargamos la lista general
        this.registerList = util.Utility.getRegisterList();
        alert = util.FXUtility.alert("Register List", "Contains Registration");
    }

    @javafx.fxml.FXML
    public void containsOnAction(ActionEvent actionEvent) throws ListException {
        if (isValid()){
            Register register = new Register(
                    Integer.parseInt(this.tf_registrationId.getText())
            );

            alert.setContentText(this.registerList.contains(register)
                    ?"The registration exist in the list"
                    :"The registration doesn't exist"
            );
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.showAndWait();

        } else{
            alert.setContentText("There was an error searching. Enter a new number");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void cleanOnAction(ActionEvent actionEvent) {
        this.tf_registrationId.setText("");
    }

    @javafx.fxml.FXML
    public void closeOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "register.fxml", bp);
    }

    //verificar que la lista y el tf no esten vacios
    private boolean isValid(){
        return !(this.tf_registrationId.getText().isEmpty() && registerList.isEmpty()) && util.Utility.isInteger(this.tf_registrationId.getText());
    }

}
