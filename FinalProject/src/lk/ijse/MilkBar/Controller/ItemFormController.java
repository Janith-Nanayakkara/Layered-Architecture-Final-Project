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
import lk.ijse.MilkBar.bo.custom.ItemBO;
import lk.ijse.MilkBar.dto.CustomerDTO;
import lk.ijse.MilkBar.dto.ItemDTO;

import lk.ijse.MilkBar.util.Navigation;
import lk.ijse.MilkBar.util.Routes;
import lk.ijse.MilkBar.view.tdm.CustomerTM;
import lk.ijse.MilkBar.view.tdm.ItemTM;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemFormController {
    public TableColumn colID;
    public TableColumn colName;
    public AnchorPane pane;
    public TextField txtItemCode;
    public TextField txtItemName;
    public TextField txtUnitPrice;
    public TableView <ItemTM>tblItem;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public TextField txtQtyOnHand;
    public JFXTextField txtSearch;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public Button newAddItem;
    ItemBO itemBO  = (ItemBO) BOFactory.getBoFactory().getBO(BOType.ITEM);
    public void initialize () throws SQLException, ClassNotFoundException {
        tblItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        getAllData();
        initUI();
        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtItemCode.setText(newValue.getCode());
                txtItemName.setText(newValue.getName());
                txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
                txtQtyOnHand.setText(String.valueOf(newValue.getQtyOnHand()));

                txtItemCode.setDisable(false);
                txtItemName.setDisable(false);
                txtUnitPrice.setDisable(false);
                txtQtyOnHand.setDisable(false);
            }
        });

        txtQtyOnHand.setOnAction(event -> btnSave.fire());
        generateNewId();
    }
    public void btnRemoveOnAction(ActionEvent actionEvent) {
        emptyTextField();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = tblItem.getSelectionModel().getSelectedItem().getCode();
        try {
            if (!existItem(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + id).show();
            }
            itemBO.deleteItem(id);
            tblItem.getItems().remove(tblItem.getSelectionModel().getSelectedItem());
            tblItem.getSelectionModel().clearSelection();
            initUI();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the item " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return itemBO.exist(id);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String code = txtItemCode.getText();
        String name = txtItemName.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

        if (!name.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtItemName.requestFocus();
            return;
        }

        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {
                if (existItem(code)) {
                    new Alert(Alert.AlertType.ERROR, code + " already exists").show();
                }

                itemBO.saveItem(new ItemDTO(code,name,qtyOnHand,unitPrice));

                tblItem.getItems().add(new ItemTM(code,name,qtyOnHand,unitPrice));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the item " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            try {
                if (!existItem(code)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + code).show();
                }

                itemBO.updateItem(new ItemDTO(code,name,qtyOnHand,unitPrice));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the item " + code + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ItemTM selectedItem = tblItem.getSelectionModel().getSelectedItem();
            selectedItem.setName(name);
            selectedItem.setQtyOnHand(qtyOnHand);
            selectedItem.setUnitPrice(unitPrice);
            tblItem.refresh();
        }
        newAddItem.fire();
    }

    private void initUI() {
        txtItemCode.clear();
        txtItemName.clear();
        txtQtyOnHand.clear();
        txtUnitPrice.clear();
        txtItemCode.setDisable(true);
        txtItemName.setDisable(true);
        txtQtyOnHand.setDisable(true);
        txtUnitPrice.setDisable(true);
        txtItemCode.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    private String generateNewId() {
        try {
            return itemBO.generateNewID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (tblItem.getItems().isEmpty()) {
            return "I00-001";
        } else {
            String id = getLastItemId();
            int newCustomerId = Integer.parseInt(id.replace("I", "")) + 1;
            return String.format("I00-%03d", newCustomerId);
        }
    }

    private String getLastItemId() {
        List<ItemTM> tempCustomersList = new ArrayList<>(tblItem.getItems());
        Collections.sort(tempCustomersList);
        return tempCustomersList.get(tempCustomersList.size() - 1).getCode();
    }


    public void btnBackOnClickAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.HOME,pane);
    }
    public void getAllData() throws SQLException, ClassNotFoundException {
        tblItem.getItems().clear();
        try {
            ArrayList<ItemDTO> allCustomers = itemBO.getAllItem();

            for (ItemDTO c : allCustomers) {
                tblItem.getItems().add(new ItemTM(c.getCode(), c.getName(), c.getQtyOnHand(),c.getUnitPrice()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void emptyTextField() {
        txtItemCode.clear();
        txtItemName.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();;
    }

    public void btnNewAddOnAction(ActionEvent actionEvent) {
        txtItemCode.setDisable(false);
        txtItemName.setDisable(false);
        txtQtyOnHand.setDisable(false);
        txtUnitPrice.setDisable(false);
        txtItemCode.clear();
        txtItemCode.setText(generateNewId());
        txtItemName.clear();
        txtQtyOnHand.clear();
        txtUnitPrice.clear();
        txtItemName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblItem.getSelectionModel().clearSelection();
    }
}
