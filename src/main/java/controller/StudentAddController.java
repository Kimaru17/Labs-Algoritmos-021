package controller;

import domain.ListException;
import domain.SinglyLinkedList;
import domain.Student;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class StudentAddController
{
    @javafx.fxml.FXML
    private TextField tf_address;
    @javafx.fxml.FXML
    private TextField tf_name;
    @javafx.fxml.FXML
    private TextField tf_age;
    @javafx.fxml.FXML
    private TextField tf_studentId;
    @javafx.fxml.FXML
    private BorderPane bp;
    //defino la lista enlazada interna
    private SinglyLinkedList studentList;
    private Alert alert; //para el manejo de alertas

    @javafx.fxml.FXML
    public void initialize() {
        //cargamos la lista general
        this.studentList = util.Utility.getStudentList();
        alert = util.FXUtility.alert("Student List", "Add Student");
    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) throws ListException {
        if (StudentIsValid()){
            Student student = new Student(
                    this.tf_studentId.getText(),
                    this.tf_name.getText(),
                    Integer.parseInt(this.tf_age.getText()),
                    this.tf_address.getText()
            );

            this.studentList.add(student);
            util.Utility.setStudentList(this.studentList);

            alert.setContentText("The student was added successfully");
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.showAndWait();

        } else{
            alert.setContentText("The student can't be added successfully");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }

    }

    @javafx.fxml.FXML
    public void cleanOnAction(ActionEvent actionEvent) {
        this.tf_studentId.setText("");
        this.tf_name.setText("");
        this.tf_age.setText("");
        this.tf_address.setText("");
    }

    @javafx.fxml.FXML
    public void closeOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "student.fxml", bp);
    }

    //verificar que no falte ningun dato sea correcto y que no haya otro estudiante con ese Id
    private boolean StudentIsValid() throws ListException {
        if (!studentList.isEmpty()){
            return !(this.tf_studentId.getText().isEmpty()) && !(this.tf_name.getText().isEmpty())
                    && !(this.tf_age.getText().isEmpty()) && util.Utility.isInteger(this.tf_age.getText())
                    && !(this.tf_address.getText().isEmpty()) && studentList.contains(this.tf_studentId.getText());
        }
        return !(this.tf_studentId.getText().isEmpty()) && !(this.tf_name.getText().isEmpty())
                && !(this.tf_age.getText().isEmpty()) && util.Utility.isInteger(this.tf_age.getText())
                && !(this.tf_address.getText().isEmpty()) ;
    }
}