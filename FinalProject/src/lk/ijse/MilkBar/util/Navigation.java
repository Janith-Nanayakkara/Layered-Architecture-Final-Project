package lk.ijse.MilkBar.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane anchorPane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.anchorPane = pane;
        Navigation.anchorPane.getChildren().clear();
        Stage window = (Stage) Navigation.anchorPane.getScene().getWindow();

        switch (route) {
            case CUSTOMER:
                window.setTitle("Customer Form");
                initUI("CustomerForm.fxml");
                break;
            case EMPLOYEE:
                window.setTitle("Employee Form");
                initUI("EmployeeForm.fxml");
                break;
            case SUPPLIER:
                window.setTitle("Supplier Form");
                initUI("SupplierForm.fxml");
                break;
            case CUSTOMER_ORDER_DETAILS:
                window.setTitle("Customer Order Details Form");
                initUI("place-order-form.fxml");
                break;
            case ITEM:
                window.setTitle("Item Form");
                initUI("ItemForm.fxml");
                break;
            case USER:
                window.setTitle("User Form");
                initUI("UserForm.fxml");
                break;
            case ADMIN_DASHBOARD:
                window.setTitle("Admin Dashboard Form");
                initUI("AdminDashboardForm.fxml");
                break;
            case REPORT:
                window.setTitle("Report Form");
                initUI("ViewReportForm.fxml");
                break;

            case LOGOUT:
                window.setTitle("Login  Form");
                initUI("LoginForm.fxml");
                break;
            case HOME:
                initUI("DashboardHomeForm.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
        }
    }
    private static void initUI(String location) throws IOException {
        Navigation.anchorPane.getChildren().add(FXMLLoader.load(Navigation.class
                .getResource("/lk/ijse/MilkBar/view/" + location)));
    }
}


