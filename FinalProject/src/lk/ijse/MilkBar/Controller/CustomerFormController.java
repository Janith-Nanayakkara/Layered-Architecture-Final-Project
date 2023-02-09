package lk.ijse.MilkBar.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import lk.ijse.MilkBar.bo.BOFactory;
import lk.ijse.MilkBar.bo.BOType;
import lk.ijse.MilkBar.bo.custom.CustomerBO;
import lk.ijse.MilkBar.dto.CustomerDTO;
import lk.ijse.MilkBar.util.Navigation;
import lk.ijse.MilkBar.util.Routes;
import lk.ijse.MilkBar.view.tdm.CustomerTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerFormController {
    public TableColumn colTelNumber;
    public TableView <CustomerTM>tblCustomer;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TextField txtTelNumber;

    public JFXButton btnDelete;
    public JFXButton btnSave;
    public Button btnAddNewCustomer;
    public JFXTextField txtCusID;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    CustomerBO customerBO  = (CustomerBO) BOFactory.getBoFactory().getBO(BOType.CUSTOMER);

    public void initialize () {
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Address"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
        initUI();
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtCusID.setText(newValue.getId());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtTelNumber.setText(newValue.getContact());

                txtCusID.setDisable(false);
                txtName.setDisable(false);
                txtAddress.setDisable(false);
                txtTelNumber.setDisable(false);
            }
        });

        txtTelNumber.setOnAction(event -> btnSave.fire());
        loadAllCustomers();
        generateNewId();

    }
    private void loadAllCustomers() {
        tblCustomer.getItems().clear();
        try {
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomer();

            for (CustomerDTO c : allCustomers) {
                tblCustomer.getItems().add(new CustomerTM(c.getId(), c.getName(), c.getAddress(),c.getContact()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = tblCustomer.getSelectionModel().getSelectedItem().getId();
        try {
            if (!existCustomer(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
            }

            customerBO.deleteCustomer(id);

            tblCustomer.getItems().remove(tblCustomer.getSelectionModel().getSelectedItem());
            tblCustomer.getSelectionModel().clearSelection();
            initUI();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the customer " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerBO.existCustomer(id);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtCusID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtTelNumber.getText();

        if (!name.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return;
        } else if (!address.matches(".{3,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtAddress.requestFocus();
            return;
        }else if (!contact.matches("0((11)|(7(7|0|8|4|9|1|[3-7]))|(3[1-8])|(4(1|5|7))|(5(1|2|4|5|7))|(6(3|[5-7]))|([8-9]1))[0-9]{7}")){
            new Alert(Alert.AlertType.ERROR, "Contact should be at least 10 Digit long").show();
            txtTelNumber.requestFocus();
            return;
        }

        if (btnSave.getText().equalsIgnoreCase("save")) {

            try {
                if (existCustomer(id)) {
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }

                customerBO.saveCustomer(new CustomerDTO(id,name,address,contact));

                tblCustomer.getItems().add(new CustomerTM(id, name, address,contact));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {

            try {
                if (!existCustomer(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
                }

                customerBO.updateCustomer(new CustomerDTO(id,name,address,contact));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + id + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            CustomerTM selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
            selectedCustomer.setName(name);
            selectedCustomer.setAddress(address);
            selectedCustomer.setContact(contact);
            tblCustomer.refresh();
        }
        btnAddNewCustomer.fire();
    }

    private void initUI() {
        txtCusID.clear();
        txtName.clear();
        txtAddress.clear();
        txtTelNumber.clear();
        txtCusID.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtTelNumber.setDisable(true);
        txtCusID.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void onClickBack(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.HOME, pane);
    }

    private String generateNewId() {
    try {
        return customerBO.generateNewCustomerID();
    } catch (SQLException e) {
        new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    if (tblCustomer.getItems().isEmpty()) {
        return "C00-001";
    } else {
        String id = getLastCustomerId();
        int newCustomerId = Integer.parseInt(id.replace("C", "")) + 1;
        return String.format("C00-%03d", newCustomerId);
        }
    }

    private String getLastCustomerId() {
        List<CustomerTM> tempCustomersList = new ArrayList<>(tblCustomer.getItems());
        Collections.sort(tempCustomersList);
        return tempCustomersList.get(tempCustomersList.size() - 1).getId();
    }

    public void btnAddNewOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        txtCusID.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtTelNumber.setDisable(false);
        txtCusID.clear();
        txtCusID.setText(generateNewId());
        txtName.clear();
        txtAddress.clear();
        txtTelNumber.clear();
        txtName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblCustomer.getSelectionModel().clearSelection();
    }
    public void name(KeyEvent keyEvent) {
        Pattern pattern=Pattern.compile("\\b([a-z]|[A-Z])+");
        if (!pattern.matcher(txtName.getText()).matches()){
            txtName.setStyle("-fx-text-fill: red");
        }else {
            txtName.setStyle("-fx-text-fill: black");

        }
    }

    public void address(KeyEvent keyEvent) {
        Pattern pattern=Pattern.compile("\\b([a-z]|[A-Z])+");
        if (!pattern.matcher(txtAddress.getText()).matches()){
            txtAddress.setStyle("-fx-text-fill: red");
        }else {
            txtAddress.setStyle("-fx-text-fill: black");

        }
    }

    public void contact(KeyEvent keyEvent) {
        Pattern pattern=Pattern.compile("0((11)|(7(7|0|8|4|9|1|[3-7]))|(3[1-8])|(4(1|5|7))|(5(1|2|4|5|7))|(6(3|[5-7]))|([8-9]1))[0-9]{7}");
        if (!pattern.matcher(txtTelNumber.getText()).matches()){
            txtTelNumber.setStyle("-fx-text-fill: red");
        }else {
            txtTelNumber.setStyle("-fx-text-fill: black");

        }
    }

}



