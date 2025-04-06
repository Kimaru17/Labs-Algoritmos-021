
package controller;

import domain.DoublyLinkedList;
import domain.ListException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class RegisterGetPrevController
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
        alert = util.FXUtility.alert("Register List", "Previous Registration");
    }

    @javafx.fxml.FXML
    public void getPrevOnAction(ActionEvent actionEvent) throws ListException {
        try {
            if (isValid()) {
                // Obtén el nodo actual
                Object current = registerList.getObject(Integer.parseInt(this.tf_registrationId.getText()));
                // Obtén el nodo previo
                Object prev = registerList.getPrev(current);

                // Verifica si el nodo previo existe
                if (prev != null) {
                    alert.setContentText("The previous registration in the list is: \n" + prev);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                } else {
                    // Si no hay previo (es el primer nodo)
                    alert.setContentText("There is no previous registration.");
                    alert.setAlertType(Alert.AlertType.WARNING);
                }
                alert.showAndWait();
            }
        } catch (ListException e) {
            // En caso de que no exista el nodo o haya un error
            alert.setContentText("The registration list doesn't exist or there's an error accessing it.");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        } catch (NullPointerException e) {
            // En caso de NullPointerException, si aux.prev es null
            alert.setContentText("This is the first registration in the list.");
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
