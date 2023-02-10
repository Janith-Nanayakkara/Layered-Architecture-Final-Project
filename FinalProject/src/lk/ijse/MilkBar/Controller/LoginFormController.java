package lk.ijse.MilkBar.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import lk.ijse.MilkBar.util.CrudUtil;
import lk.ijse.MilkBar.util.Navigation;
import lk.ijse.MilkBar.util.Routes;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginFormController {
    public AnchorPane pane;

    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public Label lblUserName;
    public Label lblPassword;


    public void btnUserLoginOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        String userName = txtUsername.getText();
        String password = txtPassword.getText();
/*
        String sql = "SELECT * FROM users WHERE username = ? and user_password = ? ";
        ResultSet result = CrudUtil.execute(sql, userName, password);

        if (!result.next()) {

            if (userName != sql) {
                lblUserName.setText("Invalid UserName");
                if (password != sql) {
                    lblPassword.setText("Invalid Password");
                }
            }
        } else {
            Navigation.navigate(Routes.ADMIN_DASHBOARD, pane);

        }*/
        Navigation.navigate(Routes.ADMIN_DASHBOARD, pane);
    }
}
