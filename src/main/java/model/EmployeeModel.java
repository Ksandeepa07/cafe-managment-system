package model;

import db.DBConnection;
import dto.Employee;
import dto.tm.EmployeeTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    public static boolean save(Employee employee) throws SQLException {
        String sql="INSERT INTO Employee (EmpId,Name,Address,NIC,DOB,JobTitle,PhoneNumber,Email) VALUE (?,?,?,?,?,?,?,?)";
        PreparedStatement pstm= DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1,employee.getId());
        pstm.setString(2,employee.getName());
        pstm.setString(3,employee.getAddress());
        pstm.setString(4,employee.getNic());
        pstm.setString(5,employee.getDob());
        pstm.setString(6,employee.getJobTitle());
        pstm.setString(7,employee.getContact());
        pstm.setString(8,employee.getEmail());

        int rows=pstm.executeUpdate();
        if(rows>0){
            return true;
        }
        return false;
    }

    public static ObservableList<EmployeeTM> getAll() throws SQLException {
        String sql="SELECT * FROM Employee";
        PreparedStatement pstm=DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet=pstm.executeQuery();
//        List<Employee>data =new ArrayList<>();
        ObservableList<EmployeeTM> data=FXCollections.observableArrayList();

        while(resultSet.next()){
            data.add(new EmployeeTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(5),
            resultSet.getString(4),
            resultSet.getString(6),
            resultSet.getString(7),
            resultSet.getString(8)
            ));
        }
        return data;

    }

    public static Employee searchById(String text) throws SQLException {
        String sql="SELECT * FROM employee WHERE EmpId=?";
        PreparedStatement pstm=DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1,text);
        ResultSet resultSet=pstm.executeQuery();

        if(resultSet.next()){
            return new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(5),
                    resultSet.getString(4),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            );
        }
        return null;
    }

    public static boolean update(Employee employee) throws SQLException {
        String sql="UPDATE employee SET Name=?,Address=?,NIC=?,DOB=?,JobTitle=?,PhoneNumber=?,Email=? " +
                "WHERE EmpId=? ";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1,employee.getName());
            pstm.setString(2,employee.getAddress());
            pstm.setString(3,employee.getNic());
            pstm.setString(4,employee.getDob());
            pstm.setString(5,employee.getJobTitle());
            pstm.setString(6,employee.getContact());
            pstm.setString(7,employee.getEmail());
            pstm.setString(8,employee.getId());

            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;

    }

    public static boolean delete(String text) throws SQLException {
        String sql="DELETE FROM employee WHERE EmpId=?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1,text);
            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;
    }
}
