package lk.ijse.MilkBar.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.MilkBar.bo.BOFactory;
import lk.ijse.MilkBar.bo.BOType;
import lk.ijse.MilkBar.bo.custom.EmployeeBO;
import lk.ijse.MilkBar.bo.custom.UserBO;
import lk.ijse.MilkBar.dto.EmployeeDTO;
import lk.ijse.MilkBar.dto.UserDTO;
import lk.ijse.MilkBar.util.Navigation;
import lk.ijse.MilkBar.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {
    public TextField txtId;
    public TextField txtName;
    public AnchorPane pane;
    public TextField txtPassword;
    public TextField txtFullName;
    public TextField txtRole;
    public JFXComboBox txtEmpID;
    public JFXTextField txtUserName;
    EmployeeBO employeeBO  = (EmployeeBO) BOFactory.getBoFactory().getBO(BOType.EMPLOYEE);
    UserBO userBO  = (UserBO) BOFactory.getBoFactory().getBO(BOType.USER);
    public void btnRemoveOnAction(ActionEvent actionEvent) {
        emptyTextField();
    }
    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String ID = txtId.getText();
        String Name = txtName.getText();
        String password = txtPassword.getText();
        String fullName = txtFullName.getText();

        boolean isUpdated = userBO.update(new UserDTO(ID,Name,password,fullName));
        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Update User").show();
            emptyTextField();
        }else {
            new Alert(Alert.AlertType.WARNING,"Something Happened").show();
            emptyTextField();
        }
    }
    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String ID= String.valueOf(txtEmpID.getValue());

        boolean isDeleted = userBO.delete(ID);
        if(isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "User Delete!").show();
            emptyTextField();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            emptyTextField();
        }
    }
    public void btnSaveOnAction(ActionEvent actionEvent) {
        String empId = String.valueOf(txtEmpID.getValue());
        String name = txtUserName.getText();
        String password = txtPassword.getText();
        String role = txtRole.getText();

        try {
            boolean isAdded = userBO.save(new UserDTO( empId,name, password,role));

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void emptyTextField() {
        txtUserName.clear();
        txtPassword.clear();
        txtRole.clear();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadEmployeeID();
    }

    public void onClickBackAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.HOME,pane);
    }
    private void loadEmployeeID() {
        ArrayList<String> ids = new ArrayList<>();
        try {
            ArrayList<EmployeeDTO> set = employeeBO.getAllIds();
            if(set.size() > 0) {
                for (EmployeeDTO e : set) {
                    ids.add(e.getId());
                }
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        txtEmpID.getItems().addAll(ids);
    }
}
