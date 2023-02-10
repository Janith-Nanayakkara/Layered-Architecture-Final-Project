package lk.ijse.MilkBar.Controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.MilkBar.db.DBConnection;
import lk.ijse.MilkBar.util.Navigation;
import lk.ijse.MilkBar.util.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class ViewReportFormController {

    public AnchorPane pane;
    public void ClickOnBackButton(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.HOME,pane);
    }

    public void CustomerOnClicked(MouseEvent mouseEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/MilkBar/report/Customer.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getDbConnection().getConnection());


            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ClickOnActionEmployee(MouseEvent mouseEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/MilkBar/report/Employee.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getDbConnection().getConnection());


            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
