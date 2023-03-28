package lk.ijse.cafe_au_lait.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cafe_au_lait.db.DBConnection;
import lk.ijse.cafe_au_lait.dto.Salary;
import lk.ijse.cafe_au_lait.dto.tm.SalaryTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryModel {
    public static ObservableList<String> loadEmpIds() throws SQLException {
        String sql="SELECT * FROM employee";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            ObservableList<String> employeeData= FXCollections.observableArrayList();
            ResultSet resultSet=pstm.executeQuery();

            while (resultSet.next()){
                employeeData.add(resultSet.getString(1));
            }
            return employeeData;
        }
    }

    public static boolean save(Salary salary) throws SQLException {
        String sql="INSERT INTO salary(salaryId,EmpId,salaryPaymentMethod,salaryPayment,salaryOt)" +
                "VALUES(?,?,?,?,?)";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1,salary.getSalaryId());
            pstm.setString(2, salary.getEmpId());
            pstm.setString(3,salary.getPaymentMethod());
            pstm.setDouble(4,salary.getPayment());
            pstm.setDouble(5,salary.getOverTime());
            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }

        }
        return false;
    }

    public static ObservableList<SalaryTM> getAll() throws SQLException {
        String sql="SELECT * FROM salary";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            ObservableList<SalaryTM> salaryData=FXCollections.observableArrayList();
            ResultSet resultSet=pstm.executeQuery();

            while (resultSet.next()){
                salaryData.add(new SalaryTM(
                        resultSet.getString(2),
                        resultSet.getString(1),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getDouble(5)

                ));

            }
            return salaryData;
        }

    }

    public static boolean update(Salary salary) throws SQLException {
        String sql="UPDATE salary SET empId=?,salaryPaymentMethod=?,salaryPayment=?,salaryOt=? WHERE" +
                " salaryId=?";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1,salary.getEmpId());
            pstm.setString(2,salary.getPaymentMethod());
            pstm.setDouble(3,salary.getPayment());
            pstm.setDouble(4,salary.getOverTime());
            pstm.setString(5,salary.getSalaryId());
            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;

    }
}
