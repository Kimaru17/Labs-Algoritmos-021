package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class RegisterController
{
    @javafx.fxml.FXML
    private TableColumn idTableColumn;
    @javafx.fxml.FXML
    private TableColumn studentIdTableColumn;
    @javafx.fxml.FXML
    private TableColumn creditsTableColumn;
    @javafx.fxml.FXML
    private TableColumn studentNameTableColumn;
    @javafx.fxml.FXML
    private TableColumn courseIdTableColumn;
    @javafx.fxml.FXML
    private TableColumn dateTableColumn;
    @javafx.fxml.FXML
    private TableView courseRegistrationTableview;
    @javafx.fxml.FXML
    private TableColumn courseNameTableColumn;
    @javafx.fxml.FXML
    private BorderPane bp;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @Deprecated
    public void addFirstOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void removeOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "registerRemove.fxml", bp);
    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "registerAdd.fxml", bp);
    }

    @Deprecated
    public void addSortedOnAction(ActionEvent actionEvent) {
    }

    @Deprecated
    public void getFirstOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void removeFirstOnAction(ActionEvent actionEvent) {
    }

    @Deprecated
    public void getLastOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void containsOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "registerContains.fxml", bp);
    }

    @javafx.fxml.FXML
    public void sizeOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void sortByStudentOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void getPrevOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void sortByIdOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void getNextOnAction(ActionEvent actionEvent) {
    }
}