package controllers;

import dao.EnrollmentDAO;
import models.Enrollment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import java.util.List;

public class MainController {

    @FXML private TextField txtStudentId;
    @FXML private TextField txtCourseId;
    @FXML private DatePicker pickerDate;
    
    @FXML private TableView<Enrollment> tableEnrollments;
    @FXML private TableColumn<Enrollment, Integer> colStudentId;
    @FXML private TableColumn<Enrollment, Integer> colCourseId;
    @FXML private TableColumn<Enrollment, LocalDate> colDate;

    private EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    private ObservableList<Enrollment> enrollmentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
         colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("enrollmentDate"));

        loadTableData();

         tableEnrollments.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtStudentId.setText(String.valueOf(newSelection.getStudentId()));
                txtCourseId.setText(String.valueOf(newSelection.getCourseId()));
                pickerDate.setValue(newSelection.getEnrollmentDate());
            }
        });
    }

     private void loadTableData() {
    enrollmentList.clear();
    List<Enrollment> data = enrollmentDAO.getAllEnrollments();
    System.out.println("Data size from DB: " + data.size()); 
    enrollmentList.addAll(data);
    tableEnrollments.setItems(enrollmentList);
}

    @FXML
    private void handleAdd() {
        try {
            int sId = Integer.parseInt(txtStudentId.getText());
            int cId = Integer.parseInt(txtCourseId.getText());
            LocalDate date = pickerDate.getValue();

            if (date == null) { 
                showAlert("Error", "Please select a date."); 
                return; 
            }

            if (enrollmentDAO.addEnrollment(new Enrollment(sId, cId, date))) {
                loadTableData();
                clearFields();
                showAlert("Success", "Enrollment added successfully.");
            }
        } catch (NumberFormatException e) { 
            showAlert("Error", "Invalid input. Please enter numeric IDs."); 
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred.");
        }
    }

    @FXML
    private void handleDelete() {
        Enrollment selected = tableEnrollments.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (enrollmentDAO.deleteEnrollment(selected.getStudentId(), selected.getCourseId())) {
                showAlert("Success", "Enrollment deleted successfully.");
                loadTableData();
                clearFields();
            }
        } else {
            showAlert("Warning", "Please select a record from the table to delete.");
        }
    }

    @FXML
    private void handleUpdate() {
        Enrollment selected = tableEnrollments.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                int newSId = Integer.parseInt(txtStudentId.getText());
                int newCId = Integer.parseInt(txtCourseId.getText());
                LocalDate newDate = pickerDate.getValue();

                if (enrollmentDAO.updateEnrollment(selected.getStudentId(), selected.getCourseId(), newSId, newCId, newDate)) {
                    showAlert("Success", "Enrollment updated successfully.");
                    loadTableData();
                    clearFields();
                }
            } catch (NumberFormatException e) { 
                showAlert("Error", "Invalid input. Please check the ID fields."); 
            } catch (Exception e) {
                showAlert("Error", "Could not update the record.");
            }
        } else {
            showAlert("Warning", "Please select the record you wish to update from the table.");
        }
    }

    private void clearFields() {
        txtStudentId.clear();
        txtCourseId.clear();
        pickerDate.setValue(null);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null); 
        alert.setContentText(content);
        alert.showAndWait();
    }
 
}