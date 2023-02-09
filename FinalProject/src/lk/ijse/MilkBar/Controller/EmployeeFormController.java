package lk.ijse.MilkBar.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.MilkBar.bo.BOFactory;
import lk.ijse.MilkBar.bo.BOType;
import lk.ijse.MilkBar.bo.custom.CustomerBO;
import lk.ijse.MilkBar.bo.custom.EmployeeBO;
import lk.ijse.MilkBar.dto.CustomerDTO;
import lk.ijse.MilkBar.dto.EmployeeDTO;
import lk.ijse.MilkBar.util.Navigation;
import lk.ijse.MilkBar.util.Routes;
import lk.ijse.MilkBar.view.tdm.CustomerTM;
import lk.ijse.MilkBar.view.tdm.EmployeeTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeFormController {
    public AnchorPane pane;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtEmail;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public TableColumn colSalary;
    public TextField txtSalary;
    public TableView <EmployeeTM>tblEmployee;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public Button btnAddNewEmployee;
    EmployeeBO employeeBO  = (EmployeeBO) BOFactory.getBoFactory().getBO(BOType.EMPLOYEE);
    public void initialize () throws SQLException, ClassNotFoundException {
        loadAllEmployees();
        generateNewId();
        tblEmployee.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblEmployee.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblEmployee.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Address"));
        tblEmployee.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Email"));
        tblEmployee.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Salary"));
        initUI();
        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtId.setText(newValue.getId());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtEmail.setText(newValue.getEmail());
                txtSalary.setText(String.valueOf(newValue.getSalary()));

                txtId.setDisable(false);
                txtName.setDisable(false);
                txtAddress.setDisable(false);
                txtEmail.setDisable(false);
                txtSalary.setDisable(false);
            }
        });

        txtSalary.setOnAction(event -> btnSave.fire());
    }
    private String getLastEmployeeId() {
        List<EmployeeTM> tempEmployeeList = new ArrayList<>(tblEmployee.getItems());
        Collections.sort(tempEmployeeList);
        return tempEmployeeList.get(tempEmployeeList.size() - 1).getId();
    }

    private String generateNewId() {
        try {
            return employeeBO.generateEmployeeNewID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (tblEmployee.getItems().isEmpty()) {
            return "E00-001";
        } else {
            String id = getLastEmployeeId();
            int newCustomerId = Integer.parseInt(id.replace("E", "")) + 1;
            return String.format("E00-%03d", newCustomerId);
        }
    }

    private void initUI() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtSalary.clear();
        txtId.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtEmail.setDisable(true);
        txtSalary.setDisable(true);
        txtId.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = tblEmployee.getSelectionModel().getSelectedItem().getId();
        try {
            if (!existEmployee(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such Employee associated with the id " + id).show();
            }
            employeeBO.deleteEmployee(id);
            tblEmployee.getItems().remove(tblEmployee.getSelectionModel().getSelectedItem());
            tblEmployee.getSelectionModel().clearSelection();
            initUI();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the Employee " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        double salary = Double.parseDouble(txtSalary.getText());

        if (!name.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return;
        } else if (!address.matches(".{3,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtAddress.requestFocus();
            return;

        }

        if (btnSave.getText().equalsIgnoreCase("save")) {

            try {
                if (existEmployee(id)) {
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }

                employeeBO.saveEmployee(new EmployeeDTO(id,name,address,email,salary));

                tblEmployee.getItems().add(new EmployeeTM(id,name,address,email,salary));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the Employee " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {

            try {
                if (!existEmployee(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such Employee associated with the id " + id).show();
                }

                employeeBO.updateEmployee(new EmployeeDTO(id,name,address,email,salary));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the Employee " + id + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            EmployeeTM selectEmployee = tblEmployee.getSelectionModel().getSelectedItem();
            selectEmployee.setName(name);
            selectEmployee.setAddress(address);
            selectEmployee.setEmail(email);
            selectEmployee.setSalary(salary);
            tblEmployee.refresh();
        }
        btnAddNewEmployee.fire();
    }

    public void onClickBackButton(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.HOME, pane);
    }
    public void loadAllEmployees() {
        tblEmployee.getItems().clear();
        try {
            ArrayList<EmployeeDTO> allEmployees = employeeBO.getAllEmployee();

            for (EmployeeDTO e : allEmployees) {
                tblEmployee.getItems().add(new EmployeeTM(e.getId(), e.getName(), e.getAddress(),e.getEmail(),e.getSalary()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeBO.existEmployee(id);
    }

    public void btnAddNewOnAction(ActionEvent actionEvent) {
        txtId.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtEmail.setDisable(false);
        txtSalary.setDisable(false);
        txtId.clear();
        txtId.setText(generateNewId());
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtSalary.clear();
        txtName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblEmployee.getSelectionModel().clearSelection();
    }
}
