
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
        try {
            if (isValid()) {
                Object current = registerList.getObject(Integer.parseInt(this.tf_registrationId.getText()));
                Object next = registerList.getNext(current);

                if (next != null) {
                    alert.setContentText("The next registration in the list is: \n" + next);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                } else {
                    alert.setContentText("There is no next registration.");
                    alert.setAlertType(Alert.AlertType.WARNING);
                }
                alert.showAndWait();
            }
        } catch (ListException e) {
            alert.setContentText("The registration list doesn't exist or there's an error accessing it.");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        } catch (NullPointerException e) {
            alert.setContentText("This is the last registration in the list.");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    //verificar que la lista y el tf no esten vacios
    private boolean isValid(){
        return !(this.tf_registrationId.getText().isEmpty() && registerList.isEmpty()) && util.Utility.isInteger(this.tf_registrationId.getText());
    }

}
