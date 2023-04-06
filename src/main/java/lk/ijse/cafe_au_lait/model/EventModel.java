package lk.ijse.cafe_au_lait.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cafe_au_lait.dto.Event;
import lk.ijse.cafe_au_lait.dto.tm.EventTM;
import lk.ijse.cafe_au_lait.util.CrudUtil;
import lk.ijse.cafe_au_lait.util.NotificationController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class EventModel {
    private static String[] data;
    private static final int currentIndex = 0;

    public static boolean save(Event event1) {
        String sql = "INSERT INTO event (eventId,empId,eventName,eventType,eventDate,eventTime)" +
                "VALUES(?,?,?,?,?,?)";

        try {
            return CrudUtil.execute(sql,
                    event1.getEventId(),
                    event1.getEmpId(),
                    event1.getEventName(),
                    event1.getEventType(),
                    event1.getEventDate(),
                    event1.getEventTime());
        } catch (SQLIntegrityConstraintViolationException throwables) {
            NotificationController.ErrorMasseage("This Supplier Id is Already Exsits");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static Event searchById(String text) {
        String sql = "SELECT * FROM event WHERE eventId=?";
        ResultSet resultSet = null;
        try {
            resultSet = CrudUtil.execute(sql, text);
            if (resultSet.next()) {
                return new Event(
                        resultSet.getString(2),
                        resultSet.getString(1),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)

                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;

    }


    public static ObservableList<EventTM> getAll() {
        String sql = "SELECT * FROM event";

        ObservableList<EventTM> eventData = FXCollections.observableArrayList();
        ResultSet resultSet = null;
        try {
            resultSet = CrudUtil.execute(sql);
            while (resultSet.next()) {
                eventData.add(new EventTM(
                        resultSet.getString(2),
                        resultSet.getString(1),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)

                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return eventData;
    }

    public static boolean update(Event event1) {
        String sql = "UPDATE event SET empId=?,eventName=?,eventType=?,eventDate=?,eventTime=?" +
                "WHERE eventId=? ";
        try {
            return CrudUtil.execute(sql,

                    event1.getEmpId(),
                    event1.getEventName(),
                    event1.getEventType(),
                    event1.getEventDate(),
                    event1.getEventTime(),
                    event1.getEventId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }

    public static boolean delete(String text) {
        String sql = "DELETE FROM event WHERE eventId=?";
        try {
            return CrudUtil.execute(sql, text);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static List<String> eventData() throws SQLException {
        String sql = "SELECT  eventName FROM event";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<String> data = new ArrayList<>();
        while (resultSet.next()) {
            data.add(
                    resultSet.getString(1)
            );
        }


        return data;
    }

}


