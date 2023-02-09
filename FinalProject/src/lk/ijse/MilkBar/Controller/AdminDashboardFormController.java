package lk.ijse.MilkBar.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.MilkBar.util.Navigation;
import lk.ijse.MilkBar.util.Routes;

import java.io.IOException;

public class AdminDashboardFormController {

    public AnchorPane pane;
    public VBox vbox;
    public AnchorPane pane2;
    public Button customer;

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER,pane);
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE,pane);
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIER,pane);
    }

    public void btnItemOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ITEM,pane);
    }

    public void btnUserOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.USER,pane);
    }

    public void onClickLogout(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.LOGOUT,pane2);
    }

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER_ORDER_DETAILS,pane);
    }


    public void btnReportOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.REPORT,pane);
    }

    public void ClickedOnHomePage(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.HOME,pane);
    }
}
