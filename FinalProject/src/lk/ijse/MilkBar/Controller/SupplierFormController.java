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
import lk.ijse.MilkBar.bo.custom.SupplierBO;
import lk.ijse.MilkBar.dto.CustomerDTO;
import lk.ijse.MilkBar.dto.SupplierDTO;
import lk.ijse.MilkBar.view.tdm.CustomerTM;
import lk.ijse.MilkBar.view.tdm.SupplierTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SupplierFormController {
    public TableColumn colID;
    public TableColumn colName;
    public TableView <SupplierTM>tblSupplier;
    public TextField txtId;
    public TextField txtName;
    public AnchorPane pane;
    public TextField txtContact;
    public TextField txtEmail;
    public TableColumn colContact;
    public TableColumn colEmail;
    public TableColumn colCompany;
    public TextField txtCompany;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public Button newAddSupplier;
    SupplierBO supplierBO  = (SupplierBO) BOFactory.getBoFactory().getBO(BOType.SUPPLIER);
    public void initialize () throws SQLException, ClassNotFoundException {

        tblSupplier.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblSupplier.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblSupplier.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblSupplier.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblSupplier.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("company"));
        getAllData();
        initUI();
        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtId.setText(newValue.getId());
                txtName.setText(newValue.getName());
                txtContact.setText(newValue.getContact());
                txtEmail.setText(newValue.getEmail());
                txtCompany.setText(newValue.getCompany());

                txtId.setDisable(false);
                txtName.setDisable(false);
                txtContact.setDisable(false);
                txtEmail.setDisable(false);
                txtCompany.setDisable(false);
            }
        });

        txtCompany.setOnAction(event -> btnSave.fire());

        generateNewId();
    }

    boolean existSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierBO.existSupplier(id);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();
        String company = txtCompany.getText();

        if (!name.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return;
        }

        if (btnSave.getText().equalsIgnoreCase("save")) {

            try {
                if (existSupplier(id)) {
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }

                supplierBO.save(new SupplierDTO(id, name, contact,email,company));

                tblSupplier.getItems().add(new SupplierTM(id, name, contact,email,company));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the Supplier " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {

            try {
                if (!existSupplier(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such Supplier associated with the id " + id).show();
                }

                supplierBO.update(new SupplierDTO(id, name, contact,email,company));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the Supplier " + id + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            SupplierTM selectedCustomer = tblSupplier.getSelectionModel().getSelectedItem();
            selectedCustomer.setName(name);
            selectedCustomer.setContact(contact);
            selectedCustomer.setEmail(email);
            selectedCustomer.setCompany(company);
            tblSupplier.refresh();
        }
        newAddSupplier.fire();
    }

    private void initUI() {
        txtId.clear();
        txtName.clear();
        txtContact.clear();
        txtEmail.clear();
        txtCompany.clear();
        txtId.setDisable(true);
        txtName.setDisable(true);
        txtContact.setDisable(true);
        txtEmail.setDisable(true);
        txtCompany.setDisable(true);
        txtId.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = tblSupplier.getSelectionModel().getSelectedItem().getId();
        try {
            if (!existSupplier(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such Supplier associated with the id " + id).show();
            }

            supplierBO.delete(id);

            tblSupplier.getItems().remove(tblSupplier.getSelectionModel().getSelectedItem());
            tblSupplier.getSelectionModel().clearSelection();
            initUI();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the Supplier " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void getAllData() throws SQLException, ClassNotFoundException {
        tblSupplier.getItems().clear();
        try {
            ArrayList<SupplierDTO> allCustomers = supplierBO.getAllSupplier();

            for (SupplierDTO c : allCustomers) {
                tblSupplier.getItems().add(new SupplierTM(c.getId(), c.getName(),c.getContact(),c.getEmail(),c.getCompany()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void onClickBackButton(MouseEvent mouseEvent) { }

    public void btnNewAddOnAction(ActionEvent actionEvent) {
        txtId.setDisable(false);
        txtName.setDisable(false);
        txtContact.setDisable(false);
        txtEmail.setDisable(false);
        txtCompany.setDisable(false);
        txtId.clear();
        txtId.setText(generateNewId());
        txtName.clear();
        txtContact.clear();
        txtEmail.clear();
        txtCompany.clear();
        txtName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblSupplier.getSelectionModel().clearSelection();
    }
    private String generateNewId() {
        try {
            return supplierBO.generateNewIDSupplier();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (tblSupplier.getItems().isEmpty()) {
            return "S00-001";
        } else {
            String id = getLastCustomerId();
            int newCustomerId = Integer.parseInt(id.replace("S", "")) + 1;
            return String.format("S00-%03d", newCustomerId);
        }
    }

    private String getLastCustomerId() {
        List<SupplierTM> tempCustomersList = new ArrayList<>(tblSupplier.getItems());
        Collections.sort(tempCustomersList);
        return tempCustomersList.get(tempCustomersList.size() - 1).getId();
    }
}
