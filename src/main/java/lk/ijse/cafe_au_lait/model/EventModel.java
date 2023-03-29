package lk.ijse.cafe_au_lait.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cafe_au_lait.db.DBConnection;
import lk.ijse.cafe_au_lait.dto.Event;
import lk.ijse.cafe_au_lait.dto.tm.EmployeeTM;
import lk.ijse.cafe_au_lait.dto.tm.EventTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class EventModel {
    public static boolean save(Event event1) throws SQLException {
        String sql="INSERT INTO event (eventId,empId,eventName,eventType,eventDate,eventTime)" +
                "VALUES(?,?,?,?,?,?)";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1,event1.getEventId());
            pstm.setString(2,event1.getEmpId());
            pstm.setString(3,event1.getEventName());
            pstm.setString(4,event1.getEventType());
            pstm.setString(5,event1.getEventDate());
            pstm.setString(6,event1.getEventTime());

            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;
    }

    public static Event searchById(String text) throws SQLException {
        String sql="SELECT * FROM event WHERE eventId=?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1,text);
            ResultSet resultSet=pstm.executeQuery();

            if(resultSet.next()){
                return new Event(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)

                );
            }
        }
        return null;
    }

    public static ObservableList<EventTM> getAll() throws SQLException {
        String sql="SELECT * FROM event";
        PreparedStatement pstm=DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet=pstm.executeQuery();
        ObservableList<EventTM> eventData= FXCollections.observableArrayList();

        while(resultSet.next()){
            eventData.add(new EventTM(
                    resultSet.getString(2),
                    resultSet.getString(1),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)

            ));
        }
        return eventData;
    }

    public static boolean update(Event event1) throws SQLException {
        String sql="UPDATE event SET empId=?,eventName=?,eventType=?,eventDate=?,eventTime=?" +
                "WHERE eventId=? ";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1,event1.getEmpId());
            pstm.setString(2,event1.getEventName());
            pstm.setString(3,event1.getEventType());
            pstm.setString(4,event1.getEventDate());
            pstm.setString(5,event1.getEventTime());
            pstm.setString(6,event1.getEventId());

            int rows=pstm.executeUpdate();
            if(rows>0){
                return true;
            }
        }
        return false;

    }

    public static boolean delete(String text) throws SQLException {
        String sql="DELETE FROM event WHERE eventId=?";
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


